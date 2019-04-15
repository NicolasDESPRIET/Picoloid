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

    private ImageView imageview;
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
        Bitmap myBitmap = BitmapFactory.decodeFile(imagePath);

        ImageView myImage = (ImageView) findViewById(R.id.IV);

        myImage.setImageBitmap(myBitmap);
    }

}

