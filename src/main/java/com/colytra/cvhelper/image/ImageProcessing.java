package com.colytra.cvhelper.image;

import com.colytra.cvhelper.core.color.Color;
import com.colytra.cvhelper.core.matrix.Matrix;

public class ImageProcessing {
    public static Matrix compressMatrix(Matrix matrix, int compressPower) {
        if (compressPower % 2 != 0)
            throw new IllegalArgumentException("the power value must be even. (" + compressPower + " % 2 != 0!)  correct values: 2, 4, 6, 8, 10...");
        Matrix res = matrix;
        for (int i = 0; i < compressPower / 2; i++) {
            res = compress2x(res);
        }
        return res;
    }

    private static Matrix compress2x(Matrix matrix) {
        Matrix res = new Matrix(matrix.getCols() / 2,matrix.getRows() / 2);

        for (int y = 1; y < res.getRows(); y++) {
            for (int x = 1; x < res.getCols(); x++) {
                res.put(x - 1,y - 1, Color.getAverageColor(matrix.get(x * 2 - 1,y * 2 - 1),matrix.get((x - 1) * 2,(y - 1) * 2)));
            }
        }
        return res;
    }
}
