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
 * Created by welcome on 29-03-2018.
 */

public class ChannelSearchAdapter extends RecyclerView.Adapter<ChannelSearchAdapter.MyViewHolder> {
    private Context mContext;
    List<ChannelSearch> channels;
    public TextView name,number;

    public ChannelSearchAdapter(Context mContext, List<ChannelSearch> channels) {
        this.mContext = mContext;
        this.channels = channels;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,number;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.channelname);
            number = (TextView) view.findViewById(R.id.channelnumber);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.number_search_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ChannelSearch channel=channels.get(position);
        holder.name.setText(channel.getChannelname());
        holder.number.setText(channel.getChannelnumber());
    }
    @Override
    public int getItemCount() {
        return channels.size();
    }
}
