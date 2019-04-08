package com.example.picoloid.source.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.picoloid.R;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.service.ApplicationRuntimeInfos;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.view.PicoloButtonEditView;
import com.example.picoloid.source.view.PicoloButtonView;
import com.example.picoloid.source.view.PicoloButtonViewPrinter;

import java.util.ArrayList;
import java.util.List;

public class PageActivityEditor extends AppCompatActivity {

    private static final String TAG = "PageActivityEdit";

    private RelativeLayout buttonLayout;

    private PicoloPage currentPage;

    private List<PicoloButtonEditView> buttonList;

    private PicoloButtonEditView selectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_editor);

        ApplicationRuntimeInfos.isEdit = true;

        buttonLayout = (RelativeLayout)findViewById(R.id.buttonLayout_Edit);

        getIntentArgs();

        PicoloButtonViewPrinter printer = new PicoloButtonViewPrinter(
                currentPage,
                this,
                buttonLayout
        );
        printer.showButtons("edit");
        buttonList = printer.getEditButtonList();
        for(int i=0;i<buttonList.size();i++){
            buttonList.get(i).setEditor(this);
        }
    }

    private void getIntentArgs(){
        Intent args= getIntent();
        Bundle bundle = args.getExtras();
        try{
            int id = (int)bundle.get("pageId");
            currentPage = PicoloBookService.getBook().getPage(id);
        }catch (Exception e){
            Log.d(TAG, "init: coulnd't load page");
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_mode_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_changes:
                Log.d(TAG, "onOptionsItemSelected: user clicked");
                saveAndQuit();
                break;
            case R.id.change_button_data:
                Toast.makeText(this, "Select button", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void saveAndQuit(){
        ArrayList<PicoloButtonEditView> list = new ArrayList<PicoloButtonEditView>();
        ViewGroup views = (ViewGroup)buttonLayout;

        for(int i=0; i< views.getChildCount();i++){
            updateSingleViewCoord((PicoloButtonEditView)views.getChildAt(i));
        }

        Intent ii = new Intent(getApplicationContext(), PageActivityUser.class);
        ii.putExtra("pageId",currentPage.getId());
        startActivity(ii);
        finish();
    }

    private void updateSingleViewCoord(PicoloButtonEditView view){
        int[] array = view.getCoordOnScreen();
        view.getButtonData().getCoord().setPosition(array[0],array[1]);
    }

    public void unselectAllButtons(){
        for(int i=0; i<buttonList.size();i++){
            buttonList.get(i).unselect();
        }
        selectedButton = null;
    }

    public void setSelectedButton(PicoloButtonEditView button){
        selectedButton = button;
    }

}
