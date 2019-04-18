package com.example.picoloid.source.model;

import android.content.Intent;
import android.graphics.Color;

public class PicoloBookSettings {

    private int backgroundColor;

    public PicoloBookSettings() {
        this.backgroundColor = Color.WHITE;
    }

    public PicoloBookSettings(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
