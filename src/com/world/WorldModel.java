package com.world;

import com.bot.Bot;
import com.bot.BotState;
import com.bot.CellState;
import com.utils.Perlin2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class WorldModel
{
    public static final Dimension DEFAULT_WORLD_SIZE = new Dimension(640, 480);

    private Dimension worldSize;

    private int seaLevel;

    private BufferedImage terrain;

    private int[][] heightMap;
    private Bot[][] worldMap;

    /* Update list */
    public LinkedList<Bot> needUpdate = new LinkedList<>();
    public ArrayList<Bot> needAdd = new ArrayList<>();
    public ArrayList<Bot> needRemove = new ArrayList<>();

    private int generation;
    private int population;
    private int diedBots;

    public WorldModel()
    {
        worldSize = DEFAULT_WORLD_SIZE;
        seaLevel = 150;
        terrain = generateTerrain(worldSize, seaLevel);
        worldMap = new Bot[worldSize.height][worldSize.width];

        generation = 0;
        population = 0;
        diedBots = 0;
    }

    public int getSeaLevel()
    {
        return seaLevel;
    }
    public int getGeneration()
    {
        return generation;
    }
    public int getPopulation()
    {
        return population;
    }
    public int getDiedBots()
    {
        return diedBots;
    }

    public Dimension getWorldSize()
    {
        return worldSize;
    }

    public BufferedImage getTerrainImage()
    {
        return terrain;
    }

    public int getTerrainHeight(final int x, final int y) { return heightMap[y][x]; }

    public BufferedImage getBotmapImage()
    {
        BufferedImage botmapImage = new BufferedImage(worldSize.width, worldSize.height, BufferedImage.TYPE_INT_ARGB);

        needUpdate.forEach((b)->{
            botmapImage.setRGB(b.getX(), b.getY(), b.getColor().getRGB());
        });

        return botmapImage;
    }

    private BufferedImage generateTerrain(final Dimension screenSize, final int seaLevel)
    {
        BufferedImage terrain = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);
        heightMap = new int[worldSize.height][worldSize.width];

        Perlin2D perlin = new Perlin2D(ThreadLocalRandom.current().nextInt(10000));
        for (int y = 0; y < screenSize.height; y++) {
            for (int x = 0; x < screenSize.width; x++) {
                float f = ThreadLocalRandom.current().nextInt(1, 2) * 160;
                float value = perlin.getNoise(x/f,y/f,8,0.45f);

                int groundHeight = (int)(value * 255 + 128) & 255;
                int surfaceHeight = groundHeight - seaLevel;

                boolean isUnderwater = groundHeight < seaLevel;

                int red = isUnderwater ? 5 : Math.min(150 + 2*surfaceHeight + surfaceHeight/2, 255);
                int green = isUnderwater ? Math.max(150 + surfaceHeight * 10, 10) : Math.min(100 + 2*surfaceHeight + (3*surfaceHeight)/5, 255);
                int blue = isUnderwater ? Math.max(140 + surfaceHeight * 3, 20) : Math.min(50 + surfaceHeight * 3, 255);

                heightMap[y][x] = groundHeight;
                terrain.setRGB(x, y, (red << 16) | (green << 8) | blue);
            }
        }

        return terrain;
    }

    public boolean addBot(int x, int y, Bot bot)
    {
        if (worldMap[y][x] == null)
        {
            worldMap[y][x] = bot;

            return true;
        }

        return false;
    }

    public boolean addBot(Bot bot)
    {
        return addBot(bot.getX(), bot.getY(), bot);
    }

    public Bot removeBot(int x, int y)
    {
        Bot removedBot = worldMap[y][x];

        worldMap[y][x] = null;

        needRemove.add(removedBot);

        return removedBot;
    }

    public Bot removeBot(Bot bot)
    {
        return removeBot(bot.getX(), bot.getY());
    }

    public Bot getBot(int x, int y)
    {
        return worldMap[y][x];
    }

    public void moveBot(Bot bot, int nx, int ny)
    {
        worldMap[bot.getY()][bot.getX()] = null;
        worldMap[ny][nx] = bot;
    }

    public CellState getCellState(Bot bot, int tx, int ty)
    {
        Bot otherBot = bot.worldModel.getBot(tx, ty);

        if (otherBot != null)
        {
            if (otherBot.getState() == BotState.DIED)
            {
                return CellState.DIED_BOT;
            }
            else if (otherBot.botInFamily(bot))
            {
                return CellState.FAMILY;
            }

            return CellState.LIVING_BOT;
        }

        return CellState.FREE_SPACE;
    }

    public void update()
    {
        if (!needRemove.isEmpty())
        {
            diedBots += needRemove.size();
            population -= needRemove.size();

            needUpdate.removeAll(needRemove);
            needRemove.clear();
        }

        if (!needAdd.isEmpty())
        {
            population += needAdd.size();

            needUpdate.addAll(needAdd);
            needAdd.clear();
        }

        needUpdate.forEach(Bot::step);

        ++generation;
    }
}
