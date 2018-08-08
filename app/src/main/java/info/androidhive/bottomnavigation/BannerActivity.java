package info.androidhive.bottomnavigation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends AppCompatActivity {
    private List<Zmovies> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BannerMovieAdapter zAdapter;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("Home");
        navigation.setSelectedItemId(R.id.navigation_wishlist);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        zAdapter=new BannerMovieAdapter(this,movieList);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(zAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Zmovies movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), movie.getMovietitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                    Intent i=new Intent(BannerActivity.this, HomeActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_category:
                    toolbar.setTitle("What's on TV");
                    Intent i1=new Intent(BannerActivity.this, WhatsOnTvActivity.class);
                    startActivity(i1);
                    return true;
                case R.id.navigation_remote:
                    toolbar.setTitle("Remote");
                    Intent i4=new Intent(BannerActivity.this, RemoteActivity.class);
                    startActivity(i4);
                    return true;
                case R.id.navigation_wishlist:
                    toolbar.setTitle("wish list");

                    return true;
                case R.id.navigation_camera:
                    toolbar.setTitle("Camera");
                    Intent i3=new Intent(BannerActivity.this, CameraActivity.class);
                    startActivity(i3);
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    /**
     * Prepares sample data to provide data set to adapter
     */
    private void prepareMovieData() {
       /* Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);
*/
        int[] covers = new int[]{
                R.drawable.dracula,
                R.drawable.geo,
                R.drawable.ragnarok,
                R.drawable.male,
                R.drawable.night,
                R.drawable.storm,
                R.drawable.tarzan};

        String[] channelName= new String[]{"Movies Now","HBO","Movies Now","HBO","Z Movies","Movies Now","Star Movies"};
        String[] movietitle= new String[]{"Dracula","Geo Storm","THOR Ragnarok","Maleficent","Night at the Museum","Into the Storm","Tarzan"};
        for(int i=0;i<covers.length;i++){
            Zmovies movie=new Zmovies(movietitle[i],channelName[i],covers[i]);
            movieList.add(movie);
        }

        zAdapter.notifyDataSetChanged();
    }
}
