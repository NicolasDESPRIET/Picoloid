package com.example.picoloid.source.service;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.net.URISyntaxException;

public class MediaPlayerService {
    private static final String TAG = "MediaPlayerService";

    private MediaPlayer mediaPlayer;
    private Context context;
    private MediaPlayerService() {
        mediaPlayer = null;
        context = null;
    }


    private static class MediaPlayerServiceHolder {
        private static final MediaPlayerService INSTANCE = new MediaPlayerService();
    }

    public static void setContext(Context context){
        Log.d(TAG, "setContext: context set");
        MediaPlayerServiceHolder.INSTANCE.context = context;
    }

    public static void startMediaPlayer(Uri son)throws URISyntaxException{
        Log.d(TAG, "startMediaPlayer: mediaplayer started");
        Log.d(TAG, "startMediaPlayer: "+son);
        if(MediaPlayerServiceHolder.INSTANCE.mediaPlayer!=null){
            MediaPlayerServiceHolder.INSTANCE.mediaPlayer.stop();
        }
        if(MediaPlayerServiceHolder.INSTANCE.context!=null) {
            MediaPlayerServiceHolder.INSTANCE.mediaPlayer = MediaPlayer.create(MediaPlayerServiceHolder.INSTANCE.context, son);
        }
        MediaPlayerServiceHolder.INSTANCE.mediaPlayer.start();

    }

    public static void pauseMediaPlayer(){
        try{
            MediaPlayerServiceHolder.INSTANCE.mediaPlayer.pause();
        }catch(Exception e){
        }
    }

}
