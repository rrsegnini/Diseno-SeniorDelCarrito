package cr.ac.tec.ec.seniordelcarrito.model;

import java.util.List;

public interface OrderBuilder {
    public Order buildOrder(List<List<Integer>> items);
}
