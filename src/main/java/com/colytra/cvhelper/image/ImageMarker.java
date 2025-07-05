package com.colytra.cvhelper.image;

import com.colytra.cvhelper.core.Point;
import com.colytra.cvhelper.core.matrix.Matrix;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImageMarker {
    private final BufferedImage image;
    public ImageMarker(BufferedImage image) {
        this.image = image;
    }

    public ImageMarker markBySquare(List<Point> points, int markColor, int w, int h) {
        points.forEach(p -> {
            try {
                for (int x = 0; x < w / 2; x++) {
                    for (int y = 0; y < h / 2; y++) {
                        image.setRGB(p.getX() + x - (w/2),p.getY() + y - (h/2),markColor);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignore) {}
        });
        return this;
    }

    public ImageMarker markByMatrix(List<Point> points, Matrix matrix) {
        points.forEach(p -> {
            try {
                for (int x = 0; x < matrix.getCols(); x++) {
                    for (int y = 0; y < matrix.getRows(); y++) {
                        image.setRGB(p.getX() + x - (matrix.getCols()/2),p.getY() + y - (matrix.getRows()/2),matrix.get(x,y));
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignore) {}
        });
        return this;
    }

    public ImageMarker markByDot(List<Point> points, int markColor) {
        points.forEach(p -> image.setRGB(p.getX(),p.getY(),markColor));
        return this;
    }

    public ImageMarker markByText(List<Point> points, int markColor, String text) {
        Graphics2D g2 = image.createGraphics();
        g2.setColor(new Color(markColor));
        if (!points.isEmpty())
            points.forEach(p -> g2.drawString(text,p.getX(),p.getY()));
        return this;
    }

    public ImageMarker markByText(Point p, int markColor, String text) {
        Graphics2D g2 = image.createGraphics();
        g2.setColor(new Color(markColor));
        if (text != null)
            g2.drawString(text,p.getX(),p.getY());
        return this;
    }

    public BufferedImage getImage() {
        return image;
    }
}