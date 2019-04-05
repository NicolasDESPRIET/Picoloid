package com.example.picoloid.source.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.picoloid.R;
import com.example.picoloid.source.adapter.RecycleViewAdapter;
import com.example.picoloid.source.managerData.JsonManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.example.picoloid.R.id.recycler_view;

public class MainActivity extends AppCompatActivity {

    private JSONArray profils = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*try {
            PicoloBookService.setBook(ObjectManager.loadBookAssetsmod(
                    "Theo",
                    this,
                    "test"
            ));
            Button button = findViewById(R.id.openPageButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ii=new Intent(MainActivity.this, PageActivityUser.class);
                    ii.putExtra("pageId", 0);
                    startActivity(ii);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        initlist();
    }

    private void initlist(){
        //TODO a modifier pour initialiser correctement l'app
        //Toast.makeText(this, this.getFilesDir().getPath(), Toast.LENGTH_LONG).show();
        try {
            JsonManager.InitFile(JsonManager.readJsonFromAsset(this,"jsonProfil.json"),this);
            String test = JsonManager.readOnFile(this);
            Toast.makeText(this, test, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            profils = new JSONObject(JsonManager.readJsonFromAsset(this,"test")).getJSONArray("book");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initRecycleView();
    }

    private void initRecycleView(){
        RecyclerView recyclerView = findViewById(recycler_view);
        RecycleViewAdapter adapter = new RecycleViewAdapter(this, profils);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
