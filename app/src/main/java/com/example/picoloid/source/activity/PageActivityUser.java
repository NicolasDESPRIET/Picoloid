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
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.view.PicoloButtonView;

import java.util.List;

public class PageActivityUser extends AppCompatActivity {

    private static final String TAG = "PageActivityUser";

    private RelativeLayout layout;

    private PicoloPage currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_user);

        layout = (RelativeLayout)findViewById(R.id.pageActivityLayout_user);

        init();
        showButtons();

    }

    private void init(){
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null){
            int j =(int) b.get("pageId");
            Log.d(TAG, "pageId: "+j);

            currentPage = PicoloBookService.getBook().getPageList().get(0);
        }else{
            Toast.makeText(this,"Error loading book",Toast.LENGTH_SHORT);
            Intent returnToMain = new Intent(PageActivityUser.this, MainActivity.class);
            startActivity(returnToMain);
        }
    }

    private void showButtons(){
        List<PicoloButton> buttonList = currentPage.getButtonList();
        for(int i=0;i<buttonList.size();i++){
            Log.d(TAG, "showButtons: "+buttonList.get(i).getTitle());
            showSingleButton(buttonList.get(i));
        }
    }

    private void showSingleButton(PicoloButton data){
        Log.d(TAG, "showSingleButton: "+data.getCoord().toString());
        PicoloButtonView button = new PicoloButtonView(this,data);
        layout.addView(button,coordToLayoutParams(data.getCoord()));
    }

    private RelativeLayout.LayoutParams coordToLayoutParams(PicoloButtonCoord coord){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(coord.getWidth(), coord.getHeight());
        params.leftMargin = coord.getLeftMargin();
        params.topMargin = coord.getTopMargin();
        return params;
    }

}
