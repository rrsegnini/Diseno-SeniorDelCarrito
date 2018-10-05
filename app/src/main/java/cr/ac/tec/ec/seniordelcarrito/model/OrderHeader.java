package cr.ac.tec.ec.seniordelcarrito.model;

public class OrderHeader {
    private String name;
    private String phone;
    private String location;

    public OrderHeader() {
        this.name = "Senior del Carrito";
        this.phone = "Tel. 86582179";
        this.phone = "Frente al edificio B1";
    }

    public OrderHeader clone() {
        return new OrderHeader();
    }
}
