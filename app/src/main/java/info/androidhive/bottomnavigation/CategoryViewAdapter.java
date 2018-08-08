package info.androidhive.bottomnavigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by welcome on 30-03-2018.
 */

public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.MyViewHolder>{
    private Context mContext;
    List<Zmovies> moviesList;

    public ImageView thumbnail;

    public CategoryViewAdapter(Context mContext, List<Zmovies> moviesList) {
        this.mContext = mContext;
        this.moviesList = moviesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.movieposter);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_view_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Zmovies movies=moviesList.get(position);
        Glide.with(mContext).load(movies.getThumbnail()).into(holder.thumbnail);
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

