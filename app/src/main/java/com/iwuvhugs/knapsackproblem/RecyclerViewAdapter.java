package com.iwuvhugs.knapsackproblem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iwuvhugs.knapsackproblem.model.Product;
import com.iwuvhugs.knapsackproblem.model.ProductWrapper;
import com.squareup.picasso.Picasso;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static Context context;
    private static ProductWrapper dataset;

    public RecyclerViewAdapter(ProductWrapper list, Context context) {
        dataset = list;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String LOGTAG = ViewHolder.class.getSimpleName();
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.recycle_item_text_view);
            imageView = (ImageView) itemView.findViewById(R.id.recycle_image_view);
        }

        @Override
        public void onClick(View v) {
            Intent resultIntent = new Intent(context, DetailActivity.class);
            Log.d(LOGTAG, getAdapterPosition() + " " +  dataset.getProducts()[getAdapterPosition()].getTitle());
            resultIntent.putExtra(Product.class.getSimpleName(), new Gson().toJson(dataset.getProducts()[getAdapterPosition()]));
            context.startActivity(resultIntent);
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (dataset != null) {

            holder.textView.setText(dataset.getProducts()[position].getTitle());
            Picasso.with(context)
                    .load(dataset.getProducts()[position].getImages()[0].getSrc())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .resizeDimen(R.dimen.list_detail_image_size, R.dimen.list_detail_image_size)
                    .centerInside()
                    .tag(context)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if (dataset != null) {
            return dataset.getProducts().length;
        } else {
            return 0;
        }
    }

    public void setNewDataset(ProductWrapper list) {
        dataset = list;
        notifyDataSetChanged();
    }

}
