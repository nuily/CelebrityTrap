package nyc.c4q.ramonaharrison.accesscats;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import nyc.c4q.ramonaharrison.accesscats.model.Cat;

/**
 * Created by Ramona Harrison
 * on 11/19/16.
 */

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private Listener listener;
    private List<Cat> cats;

    public CatAdapter(List<Cat> cats, Listener listener) {
        this.cats = cats;
        this.listener = listener;
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cat, parent, false);
        CatViewHolder vh = new CatViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        Cat cat = cats.get(position);
        holder.bind(cat);
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public void setData(List<Cat> cats) {
        this.cats = cats;
        notifyDataSetChanged();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout layout;
        TextView catName;
        TextView lastFed;
        ImageView catimgView;
        View wtfView;

        public CatViewHolder(View itemView) {
            super(itemView);
            this.wtfView = itemView;
            layout = (RelativeLayout) itemView.findViewById(R.id.layout);
            catName = (TextView) itemView.findViewById(R.id.tv_cat_name);
            lastFed = (TextView) itemView.findViewById(R.id.tv_last_fed);
            catimgView = (ImageView) itemView.findViewById(R.id.img_cat);
        }

        public void bind(Cat c) {
            final Cat cat = c;
            catName.setText(cat.getName());
            Picasso.with(itemView.getContext())
                    .load(cat.getImage())
                    .into(catimgView);
            lastFed.setText(DateFormat.getDateTimeInstance().format(new Date(cat.getLastFed())));
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCatClicked(cat);
                }
            });

            layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onCatLongClicked(cat);
                    return true;
                }
            });
        }
    }

    interface Listener {
        void onCatClicked(Cat cat);
        void onCatLongClicked(Cat cat);
    }
}
