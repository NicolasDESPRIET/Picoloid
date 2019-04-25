package com.example.picoloid.source.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.MotionEvent;

import com.example.picoloid.source.activity.ImageActivity;
import com.example.picoloid.source.activity.PageActivityUser;
import com.example.picoloid.source.activity.VideoPlayerActivity;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.service.MediaPlayerService;

import java.net.URISyntaxException;


//Main view of the app : inherits from android Button and is associated with a PicoloButton data structure
public class PicoloButtonView extends AppCompatButton {

    public static final String TAG = "PicoloButtonView";

    private PicoloButton buttonData;

    public PicoloButtonView(Context context, final PicoloButton buttonData){
        super(context);

        this.buttonData = buttonData;
        this.setText(buttonData.getTitle());
        
        this.setFocusable(true);
        this.setClickable(true);

        this.setContentDescription(buttonData.getTitle());

        try{
            BitmapDrawable bdrawable = new BitmapDrawable(context.getResources(),BitmapFactory.decodeFile(buttonData.getImagePath().toString()));
            this.setBackground(bdrawable);
        }catch(Exception e){
            //put default image
        }
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
        if(buttonData.getImagePath() == null){
            return;
        }
        Intent openImage = new Intent(getContext(), ImageActivity.class);
        openImage.putExtra("imagePath", buttonData.getImagePath().getPath());
        getContext().startActivity(openImage);
    }

    private void startVideoActivity(){
        if(buttonData.getSpecialPath() == null){
            Log.d(TAG, "startVideoActivity: path null");
            return;
        }
        Intent openVideo = new Intent(getContext(), VideoPlayerActivity.class);
        openVideo.putExtra("videoPath", buttonData.getSpecialPath().getPath());
        getContext().startActivity(openVideo);
    }

    private void startSoundPlaying(){
        if(buttonData.getSpecialPath() == null){
            return;
        }
        Uri son = buttonData.getSpecialPath();
        try {
            MediaPlayerService.startMediaPlayer(son);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void openPage(){
        MediaPlayerService.pauseMediaPlayer();

        Intent openNewPage =new Intent(getContext(), PageActivityUser.class);
        openNewPage.putExtra("pageId", buttonData.getPageId());
        getContext().startActivity(openNewPage);
    }

    public PicoloButton getButtonData(){
        return buttonData;
    }

}
