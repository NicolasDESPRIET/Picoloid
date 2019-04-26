package com.example.picoloid.source.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.text.InputType;
import android.view.Display;
import android.widget.EditText;
import android.widget.Toast;

import com.example.picoloid.source.activity.PageActivityUser;
import com.example.picoloid.source.managerData.JsonCreator;
import com.example.picoloid.source.model.PicoloBook;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonCoord;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.service.ApplicationRuntimeInfos;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.util.PicoloButtonUtils;

public class NewPageDialog {

    private PicoloPage userPage;
    private Context context;
    private boolean isNextPage;

    public NewPageDialog(PicoloPage user, Context context, boolean isNextPage){
        this.userPage = user;
        this.context = context;
        this.isNextPage = isNextPage;
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Nom de la page :");

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Créer une nouvelle page", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isNextPage){
                    positiveButtonNextPage(input.getText().toString());
                }else{
                    positiveButtonNewPage(input.getText().toString());
                }
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void positiveButtonNewPage(String input){
        PicoloPage page = new PicoloPage(input);
        PicoloBookService.getBook().addPage(page);
        JsonCreator.save(context);
        Intent openNewPage =new Intent(context, PageActivityUser.class);
        openNewPage.putExtra("pageId", page.getId());
        context.startActivity(openNewPage);
    }

    private void positiveButtonNextPage(String input){
        PicoloPage page = new PicoloPage(input);
        PicoloBookService.getBook().addPage(page);

        int width = ApplicationRuntimeInfos.screenWidth;
        int height = ApplicationRuntimeInfos.screenHeight;

        //TODO a debuger pas fini

        //boutton next
        PicoloButton next = new PicoloButton();
        PicoloButtonUtils.switchButtonToPage(next,page.getId());
        next.setTitle("page suivante");
        PicoloButtonCoord nextCoord = new PicoloButtonCoord();
        nextCoord.setDimensions(300,150);
        nextCoord.setPosition(width-350,height - 375);
        next.setCoord(nextCoord);
        userPage.addButton(next);

        //boutton previous
        PicoloButton previous = new PicoloButton();
        PicoloButtonUtils.switchButtonToPage(previous,userPage.getId());
        previous.setTitle("précedent");
        PicoloButtonCoord previousCoord = new PicoloButtonCoord();
        previousCoord.setDimensions(300,150);
        previousCoord.setPosition(50,height- 375);
        previous.setCoord(previousCoord);
        page.addButton(previous);

        JsonCreator.save(context);
        Intent openNewPage =new Intent(context, PageActivityUser.class);
        openNewPage.putExtra("pageId", page.getId());
        context.startActivity(openNewPage);
    }

}
