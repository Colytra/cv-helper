package com.colytra.cvhelper.core;

public class Rect {
    private int x;
    private int y;
    private int width;
    private int height;

    public Rect() {}

    public Rect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rect(int x, int y, Size size) {
        this.x = x;
        this.y = y;
        this.width = size.getWidth();
        this.height = size.getHeight();
    }

    public Rect(Point point, Size size) {
        this.x = point.getY();
        this.y = point.getY();
        this.width = size.getWidth();
        this.height = size.getHeight();
    }

    public int[] getValues() {
        return new int[] {x,y,width,height};
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rect{x=" +
                this.x + ", y=" +
                this.y + ", width=" +
                this.width + ", height=" +
                this.height + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;

        Rect obj = (Rect) o;
        return obj.x == this.x
                && obj.y == this.y
                && obj.width == this.width
                && obj.height == this.height;
    }
}