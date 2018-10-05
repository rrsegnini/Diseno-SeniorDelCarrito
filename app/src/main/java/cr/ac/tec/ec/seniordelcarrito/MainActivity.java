package cr.ac.tec.ec.seniordelcarrito;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
