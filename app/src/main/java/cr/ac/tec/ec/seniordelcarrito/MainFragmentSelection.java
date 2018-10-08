package cr.ac.tec.ec.seniordelcarrito;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.tec.ec.seniordelcarrito.model.Carrito;
import cr.ac.tec.ec.seniordelcarrito.model.CarritoBuilder;
import cr.ac.tec.ec.seniordelcarrito.model.Product;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragmentSelection.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragmentSelection#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragmentSelection extends DialogFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Price";
    private static final String ARG_PARAM2 = "ProductQty";
    private static final String ARG_PARAM3 = "Name";
    private static final String ARG_PARAM4 = "Product";


    private int Price;
    private int Qty;
    private String Name;
    private Product _Product;

    private CarritoBuilder carritobuilder = new CarritoBuilder();

    private OnFragmentInteractionListener mListener;

    public MainFragmentSelection() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragmentSelection.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragmentSelection newInstance(String param1, String param2) {
        MainFragmentSelection fragment = new MainFragmentSelection();
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
            Price = getArguments().getInt(ARG_PARAM1);
            Qty = getArguments().getInt(ARG_PARAM2);
            Name = getArguments().getString(ARG_PARAM3);
            _Product = (Product) getArguments().getSerializable(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_fragment_selection, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout info = getView().findViewById(R.id.main_lytSelection);

        /*TextViews that display the product's infomation*/
        final TextView NameTxt = getView().findViewById(R.id.main_txtProduct);
        final TextView QtyTxt = getView().findViewById(R.id.main_txtQty);
        final TextView TotalTxt = getView().findViewById(R.id.main_txtTotal);

        /*Setting default values*/
        QtyTxt.setText("Quantity: 1");
        TotalTxt.setText("Total: " + String.valueOf(Price));

        /*The NumberPicker to select the quantity*/
        final NumberPicker np = new NumberPicker(getContext());
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        np.setMinValue(1);
        np.setMaxValue(Qty);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                QtyTxt.setText("Quantity: " + String.valueOf(np.getValue()));
                TotalTxt.setText("Total: " + String.valueOf(np.getValue()*Price));
            }
        });

        info.addView(np, 3);
        NameTxt.setText(Name);

        int selectedQty = np.getValue();
        Button add2Carrito = getView().findViewById(R.id.main_btnAdd);
        add2Carrito.setOnClickListener(new View.OnClickListener() {
            //@SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.navigation);

                /*Builder add the item to the carrito*/
                carritobuilder.buildOrder(_Product, np.getValue());

                /*Changes the bottom navigation icon*/
                bottomNavigationView.getMenu().findItem(R.id.navigation_carrito).setTitle("Carrito(" + String.valueOf(Carrito.gettNumberOfItems()) +")");
                bottomNavigationView.getMenu().findItem(R.id.navigation_carrito).setIcon(R.mipmap.baseline_shopping_cart_black_48dp);
                bottomNavigationView.getMenu().findItem(R.id.navigation_carrito);

                //Snackbar.make(getView().findViewById(R.id.navigation), "Product added!", Snackbar.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "Product added!", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();




            }
        });




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
