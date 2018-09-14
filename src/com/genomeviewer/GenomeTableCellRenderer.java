package com.genomeviewer;

import com.bot.command.CommandList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.HashMap;

public class GenomeTableCellRenderer extends DefaultTableCellRenderer
{
    private final Color errorColor = new Color(0xFF0000);
    private final Color defaultColor = new Color(0xF5F5F5);

    public GenomeTableCellRenderer()
    {
        super();

        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }

    public void setValue(Object value)
    {
        super.setValue(value);

        if (value != null && value instanceof Integer)
        {
            int number = (int)value;

            if ( number >= 0 && number < CommandList.SIZE)
            {
                CommandList item = CommandList.getItem(number);

                this.setBackground( (item != null) ? item.color : defaultColor );
            }
            else
            {
                this.setBackground(errorColor);
            }
        }
        else
        {
            this.setBackground(defaultColor);
        }
    }
}
