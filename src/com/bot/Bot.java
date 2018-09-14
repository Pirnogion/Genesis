package com.bot;

import com.bot.command.*;
import java.awt.Color;
import com.world.WorldModel;

import java.util.concurrent.ThreadLocalRandom;

import static com.utils.MathUtils.closedRange;

public class Bot
{
    public final WorldModel worldModel;
    public final Resource health, energy, mineral;
    public final Genome genome;

    private int x, y;

    private int age;
    private Directions direction;
    private BotState state;

    private int lastgeneration;

    private Color color;

    public Bot(WorldModel worldModel, int x, int y, Genome genome, Color color)
    {
        this(worldModel, x, y, 1000, 1000, 0, Directions.UP, BotState.ALIVE, genome, color);
    }

    public Bot(WorldModel worldModel, int x, int y, int health, int energy, int mineral, Directions direction, BotState state, Genome genome, Color color)
    {
        this.worldModel = worldModel;
        this.x = x; this.y = y;

        this.genome = new Genome(genome);
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

        this.lastgeneration = worldModel.getGeneration();

        this.state = state;
        this.direction = direction;
        this.age = 0;

        this.mineral = new Resource("MINERAL", 0, 1000, mineral);
        this.energy = new Resource("ENERGY", 0, 1000, energy);
        this.health = new Resource("HEALTH", 0, 1000, health);

        worldModel.needAdd.add(this);
    }

    public Color getColor()
    {
        return color;
    }

    public void setPos(int x, int y)
    {
        this.x = x; this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public BotState getState()
    {
        return state;
    }

    public void setDirection(Directions direction)
    {
        this.direction = direction;
    }

    public Directions getDirection()
    {
        return direction;
    }

    /* Execute genome instruction */
    public void step()
    {
        if (state == BotState.ALIVE)
        {
            for (int cycle = 0; cycle < 16; cycle++)
            {
                CommandList item = CommandList.getItem(genome.getCurrentInstruction());

                if (item != null)
                {
                    item.command.execute(this);

                    if (item.command.isEndCommand())
                    {
                        genome.incCommandPointer(1);

                        break;
                    }
                }
                else
                {
                    genome.incCommandPointer(1);
                }
            }

        /* Probability of mutate 1/4 */
            if (ThreadLocalRandom.current().nextInt(100) == 53)
            {
                genome.swapInstruction(2);
            }

        /* Bot physics */
            if (!energy.consume(1))
            {
                state = BotState.DIED;
                this.color = new Color(0x444444);
            }

            ++age;

        /* Information */
            lastgeneration = worldModel.getGeneration();
        }
    }

    public int getXCoordFromDir()
    {
        return getXCoordFromDir(direction);
    }

    public int getXCoordFromDir(final Directions dir)
    {
        return closedRange(x+dir.horizontalShifts(), worldModel.getWorldSize().width);
    }

    public int getYCoordFromDir()
    {
        return getYCoordFromDir(direction);
    }

    public int getYCoordFromDir(final Directions dir)
    {
        return closedRange(y+dir.verticalShifts(), worldModel.getWorldSize().height);
    }

    public Directions findEmptyDirection()
    {
        for (Directions dir : Directions.values())
        {
            int xt = getXCoordFromDir(dir);
            int yt = getYCoordFromDir(dir);

            if (worldModel.getBot(xt, yt) == null) return dir;
        }

        return null;
    }

    public boolean botInFamily(Bot anotherBot)
    {
        return genome.genomeEquals(anotherBot.genome) < 2;
    }
}
