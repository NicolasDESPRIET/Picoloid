package com.example.picoloid.source.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import com.example.picoloid.R;
import com.example.picoloid.source.adapter.RecycleViewAdapter;
import com.example.picoloid.source.managerData.JsonManager;
import com.example.picoloid.source.service.ApplicationRuntimeInfos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private JSONArray profiles = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateScreenSize();
        initlist();

        Button button = findViewById(R.id.openPageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(MainActivity.this, SettingsActivity.class);
                ii.putExtra("bookId", getBiggerId());
                ii.putExtra("mod", "new");
                startActivity(ii);
            }
        });
    }

    private void initlist(){
        //Toast.makeText(this, JsonManager.readOnFile(this), Toast.LENGTH_SHORT).show();
        try {
            JsonManager.InitFile(JsonManager.readJsonFromAsset(this,"jsonProfil.json"),this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            profiles = new JSONObject(JsonManager.readOnFile(this)).getJSONArray("book");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initRecycleView();
    }

    private void initRecycleView(){
        RecyclerView recyclerView = findViewById(R.id.main_recycler);
        RecycleViewAdapter adapter = new RecycleViewAdapter(this, profiles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private int getBiggerId(){
        int id = 0;
        for(int i = 0; i < profiles.length(); i++){
            try {
                if (profiles.getJSONObject(i).getInt("id" )>id){
                    id = profiles.getJSONObject(i).getInt("id" );
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    private void calculateScreenSize(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        ApplicationRuntimeInfos.screenWidth = size.x;
        ApplicationRuntimeInfos.screenHeight = size.y;
    }
}
