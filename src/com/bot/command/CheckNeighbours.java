package com.bot.command;

import com.bot.Bot;

public class CheckNeighbours extends BotCommand
{
    public CheckNeighbours()
    {
        super(false);
    }

    @Override
    public void execute(Bot bot)
    {
        bot.genome.incCommandPointerIndirect((bot.findEmptyDirection() == null) ? 1 : 2 );
    }
}
