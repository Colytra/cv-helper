package com.colytra.cvhelper.core;

public class Point {
    private int[] values;
    private int x;
    private int y;

    public Point() {}

    public Point(int[] values) {
        if (values.length >= 2) {
            this.x = values[0];
            this.y = values[1];
        }
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[] getValues() {
        return new int[] {this.x,this.y};
    }

    @Override
    public String toString() {
        return "Point{x=" +
                this.x + ", y=" +
                this.y + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;

        Point obj = (Point) o;
        return obj.x == this.x
                && obj.y == this.y;
    }
}
