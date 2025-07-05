package com.colytra.cvhelper.viewer.window;

import java.awt.image.BufferedImage;

public class WindowBuilder {
    private int width = 200;
    private int height = 200;
    private int delay = 10;
    private WindowFunction function = () -> {return null;};

    public WindowBuilder delay(int delay) {
        this.delay = delay;
        return this;
    }

    public WindowBuilder width(int width) {
        this.width = width;
        return this;
    }

    public WindowBuilder height(int height) {
        this.height = height;
        return this;
    }

    public WindowBuilder function(WindowFunction function) {
        this.function = function;
        return this;
    }

    public CVWindow build() {
        return new CVWindow(width,height,delay,function);
    }
}
