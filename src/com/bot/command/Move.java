package com.bot.command;

import com.bot.Bot;
import com.bot.CellState;

/*
 * The command move a bot to free cell.
 */
public class Move extends BotCommand
{
    public Move()
    {
        super(true);
    }

    @Override
    public void execute(Bot bot)
    {
        int nx = bot.getXCoordFromDir(), ny = bot.getYCoordFromDir();
        CellState cellState = bot.worldModel.getCellState(bot, nx, ny);

        switch (cellState)
        {
            case FREE_SPACE:
                bot.worldModel.moveBot(bot, nx, ny);
                bot.setPos(nx, ny);
                break;
        }

        bot.genome.incCommandPointerIndirect( cellState.formattedId() );
    }
}
