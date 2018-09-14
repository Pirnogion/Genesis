package com.bot.command;

import com.bot.Bot;
import com.bot.CellState;

public class TransferResources extends BotCommand
{
    public TransferResources()
    {
        super(false);
    }

    @Override
    public void execute(Bot bot)
    {
        int nx = bot.getXCoordFromDir(), ny = bot.getYCoordFromDir();
        Bot otherBot = bot.worldModel.getBot(nx, ny);
        CellState cellState = bot.worldModel.getCellState(bot, nx, ny);

        switch (cellState)
        {
            case LIVING_BOT: case FAMILY:
                bot.energy.transfer(100, otherBot.energy);
                bot.mineral.transfer(100, otherBot.mineral);
                break;
        }

        bot.genome.incCommandPointerIndirect( cellState.formattedId() );
    }
}
