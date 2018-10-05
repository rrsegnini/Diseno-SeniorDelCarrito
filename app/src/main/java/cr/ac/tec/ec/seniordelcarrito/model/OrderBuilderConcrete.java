package cr.ac.tec.ec.seniordelcarrito.model;

import java.util.List;

public class OrderBuilderConcrete implements OrderBuilder {
    private static final OrderBuilderConcrete ourInstance = new OrderBuilderConcrete();

    public static OrderBuilderConcrete getInstance() {
        return ourInstance;
    }

    private OrderBuilderConcrete() {
    }

    public Order buildOrder(List<List<Integer>> items){
        for (List<Integer> i:items
             ) {
            System.out.println(i.get(0));
            System.out.println(i.get(1));

        }
        return null;
    }
}
