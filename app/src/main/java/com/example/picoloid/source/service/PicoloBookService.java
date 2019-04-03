package com.example.picoloid.source.service;

import android.content.Context;
import android.util.Log;

import com.example.picoloid.source.model.PicoloBook;

public class PicoloBookService {

    private static final String TAG = "PicoloBookService";

    private static class PicoloBookServiceHolder {
        private static final PicoloBookService INSTANCE = new PicoloBookService();
    }
    private PicoloBook selectedBook;
    private PicoloBookService() {
        selectedBook = null;
    }

    public static PicoloBook getBook(){
        return PicoloBookServiceHolder.INSTANCE.selectedBook;
    }

    public static void setBook(PicoloBook book){
        Log.d(TAG, "setting book");
        PicoloBookServiceHolder.INSTANCE.selectedBook = book;
    }
}

