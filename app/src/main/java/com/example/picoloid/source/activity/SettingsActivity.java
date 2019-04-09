package com.example.picoloid.source.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picoloid.R;
import com.example.picoloid.source.managerData.JsonCreator;
import com.example.picoloid.source.model.PicoloBook;
import com.example.picoloid.source.service.PicoloBookService;

import org.json.JSONException;

import java.io.IOException;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SettingsActivity extends AppCompatActivity {

    private EditText nameBook;
    private TextView showBGC;
    private TextView showOFC;
    private Button picker1;
    private Button picker2;
    private Button validate;
    private PicoloBook book;
    private String C1;
    private String C2;
    private int id;
    private String mod;
    private int DefaultColor1;
    private int DefaultColor2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();

        nameBook = findViewById(R.id.nameBookData);
        showOFC = findViewById(R.id.textView2);
        showBGC = findViewById(R.id.textView);

        validate = findViewById(R.id.validate);
        picker1 = findViewById(R.id.pickColorBG);
        picker2 = findViewById(R.id.pickColorOF);

        DefaultColor1 = Color.parseColor(book.getSettings().getBackgroundColor());
        DefaultColor2 = Color.parseColor(book.getSettings().getOverviewFrameworkColor());

        showBGC.setBackgroundColor(DefaultColor1);
        showOFC.setBackgroundColor(DefaultColor2);
        nameBook.setText(book.getName());

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book.getSettings().setBackgroundColor("#" + Integer.toHexString(DefaultColor1).toUpperCase());
                book.getSettings().setOverviewFrameworkColor("#" + Integer.toHexString(DefaultColor2).toUpperCase());
                book.setName(nameBook.getText().toString());
                book.setId(id);
                PicoloBookService.setBook(book);
                try {
                    JsonCreator.save(getApplicationContext());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (mod.equals("new")){
                    Intent ii=new Intent(SettingsActivity.this, MainActivity.class);
                    startActivity(ii);
                }else{
                    Intent ii=new Intent(SettingsActivity.this, PageActivityUser.class);
                    ii.putExtra("pageId", PicoloBookService.getBook().getId());
                    startActivity(ii);
                }
            }
        });
    }

    private void init(){
        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        if (bundle != null) {
            mod = bundle.getString("mod");
        }
        if (mod != null && mod.equals("new")) {
            if (bundle != null) {
                id = bundle.getInt("bookId");
            }
            book = PicoloBook.newBookFromUser("", id);
            PicoloBookService.setBook(book);
        }else if (mod != null && mod.equals("modify")){
            book=PicoloBookService.getBook();
        }


    }

    private void pickerColor(Boolean AlphaSupports, int DefaultColor) {
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, DefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                Toast.makeText(SettingsActivity.this, "pas de couleurs choisies", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                // cancel was selected by the user
            }

        });
        dialog.show();
    }


    public String getColorFromTV(TextView back){
        ColorDrawable cd = (ColorDrawable) back.getBackground();
        return "#" + Integer.toHexString(cd.getColor()).toUpperCase();
    }
}
