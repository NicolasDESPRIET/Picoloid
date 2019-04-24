package com.example.picoloid.source.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.picoloid.R;
import com.example.picoloid.source.adapter.DeletePageRecycleViewAdapter;
import com.example.picoloid.source.managerData.JsonCreator;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.service.PicoloBookService;

import java.util.ArrayList;

public class DeletePageActivity extends AppCompatActivity {

    private DeletePageRecycleViewAdapter adapter;
    private ArrayList<PicoloPage> picoloPageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_page);

        initViews();
    }

    private void initViews(){
        initRecycleView();

        Button cancel = findViewById(R.id.deletePage_CancelDeleteButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button validate = findViewById(R.id.deletePage_DeleteButton);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> listPageToDelete = adapter.getArrayList();
                for (int i = 0; i<listPageToDelete.size(); i++){
                    for (int j = 0; j < PicoloBookService.getBook().getPageList().size(); j++){
                        if (PicoloBookService.getBook().getPageList().get(j).getId() == listPageToDelete.get(i)){
                            PicoloBookService.getBook().getPageList().remove(j);
                        }
                    }
                }
                JsonCreator.save(getApplicationContext());
                Intent ii = new Intent(DeletePageActivity.this, PageActivityUser.class);
                ii.putExtra("pageId",0);
                startActivity(ii);
            }

        });
    }

    private void initList(){
        for (int i = 0; i < PicoloBookService.getBook().getPageList().size(); i++){
            if (PicoloBookService.getBook().getPageList().get(i).getId() != 0){
                picoloPageList.add(PicoloBookService.getBook().getPageList().get(i));
            }
        }
    }

    private void initRecycleView(){
        initList();

        RecyclerView recyclerView = findViewById(R.id.deletePage_Recycler);
        adapter = new DeletePageRecycleViewAdapter(this, picoloPageList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}
