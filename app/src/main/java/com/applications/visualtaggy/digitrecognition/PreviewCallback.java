package com.applications.visualtaggy.digitrecognition;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.view.Gravity;
import android.widget.Toast;


class PreviewCallback implements Camera.PreviewCallback {

    private Rect frameContentsRect;
    private NNet neuralNet;
    private FinderViewActivity finderViewActivity;
    private Point cameraResolution;

    PreviewCallback(Rect frameContentsRect, Point cameraResolution, NNet neuralNet, FinderViewActivity finderViewActivity) {

        this.frameContentsRect = frameContentsRect;
        this.cameraResolution = cameraResolution;
        this.neuralNet = neuralNet;
        this.finderViewActivity = finderViewActivity;


    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

        Bitmap frameContentsBitmap = PreviewDataManager.getFrameContentsBitmap(data, frameContentsRect, cameraResolution);
        RecognitionResult recognitionResult = neuralNet.recogniseDigit(frameContentsBitmap);

        if (!recognitionResult.recognitionIsSuccessful()) {
            Toast toast = Toast.makeText(finderViewActivity, "Digit recognition failed. Please try again.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
            return;
        }else{
            String messageText = "The digit is "+recognitionResult.getRecognisedDigit()+"\n" +
                                         " I'm "+(int)(recognitionResult.getRecognisedDigitProbability()*10000)/100.+" % sure\n" +
                                         "Brightness gap is "+(recognitionResult.brightnessGapIsPresent()?"":"NOT")+" present";
            Toast toast = Toast.makeText(finderViewActivity, messageText, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();

        }

        Bitmap greyBitmap784 = neuralNet.getBitmapFromArray(recognitionResult.getGreyValues(), 28, 28);
        // displaying processed frame picture for 3 seconds

        finderViewActivity.displayBitmapInFrameForAWhile(greyBitmap784, 3000);
        finderViewActivity.setShutterButtonClickable(true);

    }
}
