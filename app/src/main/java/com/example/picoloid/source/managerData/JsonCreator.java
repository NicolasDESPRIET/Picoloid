package com.example.picoloid.source.managerData;

import android.content.Context;

import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloPage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class JsonCreator {

    public static JSONObject saveJsonBookFromObject(Context context, PicoloPage picoloPage) throws IOException, JSONException {
        JSONObject book = new JSONObject(JsonManager.readJsonFromAsset(context, "jsonBook.js"));

        JSONObject settings = new JSONObject(JsonManager.readJsonFromAsset(context, "jsonSetting.js"));

        //TODO finir

        return book;
    }

    public static JSONObject saveJsonPageFromObject(Context context, PicoloPage picoloPage) throws IOException, JSONException {
        JSONObject page = new JSONObject(JsonManager.readJsonFromAsset(context, "jsonButton.js"));
        page.put("name", picoloPage.getName());
        page.put("id", picoloPage.getId());
        JSONArray buttonlist = new JSONArray();

        for (int i = 0; i< picoloPage.getButtonList().size(); i++){
            buttonlist.put(saveJsonButtonFromObject(context, picoloPage.getButtonList().get(i)));
        }

        page.put("button_list",buttonlist);

        return page;
    }


    public static JSONObject saveJsonButtonFromObject(Context context, PicoloButton picoloButton) throws IOException, JSONException {
        JSONObject button = new JSONObject(JsonManager.readJsonFromAsset(context, "jsonButton.js"));
        button.put("title", picoloButton.getTitle());
        button.put("id", picoloButton.getId());
        button.put("type", picoloButton.getType());
        button.put("image_path", picoloButton.getImagePath().getPath());
        button.put("page_id", -1);

        switch (button.getString("type")){
            case "VIDEO":
                button.put("special_path", picoloButton.getSpecialPath().getPath());
                break;
            case "SOUND":
                button.put("special_path", picoloButton.getSpecialPath().getPath());
                break;
            case "PAGE":
                button.put("page_id", picoloButton.getPageId());
                break;
        }

        return button;
    }


}
