package org.example.xo.terminal;

import org.example.xo.Coordinate;
import org.example.xo.GameEngine;
import org.example.xo.GameView;
import org.example.xo.Mark;

public class PrinterView implements GameView {

    private final Printer printer;
    private final GameEngine engine;

    public PrinterView(Printer printer, GameEngine engine) {
        this.printer = printer;
        this.engine = engine;
    }

    @Override
    public void refresh() {
        for (int i = 0; i < this.engine.getSize(); i++) {
            StringBuilder sb = new StringBuilder("|");
            for (int j = 0; j < this.engine.getSize(); j++) {
                sb.append(this.cellAt(i, j));
            }
            sb.append("|");
            this.printer.printLine(sb.toString());
        }
    }

    private String cellAt(int i, int j) {
        Coordinate coordinate = new Coordinate(i, j);
        if (coordinate.equals(this.engine.getNextMove())) {
            return ">%s<".formatted(this.engine.getCurrentPlayerMark());
        }
        Mark mark = this.engine.at(coordinate);
        if (mark == null) {
            return "%2d ".formatted(i * this.engine.getSize() + j + 1);
        }
        if (this.engine.isInWinningCombination(coordinate)) {
            return " [%s]".formatted(mark);
        }
        return " %s ".formatted(mark);
    }
}
