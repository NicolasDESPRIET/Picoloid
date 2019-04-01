package com.example.picoloid.source.managerData;


import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {

    public static String readJsonFromAsset(Context context, String file) throws IOException {
        String json = null;

        InputStream is = context.getAssets().open(file);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        json = new String(buffer, StandardCharsets.UTF_8);

        return json;
    }

    public static void writeFile(String fileName, String jsonString, Context context) {

        File sdLien = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File monFichier = new File(sdLien, fileName + ".json");
        BufferedWriter writer = null;
        try {
            FileWriter out = new FileWriter(monFichier);
            writer = new BufferedWriter(out);
            writer.write(jsonString);
            Toast.makeText(context, sdLien.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readOnFile(Context context, String fileName){

        File sdLien = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(sdLien, fileName + ".json");

        String result = null;
        if (file.exists()) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(file));
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();
                    while (line != null) {
                        sb.append(line);
                        sb.append("\n");
                        line = br.readLine();
                    }
                    result = sb.toString();
                }
                finally {
                    br.close();
                }
            }
            catch (IOException e) {
                Toast.makeText(context, "error" , Toast.LENGTH_LONG).show();
            }
        }

        return result;
    }

}
