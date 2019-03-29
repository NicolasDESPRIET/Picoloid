package com.example.picoloid.source.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.picoloid.R;

public class PageActivityUser extends AppCompatActivity {

    private static final String TAG = "PageActivityUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_user);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            int j =(int) b.get("pageId");
            Log.d(TAG, "onCreate: "+j);
        }
    }
}
