package com.example.picoloid.source.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picoloid.R;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> profilList = new ArrayList<>();

    public RecycleViewAdapter(Context context, ArrayList<String> profilList) {
        this.context = context;
        this.profilList = profilList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profil_list,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        if ((position%3) == 0){
            viewHolder.imageView.setImageResource(R.drawable.bulle1_p);
        }else if((position%3) == 1){
            viewHolder.imageView.setImageResource(R.drawable.bulle2_p);
        }else if((position%3) == 2){
            viewHolder.imageView.setImageResource(R.drawable.bulle3_p);
        }
        //text
        viewHolder.textView.setText(profilList.get(position));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, profilList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return profilList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        RelativeLayout parentLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewRec);
            textView = itemView.findViewById(R.id.textViewRec);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
