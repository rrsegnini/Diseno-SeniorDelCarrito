package cr.ac.tec.ec.seniordelcarrito.model;

import java.util.List;

public class CarritoBuilder implements Builder {
    @Override
    public Carrito buildOrder(Object items) {
        /*//Carrito carrito = new Carrito();
        for (List<Integer> list:items
                ) {
            int productCode = list.get(0);
            int productQty = list.get(1);
            Product product = Inventory.getProduct(productCode);
            if (product!=null){
                Carrito.add(new CarritoItem(product, productQty));
            }
        }*/
        return null;
    }

    public void buildOrder(Product p, int qty) {
        Carrito.add(new CarritoItem(p, qty));
    }
}
