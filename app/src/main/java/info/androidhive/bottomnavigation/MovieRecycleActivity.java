package info.androidhive.bottomnavigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MovieRecycleActivity extends AppCompatActivity {
    private List<Zmovies> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ZmovieAdapter zAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_recycle);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        zAdapter=new ZmovieAdapter(this,movieList);
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
                Zmovies movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), movie.getMovietitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();
    }
    /**
     * Prepares sample data to provide data set to adapter
     */
    private void prepareMovieData() {
       /* Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);
*/
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
}
