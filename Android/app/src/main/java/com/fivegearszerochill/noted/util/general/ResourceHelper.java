package com.fivegearszerochill.noted.util.general;

import android.content.Context;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.models.ColorModel;

import java.util.ArrayList;
import java.util.List;

public class ResourceHelper {

    public static List<ColorModel> getColorResourceList(Context context) {
        List<ColorModel> models = new ArrayList<>();

        String[] names = context.getResources().getStringArray(R.array.colorNameList);
        int[] ids = context.getResources().getIntArray(R.array.colorNumberList);
        if (ids.length != names.length) {
            throw new NullPointerException("The names list is of length " + names.length + " while the ids list is of length" + ids.length);
        }
        for (int i = 0; i < ids.length; i++) {
            models.add(new ColorModel(ids[i], names[i]));
        }
        return models;
    }
}
