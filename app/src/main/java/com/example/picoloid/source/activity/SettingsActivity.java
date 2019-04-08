package com.example.picoloid.source.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.picoloid.R;
import com.example.picoloid.source.model.PicoloBook;
import com.example.picoloid.source.service.PicoloBookService;

public class SettingsActivity extends AppCompatActivity {

    private EditText nameBook;
    private EditText BGcolor;
    private EditText OFcolor;
    private TextView showBGC;
    private TextView showOFC;
    private Button validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        nameBook = findViewById(R.id.nameBookData);
        BGcolor = findViewById(R.id.BGcolorData);
        OFcolor = findViewById(R.id.OFcolorData);
        showBGC = findViewById(R.id.colorBG);
        showOFC = findViewById(R.id.colorOF);

        validate = findViewById(R.id.validate);

        PicoloBook newbook = PicoloBook.newBookFromUser("",0);
        PicoloBookService.setBook(newbook);
        BGcolor.setText(newbook.getSettings().getBackgroundColor());
        OFcolor.setText(newbook.getSettings().getOverviewFrameworkColor());
        showBGC.setBackgroundColor(Color.parseColor(BGcolor.getText().toString()));
        showOFC.setBackgroundColor(Color.parseColor(OFcolor.getText().toString()));


    }
}
