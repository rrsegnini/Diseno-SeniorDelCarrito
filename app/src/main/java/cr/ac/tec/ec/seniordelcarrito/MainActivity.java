package cr.ac.tec.ec.seniordelcarrito;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.tec.ec.seniordelcarrito.model.Inventory;
import cr.ac.tec.ec.seniordelcarrito.model.Product;
import cr.ac.tec.ec.seniordelcarrito.model.ProductType;

public class MainActivity extends AppCompatActivity
        implements MainFragmentCarrito.OnFragmentInteractionListener,
        MainFragmentHome.OnFragmentInteractionListener,
        MainFragmentUser.OnFragmentInteractionListener {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        private void fragmentChanger(FragmentManager fm, Fragment fragment){
            fm.beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.main_frmFragment, fragment)
                    .commit();
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            FragmentManager fm = getSupportFragmentManager();;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new MainFragmentHome();
                    fragmentChanger(fm, fragment);  //Changes the fragment
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new MainFragmentCarrito();
                    fragmentChanger(fm, fragment);
                    return true;
                case R.id.navigation_notifications:
                    fragment = new MainFragmentUser();
                    fragmentChanger(fm, fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadData();
    }
    /*https://image.ibb.co/eXsobz/enchilada.jpg
https://image.ibb.co/mgTi9K/frescoleche.png
https://image.ibb.co/hNOGUK/frescolechevainilla.png
https://image.ibb.co/iKZqpK/hic_te_frio.png
https://image.ibb.co/gBhMwz/hic_uva.png
https://image.ibb.co/hrqkOe/minipizzas.jpg
https://image.ibb.co/jRvKie/trululu_feroz.jpg
https://image.ibb.co/b5EgUK/bubulubu.jpg
https://image.ibb.co/f8aMUK/snickers.png
https://image.ibb.co/fzGpGz/arrollado.png
https://image.ibb.co/f3yY9K/chiclets.jpg*/
    private void loadData(){
        Inventory.addItem(new Product("Pan de canela", 600,
                "https://image.ibb.co/bW7O9K/cinnamorolls.jpg", ProductType.PASTRY));
        Inventory.addItem(new Product("Arrollado jamon y queso", 650,
                "https://image.ibb.co/fzGpGz/arrollado.png", ProductType.PASTRY));
        Inventory.addItem(new Product("Enchilada de papa", 650,
                "https://image.ibb.co/eXsobz/enchilada.jpg", ProductType.PASTRY));
        Inventory.addItem(new Product("Mini pizza", 800,
                "https://image.ibb.co/hrqkOe/minipizzas.jpg", ProductType.PASTRY));

        Inventory.addItem(new Product("Chiclets", 250,
                "https://image.ibb.co/f3yY9K/chiclets.jpg", ProductType.CANDY));
        Inventory.addItem(new Product("Gomita Trululu Feroz", 400,
                "https://image.ibb.co/jRvKie/trululu_feroz.jpg", ProductType.CANDY));
        Inventory.addItem(new Product("Bubulubu", 300,
                "https://image.ibb.co/b5EgUK/bubulubu.jpg", ProductType.CANDY));
        Inventory.addItem(new Product("Snickers", 800,
                "https://image.ibb.co/f8aMUK/snickers.png", ProductType.CANDY));

        Inventory.addItem(new Product("Hi-C Uva", 600,
                "https://image.ibb.co/gBhMwz/hic_uva.png", ProductType.BEVERAGE));
        Inventory.addItem(new Product("Hi-C Te Frio", 650,
                "https://image.ibb.co/iKZqpK/hic_te_frio.png", ProductType.BEVERAGE));
        Inventory.addItem(new Product("Fresco Leche Chocolate", 650,
                "https://image.ibb.co/mgTi9K/frescoleche.png", ProductType.BEVERAGE));
        Inventory.addItem(new Product("Fresco Leche Vainilla", 800,
                "https://image.ibb.co/hNOGUK/frescolechevainilla.png", ProductType.BEVERAGE));

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
