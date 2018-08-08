package info.androidhive.bottomnavigation.fragment;

import android.icu.util.ULocale;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.bottomnavigation.Categories;
import info.androidhive.bottomnavigation.CategoriesAdapter;
import info.androidhive.bottomnavigation.R;
import info.androidhive.bottomnavigation.RecyclerTouchListener;

public class HomeFragment extends Fragment {

    CarouselView carouselView;
    int[] sportsImages = {R.drawable.cricket_backdrop,R.drawable.football_backdrop,R.drawable.hockey_backdrop,R.drawable.wrestling_backdrop,R.drawable.tennis_backdrop};
    private List<Categories> catList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CategoriesAdapter zAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

      /*  carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sportsImages.length);
        carouselView.setImageListener(imageListener);
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "Clicked item: "+ position, Toast.LENGTH_SHORT).show();
            }
        });
*/

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        zAdapter=new CategoriesAdapter(getActivity(),catList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mllmanager=new LinearLayoutManager(getActivity().getApplicationContext());
        mllmanager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mllmanager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(zAdapter);

        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Categories cat=catList.get(position);
                Toast.makeText(getActivity().getApplicationContext(),"item clicked",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));




        prepareMovieData();
        return view;
    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sportsImages[position]);
        }
    };

    private void prepareMovieData() {

        int[] covers = new int[]{
                R.drawable.cmovies,
                R.drawable.csports,
                R.drawable.cmusic,
                R.drawable.cnews,
                R.drawable.ctravel2,
                R.drawable.cdevote,
                R.drawable.cregional};

        for(int i=0;i<covers.length;i++){
            Categories movie=new Categories(covers[i]);
            catList.add(movie);
        }

        zAdapter.notifyDataSetChanged();
    }

}
