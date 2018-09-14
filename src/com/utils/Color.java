package com.utils;

public class Color
{
    private int red, green, blue, alpha;

    public Color(Color color)
    {
        setColor(color);
    }

    public Color(int hex)
    {
        setColor(hex);
    }

    public Color(int red, int green, int blue, int alpha)
    {
        setColor(red, green, blue, alpha);
    }

    public Color(int red, int green, int blue)
    {
        setColor(red, green, blue, 255);
    }

    public void setColor(int hex)
    {
        this.alpha = hex >> 24;
        this.red = (hex & 0x00ff0000) >> 16;
        this.green = (hex & 0x0000ff00) >> 8;
        this.blue = hex & 0x000000ff;
    }

    public void setColor(int red, int green, int blue, int alpha)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public void setColor(Color color)
    {
        this.red = color.red;
        this.green = color.green;
        this.blue = color.blue;
        this.alpha = color.alpha;
    }

    public void setRed(int red)
    {
        this.red = red;
    }

    public void setGreen(int green)
    {
        this.green = green;
    }

    public void setBlue(int blue)
    {
        this.blue = blue;
    }

    public void setAlpha(int alpha)
    {
        this.alpha = alpha;
    }

    public int getHexColor()
    {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    public int getRed()
    {
        return red;
    }

    public int getGreen()
    {
        return green;
    }

    public int getBlue()
    {
        return blue;
    }

    public int getAlpha()
    {
        return alpha;
    }
}
