package com.bot.command;

import com.bot.Bot;
import com.bot.CellState;

public class TransferEnergy extends BotCommand
{
    public TransferEnergy()
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
                break;
        }

        bot.genome.incCommandPointerIndirect( cellState.formattedId() );
    }
}
