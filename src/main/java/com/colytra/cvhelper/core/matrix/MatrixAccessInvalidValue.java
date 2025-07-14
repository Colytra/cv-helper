package com.colytra.cvhelper.core.matrix;

class MatrixAccessInvalidValue extends RuntimeException {
    public MatrixAccessInvalidValue(String msg) {
        super(msg);
    }
    public static void exception(int col, int row, Matrix matrix) {
        int maxCols = matrix.getCols();
        int maxRows = matrix.getRows();
        throw new MatrixAccessInvalidValue("Position goes beyond the dimensions! column=" + col + ", row=" + row + " max size=" + maxCols + "," + maxRows);
    }
    public static void exception(int row, Matrix matrix) {
        int maxCols = matrix.getCols();
        int maxRows = matrix.getRows();
        throw new MatrixAccessInvalidValue("Position goes beyond the dimensions! row=" + row + " max rows " + maxRows);
    }
    public static void exception(Matrix matrix, int col) {
        int maxCols = matrix.getCols();
        int maxRows = matrix.getRows();
        throw new MatrixAccessInvalidValue("Position goes beyond the dimensions! column=" + col + " max columns " + maxCols);
    }
}