package com;

import com.bot.Bot;
import com.bot.Genome;
import com.world.WorldModel;
import com.world.WorldView;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Genesis
{
    public static final String NAME = "Genesis";
    public static final String VERSION = "1.3.0";

    private static Thread ticker;
    private static Thread painter;

    private static WorldModel worldModel;
    private static WorldView worldView;
    private static RootWindow window;

    public static void main(String[] args)
    {
        worldModel = new WorldModel();

        int[] defaultGenome = {
            26, 26, 26, 26, 26, 26, 26, 26,
            32, 32, 32, 32, 32, 32, 32, 32,
            32, 32, 32, 32, 32, 32, 32, 32,
            32, 32, 32, 32, 32, 32, 32, 32,
            32, 32, 32, 32, 32, 32, 32, 32,
            32, 32, 32, 32, 32, 32, 32, 32,
            32, 32, 32, 32, 32, 32, 32, 32,
            16, 16, 16, 16, 16, 16, 16, 16
        };

        Bot firstBot = new Bot(
                worldModel,
                WorldModel.DEFAULT_WORLD_SIZE.width/2,
                WorldModel.DEFAULT_WORLD_SIZE.height/2,
                new Genome(defaultGenome),
                new Color(0x00ff00)
        );
        worldModel.addBot(firstBot);

        worldView = new WorldView(worldModel);

        window = new RootWindow(NAME + ' ' + VERSION, worldView, worldModel);

        ticker = new Thread(() -> {
            while (true)
            {
                worldModel.update();
                worldView.repaint();
            }
        });

        painter = new Thread(()->{
            int delay = 0;

            while (true)
            {
                if (delay == 0)
                {
                    worldView.repaint();
                }

                delay = (delay+1) % 10000;
            }
        });

        ticker.start();
        //painter.start();
    }
}
