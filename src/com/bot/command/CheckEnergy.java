package com.bot.command;

import com.bot.Bot;

public class CheckEnergy extends BotCommand
{
    public CheckEnergy()
    {
        super(false);
    }

    @Override
    public void execute(Bot bot)
    {
        if (bot.energy.getResourceCount() < bot.energy.getMaxResource() * bot.genome.getNextInstruction() / bot.genome.getSize())
        {
            bot.genome.incCommandPointerIndirect(2);
        }
        else
        {
            bot.genome.incCommandPointerIndirect(3);
        }
    }
}

