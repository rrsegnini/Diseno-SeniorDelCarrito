package cr.ac.tec.ec.seniordelcarrito.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    static List<Product> inventario = new ArrayList<>();

    public static void addItem(Product new_item){
        inventario.add(new_item);
    }
    public static List<Product> getInventario(){
        return inventario;
    }


}
