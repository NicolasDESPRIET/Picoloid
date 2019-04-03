package com.example.picoloid.source.managerData;

import android.content.Context;

import com.example.picoloid.source.model.*;
import com.example.picoloid.source.util.PicoloButtonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;

public class ObjectManager {

    public static PicoloBook loadBookAssetsmod( String name, Context context, String file) throws IOException, JSONException {
        //TODO pass in filemod when this will be implement too.
        String json = JsonManager.readJsonFromAsset(context,file);
        JSONArray profils = new JSONObject(json).getJSONArray("book");
        PicoloBook book = null;
        for (int i =0; i<profils.length(); i++){
            if (profils.getJSONObject(i).getString("name").equals(name)){
                book = loadPicoloBookfromJson(profils.getJSONObject(i));
            }
        }
        return book;
    }

    public static PicoloBook loadPicoloBookfromJson (JSONObject jsonObject) throws JSONException{
        PicoloBook book = new PicoloBook(jsonObject.getString("name"), jsonObject.getInt("id"));
        JSONArray pagelist = jsonObject.getJSONArray("list_page");
        //TODO implements setting when this will be implement too.
        for (int i = 0; i < pagelist.length(); i++){
            PicoloPage page = loadPicoloPagefromJson(pagelist.getJSONObject(i));
            book.addJsonPage(page);
        }
        return book;
    }


    public static PicoloPage loadPicoloPagefromJson (JSONObject jsonObject) throws JSONException{
        // init the picolopage with her name from the json
        PicoloPage picoloPage = new PicoloPage(jsonObject.getString("name"));
        picoloPage.setId(jsonObject.getInt("id"));
        // take the button list in JSONArray from the JSONObject
        JSONArray Buttonlist = jsonObject.getJSONArray("button_list");
        // browse the JSONArray of button for create each button
        for (int i=0; i<Buttonlist.length(); i++) {
            PicoloButton button = loadPicolobuttonfromJson(Buttonlist.getJSONObject(i));
            picoloPage.addJsonButton(button);
        }
        return picoloPage;
    }

    public static PicoloButton loadPicolobuttonfromJson(JSONObject jsonObject) throws JSONException {
        /*
         * create empty button and empty coordonate for him
         * create URI from the string path image in the jsonobject
         * */
        PicoloButton button = new PicoloButton();
        PicoloButtonCoord coord = new PicoloButtonCoord();
        URI image_path = URI.create(jsonObject.getString("image_path"));

        /*
         * set variable in the PicoloButtonCoord with the variable in the json
         * */
        coord.setDimensions(jsonObject.getJSONObject("coordonate").getInt("width"),
                jsonObject.getJSONObject("coordonate").getInt("height"));
        coord.setPosition(jsonObject.getJSONObject("coordonate").getInt("leftMargin"),
                jsonObject.getJSONObject("coordonate").getInt("topMargin"));

        /*
         * Look the type of the button in the Jsonobject and set it in the picolobutton
         * */
        switch (jsonObject.getString("type")){
            case "NONE":
                PicoloButtonUtils.switchButtonToNone(button);
                break;
            case "IMAGE":
                PicoloButtonUtils.switchButtonToImage(button);
                break;
            case "VIDEO":
                URI video_path = URI.create(jsonObject.getString("special_path"));
                PicoloButtonUtils.switchButtonToVideo(button, video_path);
                break;
            case "SOUND":
                URI sound_path = URI.create(jsonObject.getString("special_path"));
                PicoloButtonUtils.switchButtonToSound(button, sound_path);
                break;
            case "PAGE":
                PicoloButtonUtils.switchButtonToPage(button,jsonObject.getInt("page_id"));
                break;
        }

        /*
         * finish the settings of the buttons and return it
         * */
        button.setTitle(jsonObject.getString("title"));
        button.setImagePath(image_path);
        button.setCoord(coord);
        button.setId(jsonObject.getInt("id"));
        return button;
    }

    public void SaveBook(){}

    public void SavePage(){}

    public void SaveButton(){}

    //TODO
}
