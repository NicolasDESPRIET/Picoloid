package com.example.picoloid.source.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.picoloid.R;


public class ImageActivity extends AppCompatActivity {

    private static final String TAG = "ImageActivity";

    //data
    private String imagePath;

    //xml
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        getArgs();
        initViews();
    }

    private void initViews(){
        imageView = (ImageView)findViewById(R.id.imageActivity_ImageView);
        imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));

        Button goBackButton = (Button)findViewById(R.id.imageActivity_GoBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getArgs(){
        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        try{
            imagePath = (String)bundle.get("imagePath");
        }catch (Exception e){
            Log.d(TAG, "couldn't load image");
            finish();
        }
    }
}

