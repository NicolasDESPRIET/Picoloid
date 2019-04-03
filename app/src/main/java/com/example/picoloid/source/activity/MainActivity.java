package com.example.picoloid.source.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.picoloid.R;
import com.example.picoloid.source.managerData.JsonManager;
import com.example.picoloid.source.model.PicoloBook;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.util.PicoloBookTest;
import com.example.picoloid.source.view.PicoloButtonView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PicoloBookService.setBook(PicoloBookTest.getTest());

        Button button = (Button)findViewById(R.id.openPageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(MainActivity.this, PageActivityUser.class);
                ii.putExtra("pageId", 0);
                startActivity(ii);
            }
        });
    }
}
