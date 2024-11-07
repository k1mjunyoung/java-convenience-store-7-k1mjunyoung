package store.model;

public class Product {
    //name,price,quantity,promotion
    private String name;
    private Integer price;
    private Integer quantity;
    private String promotion;

    public Product(String name, Integer price, Integer quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getPromotion() {
        return promotion;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", promotion='" + promotion + '\'' +
                "}\n";
    }
}
