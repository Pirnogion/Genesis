package com.bot;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Genome
{
    private final int size;
    private int commandPointer;

    private final int[] genome;

    public Genome(int[] genome)
    {
        int copiedGenome[] = new int[genome.length];
        System.arraycopy(genome, 0, copiedGenome, 0, genome.length);

        this.size = genome.length;
        this.commandPointer = 0;
        this.genome = copiedGenome;
    }

    public Genome(Genome anotherGenome)
    {
        this(anotherGenome.genome);
    }

    public Genome(String compressedGenome)
    {
        this(decompress(compressedGenome));
    }

    public int[] getGenome()
    {
        return genome;
    }

    public int getSize()
    {
        return size;
    }

    public int getInstruction(int pos)
    {
        return (pos >= 0) ? genome[pos % size] : -1;
    }

    public int getInstructionIndirect(int pos)
    {
        return (pos >= 0) ?  genome[getInstruction(pos) % size] : -1;
    }

    public int getCurrentInstruction()
    {
        return getInstruction(commandPointer);
    }

    public int getNextInstruction()
    {
        return getInstruction((commandPointer + 1) % size);
    }

    public int incCommandPointer(int value)
    {
        return (value >= 0) ? commandPointer = (commandPointer + value) % size : -1;
    }

    public int incCommandPointerIndirect(int value)
    {
        return incCommandPointer(getInstruction(commandPointer+value));
    }

    public int getCommandPointer()
    {
        return commandPointer;
    }

    public void swapInstruction(final int count)
    {
        for (int i = 0; i < count; ++i)
        {
            int randomCommandAddress = ThreadLocalRandom.current().nextInt(size);
            int randomCommand = ThreadLocalRandom.current().nextInt(size);

            genome[randomCommandAddress] = randomCommand;
        }
    }

    public int genomeEquals(Genome anotherGenome)
    {
        if (size != anotherGenome.size) return -1;

        int dif = 0;
        for (int i = 0; i < size; i++)
        {
            if (genome[i] != anotherGenome.genome[i])
            {
                ++dif;
            }
        }

        return dif;
    }

    public static String compress(int genome[])
    {
        ByteBuffer bbuf = ByteBuffer.allocate(genome.length * Integer.BYTES);

        bbuf.asIntBuffer().put(genome);

        return new String(bbuf.array(), StandardCharsets.UTF_8);
    }

    public static int[] decompress(String compressed)
    {
        IntBuffer ibuf = ByteBuffer.wrap(compressed.getBytes(StandardCharsets.UTF_8)).order(ByteOrder.BIG_ENDIAN).asIntBuffer();

        int[] genome = new int[ibuf.remaining()];
        ibuf.get(genome);

        return genome;
    }
}
