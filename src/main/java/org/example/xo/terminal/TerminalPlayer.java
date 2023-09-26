package org.example.xo.terminal;

import org.example.xo.Coordinate;
import org.example.xo.GameEngine;
import org.example.xo.Player;

public class TerminalPlayer implements Player {

    private final Terminal terminal;
    private final GameEngine engine;

    public TerminalPlayer(Terminal terminal, GameEngine engine) {
        this.terminal = terminal;
        this.engine = engine;
    }

    @Override
    public Coordinate chooseMove() {
        this.terminal.printLine("Please choose cell's number.");
        String line = this.terminal.readLine();
        final int index;
        try {
            index = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            this.terminal.printLine("Not a valid number!");
            return this.chooseMove();
        }
        int size = this.engine.getSize();
        if (index < 1 || index > size * size) {
            this.terminal.printLine("Number out of bound!");
            return this.chooseMove();
        }
        int i = (index - 1) / size;
        int j = (index - 1) % size;
        return new Coordinate(i, j);
    }

    @Override
    public boolean confirmMove(Coordinate choice) {
        this.terminal.printLine("Please confirm you choice by hitting Enter.");
        String line = this.terminal.readLine();
        return "".equals(line);
    }
}
