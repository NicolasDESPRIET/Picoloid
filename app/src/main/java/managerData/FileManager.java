package managerData;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

public class FileManager {
    public FileManager() {
    }

    public void createFolder(Context context){
        File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"Picoloid");
        if (!directory.exists()) {
            directory.mkdirs();
            Toast.makeText(context,directory.getAbsolutePath(),Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context,"directory already exist :" + directory.getAbsolutePath(),Toast.LENGTH_LONG).show();
        }
    }
    
    //TODO
}
