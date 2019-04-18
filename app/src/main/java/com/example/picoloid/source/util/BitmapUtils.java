package com.example.picoloid.source.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtils {

    public static int getHeightFromResolution(String path,int width){
        int w,h;
        if(path!=null) {

            Bitmap bitmap= BitmapFactory.decodeFile(path);
            w=bitmap.getWidth();
            h=bitmap.getHeight();
            float ratio=((float)h/(float)w);
            float newHeight =width*ratio;

            int height=Math.round(newHeight);

            return(height);
        }
        else{
            return(width);
        }
    }

}
