package cr.ac.tec.ec.seniordelcarrito.model;

public class OrderLine {
    Product item;
    int quantity;

    public OrderLine(){}

    public OrderLine(Product item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
