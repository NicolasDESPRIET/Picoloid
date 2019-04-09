package com.example.picoloid.source.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.picoloid.R;
import com.example.picoloid.source.service.PicoloBookService;

public class ImageActivity extends AppCompatActivity {

    private static final String TAG = "ImageActivity";

    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        try{
            imagePath = (String)bundle.get("imagePath");
            showImage();
        }catch (Exception e){
            Log.d(TAG, "couldn't load image");
            finish();
        }
    }

    private void showImage() throws Exception{

    }
}
