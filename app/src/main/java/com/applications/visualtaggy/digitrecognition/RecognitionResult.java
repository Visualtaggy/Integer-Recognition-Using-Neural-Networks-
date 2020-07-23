package com.applications.visualtaggy.digitrecognition;


final class RecognitionResult {

    private int recognisedDigit;
    private boolean recognitionSuccess;
    private double recognisedDigitProbability;
    private double[] probabilityVector;
    private boolean brightnessGapIsPresent;
    private int[] greyValues;

    RecognitionResult(int recognisedDigit, double recognisedDigitProbability, double[] probabilityVector, int[] greyValues,
                      boolean recognitionSuccess, boolean brightnessGapIsPresent) {
        this.recognisedDigit = recognisedDigit;
        this.recognisedDigitProbability = recognisedDigitProbability;
        this.probabilityVector = probabilityVector;
        this.greyValues = greyValues;
        this.recognitionSuccess = recognitionSuccess;
        this.brightnessGapIsPresent = brightnessGapIsPresent;
    }

    int getRecognisedDigit() {
        return recognisedDigit;
    }

    double getRecognisedDigitProbability() {
        return recognisedDigitProbability;
    }

    public double[] getProbabilityVector() {
        return probabilityVector;
    }

    boolean recognitionIsSuccessful() {
        return recognitionSuccess;
    }

    boolean brightnessGapIsPresent() {
        return brightnessGapIsPresent;
    }

    int[] getGreyValues() {
        return greyValues;
    }
}
