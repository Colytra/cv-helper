package com.colytra.cvhelper.viewer.console;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ConsoleWindow extends JFrame {
    private final Timer timer;
    private final ConsoleFunction function;
    private int delay;
    public ConsoleWindow(String title, int delay, int x, int y, int width, int height, boolean alwaysOnTop, ConsoleFunction function) {
        this.function = function;
        this.setTitle(title);
        this.setSize(width,height);
        this.setLocation(x,y);
        this.setAlwaysOnTop(alwaysOnTop);
        this.setVisible(true);

        JTextArea area = new JTextArea();
        area.setFont(new Font("",Font.PLAIN,16));
        timer = new Timer(delay,e -> {
            try {
                area.setText(function.log(0));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        this.add(area);
        timer.start();
    }
}