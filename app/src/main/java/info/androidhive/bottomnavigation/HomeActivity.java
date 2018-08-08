package info.androidhive.bottomnavigation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    CarouselView carouselView;
    int[] sportsImages = {R.drawable.cricket_backdrop,R.drawable.football_backdrop,R.drawable.hockey_backdrop,R.drawable.wrestling_backdrop,R.drawable.tennis_backdrop};
    Toolbar toolbar;

    private RecyclerView recyclerView,recyclerView2;
    List<Zmovies> catList = new ArrayList<>();
    List<Zmovies> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        //set up carousel
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sportsImages.length);
        carouselView.setImageListener(imageListener);
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(HomeActivity.this, "Clicked item: "+ position, Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Home");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view2);

        prepareCategoryRecycle();

        prepareRecentRecycle();

    }
    public void prepareCategoryRecycle(){

        CategoryViewAdapter zAdapter;

        zAdapter=new CategoryViewAdapter(this,catList);
        recyclerView.setHasFixedSize(true);
        //layout match_parent
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(zAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Zmovies movie = catList.get(position);
                //Toast.makeText(getApplicationContext(), movie.getMovietitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(HomeActivity.this, SportsActivity.class);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        int[] covers = new int[]{
                R.drawable.csports,
                R.drawable.cnews,
                R.drawable.ctravel2,
                R.drawable.cmovies,
                R.drawable.cdevote,
                R.drawable.cregional,
                R.drawable.cmusic,
                R.drawable.ckids,
        };
        String[] channelName= new String[]{"Sport","News","Travel","Movies","Devotional","Regional","Music","Kids"};
        String[] movietitle= new String[]{"Sport","News","Travel","Movies","Devotional","Regional","Music","Kids"};
        for(int i=0;i<covers.length;i++){
            Zmovies movie=new Zmovies(movietitle[i],channelName[i],covers[i]);
            catList.add(movie);
        }

        zAdapter.notifyDataSetChanged();
    }

    public void prepareRecentRecycle(){
        ZmovieAdapter zAdapter;

        zAdapter=new ZmovieAdapter(this,movieList);
        recyclerView2.setHasFixedSize(true);
        //layout match_parent
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(mLayoutManager);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(zAdapter);

        // row click listener
        recyclerView2.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView2, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Zmovies movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), movie.getMovietitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        int[] covers = new int[]{
                R.drawable.wonder,
                R.drawable.jl,
                R.drawable.jurassic,
                R.drawable.thor,
                R.drawable.water,
                R.drawable.maze,
                R.drawable.logan,
                R.drawable.avatar,
                R.drawable.tomb,
                R.drawable.rampage,
                R.drawable.pari};
        String[] channelName= new String[]{"Movies Now","HBO","Movies Now","HBO","Z Movies","Movies Now","Star Movies","MD HD","Movies Now","Z Movies","Movies Now"};
        String[] movietitle= new String[]{"Wonder Woman","Justice League","Jurassic park","THOR","Shape of water","Maze Runner","Logan","Avatar","Tomb Rider","Rampage","Pari"};
        for(int i=0;i<covers.length;i++){
            Zmovies movie=new Zmovies(movietitle[i],channelName[i],covers[i]);
            movieList.add(movie);
        }

        zAdapter.notifyDataSetChanged();
    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sportsImages[position]);
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                    return true;
                case R.id.navigation_category:
                    toolbar.setTitle("What's on TV");
                    Intent i=new Intent(HomeActivity.this, WhatsOnTvActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_remote:
                    toolbar.setTitle("Remote");
                    Intent i2=new Intent(HomeActivity.this, RemoteActivity.class);
                    startActivity(i2);

                    return true;
                case R.id.navigation_wishlist:
                    toolbar.setTitle("wish list");
                    Intent i1=new Intent(HomeActivity.this, BannerActivity.class);
                    startActivity(i1);

                    return true;
                case R.id.navigation_camera:
                    toolbar.setTitle("Camera");
                    Intent i3=new Intent(HomeActivity.this, CameraActivity.class);
                    startActivity(i3);
                    return true;
            }

            return false;
        }
    };
    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
