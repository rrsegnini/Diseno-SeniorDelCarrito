package cr.ac.tec.ec.seniordelcarrito.model;

import java.util.List;

public class OrderBuilder implements Builder {
    private boolean delivery;
    private String address;

    @Override
    public Order buildOrder(Object items) {
        List<CarritoItem> itemsCarrito = (List<CarritoItem>)items;
        Order order = new Order(delivery, address);

        for (CarritoItem c:itemsCarrito
                ) {
            OrderLine line = new OrderLine();
            line.setItem(c.getProduct());
            line.setQuantity(c.getQuantity());
            order.addItem(line);
        }

        OrderHistory.addOrder(order);
        return null;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
