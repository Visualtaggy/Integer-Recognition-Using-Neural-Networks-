package com.applications.visualtaggy.digitrecognition;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;


class PreviewDataManager {

    static Bitmap getFrameContentsBitmap(byte[] data, Rect frameContentsRect, Point cameraResolution) {

        int frameWidth = frameContentsRect.width();
        int frameheight = frameContentsRect.height();
        int frameTop = frameContentsRect.top;
        int frameLeft = frameContentsRect.left;


        int[] frameData = new int[frameheight*frameWidth];

        int idx = 0;
        for (int h = 0; h < frameheight; h++) {
            int offset = (frameTop+h-1)*cameraResolution.x+frameLeft;
            for (int w = 0; w < frameWidth; w++) {

                //extract grey(Y channel of ) data from image and put it into ARGB_8888 format
                //the latter is stored in 4 bytes, a byte for each channel

                int grey = data[offset+w] & 0xff;
                frameData[idx++] = 0xFF000000 | (grey * 0x00010101);

            }
        }

        Bitmap bitmap = Bitmap.createBitmap(frameWidth, frameheight, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(frameData, 0, frameWidth, 0, 0, frameWidth, frameheight);


        return bitmap;

    }

}