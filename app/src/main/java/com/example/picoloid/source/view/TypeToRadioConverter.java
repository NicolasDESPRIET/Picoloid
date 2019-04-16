package com.example.picoloid.source.view;

import com.example.picoloid.R;
import com.example.picoloid.source.model.PicoloButtonType;

import static com.example.picoloid.source.model.PicoloButtonType.IMAGE;
import static com.example.picoloid.source.model.PicoloButtonType.NONE;
import static com.example.picoloid.source.model.PicoloButtonType.PAGE;
import static com.example.picoloid.source.model.PicoloButtonType.SOUND;
import static com.example.picoloid.source.model.PicoloButtonType.VIDEO;

public class TypeToRadioConverter {

    public static PicoloButtonType convertRadioButtonIdToState(int id){
        switch(id){
            case R.id.buttonEditor_Type_None:
                return NONE;
            case R.id.buttonEditor_Type_Image:
                return IMAGE;
            case R.id.buttonEditor_Type_Video:
                return VIDEO;
            case R.id.buttonEditor_Type_Sound:
                return SOUND;
            case R.id.buttonEditor_Type_Page:
                return PAGE;
            default:
                return NONE;
        }
    }

    public static int convertTypeToRadioButtonId(PicoloButtonType type){
        switch(type){
            case NONE:
                return R.id.buttonEditor_Type_None;
            case IMAGE:
                return R.id.buttonEditor_Type_Image;
            case VIDEO:
                return R.id.buttonEditor_Type_Video;
            case SOUND:
                return R.id.buttonEditor_Type_Sound;
            case PAGE:
                return R.id.buttonEditor_Type_Page;
            default:
                return R.id.buttonEditor_Type_None;
        }
    }

}
