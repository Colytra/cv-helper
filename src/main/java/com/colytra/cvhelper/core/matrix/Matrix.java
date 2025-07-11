package com.colytra.cvhelper.core.matrix;

import com.colytra.cvhelper.core.Point;
import com.colytra.cvhelper.core.Rect;
import com.colytra.cvhelper.core.Size;

import java.util.concurrent.atomic.AtomicBoolean;

public class Matrix {
    public static int COL_MAJOR_ORDER = 1;
    public static int ROW_MAJOR_ORDER = 0;

    private int[][] mat;

    private int rows;

    private int cols;

    public Matrix() {}
    public Matrix(int[][] mat) {
        this.convert(mat);
    }

    public Matrix(int cols, int rows) {
        this.create(cols,rows);
    }

    public Matrix(Size size) {
        this.create(size);
    }

    public boolean isSubMatrix(Matrix submatrix) {
        return submatrix.rows <= this.rows && submatrix.cols <= this.cols;
    }

    public static Matrix valueOf(int[][] mat) {
        Matrix res = new Matrix(mat.length,mat[0].length);
        int[][] matrix = new int[mat.length][mat[0].length];
        for (int x = 0; x < mat.length; x++) {
            System.arraycopy(matrix[x], 0, res.mat[x], 0, matrix[0].length);
        }
        return res;
    }

    public void convert(int[][] mat) {
        this.setCols(mat.length);
        this.setRows(mat[0].length);
        this.mat = new int[mat.length][mat[0].length];
        for (int x = 0; x < mat.length; x++) {
            System.arraycopy(mat[x], 0, this.mat[x], 0, mat[0].length);
        }
    }

    public void create(Size size) {
        this.setCols(size.getWidth());
        this.setRows(size.getHeight());
        mat = new int[size.getWidth()][size.getHeight()];
    }

    public void create(int cols, int rows) {
        this.setCols(cols);
        this.setRows(rows);
        mat = new int[cols][rows];
    }

    public void forEach(MatrixFunction function) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                function.run(j,i);
            }
        }
    }

    public void forEach(SimpleMatrixFunction function) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                function.run((i * rows) + j);
            }
        }
    }

    public boolean isEmpty() {
        return mat == null;
    }

    public void forEach(int major, MatrixFunction function) {
        if (major == ROW_MAJOR_ORDER) {
            this.forEach(function);
        } else if (major == COL_MAJOR_ORDER){
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                    function.run(i,j);
                }
            }
        }
    }

    public void forEach(int major, SimpleMatrixFunction function) {
        if (major == ROW_MAJOR_ORDER) {
            this.forEach(function);
        } else if (major == COL_MAJOR_ORDER){
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                    function.run((i * cols) + j);
                }
            }
        }
    }

    public Matrix submatrix(Point p, int width, int height) {
        if (width + p.getX() > cols ||height + p.getY() > rows
                || width <= 0 || height <= 0
                || p.getX() < 0 || p.getY() < 0) throw new IllegalArgumentException("Area goes beyond the dimensions! startCol=" + p.getY() + ", startRow=" + p.getY() + ", width=" + width + ", height="+ height);

        Matrix res = new Matrix(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                res.put(x,y,mat[p.getX() + x][p.getY() + y]);
            }
        }
        return res;
    }

    public Matrix submatrix(int startCol, int startRow, int width, int height) {
        if (width + startCol > cols ||height + startRow > rows
                || width <= 0 || height <= 0
                || startCol < 0 || startRow < 0) throw new IllegalArgumentException("Area goes beyond the dimensions! startCol=" + startCol + ", startRow=" + startRow + ", width=" + width + ", height="+ height);

        Matrix res = new Matrix(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                res.put(x,y,mat[startCol + x][startRow + y]);
            }
        }
        return res;
    }

    public Matrix submatrix(Rect rect) {
        if (rect.getWidth() + rect.getX() > cols || rect.getHeight() + rect.getY() > rows
                || rect.getWidth() <= 0 || rect.getHeight() <= 0
                || rect.getX() < 0 || rect.getY() < 0) throw new IllegalArgumentException("Rectangle goes beyond the dimensions! " + rect);

        Matrix res = new Matrix(rect.getWidth(), rect.getHeight());
        for (int x = 0; x < rect.getWidth(); x++) {
            for (int y = 0; y < rect.getHeight(); y++) {
                res.put(x,y,mat[rect.getX() + x][rect.getY() + y]);
            }
        }
        return res;
    }

    public int[][] getMat() {
        return mat;
    }

    public Matrix getCol(int col) {
        if ((col > (cols - 1) || col < 0)) MatrixAccessInvalidValue.exception(this,col);

        Matrix res = new Matrix(1,rows);
        res.put(0,0,this.mat[col]);
        return res;
    }

    public Matrix getRow(int row) {
        if ((row > (rows - 1) || row < 0 )) MatrixAccessInvalidValue.exception(row,this);
        Matrix res = new Matrix(cols,1);

        for (int i = 0; i < cols; i++) {
            res.put(i,0,this.mat[i][row]);
        }
        return res;
    }

    public void put(int col, int row, int... data) {
        if ((row > (rows - 1) || col > (cols - 1)) || col < 0 || row < 0 || (row * rows) + col + data.length > rows * cols) MatrixAccessInvalidValue.exception(col,row,this);

        int x;
        for (int i = 0; i < data.length; i++) {
            x = (col + i) / (cols) * cols;
            put((col + i) - x,row + x / cols,data[i]);
        }
    }

    public void put(int col, int row, int data) {
        if (row > (rows - 1) || col > (cols - 1) || col < 0 || row < 0) MatrixAccessInvalidValue.exception(col,row,this);
        this.mat[col][row] = data;
    }

    public int get(int col, int row) {
        if (row > rows || col > cols) MatrixAccessInvalidValue.exception(col,row,this);
        return mat[col][row];
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows < 0) throw new IllegalArgumentException("Rows cannot be negative!");
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        if (cols < 0) throw new IllegalArgumentException("Columns cannot be negative!");
        this.cols = cols;
    }

    public String dataToStringCorrectly() {
        StringBuilder builder = new StringBuilder("[");
        Matrix col = getCol(0);
        for (int i = 0; i < rows; i++) {
            int max = 0;
            for (int j = 0; j < col.rows; j++) {
                if (String.valueOf(col.get(0,j)).length() > String.valueOf(max).length())
                    max = String.valueOf(col.get(0,j)).length();
            }
            for (int j = 0; j < cols; j++) {
                col = getCol(j);
                for (int k = 0; k < max - String.valueOf(mat[j][i]).length(); k++) {
                    builder.append(" ");
                }
                builder.append(mat[j][i]).append(", ");
            }
            builder.append("\n ");
        }
        builder.delete(builder.length() - 4,builder.length()).append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;

        Matrix obj = (Matrix) o;
        if (cols != obj.getCols() || rows != obj.getRows())
            return false;

        AtomicBoolean b = new AtomicBoolean(true);

        forEach(((col, row) -> {
            if (mat[col][row] != obj.get(col,row)) {
                b.set(false);
            }
        }));
        return b.get();
    }

    @Override
    public Matrix clone() {
        Matrix matrix = new Matrix(cols,rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.put(j,i,mat[j][i]);
            }
        }
        return matrix;
    }

    public String dataToString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                builder.append(mat[j][i]).append(", ");
            }
            builder.append("\n ");
        }
        builder.delete(builder.length() - 4,builder.length()).append("]");
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Matrix:{columns=" + this.cols + ", rows=" + this.rows + "}";
    }
}

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