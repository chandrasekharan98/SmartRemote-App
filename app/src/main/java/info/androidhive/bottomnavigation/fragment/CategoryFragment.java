package info.androidhive.bottomnavigation.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import info.androidhive.bottomnavigation.BannerActivity;
import info.androidhive.bottomnavigation.CurrentChannel;
import info.androidhive.bottomnavigation.HomeActivity;
import info.androidhive.bottomnavigation.KeypadActivity;
import info.androidhive.bottomnavigation.MovieRecycleActivity;
import info.androidhive.bottomnavigation.R;
import info.androidhive.bottomnavigation.SportsActivity;


public class CategoryFragment extends Fragment {
    Button category,channel,movie,banner,keys;
    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        View view=inflater.inflate(R.layout.fragment_category, container, false);
        category=(Button) view.findViewById(R.id.category_button);
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), SportsActivity.class);
                startActivity(i);
            }
        });

        channel=(Button) view.findViewById(R.id.channel_button);
        channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), CurrentChannel.class);
                startActivity(i);
            }
        });

        movie=(Button) view.findViewById(R.id.movie_button);
        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), KeypadActivity.class);
                startActivity(i);
            }
        });

        banner=(Button) view.findViewById(R.id.banner_button);
        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), BannerActivity.class);
                startActivity(i);
            }
        });

        keys=(Button) view.findViewById(R.id.keys_button);
        keys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), HomeActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

}
