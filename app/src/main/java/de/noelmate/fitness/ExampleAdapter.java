package de.noelmate.fitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context context;
    private ArrayList uebung_id, uebung_name;

    ExampleAdapter(Context context, ArrayList uebung_id, ArrayList uebung_name){
        this.context = context;
        this.uebung_id = uebung_id;
        this.uebung_name = uebung_name;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.examle_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        position = uebung_id.size() - position - 1;
        holder.mImageView.setImageResource(R.drawable.weight_icon);
        holder.mTextView2.setText(String.valueOf(uebung_id.get(position)));
        holder.mTextView1.setText(String.valueOf(uebung_name.get(position)));
    }

    @Override
    public int getItemCount() {
        return uebung_id.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public ExampleViewHolder(View itemView)
        {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }

}
