package com.example.picoloid.source.dialog;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.example.picoloid.source.activity.ButtonEditorActivity;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class SoundPicker {

    private ButtonEditorActivity activity;
    private static final String VIDEO_DIRECTORY = "/PicoloRessources";
    private int GALLERY = 5;

    public SoundPicker(ButtonEditorActivity activity){
        this.activity = activity;
    }

    public void showSoundDialog(){
        //this is supposed to open a dialog where user chooses between recording
        //or picking sound. Recording not implemented so we jump to galery picking directly
    }

    private void chooseSoundFromGallary(){

    }


}
