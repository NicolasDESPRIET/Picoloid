package com.example.picoloid.source.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.picoloid.R;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.view.PicoloButtonViewPrinter;

public class ButtonEditorActivity extends AppCompatActivity {

    private static final String TAG = "ButtonEditorActivity";

    private PicoloButton currentButton;

    private EditText buttonTitle;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_editor);

        getIntentArgs();
        initViews();
    }

    private void initViews(){
        buttonTitle = (EditText) findViewById(R.id.buttoneditor_title);
        buttonTitle.setText(currentButton.getTitle());

        saveButton = (Button) findViewById(R.id.buttoneditor_savebutton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentButton.setTitle(buttonTitle.getText().toString());
                finish();
            }
        });
    }

    private void getIntentArgs(){
        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        try{
            int pid = (int)bundle.get("pageId");
            int bid = (int)bundle.get("buttonId");
            Log.d(TAG, "getIntentArgs: pageid and buttonid : "+pid+ " " +bid);
            currentButton = PicoloBookService.getBook().getPageFromId(pid).getButtonFromId(bid);
            Log.d(TAG, "getIntentArgs: currentbutton :"+currentButton);
        }catch (Exception e){
            Log.d(TAG, "init: coulnd't load page");
            finish();
        }
    }


}
