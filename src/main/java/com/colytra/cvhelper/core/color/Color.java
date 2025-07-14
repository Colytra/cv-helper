package com.colytra.cvhelper.core.color;

public class Color {
    public static boolean equals(int firstColor, int secondColor) {
        return firstColor == secondColor;
    }

    public static byte getPerceivedBrightness(int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        return (byte) (-128 + (int)(0.299 *  r + 0.587 * g + 0.114 * b));
    }

    public static int getAverageColor(int firstColor, int secondColor) {
        int a1 = (firstColor >> 24) & 0xFF;
        int r1 = (firstColor >> 16) & 0xFF;
        int g1 = (firstColor >> 8) & 0xFF;
        int b1 = firstColor & 0xFF;

        int a2 = (secondColor >> 24) & 0xFF;
        int r2 = (secondColor >> 16) & 0xFF;
        int g2 = (secondColor >> 8) & 0xFF;
        int b2 = secondColor & 0xFF;

        int avgA = (a1 + a2) / 2;
        int avgR = (r1 + r2) / 2;
        int avgG = (g1 + g2) / 2;
        int avgB = (b1 + b2) / 2;

        return (avgA << 24) | (avgR << 16) | (avgG << 8) | avgB;
    }

    public static boolean equals(int firstColor, int secondColor, ColorDifference difference) {
        int r1 = (firstColor >> 16) & 0xFF;
        int g1 = (firstColor >> 8) & 0xFF;
        int b1 = firstColor & 0xFF;

        int r2 = (secondColor >> 16) & 0xFF;
        int g2 = (secondColor >> 8) & 0xFF;
        int b2 = secondColor & 0xFF;

        return Math.abs(r1 - r2) <= (difference.getRedDiff() + 128) &&
                Math.abs(g1 - g2) <= (difference.getGreenDiff() + 128) &&
                Math.abs(b1 - b2) <= (difference.getBlueDiff() + 128);
    }

    public static int[] unpackARGB(int argb) {
        return new int[] {(argb >> 24) & 0xFF,(argb >> 16) & 0xFF,(argb >> 8)  & 0xFF,argb & 0xFF};
    }

    public static int[] unpackRGB(int rgb) {
        return new int[] {(rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF};
    }

    public static int packRGB(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }

    public static int packARGB(int r, int g, int b,int a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}