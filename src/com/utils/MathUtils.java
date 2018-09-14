package com.utils;

public final class MathUtils
{
    public static int closedRange(int value, final int range)
    {
        while (value >= range) value -= range;
        while (value < 0) value += range;

        return value;
    }

    public static int range(int value, int minValue, int maxValue)
    {
        return (value < minValue) ? minValue : (value > maxValue) ? maxValue : value;
    }
}
