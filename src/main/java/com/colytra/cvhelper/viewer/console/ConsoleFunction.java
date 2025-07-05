package com.colytra.cvhelper.viewer.console;

import java.io.IOException;

@FunctionalInterface
public interface ConsoleFunction {
    String log(double v) throws IOException;
}