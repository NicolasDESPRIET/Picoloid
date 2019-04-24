package com.example.picoloid.source.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.picoloid.R;
import com.example.picoloid.source.adapter.ListPageRecycleViewAdapter;
import com.example.picoloid.source.service.PicoloBookService;

public class ListPageActivity extends AppCompatActivity {

    private static final String TAG = "ListPageAct";

    boolean isFromButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);

        getIntentArgs();

        findViewById(R.id.closeListPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setRecycleViewAdapter();
    }

    private void setRecycleViewAdapter(){
        RecyclerView recyclerView = findViewById(R.id.listOfPageRecycleView);
        ListPageRecycleViewAdapter adapter = new ListPageRecycleViewAdapter (this, PicoloBookService.getBook().getPageList(), this, isFromButton);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getIntentArgs(){
        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        try{
            isFromButton = false;
            if (bundle != null) {
                isFromButton = (boolean)bundle.get("bool");
            }
        }catch (Exception e){
            finish();
        }
    }
}
