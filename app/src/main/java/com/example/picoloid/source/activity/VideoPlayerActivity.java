package com.example.picoloid.source.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import android.widget.MediaController;

import com.example.picoloid.R;
import com.example.picoloid.source.service.PicoloBookService;

public class VideoPlayerActivity extends AppCompatActivity {

    private static final String TAG = "VideoPlayerActivity";

    //data
    private String videoPath;
    private MediaController meController;

    //xml
    private VideoView videoView;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        try{
            getIntentArgs();
            initViews();
        }catch(Exception e){
            e.printStackTrace();
            finish();
        }
    }

    private void initViews(){

        layout = findViewById(R.id.videolayout);
        layout.setBackgroundColor(PicoloBookService.getBook().getSettings().getBackgroundColor());
        videoView = findViewById(R.id.videoActivity_VideoView);
        meController = new MediaController(this);
        videoView.setVideoPath(videoPath);
        meController.setAnchorView(videoView);
        videoView.setMediaController(meController);
        videoView.start();

        Button goBackButton = findViewById(R.id.videoActivity_GoBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIntentArgs(){
        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        if (bundle != null) {
            videoPath = (String)bundle.get("videoPath");
        }
    }
}
