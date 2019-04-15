package com.example.picoloid.source.service;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

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


    public static void setContext(Context ctxt){
        MediaPlayerServiceHolder.INSTANCE.mediaPlayer.stop();
        MediaPlayerServiceHolder.INSTANCE.context = ctxt;
    }

    public static void startMediaPlayer(Uri son)throws URISyntaxException{
        if(MediaPlayerServiceHolder.INSTANCE.mediaPlayer!=null){
            MediaPlayerServiceHolder.INSTANCE.mediaPlayer.stop();
        }
        if (MediaPlayerServiceHolder.INSTANCE.context != null){
            MediaPlayerServiceHolder.INSTANCE.mediaPlayer =  MediaPlayer.create(MediaPlayerServiceHolder.INSTANCE.context ,son);
        }
        MediaPlayerServiceHolder.INSTANCE.mediaPlayer.start();

    }

    public static void pauseMediaPlayer(){
        MediaPlayerServiceHolder.INSTANCE.mediaPlayer.pause();
    }

}
