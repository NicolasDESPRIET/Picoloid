package com.example.picoloid.source.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import com.example.picoloid.source.model.PicoloButton;

public class PicoloButtonView extends AppCompatButton {
    public static final String TAG = "PicoloButtonView";

    private PicoloButton buttonData;

    public PicoloButtonView(Context context, final PicoloButton buttonData){
        super(context);
        this.buttonData = buttonData;

        this.setText(buttonData.getTitle());

        this.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "Button "+buttonData.getTitle()+" got clicked. Id = "+buttonData.getId());
                buttonClick();
            }
        });
    }

    private void buttonClick(){
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

    }

    private void startVideoActivity(){

    }

    private void startSoundPlaying(){

    }

    private void openPage(){

    }
}
