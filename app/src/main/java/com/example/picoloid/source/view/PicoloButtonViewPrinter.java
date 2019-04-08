package com.example.picoloid.source.view;

import android.content.Context;
import android.text.Layout;
import android.widget.RelativeLayout;

import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonCoord;
import com.example.picoloid.source.model.PicoloPage;

import java.util.List;

public class PicoloButtonViewPrinter {

    PicoloPage currentPage;
    Context currentActivity;
    RelativeLayout currentActivityLayout;

    public PicoloButtonViewPrinter(PicoloPage page,
                                    Context ctxt,
                                    RelativeLayout layout){
        currentPage = page;
        currentActivity = ctxt;
        currentActivityLayout = layout;
    }

    public void showButtons(){
        List<PicoloButton> buttonList = currentPage.getButtonList();
        for(int i=0;i<buttonList.size();i++){
            showSingleButton(buttonList.get(i));
        }
    }

    private void showSingleButton(PicoloButton data){
        PicoloButtonView button = new PicoloButtonView(currentActivity,data);
        currentActivityLayout.addView(button,coordToLayoutParams(data.getCoord()));
    }

    private RelativeLayout.LayoutParams coordToLayoutParams(PicoloButtonCoord coord){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(coord.getWidth(), coord.getHeight());
        params.leftMargin = coord.getLeftMargin();
        params.topMargin = coord.getTopMargin();
        return params;
    }
}
