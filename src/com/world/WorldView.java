package com.world;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class WorldView extends JPanel
{
    private float zoom = 1.0f;

    private WorldModel worldModel;

    public WorldView(final WorldModel worldModel)
    {
        this.worldModel = worldModel;
        setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(worldModel.getTerrainImage(), 0, 0, null);
        g.drawImage(worldModel.getBotmapImage(), 0, 0, null);
    }
}
