package com.genomeviewer;

import com.bot.Genome;

import javax.swing.table.AbstractTableModel;

public class GenomeTableModel extends AbstractTableModel
{
    private int[] genome;

    public GenomeTableModel()
    {
        // Nothing
    };

    public GenomeTableModel(int[] genome)
    {
        this.genome = genome;
    }
    public GenomeTableModel(Genome genome)
    {
        this.genome = genome.getGenome();
    }

    public int[] getGenome()
    {
        return genome;
    }

    @Override
    public int getRowCount()
    {
        return 8;
    }

    @Override
    public int getColumnCount()
    {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return (genome == null) ? '?' : genome[rowIndex*8+columnIndex];
    }
}
