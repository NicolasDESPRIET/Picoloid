package com.example.picoloid.source.util;

import com.example.picoloid.source.model.PicoloBook;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloPage;

import java.net.URI;

public class PicoloBookTest {

    public static PicoloBook getTest(){

        PicoloBook book = PicoloBook.newBookFromUser("Jason",0);

        PicoloButton button1 = new PicoloButton();
        button1.setTitle("image");

        PicoloButton button2 = new PicoloButton();
        button2.setTitle("video");
        PicoloButtonUtils.switchButtonToImage(button2);
        button2.getCoord().setPosition(600,600);

        PicoloButton button3 = new PicoloButton();
        button3.setTitle("next");
        PicoloButtonUtils.switchButtonToPage(button3,1);
        button3.getCoord().setPosition(0,600);

        book.addPage(new PicoloPage("deuxieme"));


        book.getPageList().get(0).addButton(button1);
        book.getPageList().get(0).addButton(button2);
        book.getPageList().get(0).addButton(button3);

        return book;
    }

}
