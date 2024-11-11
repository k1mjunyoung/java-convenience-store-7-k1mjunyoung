package store.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import store.model.Product;
import store.model.Products;

public class ProductReader {
    private static final String PRODUCTS_PATH = "src/main/resources/products.md";

    public static Products roadStock() {
        Products stock = new Products();

        try {
            BufferedReader br = new BufferedReader(new FileReader(PRODUCTS_PATH));
            br.readLine();

            while (true) {
                String line = br.readLine();

                if (line == null) {
                    break;
                }

                Product product = convertToProduct(line);
                stock.addProduct(product);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stock;
    }

    private static Product convertToProduct(String line) {
        List<String> splitedLine = Arrays.stream(line.split(",")).toList();

        //name,price,quantity,promotion
        String name = splitedLine.get(0);
        Integer price = Integer.valueOf(splitedLine.get(1));
        Integer quantity = Integer.valueOf(splitedLine.get(2));
        String promotion = splitedLine.get(3);

        Product product = new Product(name, price, quantity, promotion);

        return product;
    }
}
