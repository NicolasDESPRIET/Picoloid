package com.example.picoloid.source.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.picoloid.R;
import com.example.picoloid.source.activity.PageActivityEditor;
import com.example.picoloid.source.activity.PageActivityUser;
import com.example.picoloid.source.managerData.ObjectManager;
import com.example.picoloid.source.service.PicoloBookService;

import org.json.JSONArray;
import org.json.JSONException;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private Context context;
    private JSONArray profilList = new JSONArray();

    public RecycleViewAdapter(Context context, JSONArray profilList) {
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
        try {
            viewHolder.textView.setText(profilList.getJSONObject(position).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PicoloBookService.setBook(ObjectManager.loadPicoloBookfromJson(profilList.getJSONObject(position)));
                    Intent ii = new Intent(context, PageActivityUser.class);
                    ii.putExtra("pageId",0);
                    context.startActivity(ii);

                    //Toast.makeText(context, profilList.getJSONObject(position).getString("name"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return profilList.length();
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
