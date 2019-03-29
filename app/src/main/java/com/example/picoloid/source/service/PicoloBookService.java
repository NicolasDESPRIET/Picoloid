package com.example.picoloid.source.service;

import android.content.Context;

import com.example.picoloid.source.model.PicoloBook;

public class PicoloBookService {
    private static class PicoloBookServiceHolder {
        private static final PicoloBookService INSTANCE = new PicoloBookService();
    }
    private PicoloBook selectedBook;
    private PicoloBookService() {
        selectedBook = null;
    }

    public PicoloBook getBook(){
        return PicoloBookServiceHolder.INSTANCE.selectedBook;
    }

    public void setBook(PicoloBook book){
        PicoloBookServiceHolder.INSTANCE.selectedBook = book;
    }
}

