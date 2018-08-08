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

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{
    private Context mContext;
    List<Categories> catList;

    public ImageView thumbnail;

    public CategoriesAdapter(Context mContext, List<Categories> catList) {
        this.mContext = mContext;
        this.catList = catList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.categoryposter);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Categories movies=catList.get(position);
        Glide.with(mContext).load(movies.getThumbnail()).into(holder.thumbnail);
    }
    @Override
    public int getItemCount() {
        return catList.size();
    }
}