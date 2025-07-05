package com.colytra.cvhelper.search;

import com.colytra.cvhelper.core.color.Color;
import com.colytra.cvhelper.core.color.ColorDifference;
import com.colytra.cvhelper.core.Point;
import com.colytra.cvhelper.core.matrix.Matrix;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Searcher {
    public static Point findFirst(Matrix matrix, Matrix subMat, ColorDifference difference, int mismatch) {
        for (int i = 0; i <= matrix.getRows() - subMat.getRows(); i++) {
            for (int j = 0; j <= matrix.getCols() - subMat.getCols(); j++) {
                if (matchesAt(matrix, subMat, i,j, difference, mismatch)) {
                    return new Point(j,i);
                }
            }
        }
        return null;
    }

    public static Point findFirst(Matrix matrix, List<Matrix> subMats, ColorDifference difference, int mismatch) {
        for (int i = 0; i <= matrix.getRows() - subMats.stream().max(Comparator.comparingInt(Matrix::getRows)).get().getRows(); i++) {
            for (int j = 0; j <= matrix.getCols() - subMats.stream().max(Comparator.comparingInt(Matrix::getCols)).get().getCols(); j++) {
                for (Matrix subMat : subMats) {
                    if (matchesAt(matrix, subMat, i, j, difference, mismatch)) {
                        return new Point(j,i);
                    }
                }
            }
        }
        return new Point();
    }

    public static List<Point> findAll(Matrix matrix, List<Matrix> subMats, ColorDifference difference, int mismatch, int skip) {
        List<Point> points = new ArrayList<>();
        boolean bool;

        for (int i = 0; i <= matrix.getRows() - subMats.stream().max(Comparator.comparingInt(Matrix::getRows)).get().getRows(); i++) {
            bool = false;
            for (int j = 0; j <= matrix.getCols() - subMats.stream().max(Comparator.comparingInt(Matrix::getCols)).get().getCols(); j++) {
                for (int k = 0; k < subMats.size(); k++) {
                    if (matchesAt(matrix, subMats.get(k), i, j,difference,mismatch)) {
                        points.add(new Point(j,i));
                        bool = true;
                        j += skip;
                    }
                }
            }
            if (bool) i += skip;
        }

        return points;
    }


    public static List<Point> findAll(Matrix matrix, Matrix subMat, ColorDifference difference, int mismatch, int skip) {
        List<Point> res = new ArrayList<>();
        if (!matrix.isSubMatrix(subMat))
            return res;
        boolean bool;
        for (int i = 0; i <= matrix.getRows() - subMat.getRows(); i++) {
            bool = false;
            for (int j = 0; j <= matrix.getCols() - subMat.getCols(); j++) {
                if (matchesAt(matrix, subMat, i,j,difference,mismatch)) {
                    res.add(new Point(j,i));
                    bool = true;
                    j += skip;
                }
            }
            if (bool) i += skip;
        }
        return res;
    }

    private static boolean matchesAt(Matrix matrix, Matrix subMat, int startRow, int startCol, ColorDifference difference, int mismatch) {
        int x = 0;
        if (startRow + subMat.getRows() > matrix.getRows() || startCol + subMat.getCols() > matrix.getCols())
            return false;
        for (int i = 0; i < subMat.getRows(); i++) {
            for (int j = 0; j < subMat.getCols(); j++) {
                if (x > mismatch)
                    return false;
                if (!Color.equals(matrix.get(startCol + j,startRow + i), subMat.get(j,i), difference)) {
                    x++;
                }
            }
        }
        return x <= mismatch;
    }
}
