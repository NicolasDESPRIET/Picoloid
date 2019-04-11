package com.example.picoloid.source.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.picoloid.R;
import com.example.picoloid.source.adapter.DeleteBookRecycleViewAdapter;
import com.example.picoloid.source.managerData.JsonCreator;
import com.example.picoloid.source.managerData.JsonManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DeleteBookActivity extends AppCompatActivity {

    private JSONArray profiles;
    private ArrayList<Boolean> deleted;
    private DeleteBookRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);

        Button cancel = findViewById(R.id.cancelDel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initlist();

        Button validate = findViewById(R.id.validateDel);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDeleted();
                for ( int i = 0; i < profiles.length(); i++){
                    if (deleted.get(i)){
                        profiles.remove(i);
                    }
                }
                JsonCreator.saveAll(getApplicationContext(), profiles);
                Intent kk = new Intent(DeleteBookActivity.this, MainActivity.class);
                startActivity(kk);
            }
        });

    }

    private void initlist(){
        try {
            profiles = new JSONObject(JsonManager.readOnFile(this)).getJSONArray("book");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initRecycleView();
    }

    private void initRecycleView(){
        RecyclerView recyclerView = findViewById(R.id.delete_recycler);
        adapter = new DeleteBookRecycleViewAdapter(this, profiles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setDeleted(){
        this.deleted = adapter.getDeleted();
    }
}
