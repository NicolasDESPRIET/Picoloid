package com.example.picoloid.source.model;

import android.net.Uri;


public class PicoloButton {

    private String title;
    private int id;
    private Uri imagePath;
    private PicoloButtonCoord coord;
    private PicoloButtonType type;
    private Uri specialPath;
    private int pageId;

    public PicoloButton(){
        title = "Undefined"; //HARDCODE
        this.id = -1;
        coord = new PicoloButtonCoord();
        type = PicoloButtonType.NONE;
        pageId = -1;
        imagePath = null;
        specialPath = null;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Uri getImagePath() {
        return imagePath;
    }

    public void setImagePath(Uri imagePath) {
        this.imagePath = imagePath;
    }

    public PicoloButtonCoord getCoord() {
        return coord;
    }

    public void setCoord(PicoloButtonCoord coord) {
        this.coord = coord;
    }

    public PicoloButtonType getType() {
        return type;
    }

    public void setType(PicoloButtonType type) {
        this.type = type;
    }

    public Uri getSpecialPath() {
        return specialPath;
    }

    public void setSpecialPath(Uri specialPath) {
        this.specialPath = specialPath;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    @Override
    public String toString(){
        return "Button : "+title+" of id "+id+". Coord : "+coord.toString()+". Type = "+type.toString();
    }
}
