package com.example.picoloid.source.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.picoloid.R;
import com.example.picoloid.source.adapter.ListPageRecycleViewAdapter;
import com.example.picoloid.source.service.PicoloBookService;

public class ListPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);

        setRecycleViewAdapter();
    }

    private void setRecycleViewAdapter(){
        RecyclerView recyclerView = findViewById(R.id.listOfPageRecycleView);
        ListPageRecycleViewAdapter adapter = new ListPageRecycleViewAdapter (this, PicoloBookService.getBook().getPageList());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
