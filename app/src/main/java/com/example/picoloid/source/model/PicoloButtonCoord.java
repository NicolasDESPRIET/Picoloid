package com.example.picoloid.source.model;

public class PicoloButtonCoord {

    private int width;
    private int height;
    private int leftMargin;
    private int topMargin;

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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public int getTopMargin(){
        return topMargin;
    }

    public String toString(){
        return width + " " + height + " " + leftMargin + " " + topMargin;
    }
}
