package util;

import android.support.annotation.NonNull;

import java.net.URI;

import model.PicoloButton;
import model.PicoloButtonType;

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

    public static void switchButtonToVideo(@NonNull PicoloButton button, URI videoPath){
        button.setPageId(-1);
        button.setSpecialPath(videoPath);
        button.setType(PicoloButtonType.VIDEO);
    }

    public static void switchButtonToSound(@NonNull PicoloButton button, URI soundPath){
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