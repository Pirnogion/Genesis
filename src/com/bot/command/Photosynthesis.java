package com.bot.command;

import com.bot.Bot;

public class Photosynthesis extends BotCommand
{
    public Photosynthesis()
    {
        super(true);
    }

    @Override
    public void execute(Bot bot)
    {
        int height = bot.worldModel.getTerrainHeight(bot.getX(), bot.getY());
        int seaLevel = bot.worldModel.getSeaLevel();

        double mineralModifier = bot.mineral.getResourceCount() / 10;

        double producedEnergy = 0;
        if ((height > seaLevel) && (height <= seaLevel + 60))
        {
            producedEnergy = mineralModifier + (seaLevel + 60 - height) * 0.2;
        }

        if (producedEnergy > 0)
        {
            bot.energy.produce((int)producedEnergy);
            //TODO: goGreen()
        }

        bot.genome.incCommandPointer(1);
    }
}
