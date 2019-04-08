package com.example.picoloid.source.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.picoloid.R;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonCoord;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.service.ApplicationRuntimeInfos;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.view.PicoloButtonView;
import com.example.picoloid.source.view.PicoloButtonViewPrinter;

import java.util.List;

public class PageActivityEditor extends AppCompatActivity {

    private static final String TAG = "PageActivityEdit";

    private RelativeLayout buttonLayout;

    private PicoloPage currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_editor);

        ApplicationRuntimeInfos.isEdit = true;

        buttonLayout = (RelativeLayout)findViewById(R.id.buttonLayout_Edit);

        getIntentArgs();

        PicoloButtonViewPrinter printer = new PicoloButtonViewPrinter(
                currentPage,
                this,
                buttonLayout
        );
        printer.showButtons();
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



}
