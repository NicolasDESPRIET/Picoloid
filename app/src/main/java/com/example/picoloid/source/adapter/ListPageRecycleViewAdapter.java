package com.example.picoloid.source.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.picoloid.R;
import com.example.picoloid.source.activity.PageActivityUser;
import com.example.picoloid.source.model.PicoloPage;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ListPageRecycleViewAdapter extends RecyclerView.Adapter<ListPageRecycleViewAdapter.ViewHolder> {

    Activity activity;
    Context context;
    List<PicoloPage> list;
    boolean isFromButton;

    public ListPageRecycleViewAdapter(Context context, List<PicoloPage> list, Activity activity, boolean isFromButton) {
        this.activity = activity;
        this.context = context;
        this.list = list;
        this.isFromButton = isFromButton;
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
                onPageSelected(i);
            }
        });
    }

    private void onPageSelected(int id){
        if(isFromButton){
            Intent returnIntent = new Intent();
            returnIntent.putExtra("pageId",list.get(id).getId());
            activity.setResult(RESULT_OK,returnIntent);
            activity.finish();
        }else{
            Intent ii = new Intent(context, PageActivityUser.class);
            ii.putExtra("pageId",list.get(id).getId());
            context.startActivity(ii);
        }
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
