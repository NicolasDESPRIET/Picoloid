package com.example.picoloid.source.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.RelativeLayout;

import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonCoord;
import com.example.picoloid.source.model.PicoloPage;

import java.util.ArrayList;
import java.util.List;

//Simple objects used in both PageActivities to print the buttons on screen
//Only needs a layout and a picolopage to work
public class PicoloButtonViewPrinter {

    private static final String TAG = "PicoloPrinter";

    PicoloPage picoloPage;
    Context context;
    RelativeLayout layout;

    ArrayList<PicoloButtonEditView> editButtonList;

    public PicoloButtonViewPrinter(PicoloPage page, Context ctxt, RelativeLayout layout){
        picoloPage = page;
        context = ctxt;
        this.layout = layout;

        editButtonList = new ArrayList<PicoloButtonEditView>();
    }

    public void showButtons(String buttonType){
        List<PicoloButton> buttonList = picoloPage.getButtonList();
        for(int i=0;i<buttonList.size();i++){
            switch(buttonType){
                case "user":
                    showSingleButton(buttonList.get(i));
                    break;
                case "edit":
                    showSingleEditButton(buttonList.get(i));
                    break;
            }
        }
    }

    private void showSingleButton(PicoloButton data){
        PicoloButtonView button = new PicoloButtonView(context,data);
        layout.addView(button,coordToLayoutParams(data));
    }

    private void showSingleEditButton(PicoloButton data){
        PicoloButtonEditView button = new PicoloButtonEditView(context,data);
        editButtonList.add(button);
        layout.addView(button,coordToLayoutParams(data));
    }

    //transforms PicoloButtonCoord into android view parameters
    private RelativeLayout.LayoutParams coordToLayoutParams(PicoloButton data){
        PicoloButtonCoord coord = data.getCoord();
        int height = coord.getHeight();
        try{
            height = calculateHeightFromWidth(data.getImagePath().getPath(),coord.getWidth());
        }catch(Exception e){

        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(coord.getWidth(), height);
        params.leftMargin = coord.getLeftMargin();
        params.topMargin = coord.getTopMargin();
        return params;
    }

    //so that the button fits its image's dimension
    public int calculateHeightFromWidth(String path,int width){
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

    public List<PicoloButtonEditView> getEditButtonList(){
        return editButtonList;
    }
}
