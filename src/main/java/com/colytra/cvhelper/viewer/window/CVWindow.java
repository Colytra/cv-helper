package com.colytra.cvhelper.viewer.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CVWindow extends JFrame {
    private final int width;
    private final int height;
    private int delay;
    private BufferedImage image;
    private final WindowFunction function;
    private double scale = 1;
    private int x = 1;
    private int y = 1;


    private final JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.scale(scale,scale);
            if (image != null)
                g2.drawImage(image.getScaledInstance(image.getWidth(),image.getHeight(),1),x,y,this);
        }
    };

    private Timer timer;

    public CVWindow(int width, int height, int delay, WindowFunction function) {
        this.function = function;
        this.delay = delay;
        this.width = width;
        this.height = height;
        init();
    }

    private void init() {
        setTitle("test");
        setType(Type.POPUP);
        setSize(width,height);
        setContentPane(panel);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_EQUALS)
                    scale += 0.1;
                if (e.getKeyCode() == KeyEvent.VK_MINUS)
                    scale -= 0.1;
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    y += 4;
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    y -= 4;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    x -= 4;
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    x += 4;
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setVisible(true);
        timer = new Timer(delay,e -> {
            try {
                long startTime = System.nanoTime();
                updateImage(function.find());
                this.setTitle("Delay: " + String.format("%.10f%n", (System.nanoTime() - startTime) / 1e9));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        timer.start();
    }

    public void updateImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    private BufferedImage getImage() {
        return image;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }
}