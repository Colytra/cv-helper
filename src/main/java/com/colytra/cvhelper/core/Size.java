package com.colytra.cvhelper.core;

import java.io.Serializable;

public class Size implements Serializable {
    private int width;
    private int height;

    public Size(int width, int height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width < 0) throw new IllegalArgumentException("Width cannot be negative!");
        this.width = width;
    }

    public int getHeight() {
        if (height < 0) throw new IllegalArgumentException("Height cannot be negative!");
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Size{width=" +
                this.width + ", height=" +
                this.height + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;

        Size obj = (Size) o;
        return obj.width == this.width
                && obj.height == this.height;
    }
}
