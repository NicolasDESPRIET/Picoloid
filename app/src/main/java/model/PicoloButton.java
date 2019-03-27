package model;

import java.net.URI;

public class PicoloButton {

    private String title;
    private int id;
    private URI imagePath;
    private PicoloButtonCoord coord;
    private PicoloButtonType type;
    private URI specialPath;
    private int pageId;

    public PicoloButton(){
        title = "Undefined"; //HARDCODE
        this.id = -1;
        imagePath = null;
        coord = new PicoloButtonCoord();
        type = PicoloButtonType.NONE;
        specialPath = null;
        pageId = -1;
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

    public URI getImagePath() {
        return imagePath;
    }

    public void setImagePath(URI imagePath) {
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

    public URI getSpecialPath() {
        return specialPath;
    }

    public void setSpecialPath(URI specialPath) {
        this.specialPath = specialPath;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
}
