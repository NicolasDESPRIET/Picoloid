package com.example.picoloid.source.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.widget.EditText;

import com.example.picoloid.source.activity.ButtonEditorActivity;
import com.example.picoloid.source.activity.PageActivityUser;
import com.example.picoloid.source.managerData.JsonCreator;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.service.PicoloBookService;

public class NewPageFromButtonEditorDialog {

    Context context;
    PicoloButton buttonData;
    ButtonEditorActivity activity;

    public NewPageFromButtonEditorDialog(Context context, PicoloButton buttonData, ButtonEditorActivity activity){
        this.context = context;
        this.buttonData = buttonData;
        this.activity = activity;
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Nouvelle page :");

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                positiveButton(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void positiveButton(String input){
        PicoloPage page = new PicoloPage(input);
        PicoloBookService.getBook().addPage(page);
        buttonData.setPageId(page.getId());
        activity.setPagePointerId(page.getId());
        JsonCreator.save(context);
    }

}
