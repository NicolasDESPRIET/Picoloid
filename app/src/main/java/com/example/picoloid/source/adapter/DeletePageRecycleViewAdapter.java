package com.example.picoloid.source.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.picoloid.R;
import com.example.picoloid.source.model.PicoloPage;

import java.util.ArrayList;

public class DeletePageRecycleViewAdapter extends RecyclerView.Adapter<DeletePageRecycleViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PicoloPage> picoloPageList;
    private ArrayList<Integer> arrayList = new ArrayList<>();

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public DeletePageRecycleViewAdapter(Context context, ArrayList<PicoloPage> picoloPageList) {
        this.context = context;
        this.picoloPageList = picoloPageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_delete_list,viewGroup, false);
        return new DeletePageRecycleViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.textView.setText(picoloPageList.get(position).getName());

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked()){
                    arrayList.add(picoloPageList.get(position).getId());
                }else if(!((CheckBox) v).isChecked()){
                    arrayList.remove((Integer) picoloPageList.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return picoloPageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        CheckBox checkBox;
        ConstraintLayout layout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.deleteText);
            checkBox = itemView.findViewById(R.id.checkBoxDelete);
            layout = itemView.findViewById(R.id.deleteParent);
        }
    }
}
