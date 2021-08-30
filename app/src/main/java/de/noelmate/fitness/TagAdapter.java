package de.noelmate.fitness;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ExampleViewHolder> {

    private Context context;
    private ArrayList uebung_id, uebung_saetze, uebung_gewicht;

    TagAdapter(Context context, ArrayList uebung_id, ArrayList uebung_saetze, ArrayList uebung_gewicht){
        this.context = context;
        this.uebung_id = uebung_id;
        this.uebung_saetze = uebung_saetze;
        this.uebung_gewicht = uebung_gewicht;
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
        //holder.mTextView2.setText(String.valueOf(uebung_id.get(position)));
        holder.mTextView1.setText(String.valueOf(uebung_saetze.get(position)));
        holder.mTextView2.setText(String.valueOf(uebung_gewicht.get(position)));
        int finalPosition = position;
        holder.example_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Uebung.class);
                intent.putExtra("saetze", String.valueOf(uebung_saetze.get(finalPosition)));
                intent.putExtra("gewicht", String.valueOf(uebung_gewicht.get(finalPosition)));
                context.startActivity(intent);
            }
        });
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
        CardView example_item;

        public ExampleViewHolder(View itemView)
        {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            example_item = itemView.findViewById(R.id.cardview);
        }
    }

}
