package com.example.picoloid.source.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.MotionEvent;

import com.example.picoloid.source.activity.ImageActivity;
import com.example.picoloid.source.activity.PageActivityEditor;
import com.example.picoloid.source.activity.PageActivityUser;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.service.ApplicationRuntimeInfos;

public class PicoloButtonEditView extends AppCompatButton {
    
    public static final String TAG = "PicoloButtonEditView";

    private PicoloButton buttonData;

    private boolean selected;

    private PageActivityEditor editor;

    public PicoloButtonEditView(Context context, final PicoloButton buttonData){
        super(context);

        this.buttonData = buttonData;
        this.setText(buttonData.getTitle());

        Log.d(TAG, "Loading view: "+buttonData.toString());
    }

    float dX,dY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        buttonClickOnEdit(event);
        return true;
    }

    private void buttonClickOnEdit(MotionEvent event){

        select();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = this.getX() - event.getRawX();
                dY = this.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                this.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;

            default:
                break;
        }

    }

    public PicoloButton getButtonData(){
        return buttonData;
    }

    public int[] getCoordOnScreen(){
        int[] array = {(int)getX(),(int)getY()};
        return array;
    }

    public void select(){
        editor.unselectAllButtons();
        selected = true;
        this.setBackgroundColor(Color.BLUE);
        editor.setSelectedButton(this);
    }

    public void unselect(){
        selected = false;
        this.setBackgroundColor(Color.WHITE);
    }

    public boolean isSelected(){
        return selected;
    }

    public void setEditor(PageActivityEditor editor){
        this.editor = editor;
    }
}
