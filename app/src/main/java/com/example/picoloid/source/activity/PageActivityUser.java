package com.example.picoloid.source.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.picoloid.R;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.service.ApplicationRuntimeInfos;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.view.PicoloButtonViewPrinter;

public class PageActivityUser extends AppCompatActivity {

    private static final String TAG = "PageActivityUser";

    private RelativeLayout buttonLayout;

    private PicoloPage currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_user);

        Log.d(TAG, "onCreate: OPEN PAGE");

        ApplicationRuntimeInfos.isEdit = false;

        buttonLayout = (RelativeLayout)findViewById(R.id.buttonLayout_User);

        getIntentArgs();

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
            case R.id.go_to_edit_mode:
                Log.d(TAG, "onOptionsItemSelected: user clicked");
                Intent ii = new Intent(this, PageActivityEditor.class);
                ii.putExtra("pageId",currentPage.getId());
                this.startActivity(ii);
                finish();
                break;
            case R.id.create_new_next_page:
                break;
            case R.id.help:
                break;
            case R.id.options:
                break;
            case R.id.about:
                break;
            case R.id.show_all_pages:
                break;
            case R.id.create_new_page:
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void getIntentArgs(){
        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        try{
            int id = (int)bundle.get("pageId");
            currentPage = PicoloBookService.getBook().getPageFromId(id);
        }catch (Exception e){
            Log.d(TAG, "init: coulnd't load page");
            finish();
        }
    }

}
