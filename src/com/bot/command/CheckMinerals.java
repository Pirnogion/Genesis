package com.bot.command;

import com.bot.Bot;

public class CheckMinerals extends BotCommand
{
    public CheckMinerals()
    {
        super(false);
    }

    @Override
    public void execute(Bot bot)
    {
        if (bot.mineral.getResourceCount() < bot.mineral.getMaxResource() * bot.genome.getNextInstruction() / bot.genome.getSize())
        {
            bot.genome.incCommandPointerIndirect(2);
        } else {
            bot.genome.incCommandPointerIndirect(3);
        }
    }
}


