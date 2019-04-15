package com.example.picoloid.source.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import com.example.picoloid.R;
import com.example.picoloid.source.adapter.MainRecycleViewAdapter;
import com.example.picoloid.source.managerData.JsonManager;
import com.example.picoloid.source.service.ApplicationRuntimeInfos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //data
    private JSONArray profiles = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateScreenSize(); //sets screen size for future button placement

        initViews();
    }

    private void initViews(){
        initRecycleView();

        Button openSettingsPage = findViewById(R.id.main_CreateBookButton);
        openSettingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewBook();
            }
        });

        Button openDeleteAc = findViewById(R.id.main_DeleteBookButton);
        openDeleteAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBook();
            }
        });
    }

    private void createNewBook(){
        Intent ii=new Intent(MainActivity.this, SettingsActivity.class);
        ii.putExtra("bookId", getLastId()+1);
        ii.putExtra("mod", "new");
        startActivity(ii);
    }

    private void deleteBook(){
        Intent jj = new Intent(MainActivity.this, DeleteBookActivity.class);
        startActivity(jj);
    }

    private void initRecycleView(){
        try {
            initlist();
            setRecycleViewAdapter();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            profiles = new JSONObject(JsonManager.readOnFile(this)).getJSONArray("book");
            Log.d(TAG,  profiles.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initlist() throws IOException,JSONException{
        JsonManager.InitFile(JsonManager.readJsonFromAsset(this,"jsonProfil.json"),this);
        profiles = new JSONObject(JsonManager.readOnFile(this)).getJSONArray("book");
    }

    private void setRecycleViewAdapter(){
        RecyclerView recyclerView = findViewById(R.id.main_RecyclerView);
        MainRecycleViewAdapter adapter = new MainRecycleViewAdapter(this, profiles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private int getLastId(){
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
