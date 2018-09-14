package com.bot.command;

import com.bot.Bot;
import com.bot.CellState;

public class See extends BotCommand
{
    public See()
    {
        super(false);
    }

    @Override
    public void execute(Bot bot)
    {
        int nx = bot.getXCoordFromDir(), ny = bot.getYCoordFromDir();
        CellState cellStatus = bot.worldModel.getCellState(bot, nx, ny);

        bot.genome.incCommandPointerIndirect( cellStatus.formattedId() );
    }
}
