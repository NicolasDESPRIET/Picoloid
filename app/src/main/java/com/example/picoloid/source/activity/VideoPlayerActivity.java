package com.example.picoloid.source.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;
import android.widget.MediaController;

import com.example.picoloid.R;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;

    private static final String TAG = "VideoPlayerActivity";

    MediaController meController;

    private String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        Log.d(TAG, "onCreate: Video act opened");

        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        try{
            videoPath = (String)bundle.get("videoPath");
            showVideo();
        }catch (Exception e){
            Log.d(TAG, "couldn't load video");
            e.printStackTrace();
            finish();
        }
    }

    private void showVideo() throws Exception{

        videoView = (VideoView) findViewById(R.id.vView);
        meController = new MediaController(this);
        videoView.setVideoPath(videoPath);
        meController.setAnchorView(videoView);
        videoView.setMediaController(meController);
        videoView.start();
    }

    public void onClick(View v) {
        finish();
    }
}
