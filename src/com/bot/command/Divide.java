package com.bot.command;

import com.bot.Bot;
import com.bot.BotState;
import com.bot.Directions;
import com.world.WorldModel;

import java.util.concurrent.ThreadLocalRandom;

/*
 * The command create new bot using copy of old bot genome.
 */
public class Divide extends BotCommand
{
    public Divide()
    {
        super(true);
    }

    @Override
    public void execute(Bot bot)
    {
        WorldModel worldModel = bot.worldModel;

        Directions freeDir = bot.findEmptyDirection();

        if (freeDir != null && bot.energy.consume(150))
        {
            int xt = bot.getXCoordFromDir(freeDir);
            int yt = bot.getYCoordFromDir(freeDir);
            Directions newDir = Directions.fromId(ThreadLocalRandom.current().nextInt(0, 8));

            int newEnergy = bot.energy.getResourceCount()/2;
            int newMineral = bot.mineral.getResourceCount()/2;
            int newHealth = bot.health.getMaxResource();

            //if (bot.energy.consume(newEnergy) && bot.mineral.consume(newMineral))
            {
                Bot newbot = new Bot(
                        worldModel,
                        xt, yt,
                        newHealth,
                        newEnergy,
                        newMineral,
                        newDir,
                        BotState.ALIVE,
                        bot.genome,
                        bot.getColor()
                );

                worldModel.addBot(newbot);
            }
        }

        bot.genome.incCommandPointer(1);
    }
}
