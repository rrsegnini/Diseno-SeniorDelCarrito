package cr.ac.tec.ec.seniordelcarrito.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int idProduct;
    private String name;
    private int price;
    private String imageURL;
    private ProductType type;
    private int quantity;

    public Product(int idProduct, String name, int price, String imageURL, ProductType type, int quantity) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
        this.type = type;
        this.quantity = quantity;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
