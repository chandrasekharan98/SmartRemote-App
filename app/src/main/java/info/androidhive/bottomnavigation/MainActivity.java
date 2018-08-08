package info.androidhive.bottomnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import info.androidhive.bottomnavigation.fragment.RemoteFragment;
import info.androidhive.bottomnavigation.fragment.CategoryFragment;
import info.androidhive.bottomnavigation.fragment.WishListFragment;
import info.androidhive.bottomnavigation.fragment.HomeFragment;
import info.androidhive.bottomnavigation.fragment.CameraFragment;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("shop");
        loadFragment(new HomeFragment());
        Log.d("tag",Integer.toString(navigation.getSelectedItemId()));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_category:
                    toolbar.setTitle("My Categories");
                    fragment = new CategoryFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_remote:
                    toolbar.setTitle("Remote");
                    fragment = new RemoteFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_wishlist:
                    toolbar.setTitle("wish list");
                    fragment = new WishListFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_camera:
                    toolbar.setTitle("Camera");
                    fragment = new CameraFragment();
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
