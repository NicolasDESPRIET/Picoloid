package com.example.picoloid.source.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.TextView;

import com.example.picoloid.R;
import com.example.picoloid.source.dialog.NewPageFromButtonEditorDialog;
import com.example.picoloid.source.dialog.PagePicker;
import com.example.picoloid.source.dialog.SoundPicker;
import com.example.picoloid.source.managerData.JsonCreator;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonType;
import com.example.picoloid.source.service.MediaPlayerService;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.dialog.ImagePicker;
import com.example.picoloid.source.util.PicoloButtonUtils;
import com.example.picoloid.source.dialog.VideoPicker;

import static com.example.picoloid.source.model.PicoloButtonType.PAGE;
import static com.example.picoloid.source.model.PicoloButtonType.SOUND;
import static com.example.picoloid.source.model.PicoloButtonType.VIDEO;
import static com.example.picoloid.source.view.TypeToRadioConverter.convertRadioButtonIdToState;
import static com.example.picoloid.source.view.TypeToRadioConverter.convertTypeToRadioButtonId;

public class ButtonEditorActivity extends AppCompatActivity {

    private static final String TAG = "ButtonEditorActivity";

    //objects
    private VideoPicker videoPicker;
    private ImagePicker imagePicker;
    private SoundPicker soundPicker;
    private PagePicker pagePicker;

    //data
    private PicoloButton currentButton;
    private int currentPageId;

    private Uri videoPath;
    private Uri imagePath;
    private Uri soundPath;
    private int pagePointerId;

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
    private ImageView imagePreview2;
    private ImageView videoPreview;
    private TextView pageNamePreview;
    private TextView soundPickerPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_editor);

        videoPicker = new VideoPicker(this);
        imagePicker = new ImagePicker(this);
        soundPicker = new SoundPicker(this);
        pagePicker = new PagePicker(this);

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

        Button soundPickerButton = (Button) findViewById(R.id.buttonEditor_SoundPicker);
        soundPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPicker.showSoundDialog();
            }
        });

        soundPickerPreview = (TextView) findViewById(R.id.buttonEditor_SoundPickerPreview);
        if(currentButton.getType() == SOUND){
            soundPickerPreview.setText(getFileName(currentButton.getSpecialPath()));
        }


        Button createPageButton = (Button) findViewById(R.id.buttonEditor_PageCreater);
        final ButtonEditorActivity activity = this;
        createPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPageFromButtonEditorDialog dialog = new NewPageFromButtonEditorDialog(ButtonEditorActivity.this,currentButton,activity);
                dialog.showDialog();
            }
        });

        Button choosePageButton = (Button) findViewById(R.id.buttonEditor_PagePicker);
        choosePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagePicker.choosePage();
            }
        });

        imagePreview2 = (ImageView)findViewById(R.id.buttonEditor_ImagePreview2);
        if(currentButton.getImagePath() != null){
            imagePreview2.setImageBitmap(BitmapFactory.decodeFile(currentButton.getImagePath().toString()));
        }

        videoPreview = (ImageView)findViewById(R.id.buttonEditor_VideoPreview);
        if(currentButton.getType() == VIDEO && currentButton.getSpecialPath() != null){
            videoPreview.setImageBitmap(ThumbnailUtils.createVideoThumbnail(currentButton.getSpecialPath().getPath(), MediaStore.Video.Thumbnails.MICRO_KIND));
        }

        pageNamePreview = (TextView)findViewById(R.id.buttonEditor_PageName);
        if(currentButton.getType() == PAGE && currentButton.getPageId() > -1){
            pageNamePreview.setText(PicoloBookService.getBook().getPageFromId(currentButton.getPageId()).getName());
        }


        refreshSpecialLayout();
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
            case SOUND:
                PicoloButtonUtils.switchButtonToSound(currentButton,soundPath);
                break;
            case PAGE:
                PicoloButtonUtils.switchButtonToPage(currentButton,pagePointerId);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        videoPicker.onActivityResult(requestCode,resultCode,data);
        imagePicker.onActivityResult(requestCode,resultCode,data);
        soundPicker.onActivityResult(requestCode,resultCode,data);
        pagePicker.onActivityResult(requestCode,resultCode,data);
    }

    public void setVideoPath(String path){
        videoPath = Uri.parse(path);
        videoPreview.setImageBitmap(ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MICRO_KIND));
    }

    public void setImagePath(String path){
        imagePath = Uri.parse(path);
        imagePreview.setImageBitmap(BitmapFactory.decodeFile(path));
        imagePreview2.setImageBitmap(BitmapFactory.decodeFile(path));
    }

    public void setSoundPath(String path){
        soundPath = Uri.parse(path);
        soundPickerPreview.setText(getFileName(soundPath));
    }

    public void setPagePointerId(int id){
        pagePointerId = id;
        pageNamePreview.setText(PicoloBookService.getBook().getPageFromId(id).getName());
    }

    private String getFileName(Uri uri){
        String path = uri.getPath();
        return path.substring(path.lastIndexOf("/")+1);
    }

}
