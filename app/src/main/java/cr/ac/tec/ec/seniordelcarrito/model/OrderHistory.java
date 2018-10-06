package cr.ac.tec.ec.seniordelcarrito.model;

import java.util.Date;

public class OrderHistory {
    private User user;
    private Order order;
    private Date date;

    public OrderHistory(User user, Order order) {
        this.user = user;
        this.order = order;
        this.date = new Date();
    }


}
