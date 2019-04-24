package com.example.picoloid.source.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.picoloid.R;
import com.example.picoloid.source.dialog.NewPageDialog;
import com.example.picoloid.source.model.PicoloBook;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.service.ApplicationRuntimeInfos;
import com.example.picoloid.source.service.MediaPlayerService;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.view.PicoloButtonViewPrinter;

public class PageActivityUser extends AppCompatActivity {

    private static final String TAG = "PageActivityUser";

    //data
    private PicoloPage currentPage;

    //xml
    private RelativeLayout buttonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_user);

        ApplicationRuntimeInfos.isEdit = false;

        MediaPlayerService.setContext(this);

        getIntentArgs();

        this.setTitle("Picoloid : Livre de " + PicoloBookService.getBook().getName() + " - " + currentPage.getName());

        initViews();
    }

    private void initViews(){
        buttonLayout = findViewById(R.id.pageUserlayout);
        buttonLayout.setBackgroundColor(PicoloBookService.getBook().getSettings().getBackgroundColor());

        PicoloButtonViewPrinter printer = new PicoloButtonViewPrinter(
                currentPage,
                this,
                buttonLayout
        );

        printer.showButtons("user");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_mode_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.pageUser_GoToEditMode:
                goToEditMode();
                break;
            case R.id.pageUser_CreateNewNextPage:
                createNewNextPage();
                break;
            case R.id.pageUser_Options:
                options();
                break;
            case R.id.pageUser_About:
                about();
                break;
            case R.id.pageUser_ShowAllPages:
                showAllPages();
                break;
            case R.id.pageUser_CreateNewPage:
                createNewPage();
                break;
            case R.id.pageUser_ChangeUser:
                changeUser();
                break;
            case R.id.pageUser_DeletePage:
                deletePage();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void deletePage(){
        MediaPlayerService.pauseMediaPlayer();
        Intent pp = new Intent(this, DeletePageActivity.class);
        this.startActivity(pp);
    }

    private void goToEditMode(){
        MediaPlayerService.pauseMediaPlayer();

        Intent ii = new Intent(this, PageActivityEditor.class);
        ii.putExtra("pageId",currentPage.getId());
        ii.putExtra("bookId",PicoloBookService.getBook().getId());
        this.startActivity(ii);
        finish();
    }

    private void createNewNextPage(){
        MediaPlayerService.pauseMediaPlayer();

        NewPageDialog dialog = new NewPageDialog(currentPage,this,true);
        dialog.showDialog();
    }

    private void options(){
        MediaPlayerService.pauseMediaPlayer();

        Intent settings = new Intent(this, SettingsActivity.class);
        settings.putExtra("mod","modify");
        settings.putExtra("bookId", PicoloBookService.getBook().getId());
        this.startActivity(settings);
        finish();
    }

    private void about(){
        MediaPlayerService.pauseMediaPlayer();

        Intent about = new Intent(this, About.class);
        this.startActivity(about);
    }

    private void showAllPages(){
        MediaPlayerService.pauseMediaPlayer();

        Intent ii = new Intent(PageActivityUser.this, ListPageActivity.class);
        startActivity(ii);
    }

    private void createNewPage(){
        MediaPlayerService.pauseMediaPlayer();

        NewPageDialog dialog = new NewPageDialog(currentPage,this,false);
        dialog.showDialog();
    }

    private void changeUser(){
        MediaPlayerService.pauseMediaPlayer();

        Intent main = new Intent(this, MainActivity.class);
        this.startActivity(main);
        finish();
    }

    private void getIntentArgs(){
        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        try{
            int id = 0;
            if (bundle != null) {
                id = (int)bundle.get("pageId");
            }
            currentPage = PicoloBookService.getBook().getPageFromId(id);
        }catch (Exception e){
            finish();
        }
    }

}
