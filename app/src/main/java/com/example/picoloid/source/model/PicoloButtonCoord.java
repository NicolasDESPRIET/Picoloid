package com.example.picoloid.source.model;

public class PicoloButtonCoord {

    public int width;
    public int height;
    public int leftMargin;
    public int topMargin;

    public PicoloButtonCoord(){
        width = 400;
        height = 400;
        leftMargin = 0;
        topMargin = 0;
    }

    public void setPosition(int leftMargin,int topMargin){
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;
    }

    public void setDimensions(int width, int height){
        this.width = width;
        this.height = height;
    }

}
