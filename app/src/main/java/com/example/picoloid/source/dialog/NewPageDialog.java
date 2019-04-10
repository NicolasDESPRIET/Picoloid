package com.example.picoloid.source.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.widget.EditText;

import com.example.picoloid.source.activity.ImageActivity;
import com.example.picoloid.source.activity.PageActivityEditor;
import com.example.picoloid.source.activity.PageActivityUser;
import com.example.picoloid.source.managerData.JsonCreator;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.service.PicoloBookService;

public class NewPageDialog {

    PageActivityUser userPage;
    Context context;
    boolean isNextPage;

    public NewPageDialog(PageActivityUser user, Context context, boolean isNextPage){
        this.userPage = user;
        this.context = context;
        this.isNextPage = isNextPage;
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
                PicoloPage page = new PicoloPage(input.
                        getText().toString());
                PicoloBookService.getBook().addPage(page);
                JsonCreator.save(context);
                Intent openNewPage =new Intent(context, PageActivityUser.class);
                openNewPage.putExtra("pageId", page.getId());
                context.startActivity(openNewPage);
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

}
