package com.example.picoloid.source.dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.picoloid.source.activity.ButtonEditorActivity;

public class ImagePicker {
    private ButtonEditorActivity activity;
    private static final String VIDEO_DIRECTORY = "/PicoloRessources";
    private int GALLERY = 3, CAMERA = 4;

    public ImagePicker(ButtonEditorActivity activity){
        this.activity = activity;
    }

    public void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(activity);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select image from gallery",
                "Take image from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                chooseImageFromGallary();
                                break;
                            case 1:
                                takeImageFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void chooseImageFromGallary(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        activity.startActivityForResult(galleryIntent, GALLERY);
    }

    private void takeImageFromCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, CAMERA);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == activity.RESULT_CANCELED) {
            Log.d("what","cancle");
            return;
        }
        if (requestCode == GALLERY) {
            Log.d("what","gale");
            if (data != null) {
                Uri contentURI = data.getData();
                String selectedImagePath = getPath(contentURI);
                activity.setImagePath(selectedImagePath);
            }

        } else if (requestCode == CAMERA) {
            Uri contentURI = data.getData();
            String takenImagePath = getPath(contentURI);
            activity.setImagePath(takenImagePath);
        }

    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }
}
