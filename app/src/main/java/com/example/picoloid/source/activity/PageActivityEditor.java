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
import com.example.picoloid.source.dialog.DeleteButtonDialog;
import com.example.picoloid.source.managerData.JsonCreator;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.service.ApplicationRuntimeInfos;
import com.example.picoloid.source.service.PicoloBookService;
import com.example.picoloid.source.view.PicoloButtonEditView;
import com.example.picoloid.source.view.PicoloButtonViewPrinter;

import java.util.List;

public class PageActivityEditor extends AppCompatActivity {

    private static final String TAG = "PageActivityEdit";

    //data
    private PicoloPage currentPage;
    private List<PicoloButtonEditView> buttonList;
    private PicoloButtonEditView selectedButton;

    //xml
    private RelativeLayout buttonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_editor);

        ApplicationRuntimeInfos.isEdit = true;

        getIntentArgs();

        initViews();
    }

    private void initViews(){
        buttonLayout = findViewById(R.id.pageEditor_Layout);
        buttonLayout.setBackgroundColor(PicoloBookService.getBook().getSettings().getBackgroundColor());

        printPicoloButtons();
    }

    private void printPicoloButtons(){
        PicoloButtonViewPrinter printer = new PicoloButtonViewPrinter(
                currentPage,
                this,
                buttonLayout
        );
        printer.showButtons("edit");
        buttonList = printer.getEditButtonList();
        setEditorOnButtons();
    }

    private void setEditorOnButtons(){
        for(int i=0;i<buttonList.size();i++){
            buttonList.get(i).setEditor(this);
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
            case R.id.pageEditor_SaveChangesButton:
                saveChangeButton();
                break;
            case R.id.pageEditor_EditButtonData:
                editButtonData();
                break;
            case R.id.pageEditor_AddNewButton:
                addNewButton();
                break;
            case R.id.pageEditor_DeleteButton:
                deleteButton();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void saveChangeButton(){
        saveChangesLocally();
        JsonCreator.save(this);
        returnToUserMode();
    }

    private void editButtonData(){
        if(selectedButton == null) return;
        Intent openButtonDataEditor = new Intent(getApplicationContext(), ButtonEditorActivity.class);
        openButtonDataEditor.putExtra("pageId",currentPage.getId());
        openButtonDataEditor.putExtra("buttonId",selectedButton.getButtonData().getId());
        startActivity(openButtonDataEditor);
    }

    private void addNewButton(){
        PicoloButton newButton = new PicoloButton();
        currentPage.addButton(newButton);
        saveChangesLocally();
        refreshPage();
    }

    private void deleteButton(){
        if(selectedButton == null) return;
        DeleteButtonDialog dialog = new DeleteButtonDialog(this,this,selectedButton.getButtonData());
        dialog.showDialog();
    }

    public void saveChangesLocally(){
        for(int i=0; i< buttonList.size();i++){
            updateSingleViewCoord(buttonList.get(i));
        }
    }

    private void returnToUserMode(){
        Intent ii = new Intent(getApplicationContext(), PageActivityUser.class);
        ii.putExtra("pageId",currentPage.getId());

        startActivity(ii);
        finish();
    }

    public void refreshPage(){
        Intent ii = new Intent(getApplicationContext(), PageActivityEditor.class);
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
            Log.d(TAG, "init: coulnd't load page");
            finish();
        }
    }

    public void setSelectedButton(PicoloButtonEditView button){
        selectedButton = button;
    }
    public PicoloPage getCurrentPage(){
        return currentPage;
    }
}
