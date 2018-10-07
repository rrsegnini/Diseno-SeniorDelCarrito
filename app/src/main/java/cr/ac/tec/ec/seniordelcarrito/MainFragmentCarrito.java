package cr.ac.tec.ec.seniordelcarrito;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cr.ac.tec.ec.seniordelcarrito.model.Carrito;
import cr.ac.tec.ec.seniordelcarrito.model.CarritoItem;
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
        List<CarritoItem> addedItems = Carrito.getAddedItems();

        ListView carritoList = getView().findViewById(R.id.carrito_lstOrderLines);

        Product p;
        int qty = 0;

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);


        ArrayList<String> itemsList = new ArrayList<>();

        int productTotal = 0;
        for (CarritoItem c:addedItems
             ) {
            p = c.getProduct();
            qty = c.getQuantity();

            productTotal += p.getPrice()*qty;

            itemsList.add(p.getName() + "          " + String.valueOf(qty)
                    + "          " + String.valueOf(p.getPrice()*qty));
        }
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, itemsList);
        // Set The Adapter
        carritoList.setAdapter(arrayAdapter);


        /*Setting TOTAL: label*/
        TextView TotalTxt = getView().findViewById(R.id.carrito_txtTotal);
        TotalTxt.setText("Total: " + productTotal);


        /*Setting the 'Place order' button*/
        Button placeOrder = getView().findViewById(R.id.carrito_btnPlaceOrder);
        if (Carrito.gettNumberOfItems() == 0){
            placeOrder.setBackgroundColor(Color.GRAY);
            placeOrder.setEnabled(false);
        }else{
            placeOrder.setBackgroundColor(Color.GREEN);
            placeOrder.setEnabled(true);
        }



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
