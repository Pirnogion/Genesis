package com;

import com.world.WorldModel;
import com.world.WorldView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public final class RootWindow extends JFrame
{
    public RootWindow(final String windowTitle, final WorldView worldView, final WorldModel worldModel)
    {
        setTitle(windowTitle);
        setSize( Toolkit.getDefaultToolkit().getScreenSize() );
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.add(worldView, BorderLayout.CENTER);

        AbstractAction keyEventListener = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Redraw map...");

                worldView.repaint();
            }
        };

        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke("Q"), "Q_KeyPressed");
        this.getRootPane().getActionMap().put("Q_KeyPressed", keyEventListener);

        this.pack();
        this.setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
    }
}
