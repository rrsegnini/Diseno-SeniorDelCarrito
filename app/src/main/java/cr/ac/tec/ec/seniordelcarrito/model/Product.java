package cr.ac.tec.ec.seniordelcarrito.model;

public class Product {
    private String name;
    private int price;
    private String imageURL;
    private ProductType type;
    private int quantity;

    public Product(String name, int price, String imageURL, ProductType type) {
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
        this.type = type;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}
