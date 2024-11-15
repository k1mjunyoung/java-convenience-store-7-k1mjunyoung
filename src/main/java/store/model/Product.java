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

    public Product(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
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

    public String toMessage() {
        if (promotion.equals("null")) {
            return "- " + name + " " + String.format("%,d", price) + "원 " + quantity + "개 ";
        }

        return "- " + name + " " + String.format("%,d", price) + "원 " + quantity + "개 " + promotion;
    }
}
