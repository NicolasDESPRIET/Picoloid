package com.example.picoloid.source.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.picoloid.R;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.view.PicoloButtonView;

public class PageActivityEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_editor);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        String j =(String) b.get("name");


        PicoloButton buttonData = new PicoloButton();
        buttonData.setTitle(j);

        PicoloButtonView button = new PicoloButtonView(getApplicationContext(),buttonData);
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.editor_relative_layout);
        rl.addView(button);
    }
}
