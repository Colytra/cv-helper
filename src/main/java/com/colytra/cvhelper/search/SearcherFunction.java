package com.colytra.cvhelper.search;

import com.colytra.cvhelper.core.Point;
import com.colytra.cvhelper.core.color.ColorDifference;

@FunctionalInterface
public interface SearcherFunction {
    void find(int x, int y);
}
