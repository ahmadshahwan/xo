package org.example.xo.terminal;

import org.example.xo.GameEngine;
import org.example.xo.GameView;
import org.example.xo.Gameplay;
import org.example.xo.Mark;
import org.example.xo.Player;

public class TerminalGameplay extends Gameplay {

    private final Terminal terminal;

    public TerminalGameplay(Terminal terminal) {
        this.terminal = terminal;
    }


    @Override
    protected void declareWinner() {
        this.terminal.printLine("Player %s won.".formatted(this.engine.getCurrentPlayerMark().other()));
    }

    @Override
    protected void declareDraw() {
        this.terminal.printLine("Game ended in draw");
    }

    @Override
    protected Player acceptPlayer(Mark mark) {
        return new TerminalPlayer(this.terminal, this.engine);
    }

    @Override
    protected GameView createView(GameEngine engine) {
        return new PrinterView(this.terminal, engine);
    }

    @Override
    protected int readSize() {
        this.terminal.printLine("Please provide game dimension.");
        String line = this.terminal.readLine();
        try {
            int size = Integer.parseInt(line);
            if (size < 3 || size > 33) {
                this.terminal.printLine("Size should be between 3 and 33.");
                return this.readSize();
            }
            return size;
        } catch (NumberFormatException ignore) {
            return this.readSize();
        }
    }
}
