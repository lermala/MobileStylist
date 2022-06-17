package com.lermala.lookconstructor.helpers;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lermala.lookconstructor.R;
import com.lermala.lookconstructor.mainapp.data.models.Portfolio;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    // https://www.youtube.com/watch?v=94rCjYxvzEE&ab_channel=CodingWithMitch
    // https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example

    private static final String TAG = "RecyclerViewAdapter";

    private LayoutInflater mInflater;
    private ArrayList<Portfolio> portfolioArrayList = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<Portfolio> portfolioArrayList) {
        this.portfolioArrayList = portfolioArrayList;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.porfolio_grid,
        //         parent, false);

        View view = mInflater.inflate(R.layout.item_presentation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Portfolio curPortfolio = portfolioArrayList.get(position);
        // set image
        // Glide.with(mContext)
        //         .asBitmap()
        //         .load(curPortfolio.image_id)
        //         .into(holder.imageView);

        holder.textView.setText(curPortfolio.name);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d(TAG + " onClick " + curPortfolio.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return portfolioArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView textView;

        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.pres_name);
            // imageView = itemView.findViewById(R.id.ci)
        }
    }

}
