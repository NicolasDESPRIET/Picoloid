package com.example.picoloid.source.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picoloid.R;
import com.example.picoloid.source.model.PicoloPage;

import java.util.List;

public class ListPageRecycleViewAdapter extends RecyclerView.Adapter<ListPageRecycleViewAdapter.ViewHolder> {

    Context context;
    List<PicoloPage> list;

    public ListPageRecycleViewAdapter(Context context, List<PicoloPage> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_page_list,viewGroup, false);
        return new ListPageRecycleViewAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.pageName.setText(list.get(i).getName());

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView pageName;
        ConstraintLayout parentLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            pageName = itemView.findViewById(R.id.pageNameR);
            parentLayout = itemView.findViewById(R.id.pageListCase);
        }
    }
}
