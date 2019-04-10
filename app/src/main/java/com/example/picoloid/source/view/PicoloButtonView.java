package com.example.picoloid.source.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import com.example.picoloid.source.activity.PageActivityUser;
import com.example.picoloid.source.model.PicoloButton;

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
        this.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClick();
            }
        });

        Log.d(TAG, "Loading view: "+buttonData.toString());
    }

    private void buttonClick(){
        Log.d(TAG, buttonData.getTitle()+" clicked.");

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
    }

    private void startVideoActivity(){
        Log.d(TAG, "startVideoActivity");
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
        Log.d(TAG, "openPage of id = "+buttonData.getId());

        Intent ii=new Intent(getContext(), PageActivityUser.class);
        ii.putExtra("pageId", buttonData.getPageId());
        getContext().startActivity(ii);
    }

    public PicoloButton getButtonData(){
        return buttonData;
    }
}
