package com.example.picoloid.source.view;

import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonCoord;
import com.example.picoloid.source.model.PicoloPage;

import java.util.ArrayList;
import java.util.List;

public class PicoloButtonViewPrinter {

    private static final String TAG = "picoloPrinter";

    PicoloPage currentPage;
    Context currentActivity;
    RelativeLayout currentActivityLayout;

    ArrayList<PicoloButtonView> buttonList;
    ArrayList<PicoloButtonEditView> editButtonList;

    public PicoloButtonViewPrinter(PicoloPage page, Context ctxt, RelativeLayout layout){
        currentPage = page;
        currentActivity = ctxt;
        currentActivityLayout = layout;

        buttonList = new ArrayList<PicoloButtonView>();
        editButtonList = new ArrayList<PicoloButtonEditView>();
    }

    public void showButtons(String buttonType){
        Log.d(TAG, "showButtons: PRINT");
        List<PicoloButton> buttonList = currentPage.getButtonList();
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
        PicoloButtonView button = new PicoloButtonView(currentActivity,data);
        buttonList.add(button);
        currentActivityLayout.addView(button,coordToLayoutParams(data.getCoord()));
    }

    private void showSingleEditButton(PicoloButton data){
        PicoloButtonEditView button = new PicoloButtonEditView(currentActivity,data);
        editButtonList.add(button);
        currentActivityLayout.addView(button,coordToLayoutParams(data.getCoord()));
    }

    private RelativeLayout.LayoutParams coordToLayoutParams(PicoloButtonCoord coord){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(coord.getWidth(), coord.getHeight());
        params.leftMargin = coord.getLeftMargin();
        params.topMargin = coord.getTopMargin();
        return params;
    }

    public List<PicoloButtonView> getButtonList(){
        return buttonList;
    }
    public List<PicoloButtonEditView> getEditButtonList(){
        return editButtonList;
    }
}
