package com.colytra.cvhelper.viewer.window;

import java.awt.image.BufferedImage;
import java.io.IOException;

@FunctionalInterface
public interface WindowFunction {
    BufferedImage find() throws IOException;
}
