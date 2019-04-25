package com.example.picoloid.source.managerData;

import android.content.Context;
import android.net.Uri;

import com.example.picoloid.source.model.PicoloBook;
import com.example.picoloid.source.model.PicoloBookSettings;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloButtonCoord;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.util.PicoloButtonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;


public class ObjectManager {

    public static PicoloBook loadBookAssetsmod(String name, Context context, String file) throws IOException, JSONException {
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
        PicoloBookSettings settings;
        if (jsonObject.getJSONObject("settings").getString("backgroundColor")== null){
            settings = new PicoloBookSettings();
        }else{
            settings = new PicoloBookSettings(jsonObject.getJSONObject("settings").getInt("backgroundColor"));
        }
        for (int i = 0; i < pagelist.length(); i++){
            PicoloPage page = loadPicoloPagefromJson(pagelist.getJSONObject(i));
            book.addJsonPage(page);
        }
        book.setSettings(settings);
        return book;
    }


    private static PicoloPage loadPicoloPagefromJson(JSONObject jsonObject) throws JSONException{
        PicoloPage picoloPage = new PicoloPage(jsonObject.getString("name"));
        picoloPage.setId(jsonObject.getInt("id"));
        picoloPage.setHaveNext(jsonObject.getInt("have_next"));
        JSONArray Buttonlist = jsonObject.getJSONArray("button_list");

        for (int i=0; i<Buttonlist.length(); i++) {
            PicoloButton button = loadPicolobuttonfromJson(Buttonlist.getJSONObject(i));
            picoloPage.addJsonButton(button);
        }
        return picoloPage;
    }

    private static PicoloButton loadPicolobuttonfromJson(JSONObject jsonObject) throws JSONException {
        PicoloButton button = new PicoloButton();
        PicoloButtonCoord coord = new PicoloButtonCoord();

        button.setImagePath(null);
        button.setSpecialPath(null);

        coord.setDimensions(jsonObject.getJSONObject("coordonate").getInt("width"),
                jsonObject.getJSONObject("coordonate").getInt("height"));
        coord.setPosition(jsonObject.getJSONObject("coordonate").getInt("leftMargin"),
                jsonObject.getJSONObject("coordonate").getInt("topMargin"));

        switch (jsonObject.getString("type")){
            case "NONE":
                PicoloButtonUtils.switchButtonToNone(button);
                break;
            case "IMAGE":
                PicoloButtonUtils.switchButtonToImage(button);
                break;
            case "VIDEO":
                if(!jsonObject.getString("special_path").equals("")){
                    PicoloButtonUtils.switchButtonToVideo(button,Uri.parse(jsonObject.getString("special_path")));
                }else{
                    PicoloButtonUtils.switchButtonToNone(button);
                }
                break;
            case "SOUND":
                if(!jsonObject.getString("special_path").equals("")){
                    PicoloButtonUtils.switchButtonToSound(button,Uri.parse(jsonObject.getString("special_path")));
                }else{
                    PicoloButtonUtils.switchButtonToNone(button);
                }
                break;
            case "PAGE":
                PicoloButtonUtils.switchButtonToPage(button,jsonObject.getInt("page_id"));
                break;
        }

        if (!jsonObject.getString("image_path").equals("")){
            button.setImagePath(Uri.parse(jsonObject.getString("image_path")));
        }
        button.setTitle(jsonObject.getString("title"));
        button.setCoord(coord);
        button.setId(jsonObject.getInt("id"));
        return button;
    }
}
