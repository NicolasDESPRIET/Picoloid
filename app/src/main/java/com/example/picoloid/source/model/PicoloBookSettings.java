package com.example.picoloid.source.model;

public class PicoloBookSettings {

    private String backgroundColor;
    private String OverviewFrameworkColor;

    public PicoloBookSettings() {
        this.backgroundColor = "#FFFFFF";
        this.OverviewFrameworkColor = "#FF0000";
    }

    public PicoloBookSettings(String backgroundColor, String overviewFrameworkColor) {
        this.backgroundColor = backgroundColor;
        this.OverviewFrameworkColor = overviewFrameworkColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getOverviewFrameworkColor() {
        return OverviewFrameworkColor;
    }

    public void setOverviewFrameworkColor(String overviewFrameworkColor) {
        OverviewFrameworkColor = overviewFrameworkColor;
    }
}
