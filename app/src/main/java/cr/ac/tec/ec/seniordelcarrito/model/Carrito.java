package cr.ac.tec.ec.seniordelcarrito.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private static List<CarritoItem> items = new ArrayList<>();

    public static void add(CarritoItem newItem){
        items.add(newItem);
    }

    public static List<CarritoItem> getAddedItems(){
        return items;
    }
    public static int gettNumberOfItems(){
        return items.size();
    }
    public static void emptyCarrito(){
        items = new ArrayList<>();
    }
    public static void deleteCarritoItem(CarritoItem c){
        items.remove(c);
    }
}
