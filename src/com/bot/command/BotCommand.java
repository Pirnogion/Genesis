package com.bot.command;

import com.bot.Bot;

public abstract class BotCommand
{
    private boolean isEndCommand;

    protected BotCommand(final boolean isEndCommand)
    {
        this.isEndCommand = isEndCommand;
    }

    public abstract void execute(final Bot bot);

    public final boolean isEndCommand()
    {
        return isEndCommand;
    }
}
