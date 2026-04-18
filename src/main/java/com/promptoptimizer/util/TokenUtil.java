package com.promptoptimizer.util;

public class TokenUtil {

    public static int estimateTokens(String text) {
        return text.length() / 4;
    }

    public static double estimateCost(int tokens) {
        return (tokens / 1000.0) * 0.002;
    }
}
