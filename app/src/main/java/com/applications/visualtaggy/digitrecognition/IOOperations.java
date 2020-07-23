package com.applications.visualtaggy.digitrecognition;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

final class IOOperations {

    private final String CLASS_TAG = NNet.class.getSimpleName();

    ArrayList<String> readDataFromAssets(Context context, String filename){

        BufferedReader reader = null;

        ArrayList<String> arrayList = new ArrayList<>();

        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                arrayList.add(mLine);
            }
        } catch (IOException e) {
            Log.e(CLASS_TAG, "IOException while reading file "+filename);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(CLASS_TAG, "IOException while closing file "+filename);
                }
            }
        }

        return arrayList;

    }


}
