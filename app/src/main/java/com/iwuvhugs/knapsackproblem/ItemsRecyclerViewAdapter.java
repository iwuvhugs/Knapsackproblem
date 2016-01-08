//package com.iwuvhugs.knapsackproblem;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.iwuvhugs.knapsackproblem.model.Variants;
//
//
//public class ItemsRecyclerViewAdapter extends RecyclerView.Adapter<ItemsRecyclerViewAdapter.ViewHolder>{
//
//    private Variants[] variants;
//
//    public ItemsRecyclerViewAdapter(Variants[] variants) {
//        this.variants = variants;
//    }
//
//    @Override
//    public ItemsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.items_view_item, parent, false);
//
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(ItemsRecyclerViewAdapter.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//        }
//    }
//}
