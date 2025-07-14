package com.colytra.cvhelper.core.color;

public class ColorDifference {
    private byte redDiff;
    private byte greenDiff;
    private byte blueDiff;

    public ColorDifference(byte difference) {
        this.redDiff = difference;
        this.greenDiff = difference;
        this.blueDiff = difference;
    }

    public ColorDifference(byte red, byte green, byte blue) {
        this.redDiff = red;
        this.greenDiff = green;
        this.blueDiff = blue;
    }

    public byte getBlueDiff() {
        return blueDiff;
    }

    public void setBlueDiff(byte blueDiff) {
        this.blueDiff = blueDiff;
    }

    public byte getGreenDiff() {
        return greenDiff;
    }

    public void setGreenDiff(byte greenDiff) {
        this.greenDiff = greenDiff;
    }

    public byte getRedDiff() {
        return redDiff;
    }

    public void setRedDiff(byte redDiff) {
        this.redDiff = redDiff;
    }
}
