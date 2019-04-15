package com.example.picoloid.source.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.MotionEvent;

import com.example.picoloid.source.activity.ImageActivity;
import com.example.picoloid.source.activity.PageActivityUser;
import com.example.picoloid.source.model.PicoloButton;

import java.net.URISyntaxException;

import static com.example.picoloid.source.service.MediaPlayerService.setContext;
import static com.example.picoloid.source.service.MediaPlayerService.startMediaPlayer;

//import com.example.picoloid.source.service.MediaPlayerService;

public class PicoloButtonView extends AppCompatButton {

    public static final String TAG = "PicoloButtonView";

    private PicoloButton buttonData;

    public PicoloButtonView(Context context, final PicoloButton buttonData){
        super(context);

        this.buttonData = buttonData;
        this.setText(buttonData.getTitle());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        buttonClickOnUser(event);
        return true;
    }

    private void buttonClickOnUser(MotionEvent event){
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
        Intent openImage = new Intent(getContext(), ImageActivity.class);
        openImage.putExtra("imagePath", buttonData.getImagePath().getPath());
        getContext().startActivity(openImage);
    }

    private void startVideoActivity(){
        Intent openVideo = new Intent(getContext(), ImageActivity.class);
        openVideo.putExtra("videoPath", buttonData.getSpecialPath().getPath());
        getContext().startActivity(openVideo);
    }

    private void startSoundPlaying(){
        Log.d(TAG, "startSoundPlaying");
        if(buttonData.getSpecialPath()!=null) {
            Uri son = buttonData.getSpecialPath();
            setContext(getContext());
            try {
                startMediaPlayer(son);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    private void openPage(){
        Intent openNewPage =new Intent(getContext(), PageActivityUser.class);
        openNewPage.putExtra("pageId", buttonData.getPageId());
        getContext().startActivity(openNewPage);
    }

    public PicoloButton getButtonData(){
        return buttonData;
    }

}
