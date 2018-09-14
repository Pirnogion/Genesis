package com.bot.command;

import com.bot.Bot;

public class CheckHealth extends BotCommand
{
    public CheckHealth()
    {
        super(false);
    }

    @Override
    public void execute(Bot bot)
    {
        if (bot.health.getResourceCount() < bot.health.getMaxResource() * bot.genome.getNextInstruction() / bot.genome.getSize())
        {
            bot.genome.incCommandPointerIndirect(2);
        }
        else
        {
            bot.genome.incCommandPointerIndirect(3);
        }
    }
}

