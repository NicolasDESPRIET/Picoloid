package com.example.picoloid.source.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.picoloid.R;


public class ImageActivity extends AppCompatActivity {

    private ImageView imageView;
    private static final String TAG = "ImageActivity";

    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = (ImageView)findViewById(R.id.IV);

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
        imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath));
    }

}

