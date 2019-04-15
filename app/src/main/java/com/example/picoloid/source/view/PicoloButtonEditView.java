package com.example.picoloid.source.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.view.MotionEvent;

import com.example.picoloid.source.activity.PageActivityEditor;
import com.example.picoloid.source.model.PicoloButton;

public class PicoloButtonEditView extends AppCompatButton {
    
    public static final String TAG = "PicoloButtonEditView";

    private PicoloButton buttonData;
    private boolean selected;
    private PageActivityEditor editor;

    private float coordX, coordY;

    public PicoloButtonEditView(Context context, final PicoloButton buttonData){
        super(context);

        this.buttonData = buttonData;
        this.setText(buttonData.getTitle());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        buttonClickOnEdit(event);
        return true;
    }

    private void buttonClickOnEdit(MotionEvent event){
        select();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                coordX = this.getX() - event.getRawX();
                coordY = this.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                this.animate()
                        .x(event.getRawX() + coordX)
                        .y(event.getRawY() + coordY)
                        .setDuration(0)
                        .start();
                break;

            default:
                break;
        }
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

    public PicoloButton getButtonData(){
        return buttonData;
    }
    public boolean isSelected(){
        return selected;
    }
    public void setEditor(PageActivityEditor editor){
        this.editor = editor;
    }
}
