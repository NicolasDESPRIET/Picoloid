package com.example.picoloid.source.util;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonType;

public class PicoloButtonUtils {
    
    private static final String TAG = "ButtonUtils";

    public static void switchButtonToNone(@NonNull PicoloButton button){

        Log.d(TAG, "switchButtonToNone: ");
        button.setPageId(-1);
        button.setSpecialPath(null);
        button.setType(PicoloButtonType.NONE);
    }

    public static void switchButtonToImage(@NonNull PicoloButton button){

        Log.d(TAG, "switchButtonToImage: ");
        button.setPageId(-1);
        button.setSpecialPath(null);
        button.setType(PicoloButtonType.IMAGE);
    }

    public static void switchButtonToVideo(@NonNull PicoloButton button, Uri videoPath){

        Log.d(TAG, "switchButtonToVideo: ");
        button.setPageId(-1);
        button.setSpecialPath(videoPath);
        button.setType(PicoloButtonType.VIDEO);
    }

    public static void switchButtonToSound(@NonNull PicoloButton button, Uri soundPath){

        Log.d(TAG, "switchButtonToSound: ");
        button.setPageId(-1);
        button.setSpecialPath(soundPath);
        button.setType(PicoloButtonType.SOUND);
    }

    public static void switchButtonToPage(@NonNull PicoloButton button, int pageId){

        Log.d(TAG, "switchButtonToPage: ");
        button.setPageId(pageId);
        button.setSpecialPath(null);
        button.setType(PicoloButtonType.PAGE);
    }

}