package com.example.picoloid.source.util;

import android.net.Uri;
import android.support.annotation.NonNull;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonType;

public class PicoloButtonUtils {

    public static void switchButtonToNone(@NonNull PicoloButton button){
        button.setPageId(-1);
        button.setSpecialPath(null);
        button.setType(PicoloButtonType.NONE);
    }

    public static void switchButtonToImage(@NonNull PicoloButton button){
        button.setPageId(-1);
        button.setSpecialPath(null);
        button.setType(PicoloButtonType.IMAGE);
    }

    public static void switchButtonToVideo(@NonNull PicoloButton button, Uri videoPath){
        button.setPageId(-1);
        button.setSpecialPath(videoPath);
        button.setType(PicoloButtonType.VIDEO);
    }

    public static void switchButtonToSound(@NonNull PicoloButton button, Uri soundPath){
        button.setPageId(-1);
        button.setSpecialPath(soundPath);
        button.setType(PicoloButtonType.SOUND);
    }

    public static void switchButtonToPage(@NonNull PicoloButton button, int pageId){
        button.setPageId(pageId);
        button.setSpecialPath(null);
        button.setType(PicoloButtonType.PAGE);
    }

}