package com.bancusoft.statdataexplorer.utils;

import android.content.Context;
import android.content.Intent;

public class Utils {

    /**
     * Deschide o activitate nouă dintr-un context dat.
     * @param from Contextul sursă (de ex. Activity curentă)
     * @param to Clasa activității țintă
     */
    public static void openActivity(Context from, Class<?> to) {
        Intent intent = new Intent(from, to);
        from.startActivity(intent);
    }
}
