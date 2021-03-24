package com.code.monitor.algorithm.并查集;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/8 18:27
 */
public class LruAlgo extends LinkedHashMap {

    private final int maxSize;

    public LruAlgo(int maxSize) {
        super((int) Math.ceil(maxSize / 0.75) + 1, 0.75f, true);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > maxSize;
    }
}
