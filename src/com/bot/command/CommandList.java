package com.bot.command;

import java.awt.*;

public enum CommandList
{
    MUTATE(0, new Mutate(), 0x8EF13C),
    DIVIDE(16, new Divide(), 0xD235D2),
    ROTATE(23, new Rotate(), 0xEE3C7E),
    MOVE(26, new Move(), 0xD9E9FF),
    PHOTOSYNTHESIS(32, new Photosynthesis(), 0x42E73A),
    CHEMOSYNTHESIS(33, new Chemosynthesis(), 0x36BBCE),
    EAT(34, new Eat(), 0xFF7640),
    TRANSFER_RESOURCES(36, new TransferResources(), 0xA74400),
    TRANSFER_ENERGY(38, new TransferEnergy(), 0xFFDF40),
    SEE(40, new See(), 0x39AECF),
    CHECK_HEIGHT(41, new CheckHeight(), 0x6971DB),
    CHECK_HP(42, new CheckHealth(), 0xE60B0B),
    CHECK_MINERALS(43, new CheckMinerals(), 0x9A9CAD),
    CHECK_ENERGY(44, new CheckEnergy(), 0xFFFF36),
    CHECK_NEIGHBOURS(46, new CheckNeighbours(), 0x9DD134),
    ATTACK_NEIGHBOUR(52, new AttackNeighbour(), 0xFF2300);

    public final static int SIZE = 64;

    public final int commandId;
    public final Color color;
    public final BotCommand command;

    private final static CommandList[] items = new CommandList[SIZE];

    static
    {
        for (CommandList value : values())
            items[value.commandId] = value;
    }

    CommandList(int commandId, BotCommand command, int color)
    {
        this.commandId = commandId;
        this.command = command;

        this.color = new Color(color);
    }

    public static CommandList getItem(int id)
    {
        return (id >= 0 && id < items.length) ? items[id] : null;
    }
}
