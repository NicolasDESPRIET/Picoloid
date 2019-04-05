package com.example.picoloid.source.managerData;


import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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

    public static void writer(File file, String data){
        BufferedWriter writer = null;
        try {
            FileWriter out = new FileWriter(file);
            writer = new BufferedWriter(out);
            writer.write(data);
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

    public static void InitFile(String jsonString, Context context){
        //File sdLien = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

        File sdLien = new File(context.getFilesDir().getPath());

        File monFichier = new File(sdLien, "DataPicoloid.json");

        if (monFichier.exists()){
            //Toast.makeText(context, "file exist"+ monFichier.getAbsolutePath(), Toast.LENGTH_LONG).show();
        }else{
            writer(monFichier,jsonString);
        }
    }

    public static void saveDataOnFiles(Context context, String data){
        File sdLien = new File(context.getFilesDir().getPath());
        File file = new File(sdLien, "DataPicoloid.json");

        writer(file,data);
    }


    public static String readOnFile(Context context){

        File sdLien = new File(context.getFilesDir().getPath());
        File file = new File(sdLien, "DataPicoloid.json");

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
