package com.example.picoloid.source.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.picoloid.source.activity.ImageActivity;
import com.example.picoloid.source.activity.PageActivityUser;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonCoord;
import com.example.picoloid.source.service.ApplicationRuntimeInfos;
import com.example.picoloid.source.service.MediaPlayerService;

import java.net.URISyntaxException;

import static com.example.picoloid.source.service.MediaPlayerService.setContext;
import static com.example.picoloid.source.service.MediaPlayerService.startMediaPlayer;

public class PicoloButtonView extends AppCompatButton {

    public static final String TAG = "PicoloButtonView";

    private PicoloButton buttonData;

    public PicoloButtonView(Context context, final PicoloButton buttonData){
        super(context);

        this.buttonData = buttonData;
        this.setText(buttonData.getTitle());

        Log.d(TAG, "Loading view: "+buttonData.toString());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        buttonClickOnUser(event);
        return true;
    }


    private void buttonClickOnUser(MotionEvent event){
        Log.d(TAG, buttonData.getTitle()+" clicked.");

        if(event.getAction() != MotionEvent.ACTION_UP) return;

        switch(buttonData.getType()){
            case NONE:
                break;
            case IMAGE:
                startImageActivity();
                break;
            case VIDEO:
                startVideoActivity();
                break;
            case SOUND:
                startSoundPlaying();
                break;
            case PAGE:
                openPage();
                break;
        }
    }

    private void startImageActivity(){
        Log.d(TAG, "startImageActivity");

        Intent openImage = new Intent(getContext(), ImageActivity.class);
        openImage.putExtra("imagePath", buttonData.getImagePath().getPath());
        getContext().startActivity(openImage);
    }

    private void startVideoActivity(){
        Log.d(TAG, "startVideoActivity");

        Intent openVideo = new Intent(getContext(), ImageActivity.class);
        openVideo.putExtra("videoPath", buttonData.getSpecialPath().getPath());
        getContext().startActivity(openVideo);
    }

    private void startSoundPlaying(){
        Log.d(TAG, "startSoundPlaying");
    }

    private void openPage(){
        Log.d(TAG, "openPage of id = "+buttonData.getPageId());

        Intent openNewPage =new Intent(getContext(), PageActivityUser.class);
        openNewPage.putExtra("pageId", buttonData.getPageId());
        getContext().startActivity(openNewPage);
    }

    public PicoloButton getButtonData(){
        return buttonData;
    }

}
