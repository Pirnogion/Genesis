package com.bot.command;

import com.bot.Bot;

public class Chemosynthesis extends BotCommand
{
    public Chemosynthesis()
    {
        super(true);
    }

    @Override
    public void execute(Bot bot)
    {
        int underwater = bot.worldModel.getSeaLevel() - bot.worldModel.getTerrainHeight(bot.getX(), bot.getY());

        if (underwater > 0)
            bot.mineral.produce(underwater);

        if (bot.mineral.getResourceCount() > 500)
        {
            bot.energy.produce(10);
            bot.mineral.consume(1);
        }

        bot.genome.incCommandPointer(1);
    }
}
