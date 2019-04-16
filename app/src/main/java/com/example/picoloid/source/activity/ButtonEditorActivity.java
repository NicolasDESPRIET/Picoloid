package com.example.picoloid.source.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.picoloid.R;
import com.example.picoloid.source.managerData.JsonCreator;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonType;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.util.PicoloButtonUtils;
import com.example.picoloid.source.util.VideoPicker;

import static com.example.picoloid.source.view.TypeToRadioConverter.convertRadioButtonIdToState;
import static com.example.picoloid.source.view.TypeToRadioConverter.convertTypeToRadioButtonId;

public class ButtonEditorActivity extends AppCompatActivity {

    private static final String TAG = "ButtonEditorActivity";

    //objects
    private VideoPicker videoPicker;

    //data
    private PicoloButton currentButton;
    private int currentPageId;

    private Uri videoPath;
    private Uri imagePath;
    private Uri soundPath;

    //xml
    private EditText buttonTitle;
    private Button saveButton;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_editor);

        videoPicker = new VideoPicker(this);

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

        Button videoPickerButton = (Button) findViewById(R.id.buttonEditor_VideoPicker);
        videoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPicker.showPictureDialog();
            }
        });
    }

    private void saveAndQuit(){
        currentButton.setTitle(buttonTitle.getText().toString());
        saveType();

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

    private void saveType(){
        PicoloButtonType type = convertRadioButtonIdToState(radioGroup.getCheckedRadioButtonId());
        switch(type){
            case NONE:
                PicoloButtonUtils.switchButtonToNone(currentButton);
                break;
            case VIDEO:
                PicoloButtonUtils.switchButtonToVideo(currentButton,videoPath);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        videoPicker.onActivityResult(requestCode,resultCode,data);
    }

    public void setVideoPath(Uri path){
        videoPath = path;
    }

    public void setImagePath(Uri path){
        imagePath = path;
    }

    public void setSoundPath(Uri path){
        soundPath = path;
    }
}
