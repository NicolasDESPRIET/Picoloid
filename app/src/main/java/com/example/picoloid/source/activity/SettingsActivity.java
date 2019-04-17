package com.example.picoloid.source.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.picoloid.R;
import com.example.picoloid.source.managerData.JsonCreator;
import com.example.picoloid.source.model.PicoloBook;
import com.example.picoloid.source.service.PicoloBookService;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";

    //data
    private PicoloBook book;
    private int bookId;
    private int pageId = 0;
    private String mod;
    private int DefaultColor1;
    //xml
    private EditText nameBook;
    private TextView showBGC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getIntentArgs();

        initViews();
    }

    private void initViews(){
        nameBook = findViewById(R.id.nameBookData);
        showBGC = findViewById(R.id.textView);

        DefaultColor1 = Color.parseColor(book.getSettings().getBackgroundColor());

        showBGC.setBackgroundColor(DefaultColor1);
        nameBook.setText(book.getName());

        Button validate = findViewById(R.id.validate);
        Button picker1 = findViewById(R.id.pickColorBG);

        picker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker(DefaultColor1);
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book.setName(nameBook.getText().toString());
                book.setId(bookId);
                PicoloBookService.setBook(book);

                JsonCreator.save(getApplicationContext());

                Intent ii=new Intent(SettingsActivity.this, PageActivityUser.class);
                ii.putExtra("pageId", pageId);
                startActivity(ii);

            }
        });
    }

    public void openColorPicker(int Dcolor){
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, Dcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                showBGC.setBackgroundColor(color);
                DefaultColor1 = color;
                book.getSettings().setBackgroundColor(Integer.toHexString(DefaultColor1).toUpperCase().substring(2));
            }
        });
        colorPicker.show();
    }

    private void getIntentArgs(){
        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        if (bundle != null) {
            mod = bundle.getString("mod");
        }
        if (mod != null && mod.equals("new")) {
            if (bundle != null) {
                bookId = bundle.getInt("bookId");
            }
            book = PicoloBook.newBookFromUser("", bookId);
        }else if (mod != null && mod.equals("modify")){
            book = PicoloBookService.getBook();
            if (bundle != null) {
                bookId = bundle.getInt("bookId");
                pageId = bundle.getInt("pageId");
            }
        }
    }
}
