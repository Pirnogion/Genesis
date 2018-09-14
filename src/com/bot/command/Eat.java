package com.bot.command;

import com.bot.Bot;
import com.bot.CellState;

public class Eat extends BotCommand
{
    public Eat()
    {
        super(true);
    }

    @Override
    public void execute(Bot bot)
    {
        int xt = bot.getXCoordFromDir(), yt = bot.getYCoordFromDir();

        CellState cellState = bot.worldModel.getCellState(bot, xt, yt);
        Bot prey = bot.worldModel.getBot(xt, yt);

        int hunterMinerals, preyMinerals;

        switch (cellState)
        {
            case DIED_BOT:
                hunterMinerals = bot.mineral.getResourceCount();
                preyMinerals = prey.mineral.getResourceCount();

                if (hunterMinerals >= preyMinerals)
                {
                    bot.mineral.consume(preyMinerals);
                    bot.energy.produce( prey.energy.getResourceCount() );

                    bot.worldModel.removeBot(prey);
                }
                else
                {
                    bot.mineral.consume(hunterMinerals);
                    prey.mineral.consume(hunterMinerals);
                }
                break;

            case LIVING_BOT:
                hunterMinerals = bot.mineral.getResourceCount();
                preyMinerals = prey.mineral.getResourceCount();

                if (hunterMinerals >= preyMinerals)
                {
                    bot.mineral.consume(preyMinerals);
                    bot.energy.produce( prey.energy.getResourceCount() );

                    bot.worldModel.removeBot(prey);
                    bot.worldModel.needRemove.add(prey);
                }
                else
                {
                    bot.mineral.consume(hunterMinerals);
                    prey.mineral.consume(hunterMinerals);
                }
                break;
        }

        bot.energy.consume(100);
        bot.genome.incCommandPointerIndirect(cellState.formattedId());
    }
}
