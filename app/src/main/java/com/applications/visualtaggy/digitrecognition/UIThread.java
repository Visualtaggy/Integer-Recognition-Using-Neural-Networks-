package com.applications.visualtaggy.digitrecognition;

import android.util.Log;

final class UIThread extends Thread {

    private FinderViewActivity context;
    private int delayMS;
    private Runnable runnable;
    private final String CLASS_TAG = NNet.class.getSimpleName();


    UIThread(FinderViewActivity context, int delayMS, Runnable runnable) {
        this.context = context;
        this.delayMS = delayMS;
        this.runnable = runnable;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(delayMS);
        } catch (InterruptedException e) {
            Log.e(CLASS_TAG, "The thread didn't go to sleep");
        }

        context.runOnUiThread(runnable);

    }
}
