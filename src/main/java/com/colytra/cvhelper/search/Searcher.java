package com.colytra.cvhelper.search;

import com.colytra.cvhelper.core.color.Color;
import com.colytra.cvhelper.core.color.ColorDifference;
import com.colytra.cvhelper.core.Point;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    public static Point findFirst(int[][] matrix, int[][] subMatrix, ColorDifference difference, int mismatch) {
        if (subMatrix.length > matrix[0].length || subMatrix[0].length > matrix.length) {
            return null;
        }
        for (int i = 0; i <= matrix.length - subMatrix.length; i++) {
            for (int j = 0; j <= matrix[0].length - subMatrix[0].length; j++) {
                if (matchesAt(matrix, subMatrix, i, j,difference,mismatch)) {
                    return new Point(i,j);
                }
            }
        }
        return null;
    }

    public static Point findFirst(int[][] matrix, int[][][] subMatrices, ColorDifference difference, int mismatch) {
        if (subMatrices.length > matrix[0].length || subMatrices[0].length > matrix.length) {
            return null;
        }
        for (int i = 0; i <= matrix.length - subMatrices.length; i++) {
            for (int j = 0; j <= matrix[0].length - subMatrices[0].length; j++) {
                for (int[][] matrices : subMatrices) {
                    if (matchesAt(matrix, matrices, i, j, difference, mismatch)) {
                        return new Point(i, j);
                    }
                }
            }
        }
        return null;
    }

    public static List<Point> findAll(int[][] matrix, int[][] subMatrix, ColorDifference difference, int mismatch, int skip) {
        List<Point> points = new ArrayList<>();

        int rows = matrix.length;
        int cols = matrix[0].length;
        int subRows = subMatrix.length;
        int subCols = subMatrix[0].length;

        if (subRows > cols || subCols > rows) {
            return null;
        }

        boolean bool;

        for (int i = 0; i <= rows - subRows; i++) {
            bool = false;
            for (int j = 0; j <= cols - subCols; j++) {
                if (matchesAt(matrix, subMatrix, i, j,difference,mismatch)) {
                    points.add(new Point(i,j));
                    bool = true;
                    j += skip;
                }
            }
            if (bool) i += skip;
        }

        return points;
    }

    public static List<Point> findAll(int[][] matrix, int[][][] subMatrices, ColorDifference difference, int mismatch, int skip) {
        List<Point> points = new ArrayList<>();

        int rows = matrix.length;
        int cols = matrix[0].length;
        int subRows = subMatrices[0].length;
        int subCols = subMatrices[0][0].length;

        boolean bool;

        for (int i = 0; i <= rows - subRows; i++) {
            bool = false;
            for (int j = 0; j <= cols - subCols; j++) {
                for (int[][] matrices : subMatrices) {
                    if (matchesAt(matrix, matrices, i, j, difference, mismatch)) {
                        points.add(new Point(i, j));
                        bool = true;
                        j += skip;
                    }
                }
            }
            if (bool) i += skip;
        }
        return points;
    }

    private static boolean matchesAt(int[][] bigMatrix, int[][] smallMatrix, int startRow, int startCol, ColorDifference difference, int mismatch) {
        int x = 0;
        if (startRow + smallMatrix.length > bigMatrix.length || startCol + smallMatrix[0].length > bigMatrix[0].length)
            return false;
        for (int i = 0; i < smallMatrix.length; i++) {
            for (int j = 0; j < smallMatrix[0].length; j++) {
                if (x > mismatch)
                    return false;
                if (!Color.equals(bigMatrix[startRow + i][startCol + j], smallMatrix[i][j], difference)) {
                    x++;
                }
            }
        }
        return x <= mismatch;
    }
}
