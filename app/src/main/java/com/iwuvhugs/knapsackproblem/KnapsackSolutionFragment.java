package com.iwuvhugs.knapsackproblem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iwuvhugs.knapsackproblem.knapsack.KnapsackSolution;
import com.iwuvhugs.knapsackproblem.model.Variants;


public class KnapsackSolutionFragment extends Fragment {

    private static final String LOGTAG = KnapsackSolutionFragment.class.getSimpleName();

    private static final String KNAPSACK_CONTENT = "knapsack_content";
    private static final String KNAPSACK_WEIGHT = "knapsack_weight";
    private static final String KNAPSACK_COST = "knapsack_cost";

    public KnapsackSolutionFragment() {
    }

    public static KnapsackSolutionFragment newInstance(KnapsackSolution solution) {
        KnapsackSolutionFragment fragment = new KnapsackSolutionFragment();
        if (solution != null) {
            Bundle args = new Bundle();
            args.putString(KNAPSACK_CONTENT, new Gson().toJson(solution.getContents()));
            args.putInt(KNAPSACK_WEIGHT, solution.getContentWeight());
            args.putDouble(KNAPSACK_COST, solution.getContentCost());
            fragment.setArguments(args);
        }
        return fragment;
    }

    public static Fragment newInstance() {
        return new KnapsackSolutionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_solution, container, false);
        TextView weightTextView = (TextView) rootView.findViewById(R.id.weight_textView);
        TextView costTextView = (TextView) rootView.findViewById(R.id.cost_textView);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.items_recyclersView);
        recyclerView.setHasFixedSize(true);
        ;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);


        if (getArguments() != null) {
            int weight = getArguments().getInt(KNAPSACK_WEIGHT);
            double cost = getArguments().getDouble(KNAPSACK_COST);
            Variants[] variants = new Gson().fromJson(getArguments().getString(KNAPSACK_CONTENT), Variants[].class);
//            Log.d(LOGTAG, "Lenght: " + variants.length);

            if (weight != 0)
                weightTextView.setText((weight / 1000.0) + " kg");
            if (cost != 0)
                costTextView.setText("$ " + cost);

//            ItemsRecyclerViewAdapter adapter = new ItemsRecyclerViewAdapter(variants);
//            recyclerView.setAdapter(adapter);
//            recyclerView.setItemAnimator(new DefaultItemAnimator());

        }

        return rootView;
    }


}
