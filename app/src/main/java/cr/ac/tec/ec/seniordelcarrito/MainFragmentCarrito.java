package cr.ac.tec.ec.seniordelcarrito;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cr.ac.tec.ec.seniordelcarrito.model.Carrito;
import cr.ac.tec.ec.seniordelcarrito.model.CarritoItem;
import cr.ac.tec.ec.seniordelcarrito.model.OrderBuilder;
import cr.ac.tec.ec.seniordelcarrito.model.Product;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragmentCarrito.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragmentCarrito#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragmentCarrito extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragmentCarrito() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragmentCarrito.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragmentCarrito newInstance(String param1, String param2) {
        MainFragmentCarrito fragment = new MainFragmentCarrito();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    private void setDeliverySwitch(){
        Switch delivery = getView().findViewById(R.id.carrito_swtDelivery);
        final TextInputLayout address = getView().findViewById(R.id.carrito_txlAddress);
        delivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    address.setFocusable(true);
                    address.setEnabled(true);
                    address.setHint("Specify address, office or classroom");

                } else {
                    address.setEnabled(false);
                    address.setFocusable(false);
                    address.setHint("I'll pick it up myself");
                }
            }
        });

    }

    private void disableAddressInput(){
        final TextInputLayout address = getView().findViewById(R.id.carrito_txlAddress);
        address.setHint("I'll pick it up myself");
        address.setFocusable(false);
        address.setEnabled(false);
        //address.setInputType(InputType.TYPE_NULL);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_fragment_carrito, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        disableAddressInput();
        setDeliverySwitch();
        updateButtons();
        int productTotal = populateCarritoList();
        setButtonListeners(productTotal);
        setTotalLabel(productTotal);
    }

    /*Setting TOTAL: label*/
    private void setTotalLabel(int productTotal){

        TextView TotalTxt = getView().findViewById(R.id.carrito_txtTotal);
        TotalTxt.setText("Total: " + productTotal);
    }

    private void updateTotalLabel(int total, int productToSubtract){

        TextView TotalTxt = getView().findViewById(R.id.carrito_txtTotal);
        total = total - productToSubtract;
        TotalTxt.setText("Total: " + total);
    }

    /*Disables and enables buttons, depending on the amount of items the carrito has*/
    private void updateButtons(){
        /*Setting the 'Place order' button*/
        final Button placeOrder = getView().findViewById(R.id.carrito_btnPlaceOrder);
        final Button removeAll = getView().findViewById(R.id.carrito_btnEmptyCarrito);
        if (Carrito.gettNumberOfItems() <= 0){
            placeOrder.setBackgroundColor(Color.GRAY);
            placeOrder.setEnabled(false);
            removeAll.setBackgroundColor(Color.GRAY);
            removeAll.setEnabled(false);
        }else{
            placeOrder.setBackgroundColor(Color.GREEN);
            placeOrder.setEnabled(true);
            removeAll.setBackgroundColor(Color.RED);
            removeAll.setEnabled(true);
        }

    }

    /*Setting button listeners*/
    private void setButtonListeners(int productTotal){
        final BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);
        final int productTotalFinal = productTotal;
        Button placeOrderBtn = getView().findViewById(R.id.carrito_btnPlaceOrder);
        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder(productTotalFinal);

            }
        });
        Button removeAllBtn = getView().findViewById(R.id.carrito_btnEmptyCarrito);
        removeAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Do you want to remove all the items from your carrito?").setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Carrito.emptyCarrito();
                        populateCarritoList();
                        updateButtons();
                        bottomNavigationView.getMenu().findItem(R.id.navigation_carrito).setTitle("Carrito");
                        setTotalLabel(0);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    /*Populates the ListView and returns the total cost of the products*/
    private int populateCarritoList(){
        Product p;
        int qty = 0;
        ListView carritoList = getView().findViewById(R.id.carrito_lstOrderLines);
        ArrayList<CarritoItem> itemsList = new ArrayList<>();
        List<CarritoItem> addedItems = Carrito.getAddedItems();
        int productTotal = 0;
        for (CarritoItem c:addedItems
                ) {
            p = c.getProduct();
            qty = c.getQuantity();

            productTotal += p.getPrice()*qty;

            itemsList.add(c);
        }
        ArrayAdapter<CarritoItem> arrayAdapter =
                new ArrayAdapter<CarritoItem>(getContext(),android.R.layout.simple_list_item_1, itemsList);
        // Set The Adapter
        carritoList.setAdapter(arrayAdapter);

        final int finalProductTotal = productTotal;
        carritoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final CarritoItem item_carrito = (CarritoItem)parent.getItemAtPosition(position);
                PopupMenu popup = new PopupMenu(getContext(), view);
                if(Build.VERSION.SDK_INT > 23 ) {
                    popup.setGravity(Gravity.NO_GRAVITY);
                }
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.item_navigation, popup.getMenu());
                popup.show();

                PopupMenu.OnMenuItemClickListener mOnMenuItemCLickedListener = new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_navigation_delete:
                                int totalDeletedPrice = item_carrito.getProduct().getPrice()*item_carrito.getQuantity();
                                updateTotalLabel(finalProductTotal, totalDeletedPrice);
                                Carrito.deleteCarritoItem(item_carrito);
                                populateCarritoList();
                                updateButtons();
                                return true;

                        }
                        return false;
                    }
                };
                popup.setOnMenuItemClickListener(mOnMenuItemCLickedListener);
            }
        });

        return productTotal;

    }


    /*Takes all the info and saves the order*/
    public void placeOrder(int total){
        try {
            final BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);
            final TextInputEditText address = getView().findViewById(R.id.carrito_txtAddress);
            final String deliveryAddress = address.getText().toString();
            final OrderBuilder orderBuilder = new OrderBuilder();
            final List<CarritoItem> items = Carrito.getAddedItems();


            /*Check if the order needs to be delivered*/
            Switch delivery = getView().findViewById(R.id.carrito_swtDelivery);
            boolean deliv;
            if (delivery.isChecked()) {
                deliv = true;
                if (deliveryAddress.isEmpty()) {
                    /*Do nothing because the Delivery option was selected, but no address was given*/
                    Toast.makeText(getContext(), "Please enter an address", Toast.LENGTH_LONG).show();
                    throw new Exception();
                }
            } else {
                deliv = false;
            }
            final boolean isDelivery = deliv;


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            String addressInfoMsg = "";
            if (isDelivery) {
                addressInfoMsg = " Deliver to: " + deliveryAddress;
            }
            builder.setMessage("Would you like to place the order? \n" + addressInfoMsg +
                    "\n Payment amount: " + String.valueOf(total))
                    .setTitle("Order confirmation");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    orderBuilder.buildOrder(items);
                    orderBuilder.setAddress(deliveryAddress);
                    orderBuilder.setDelivery(isDelivery);
                    setTotalLabel(0);
                    Carrito.emptyCarrito();
                    populateCarritoList();
                    bottomNavigationView.getMenu().findItem(R.id.navigation_carrito).setTitle("Carrito");

                    AlertDialog.Builder builderSuccess = new AlertDialog.Builder(getActivity());

                    builderSuccess.setMessage("Order placed :)\n" +
                            "Go to User/Orders to check your orders")
                            .setTitle("Success!");
                    builderSuccess.setPositiveButton("Ok",null);
                    AlertDialog dialogSuccess = builderSuccess.create();
                    dialogSuccess.show();

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }catch(Exception e){

        }


        //
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
