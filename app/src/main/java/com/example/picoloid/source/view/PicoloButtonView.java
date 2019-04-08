package com.example.picoloid.source.view;

import android.content.Context;
import android.content.Intent;
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

public class PicoloButtonView extends AppCompatButton {

    public static final String TAG = "PicoloButtonView";

    private PicoloButton buttonData;

    public PicoloButtonView(Context context, final PicoloButton buttonData){
        super(context);

        this.buttonData = buttonData;
        this.setText(buttonData.getTitle());

        Log.d(TAG, "Loading view: "+buttonData.toString());
    }

    float dX,dY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(ApplicationRuntimeInfos.isEdit){
            buttonClickOnEdit(event);
        }else{
            buttonClickOnUser(event);
        }
        return true;
    }

    private void buttonClickOnEdit(MotionEvent event){

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = this.getX() - event.getRawX();
                dY = this.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                this.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;

            default:
                break;
        }

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

    public int[] getCoordOnScreen(){
        int[] array = {(int)getX(),(int)getY()};
        return array;
    }
}
