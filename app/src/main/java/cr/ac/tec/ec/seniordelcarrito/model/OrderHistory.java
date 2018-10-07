package cr.ac.tec.ec.seniordelcarrito.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderHistory {
    private User user;
    private Order order;
    private Date date;
    private static List<Order> orderHistory = new ArrayList<>();

    public OrderHistory(User user, Order order) {
        this.user = user;
        this.order = order;
        this.date = new Date();
    }

    public static void addOrder(Order o){
        orderHistory.add(o);
    }

    public static List<Order> getOrders(){
        return orderHistory;
    }

    public static ArrayList<String> toStringArrayList() {
        ArrayList<String> itemsList = new ArrayList<>();
        Product p;
        int qty = 0;
        int productTotal = 0;
        for (Order o:orderHistory
                ) {


            itemsList.add("\n" + o.toString());
        }

        return itemsList;
    }


}
