package com.bot.command;

import com.bot.Bot;

public class CheckHeight extends BotCommand
{
    public CheckHeight()
    {
        super(false);
    }

    @Override
    public void execute(Bot bot)
    {
        if (bot.worldModel.getBotmapImage().getRGB(bot.getX(), bot.getY()) < 256 * bot.genome.getNextInstruction() / bot.genome.getSize())
        {
            bot.genome.incCommandPointerIndirect(2);
        }
        else
        {
            bot.genome.incCommandPointerIndirect(3);
        }
    }
}
