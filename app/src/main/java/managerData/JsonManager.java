package managerData;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {
    public JsonManager() {
    }

    public String readJsonFromAsset(Context context, String file) throws IOException{
        String json = null;

        InputStream is = context.getAssets().open(file);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        json = new String(buffer, StandardCharsets.UTF_8);

        return json;
    }

    //String path = context.getFilesDir().getAbsolutePath(); retour le chemin du filedirectory

    public String readJsonFromFile(Context context, String file) throws IOException{
        String json = null;

        InputStream is = context.openFileInput(file);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        json = new String(buffer, StandardCharsets.UTF_8);

        return json;
    }

    public JSONObject jsonStringToObject(String json) throws JSONException{
        return new JSONObject(json);
    }

    public String jsonObjectToString(JSONObject json) throws JSONException{
        return json.toString(2);
    }

    public JSONArray getJsonArray(JSONObject json, String nametab) throws JSONException{
        return json.getJSONArray(nametab);
    }

    public Integer getIntValFromJson(JSONObject json, String nameVal) throws JSONException{
        return json.getInt(nameVal);
    }

    public Double getDoubleValFromJson(JSONObject json, String nameVal) throws JSONException{
        return json.getDouble(nameVal);
    }

    public String getStringValFromJson(JSONObject json, String nameVal) throws JSONException{
        return json.getString(nameVal);
    }

    public void setArrayInJson(JSONObject json, JSONArray jsonArray, String nameVal) throws JSONException{
        json.put(nameVal, jsonArray);
    }

    public void setIntValInJson(JSONObject json, String nameVal, int val) throws JSONException{
        json.put(nameVal, val);
    }

    public void setDoubleValInJson(JSONObject json, String nameVal, double val) throws JSONException{
        json.put(nameVal,val);
    }

    public void setStringValInJson(JSONObject json, String nameVal, String val) throws JSONException{
        json.put(nameVal,val);
    }

    public List<JSONObject> getArrayformJarray(JSONArray Jarray) throws JSONException{
        List<JSONObject> list = new ArrayList<JSONObject>();
        for (int i=0; i<Jarray.length(); i++) {
            list.add( jsonStringToObject(Jarray.getString(i)) );
        }
        return list;
    }

    public JSONArray setJArrayFromArray(ArrayList<JSONObject> list) throws JSONException{
        List<String> Slist = new ArrayList<String>();
        JSONArray Jarray = null;
        for (int i = 0; i < list.size(); i++){
            Slist.add(jsonObjectToString(list.get(i)));
        }
        Jarray = new JSONArray(Slist);
        return Jarray;
    }
}
