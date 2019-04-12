package com.example.picoloid.source.managerData;

import android.content.Context;
import android.util.Log;

import com.example.picoloid.source.model.PicoloBook;
import com.example.picoloid.source.model.PicoloButton;
import com.example.picoloid.source.model.PicoloPage;
import com.example.picoloid.source.service.PicoloBookService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class JsonCreator {

    private static final String TAG = "JsonCreator";

    public static void saveDeleted(Context context, JSONArray jsonArray){
        try {
            JSONObject jsonObjectProfils = new JSONObject(JsonManager.readJsonFromAsset(context,"jsonProfil.json"));

            String Saved = jsonObjectProfils.toString();

            Log.d(TAG, Saved);

            JsonManager.saveDataOnFiles(context, Saved);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(Context context) {
        JSONObject jsonObjectProfils = null;
        try {
            jsonObjectProfils = new JSONObject(JsonManager.readOnFile(context));
            PicoloBook book = PicoloBookService.getBook();
            JSONObject jsonObjectBook = saveJsonBookFromObject(context, book);

            JSONArray listBook = jsonObjectProfils.getJSONArray("book");
            for (int i = 0; i< listBook.length(); i++){
                if (listBook.getJSONObject(i).getInt("id") == jsonObjectBook.getInt("id")){
                    listBook.remove(i);
                    Log.d(TAG, "boucle for du save");
                }
            }
            listBook.put(jsonObjectBook);

            jsonObjectProfils.put("book",listBook);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String Saved = null;
        if (jsonObjectProfils != null) {
            Saved = jsonObjectProfils.toString();
        }

        Log.d(TAG, Saved);

        JsonManager.saveDataOnFiles(context, Saved);

    }

    private static JSONObject saveJsonBookFromObject(Context context, PicoloBook picoloBook) throws IOException, JSONException {
        JSONObject book = new JSONObject(JsonManager.readJsonFromAsset(context, "jsonBook.json"));
        JSONArray page_list = new JSONArray();
        JSONObject settings = new JSONObject(JsonManager.readJsonFromAsset(context, "jsonSetting.json"));

        settings.put("backgroundColor", picoloBook.getSettings().getBackgroundColor());
        settings.put("OverviewFrameworkColor", picoloBook.getSettings().getOverviewFrameworkColor());

        book.put("name", picoloBook.getName());
        book.put("id", picoloBook.getId());
        book.put("settings", settings);

        for (int i = 0; i< picoloBook.getPageList().size(); i++){
            page_list.put(saveJsonPageFromObject(context, picoloBook.getPageList().get(i)));
        }

        book.put("list_page", page_list);

        return book;
    }

    private static JSONObject saveJsonPageFromObject(Context context, PicoloPage picoloPage) throws IOException, JSONException {
        JSONObject page = new JSONObject(JsonManager.readJsonFromAsset(context, "jsonButton.json"));
        page.put("name", picoloPage.getName());
        page.put("id", picoloPage.getId());
        JSONArray buttonlist = new JSONArray();

        for (int i = 0; i< picoloPage.getButtonList().size(); i++){
            buttonlist.put(saveJsonButtonFromObject(context, picoloPage.getButtonList().get(i)));
        }

        page.put("button_list",buttonlist);

        return page;
    }


    private static JSONObject saveJsonButtonFromObject(Context context, PicoloButton picoloButton) throws IOException, JSONException {
        JSONObject button = new JSONObject(JsonManager.readJsonFromAsset(context, "jsonButton.json"));
        button.put("title", picoloButton.getTitle());
        button.put("id", picoloButton.getId());
        button.put("type", picoloButton.getType());
        if (picoloButton.getImagePath() == null){
            button.put("image_path", null);
        }else{
            button.put("image_path", picoloButton.getImagePath().getPath());
        }
        button.put("page_id", -1);

        switch (button.getString("type")){
            case "VIDEO":
                if (picoloButton.getSpecialPath() == null){
                    button.put("special_path", null);
                }else{
                    button.put("special_path", picoloButton.getSpecialPath().getPath());
                }

                break;
            case "SOUND":
                if (picoloButton.getSpecialPath() == null){
                    button.put("special_path", null);
                }else{
                    button.put("special_path", picoloButton.getSpecialPath().getPath());
                }
                break;
            case "PAGE":
                button.put("page_id", picoloButton.getPageId());
                break;
        }

        return button;
    }


}
