package com.example.picoloid.source.dialog;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.example.picoloid.source.activity.ButtonEditorActivity;
import com.example.picoloid.source.activity.ListPageActivity;

public class PagePicker {
    
    private static final String TAG = "PagePicker";

    private ButtonEditorActivity activity;
    private int PAGEREQ = 7;

    public PagePicker(ButtonEditorActivity activity){
        Log.d(TAG, "PagePicker: page picker created");
        this.activity = activity;
    }

    public void choosePage(){
        Log.d(TAG, "choosePage: starting list page activity");
        Intent choosePageIntent = new Intent(activity, ListPageActivity.class);
        choosePageIntent.putExtra("bool", true);
        activity.startActivityForResult(choosePageIntent,PAGEREQ);
        Log.d(TAG, "choosePage: list page started");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: called");
        if (resultCode == activity.RESULT_CANCELED) {
            Log.d(TAG, "onActivityResult: error");
            return;
        }
        if (requestCode == PAGEREQ) {
            if (data != null) {
                int pageId = data.getIntExtra("pageId", 0);
                activity.setPagePointerId(pageId);
                Log.d(TAG, "onActivityResult: setPagePointerId called");
            }

        }
    }
}
