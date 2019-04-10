package com.example.picoloid.source.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.picoloid.source.activity.MainActivity;
import com.example.picoloid.source.activity.PageActivityEditor;
import com.example.picoloid.source.model.PicoloButton;

public class DeleteButtonDialog {

    PageActivityEditor editor;
    Context context;
    PicoloButton buttonToDelete;

    public DeleteButtonDialog(PageActivityEditor editor, Context context, PicoloButton buttonToDelete){
        this.editor = editor;
        this.context = context;
        this.buttonToDelete = buttonToDelete;
    }

    public void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Do you really want to delete this button ?");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        editor.getCurrentPage().removeButton(buttonToDelete);
                        editor.saveModifs();
                        editor.refreshPage();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
