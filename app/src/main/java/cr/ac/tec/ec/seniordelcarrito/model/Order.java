package cr.ac.tec.ec.seniordelcarrito.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static final List<OrderLine> orderLines = new ArrayList<>();

    public static void addItem(OrderLine item){
        orderLines.add(item);
    }
}
