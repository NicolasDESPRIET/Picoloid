package com.example.picoloid.source.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.picoloid.R;
import com.example.picoloid.source.dialog.NewPageDialog;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.service.ApplicationRuntimeInfos;
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

        getIntentArgs();

        this.setTitle("Picoloid : Livre de " + PicoloBookService.getBook().getName() + " - " + currentPage.getName());

        initViews();
    }

    private void initViews(){
        buttonLayout = (RelativeLayout)findViewById(R.id.pageUserlayout);

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
            case R.id.pageUser_Help:
                help();
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

    }

    private void goToEditMode(){
        Intent ii = new Intent(this, PageActivityEditor.class);
        ii.putExtra("pageId",currentPage.getId());
        ii.putExtra("bookId",PicoloBookService.getBook().getId());
        this.startActivity(ii);
        finish();
    }

    private void createNewNextPage(){

    }

    private void help(){

    }

    private void options(){
        Intent settings = new Intent(this, SettingsActivity.class);
        settings.putExtra("mod","modify");
        settings.putExtra("bookId", PicoloBookService.getBook().getId());
        this.startActivity(settings);
        finish();
    }

    private void about(){
        Intent about = new Intent(this, About.class);
        this.startActivity(about);
    }

    private void showAllPages(){
        Intent ii = new Intent(PageActivityUser.this, ListPageActivity.class);
        startActivity(ii);
    }

    private void createNewPage(){
        NewPageDialog dialog = new NewPageDialog(this,this,false);
        dialog.showDialog();
    }

    private void changeUser(){
        Intent main = new Intent(this, MainActivity.class);
        this.startActivity(main);
        finish();
    }

    private void getIntentArgs(){
        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        try{
            int id = (int)bundle.get("pageId");
            currentPage = PicoloBookService.getBook().getPageFromId(id);
        }catch (Exception e){
            finish();
        }
    }

}
