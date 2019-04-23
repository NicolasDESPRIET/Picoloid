package com.example.picoloid.source.dialog;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.picoloid.source.activity.ButtonEditorActivity;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class SoundPicker {

    private static final String TAG = "SoundPicker";

    private ButtonEditorActivity activity;
    private static final String VIDEO_DIRECTORY = "/PicoloRessources";
    private int GALLERY = 5;

    public SoundPicker(ButtonEditorActivity activity){
        this.activity = activity;
    }

    public void showSoundDialog(){
        //this is supposed to open a dialog where user chooses between recording
        //or picking sound. Recording not implemented so we jump to galery picking directly
        chooseSoundFromGallary();
    }

    private void chooseSoundFromGallary(){
        Intent videoIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(Intent.createChooser(videoIntent, "Select Audio"), GALLERY);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){

        if(requestCode == GALLERY){

            if(resultCode == activity.RESULT_OK){

                //the selected audio.
                Uri uri = data.getData();
                String path = getPath(uri);
                activity.setSoundPath(path);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Audio.Media.DATA };
        Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

}

