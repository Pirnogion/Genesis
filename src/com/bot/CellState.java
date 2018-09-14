package com.bot;

public enum CellState
{
    FREE_SPACE(0),
    LIVING_BOT(1),
    FAMILY(2),
    DIED_BOT(3);

    private int id;

    CellState(final int id)
    {
        this.id = id;
    }

    public int id()
    {
        return id;
    }

    public int formattedId()
    {
        return 2 + id;
    }
}
