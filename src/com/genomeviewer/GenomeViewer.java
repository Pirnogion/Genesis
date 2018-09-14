package com.genomeviewer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;

public class GenomeViewer extends JFrame
{
    private final int tileSize = 50;
    private final int tileGap = 5;
    private final int genomeCanvasSize = (tileSize+tileGap)*8 - tileGap;

    private JButton acceptGenomeButton = new JButton("Accept genome");

    private GenomeTableCellRenderer genomeTableCellRenderer = new GenomeTableCellRenderer();
    private GenomeTableModel genomeTableModel = new GenomeTableModel();
    private JTable genomeTable = new JTable(genomeTableModel)
    {
        public TableCellRenderer getCellRenderer(int row, int column)
        {
            return genomeTableCellRenderer;
        }
    };

    public GenomeViewer()
    {
        super("Genome viewer");

        /* Initialise the JFrame */
        this.setBounds(0, 0, 860, 640);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());

        /* Initialise 'genomeTable' */
        TableColumnModel columnModel = genomeTable.getColumnModel();
        genomeTable.setRowHeight(tileSize);
        for (int i = 0; i < genomeTable.getColumnModel().getColumnCount(); i++)
        {
            genomeTable.getColumnModel().getColumn(i).setMaxWidth(tileSize);
            genomeTable.getColumnModel().getColumn(i).setMinWidth(tileSize);
        }
        genomeTable.setShowGrid(false);
        genomeTable.setIntercellSpacing(new Dimension(5, 5));

        /* Initialise 'acceptGenomeButton' */
        acceptGenomeButton.addActionListener(new ButtonEventListener());

        /* Add components to content pan */
        container.add(genomeTable, BorderLayout.CENTER);
        container.add(acceptGenomeButton, BorderLayout.SOUTH);
    }

    public void openGenome(final int[] genome)
    {
        //genomeTableModel.setGenome(genome);
    }

    class ButtonEventListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(null, "Ty pidor!", "Attention!", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
