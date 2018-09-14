package com.bot.command;

import com.bot.Bot;
import com.bot.BotState;

public class AttackNeighbour extends BotCommand
{
    public AttackNeighbour()
    {
        super(true);
    }

    @Override
    public void execute(Bot bot)
    {
        Bot otherBot = bot.worldModel.getBot(bot.getXCoordFromDir(), bot.getYCoordFromDir());

        if (otherBot != null && otherBot.getState() == BotState.ALIVE)
        {
            otherBot.genome.swapInstruction(4);
        }

        bot.genome.incCommandPointer(1);
    }
}
