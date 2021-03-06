package com.example.picoloid.source.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.picoloid.source.activity.PageActivityEditor;
import com.example.picoloid.source.managerData.JsonCreator;
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
        alertDialogBuilder.setTitle("Voulez-vous vraiment supprimer ce bouton ?");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        positiveButton();
                    }
                })
                .setNegativeButton("Non",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void positiveButton(){
        editor.getCurrentPage().removeButton(buttonToDelete);
        editor.saveChangesLocally();
        JsonCreator.save(context);
        editor.refreshPage();
    }
}
