package com.colytra.cvhelper.image;

import com.colytra.cvhelper.core.matrix.Matrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageMatrix {
    /**
     Method for parsing image to matrix of pixels
     @param image is the image, that need to parse
     */
    public static Matrix parseImageToMatrix(BufferedImage image) {
        Matrix res = new Matrix(image.getWidth(),image.getHeight());
        int y;
        for (int i = 0; i < image.getWidth() * image.getHeight(); i++) {
            y = i / image.getWidth();
            res.put(i - (image.getWidth() * y),y, image.getRGB(i - (image.getWidth() * y),y));
        }
        return res;
    }

    public static Matrix parseImageToMatrix(File file) {
        BufferedImage image;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            return new Matrix();
        }
        Matrix res = new Matrix(image.getWidth(),image.getHeight());
        int y;
        for (int i = 0; i < image.getWidth() * image.getHeight(); i++) {
            y = i / image.getWidth();
            res.put(i - (image.getWidth() * y),y, image.getRGB(i - (image.getWidth() * y),y));
        }
        return res;
    }

    public static Matrix parseImageToMatrix(String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            return new Matrix();
        }
        Matrix res = new Matrix(image.getWidth(),image.getHeight());
        int y;
        for (int i = 0; i < image.getWidth() * image.getHeight(); i++) {
            y = i / image.getWidth();
            res.put(i - (image.getWidth() * y),y, image.getRGB(i - (image.getWidth() * y),y));
        }
        return res;
    }

    public static void saveAsImage(Matrix matrix, String path, String format) {
        try {
            ImageIO.write(parseMatrixToImage(matrix),format,new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage parseMatrixToImage(Matrix matrix) {
        BufferedImage res = new BufferedImage(matrix.getCols(),matrix.getRows(),BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < matrix.getCols(); x++) {
            for (int y = 0; y < matrix.getRows(); y++) {
                res.setRGB(x,y,matrix.get(x,y));
            }
        }
        return res;
    }
}