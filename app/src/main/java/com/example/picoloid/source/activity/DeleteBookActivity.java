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

    private static final String TAG = "DeleteBookActivity";

    //data
    private JSONArray profiles;
    private DeleteBookRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);

        initViews();
    }

    private void initViews(){
        initRecycleView();

        Button cancel = findViewById(R.id.deleteBook_CancelDeleteButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button validate = findViewById(R.id.delete_DeleteButton);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> list = adapter.getDeleted();

                for (int i = 0; i < profiles.length() ; i++) {
                    for (int j = 0; j< list.size(); j++){
                        try {
                            if ( profiles.getJSONObject(i).getInt("id") == list.get(j)){
                                profiles.remove(i);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                JsonCreator.saveDeleted(DeleteBookActivity.this, profiles);
                Intent kk = new Intent(DeleteBookActivity.this, MainActivity.class);
                startActivity(kk);
            }
        });
    }

    private void initRecycleView(){
        initList();

        RecyclerView recyclerView = findViewById(R.id.deleteBook_Recycler);
        adapter = new DeleteBookRecycleViewAdapter(this, profiles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initList(){
        try {
            profiles = new JSONObject(JsonManager.readOnFile(this)).getJSONArray("book");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
