package com.example.picoloid.source.model;

public class PicoloBookSettings {

    private String backgroundColor;

    public PicoloBookSettings() {
        this.backgroundColor = "#FFFFFF";
    }

    public PicoloBookSettings(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
