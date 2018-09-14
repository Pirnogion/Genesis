package com.bot;

public enum Directions
{
    UP(0),
    UP_RIGHT(1),
    RIGHT(2),
    DOWN_RIGHT(3),
    DOWN(4),
    DOWN_LEFT(5),
    LEFT(6),
    UP_LEFT(7);

    private int id;

    Directions(final int id)
    {
        this.id = id;
    }

    public static Directions fromId(final int id)
    {
        for (Directions dir : values())
            if (dir.id == id) return dir;

        return null;
    }

    public int normalId()
    {
        return id;
    }

    public int oppositeId()
    {
        return id + 4;
    }

    public int nextId()
    {
        return (id + 1) % 8;
    }

    public int prevId()
    {
        return (id + 7) % 8;
    }

    public int horizontalShifts()
    {
        return hShifts(id);
    }

    public int verticalShifts()
    {
        return vShifts(id);
    }

    private int hShifts(final int value)
    {
        switch(value % 8)
        {
            case 1: case 2: case 3: return  1;
            case 5: case 6: case 7: return -1;
        }

        return 0;
    }

    private int vShifts(final int value)
    {
        return hShifts(value+6);
    }
}
