package com.example.picoloid.source.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.picoloid.R;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonCoord;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.service.ApplicationRuntimeInfos;
import com.example.picoloid.source.service.MediaPlayerService;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.view.PicoloButtonView;

import java.util.List;

public class PageActivityUser extends AppCompatActivity {

    private static final String TAG = "PageActivityUser";

    private RelativeLayout buttonLayout;

    private PicoloPage currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_user);

        ApplicationRuntimeInfos.isEdit = false;

        buttonLayout = (RelativeLayout)findViewById(R.id.buttonLayout_User);

        getIntentArgs();
        showButtons();
    }

    private void getIntentArgs(){
        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        try{
            int id = (int)bundle.get("pageId");
            currentPage = PicoloBookService.getBook().getPage(id);
        }catch (Exception e){
            Log.d(TAG, "init: coulnd't load page");
            finish();
        }
    }

    private void showButtons(){
        List<PicoloButton> buttonList = currentPage.getButtonList();
        for(int i=0;i<buttonList.size();i++){
            showSingleButton(buttonList.get(i));
        }
    }

    private void showSingleButton(PicoloButton data){
        PicoloButtonView button = new PicoloButtonView(this,data);
        buttonLayout.addView(button,coordToLayoutParams(data.getCoord()));
    }

    private RelativeLayout.LayoutParams coordToLayoutParams(PicoloButtonCoord coord){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(coord.getWidth(), coord.getHeight());
        params.leftMargin = coord.getLeftMargin();
        params.topMargin = coord.getTopMargin();
        return params;
    }

}
