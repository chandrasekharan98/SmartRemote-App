package info.androidhive.bottomnavigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by welcome on 27-03-2018.
 */

public class ZmovieAdapter extends RecyclerView.Adapter<ZmovieAdapter.MyViewHolder>{
    private Context mContext;
    List<Zmovies> moviesList;

    public TextView title, channel;
    public ImageView thumbnail;

    public ZmovieAdapter(Context mContext, List<Zmovies> moviesList) {
        this.mContext = mContext;
        this.moviesList = moviesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, channel;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.movietitle);
            channel = (TextView) view.findViewById(R.id.moviechannel);
            thumbnail = (ImageView) view.findViewById(R.id.movieposter);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.zmovies_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Zmovies movies=moviesList.get(position);
        holder.title.setText(movies.getMovietitle());
        holder.channel.setText(movies.getChannelname());
        // loading album cover using Glide library
        Glide.with(mContext).load(movies.getThumbnail()).into(holder.thumbnail);
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
