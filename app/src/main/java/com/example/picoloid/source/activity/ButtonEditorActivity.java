package com.example.picoloid.source.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.picoloid.R;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonType;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.view.PicoloButtonViewPrinter;

import static com.example.picoloid.source.model.PicoloButtonType.IMAGE;
import static com.example.picoloid.source.model.PicoloButtonType.NONE;
import static com.example.picoloid.source.model.PicoloButtonType.PAGE;
import static com.example.picoloid.source.model.PicoloButtonType.SOUND;
import static com.example.picoloid.source.model.PicoloButtonType.VIDEO;

public class ButtonEditorActivity extends AppCompatActivity {

    private static final String TAG = "ButtonEditorActivity";

    private PicoloButton currentButton;

    private EditText buttonTitle;
    private Button saveButton;
    private RadioGroup radioGroup;

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

        radioGroup = (RadioGroup)findViewById(R.id.buttoneditor_radiogroup);
        radioGroup.check(convertTypeToRadioButtonId(currentButton.getType()));
    }

    private int convertRadioButtonIdToState(int id){
        switch(id){
            case R.id.btnedit_none:
                return 0;
            case R.id.btnedit_image:
                return 1;
            case R.id.btnedit_video:
                return 2;
            case R.id.btnedit_sound:
                return 3;
            case R.id.btnedit_page:
                return 4;
            default:
                return 0;
        }
    }

    private int convertTypeToRadioButtonId(PicoloButtonType type){
        switch(type){
            case NONE:
                return R.id.btnedit_none;
            case IMAGE:
                return R.id.btnedit_image;
            case VIDEO:
                return R.id.btnedit_video;
            case SOUND:
                return R.id.btnedit_sound;
            case PAGE:
                return R.id.btnedit_page;
            default:
                return R.id.btnedit_none;
        }
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
