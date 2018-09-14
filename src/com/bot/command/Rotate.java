package com.bot.command;

import com.bot.Bot;
import com.bot.Directions;

/*
 * The command rotate a Bot at angle defined in a genome.
 */
public class Rotate extends BotCommand
{
    public Rotate()
    {
        super(false);
    }

    @Override
    public void execute(Bot bot)
    {
        bot.energy.consume(1);

        bot.setDirection(Directions.fromId( (bot.getDirection().normalId() + bot.genome.getNextInstruction()) % 8 ));
        bot.genome.incCommandPointer(2);
    }
}
