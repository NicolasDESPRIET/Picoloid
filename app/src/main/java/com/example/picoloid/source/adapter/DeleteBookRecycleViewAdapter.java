package com.example.picoloid.source.adapter;

import android.annotation.SuppressLint;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DeleteBookRecycleViewAdapter extends RecyclerView.Adapter<DeleteBookRecycleViewAdapter.ViewHolder> {
    private Context context;
    private JSONArray profilList;

    private ArrayList<Integer> deleted = new ArrayList<>();

    public DeleteBookRecycleViewAdapter(Context context, JSONArray profilList) {
        this.context = context;
        this.profilList = profilList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_delete_list,viewGroup, false);
        return new DeleteBookRecycleViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
        try {
            viewHolder.textView.setText(profilList.getJSONObject(i).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked()){
                    try {
                        deleted.add(profilList.getJSONObject(i).getInt("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else if(!((CheckBox) v).isChecked()){
                    try {
                        deleted.remove((Integer) profilList.getJSONObject(i).getInt("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.profilList.length();
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

    public ArrayList<Integer> getDeleted() {
        return deleted;
    }
}
