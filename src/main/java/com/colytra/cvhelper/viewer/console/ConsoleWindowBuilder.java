package com.colytra.cvhelper.viewer.console;

import java.awt.image.BufferedImage;

public class ConsoleWindowBuilder {
    private BufferedImage image = null;
    private String title = "Console Window";
    private int width = 200;
    private int height = 200;
    private int delay = 10;
    private int x = 0;
    private int y = 0;
    private boolean alwaysOnTop = true;
    private ConsoleFunction function = e -> {return null;};

    public ConsoleWindowBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ConsoleWindowBuilder alwaysOnTop(boolean alwaysOnTop) {
        this.alwaysOnTop = alwaysOnTop;
        return this;
    }
    public ConsoleWindowBuilder x(int x) {
        this.x = x;
        return this;
    }
    public ConsoleWindowBuilder y(int y) {
        this.y = y;
        return this;
    }

    public ConsoleWindowBuilder delay(int delay) {
        this.delay = delay;
        return this;
    }

    public ConsoleWindowBuilder width(int width) {
        this.width = width;
        return this;
    }

    public ConsoleWindowBuilder height(int height) {
        this.height = height;
        return this;
    }

    public ConsoleWindowBuilder function(ConsoleFunction function) {
        this.function = function;
        return this;
    }

    public ConsoleWindow build() {
        return new ConsoleWindow(title,delay,x,y,width,height,alwaysOnTop, function);
    }

}
