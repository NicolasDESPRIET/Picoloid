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

    public static PicoloBook newBookFromUser(String name, int id){
        PicoloBook book = new PicoloBook(name,0);
        PicoloPage mainPage = new PicoloPage("MainPage");
        mainPage.setId(0);
        book.pageList.add(mainPage);

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

    public PicoloPage getPage(int pageId){
        return pageList.get(findListIdByPageId(pageId));
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
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PicoloBookSettings getSettings() {
        return settings;
    }

    public void setSettings(PicoloBookSettings settings) {
        this.settings = settings;
    }

    public List<PicoloPage> getPageList() {
        return pageList;
    }

    public void setPageList(List<PicoloPage> pageList) {
        this.pageList = pageList;
    }
}
