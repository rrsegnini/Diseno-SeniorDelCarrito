package cr.ac.tec.ec.seniordelcarrito.model;

public class CarritoItem {
    private Product product;
    private int quantity;

    public CarritoItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        Product p = this.getProduct();
        int qty = this.getQuantity();

        return (p.getName() + "          " + String.valueOf(qty)
                    + "          " + String.valueOf(p.getPrice()*qty));

    }
}
