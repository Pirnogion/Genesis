package com.bot.command;

import com.bot.Bot;

/*
 * The command swap n random number pairs in a bot genome.
 */
public class Mutate extends BotCommand
{
    public Mutate()
    {
        super(true);
    }

    @Override
    public void execute(Bot bot)
    {
        bot.genome.swapInstruction(2);
        bot.genome.incCommandPointer(1);
    }
}
