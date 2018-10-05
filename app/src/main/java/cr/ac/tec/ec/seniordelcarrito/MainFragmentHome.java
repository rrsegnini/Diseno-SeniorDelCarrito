package cr.ac.tec.ec.seniordelcarrito;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.tec.ec.seniordelcarrito.model.Inventory;
import cr.ac.tec.ec.seniordelcarrito.model.Product;
import cr.ac.tec.ec.seniordelcarrito.model.ProductType;
import cr.ac.tec.ec.seniordelcarrito.utility.Images;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragmentHome.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragmentHome extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragmentHome newInstance(String param1, String param2) {
        MainFragmentHome fragment = new MainFragmentHome();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*new Images.DownloadImageTask((ImageView) getView().findViewById(R.id.main_imgReco1))
                .execute(p1.getPosterURL());*/

        populateHome();

    }

    private void populateHome(){


        LinearLayout pastry = getView().findViewById(R.id.main_lytPastry);
        LinearLayout candy = getView().findViewById(R.id.main_lytCandy);
        LinearLayout beverages = getView().findViewById(R.id.main_lytBeverages);

        for (Product p:Inventory.getInventario()
             ) {
            if (p.getType() == ProductType.PASTRY){
                setProduct(p, pastry);
            }else if (p.getType() == ProductType.CANDY){
                setProduct(p, candy);
            }else if (p.getType() == ProductType.BEVERAGE){
                setProduct(p, beverages);
            }

        }

    }

    /*Add the product to its respective ScrollView in the Home fragment*/
    private void setProduct(Product p, LinearLayout product_layout){
        CardView new_item_card = new CardView(getContext());
        ImageView new_item = new ImageView(getContext());
        TextView product_info = new TextView(getContext());
        product_info.setBackgroundColor(Color.parseColor("#ffffff"));
        product_info.setText("   " + p.getName() + "\n   " + p.getPrice());
        product_info.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        /*Downloads the image from the URL and displays it on the ImageView*/
        new Images.DownloadImageTask(new_item)
                .execute(p.getImageURL());

        /*Changing ImageView settings*/
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
        new_item.setLayoutParams(layoutParams);
        //new_item.setScaleType(ImageView.ScaleType.FIT_CENTER);

        /*Changing CardView settings*/
        new_item_card.setRadius((float)18);

        new_item_card.setPadding(100,100,100,100);
        final int version = Build.VERSION.SDK_INT;
        if (version >= 21) {
            new_item_card.setElevation((float)0);
        } else {
            /*Does nothing*/
        }

        /*Changing LinearLayout settings*/




        LinearLayout.LayoutParams layoutParamsText = new LinearLayout.LayoutParams(200, 50);
        product_info.setLayoutParams(layoutParamsText);

        new_item_card.addView(new_item);
        new_item_card.addView(product_info);
        product_layout.addView(new_item_card);
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
