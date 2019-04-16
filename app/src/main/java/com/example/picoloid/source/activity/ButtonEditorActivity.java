package com.example.picoloid.source.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.picoloid.R;
import com.example.picoloid.source.managerData.JsonCreator;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonType;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.util.ImagePicker;
import com.example.picoloid.source.util.PicoloButtonUtils;
import com.example.picoloid.source.util.VideoPicker;

import static com.example.picoloid.source.model.PicoloButtonType.NONE;
import static com.example.picoloid.source.view.TypeToRadioConverter.convertRadioButtonIdToState;
import static com.example.picoloid.source.view.TypeToRadioConverter.convertTypeToRadioButtonId;

public class ButtonEditorActivity extends AppCompatActivity {

    private static final String TAG = "ButtonEditorActivity";

    //objects
    private VideoPicker videoPicker;
    private ImagePicker imagePicker;

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

    private ConstraintLayout noneLayout;
    private ConstraintLayout imageLayout;
    private ConstraintLayout videoLayout;
    private ConstraintLayout soundLayout;
    private ConstraintLayout pageLayout;

    private ImageView imagePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_editor);

        videoPicker = new VideoPicker(this);
        imagePicker = new ImagePicker(this);

        getIntentArgs();
        initViews();
    }

    private void initViews(){

        //MAIN FORMULAR
        buttonTitle = (EditText) findViewById(R.id.buttonEditor_ButtonTitle);
        buttonTitle.setText(currentButton.getTitle());

        saveButton = (Button) findViewById(R.id.buttonEditor_SaveChanges);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndQuit();
            }
        });
        //IMAGE FORMULAR
        Button imagePickerButton = (Button) findViewById(R.id.buttonEditor_ImagePicker);
        imagePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePicker.showPictureDialog();
            }
        });

        imagePreview = (ImageView)findViewById(R.id.buttonEditor_ImagePreview);
        if(currentButton.getImagePath() != null){
            imagePreview.setImageBitmap(BitmapFactory.decodeFile(currentButton.getImagePath().toString()));
        }

        radioGroup = (RadioGroup)findViewById(R.id.buttonEditor_ButtonTypeRadioGroup);
        radioGroup.check(convertTypeToRadioButtonId(currentButton.getType()));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                refreshSpecialLayout();
            }
        });

        //SPECIAL FORMULAR
        noneLayout =(ConstraintLayout) findViewById(R.id.buttonEditor_NoneLayout);
        imageLayout =(ConstraintLayout) findViewById(R.id.buttonEditor_ImageLayout);
        videoLayout =(ConstraintLayout) findViewById(R.id.buttonEditor_VideoLayout);
        soundLayout =(ConstraintLayout) findViewById(R.id.buttonEditor_SoundLayout);
        pageLayout =(ConstraintLayout) findViewById(R.id.buttonEditor_PageLayout);

        Button videoPickerButton = (Button) findViewById(R.id.buttonEditor_VideoPicker);
        videoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPicker.showVideoDialog();
            }
        });






    }

    private void refreshSpecialLayout(){
        Log.d(TAG, "refreshSpecialLayout: ");
        disableAllSpecialLayouts();
        switch(convertRadioButtonIdToState(radioGroup.getCheckedRadioButtonId())){
            case NONE:
                noneLayout.setVisibility(ConstraintLayout.VISIBLE);
                break;
            case IMAGE:
                imageLayout.setVisibility(ConstraintLayout.VISIBLE);
                break;
            case VIDEO:
                videoLayout.setVisibility(ConstraintLayout.VISIBLE);
                break;
            case SOUND:
                soundLayout.setVisibility(ConstraintLayout.VISIBLE);
                break;
            case PAGE:
                pageLayout.setVisibility(ConstraintLayout.VISIBLE);
                break;
        }
    }

    private void disableAllSpecialLayouts(){
        noneLayout.setVisibility(ConstraintLayout.INVISIBLE);
        imageLayout.setVisibility(ConstraintLayout.INVISIBLE);
        videoLayout.setVisibility(ConstraintLayout.INVISIBLE);
        soundLayout.setVisibility(ConstraintLayout.INVISIBLE);
        pageLayout.setVisibility(ConstraintLayout.INVISIBLE);
    }

    private void saveAndQuit(){
        currentButton.setTitle(buttonTitle.getText().toString());
        currentButton.setImagePath(imagePath);
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
            case IMAGE:
                PicoloButtonUtils.switchButtonToImage(currentButton);
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
        imagePicker.onActivityResult(requestCode,resultCode,data);


    }

    public void setVideoPath(String path){
        videoPath = Uri.parse(path);
    }

    public void setImagePath(String path){
        imagePath = Uri.parse(path);
        imagePreview.setImageBitmap(BitmapFactory.decodeFile(path));
    }

    public void setSoundPath(String path){
        soundPath = Uri.parse(path);
    }
}
