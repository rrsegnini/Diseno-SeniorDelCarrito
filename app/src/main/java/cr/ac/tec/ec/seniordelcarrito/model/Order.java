package cr.ac.tec.ec.seniordelcarrito.model;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class Order {
    //private int id;
    private List<OrderLine> orderLines = new ArrayList<>();
    private boolean delivery;
    private String address;

    public Order(boolean delivery, String address) {
        this.delivery = delivery;
        this.address = address;
    }

    public void addItem(OrderLine item){
        orderLines.add(item);
    }

    @Override
    public String toString() {
        String itemsString = "";
        Product p;
        int qty = 0;
        int productTotal = 0;
        for (OrderLine o:orderLines
                ) {
            p = o.getItem();
            qty = o.getQuantity();

            productTotal += p.getPrice()*qty;

            itemsString += p.getName() + "(" + String.valueOf(qty) + ") " + String.valueOf(p.getPrice()*qty) + "\n";
        }

        return itemsString;
    }

    public ArrayList<String> toStringArrayList() {
        ArrayList<String> itemsList = new ArrayList<>();
        Product p;
        int qty = 0;
        int productTotal = 0;
        for (OrderLine o:orderLines
                ) {
            p = o.getItem();
            qty = o.getQuantity();

            productTotal += p.getPrice()*qty;

            itemsList.add(p.getName() + "          " + String.valueOf(qty)
                    + "          " + String.valueOf(p.getPrice()*qty));
        }

        return itemsList;
    }


}
