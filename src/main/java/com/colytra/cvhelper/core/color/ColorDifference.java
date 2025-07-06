package com.colytra.cvhelper.core.color;

public class ColorDifference {
    private int redDiff;
    private int greenDiff;
    private int blueDiff;

    public ColorDifference(int difference) {
        this.redDiff = difference;
        this.greenDiff = difference;
        this.blueDiff = difference;
    }

    public ColorDifference(int red, int green, int blue) {
        this.redDiff = red;
        this.greenDiff = green;
        this.blueDiff = blue;
    }

    public int getBlueDiff() {
        return blueDiff;
    }

    public void setBlueDiff(int blueDiff) {
        this.blueDiff = blueDiff;
    }

    public int getGreenDiff() {
        return greenDiff;
    }

    public void setGreenDiff(int greenDiff) {
        this.greenDiff = greenDiff;
    }

    public int getRedDiff() {
        return redDiff;
    }

    public void setRedDiff(int redDiff) {
        this.redDiff = redDiff;
    }
}
