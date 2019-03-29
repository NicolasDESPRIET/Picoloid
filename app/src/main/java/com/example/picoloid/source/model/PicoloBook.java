package com.example.picoloid.source.model;

import java.util.ArrayList;
import java.util.List;

public class PicoloBook {

    private String name;
    private int id;
    private PicoloBookSettings settings;
    private List<PicoloPage> pageList;

    public PicoloBook(String name, int id){
        this.name = name;
        this.id = id;
        settings = new PicoloBookSettings();
        pageList = new ArrayList<PicoloPage>();
    }

    public static PicoloBook newBookFromUser(String name){
        PicoloBook book = new PicoloBook(name,0);
        book.pageList.add(new PicoloPage("MainPage"));
        return book;
    }

    public void addJsonPage(PicoloPage page){
        pageList.add(page);
    }

    public void addPage(PicoloPage page){
        page.setId(getLastPageId() + 1);
        pageList.add(page);
    }

    public void removePage(PicoloPage page){
        if(page.getId() == 0)return;
        int id = findListIdByPageId(page.getId());
        if(id == -1)return;
        pageList.remove(id);
    }

    private int getLastPageId(){
        int maxId = 0;
        for(int i=0;i<pageList.size();i++){
            if(pageList.get(i).getId() > maxId) maxId = pageList.get(i).getId();
        }
        return maxId;
    }
    private int findListIdByPageId(int pageId){
        for(int i=0;i<pageList.size();i++){
            if(pageId==pageList.get(i).getId()){
                return i;
            }
        }
        return -1;
    }

    public String getName() {
        return name;
    }
}
