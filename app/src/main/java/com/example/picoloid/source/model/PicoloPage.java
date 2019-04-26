package com.example.picoloid.source.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PicoloPage{

    private String name;
    private int id;
    private List<PicoloButton> buttonList;
    private int haveNext;

    public PicoloPage(String name){
        this.name = name;
        this.id = -1;
        this.buttonList = new ArrayList<PicoloButton>();
        this.haveNext = 0;
    }

    public void addJsonButton(PicoloButton button){
        buttonList.add(button);
    }

    public void addButton(PicoloButton button){
        button.setId(getLastButtonId() + 1);
        buttonList.add(button);
    }

    public void removeButton(PicoloButton button){
        int id = findListIdByButtonId(button.getId());
        if(id == -1)return;
        buttonList.remove(id);
    }

    //returns max(id) or -1 if list is empty
    private int getLastButtonId(){
        if(buttonList.isEmpty())return -1;
        int maxId = -1;
        for(int i=0;i<buttonList.size();i++){
            if(buttonList.get(i).getId() > maxId) maxId = buttonList.get(i).getId();
        }
        return maxId;
    }

    private int findListIdByButtonId(int buttonId){
        for(int i=0;i<buttonList.size();i++){
            if(buttonId==buttonList.get(i).getId()){
                return i;
            }
        }
        return -1;
    }

    public PicoloButton getButtonFromId(int id){
        for(int i=0; i<buttonList.size();i++){
            if(buttonList.get(i).getId() == id){
                Log.d("PicoloPage", "getButtonFromId: button found !");
                return buttonList.get(i);
            }
        }
        return null;
    }

    public int getHaveNext() {
        return haveNext;
    }

    public void setHaveNext(int haveNext) {
        this.haveNext = haveNext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PicoloButton> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<PicoloButton> buttonList) {
        this.buttonList = buttonList;
    }

    @Override
    public String toString(){
        return "Page of id "+id+" named "+name+". Number of buttons : "+buttonList.size();
    }
}
