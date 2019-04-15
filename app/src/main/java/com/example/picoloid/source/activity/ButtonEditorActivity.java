package com.example.picoloid.source.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.picoloid.R;
import com.example.picoloid.source.managerData.JsonCreator;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonType;
import com.example.picoloid.source.service.PicoloBookService;

import static com.example.picoloid.source.model.PicoloButtonType.IMAGE;
import static com.example.picoloid.source.model.PicoloButtonType.NONE;
import static com.example.picoloid.source.model.PicoloButtonType.PAGE;
import static com.example.picoloid.source.model.PicoloButtonType.SOUND;
import static com.example.picoloid.source.model.PicoloButtonType.VIDEO;

public class ButtonEditorActivity extends AppCompatActivity {

    private static final String TAG = "ButtonEditorActivity";

    //data
    private PicoloButton currentButton;
    private int currentPageId;

    //xml
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
        buttonTitle = (EditText) findViewById(R.id.buttonEditor_ButtonTitle);
        buttonTitle.setText(currentButton.getTitle());

        saveButton = (Button) findViewById(R.id.buttonEditor_SaveChanges);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndQuit();
            }
        });

        radioGroup = (RadioGroup)findViewById(R.id.buttonEditor_ButtonTypeRadioGroup);
        radioGroup.check(convertTypeToRadioButtonId(currentButton.getType()));
    }

    private void saveAndQuit(){
        currentButton.setTitle(buttonTitle.getText().toString());
        currentButton.setType(convertRadioButtonIdToState(radioGroup.getCheckedRadioButtonId()));

        JsonCreator.save(getApplicationContext());

        Intent returnToEditor = new Intent(getApplicationContext(), PageActivityEditor.class);
        returnToEditor.putExtra("pageId",currentPageId);
        startActivity(returnToEditor);
        finish();
    }

    private void getIntentArgs(){
        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        try{
            currentPageId = (int)bundle.get("pageId");
            int bid = (int)bundle.get("buttonId");
            currentButton = PicoloBookService.getBook().getPageFromId(currentPageId).getButtonFromId(bid);
        }catch (Exception e){
            finish();
        }
    }

    private PicoloButtonType convertRadioButtonIdToState(int id){
        switch(id){
            case R.id.buttonEditor_Type_None:
                return NONE;
            case R.id.buttonEditor_Type_Image:
                return IMAGE;
            case R.id.buttonEditor_Type_Video:
                return VIDEO;
            case R.id.buttonEditor_Type_Sound:
                return SOUND;
            case R.id.buttonEditor_Type_Page:
                return PAGE;
            default:
                return NONE;
        }
    }

    private int convertTypeToRadioButtonId(PicoloButtonType type){
        switch(type){
            case NONE:
                return R.id.buttonEditor_Type_None;
            case IMAGE:
                return R.id.buttonEditor_Type_Image;
            case VIDEO:
                return R.id.buttonEditor_Type_Video;
            case SOUND:
                return R.id.buttonEditor_Type_Sound;
            case PAGE:
                return R.id.buttonEditor_Type_Page;
            default:
                return R.id.buttonEditor_Type_None;
        }
    }
}
