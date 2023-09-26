package org.example.xo;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private final Mark[][] board;
    private final int size;
    private int remainingCells;

    private Mark currentPlayerMark = Mark.X;

    private Coordinate nextMove;

    private List<Coordinate> winningCombination;


    public GameEngine(int size) {
        this.size = size;
        this.board = new Mark[size][size];
        this.remainingCells = size * size;
    }

    public Mark getCurrentPlayerMark() {
        return currentPlayerMark;
    }

    public Coordinate getNextMove() {
        return nextMove;
    }

    /**
     * Get element at a given coordinate.
     *
     * @param coordinate coordinate of element
     * @return the element
     */
    public Mark at(Coordinate coordinate) {
        int i = coordinate.i();
        int j = coordinate.j();
        return this.board[i][j];
    }

    public int getSize() {
        return size;
    }

    public boolean isFull() {
        return this.remainingCells == 0;
    }

    /**
     * Update next move.
     *
     * @param next next move.
     * @return true if move is successful.
     */
    public boolean updateNextMove(Coordinate next) {
        int i = next.i();
        int j = next.j();
        if (this.isAvailable(i, j)) {
            return false;
        }
        this.nextMove = next;
        return true;
    }

    private boolean isAvailable(int i, int j) {
        return this.isCoordinateValid(i, j) || this.board[i][j] != null;
    }

    /**
     * Denote if coordinates are valid.
     *
     * @param i line number
     * @param j column number
     * @return line and column are valid coordinates
     */
    private boolean isCoordinateValid(int i, int j) {
        return i < 0 || i >= this.size || j < 0 || j >= this.size;
    }

    public void confirmMove() {
        if (this.nextMove == null) {
            throw new IllegalStateException("Next move not set.");
        }
        int i = this.nextMove.i();
        int j = this.nextMove.j();
        this.board[i][j] = this.currentPlayerMark;
        this.findWinner();
        this.remainingCells--;
        this.currentPlayerMark = this.currentPlayerMark.other();
        this.nextMove = null;
    }

    private boolean findWinner() {
        int i = this.nextMove.i();
        int j = this.nextMove.j();
        return this.findWinnerLine(i) || this.findWinnerColumn(j) || this.findWinnerDiagonal(i, j) || this.findWinnerAntidiagonal(i, j);
    }

    private boolean findWinnerLine(int i) {
        List<Coordinate> winning = new ArrayList<>(this.size);
        for (int step = 0; step < this.size; step++) {
            Coordinate coordinate = new Coordinate(i, step);
            if (this.at(coordinate) != this.currentPlayerMark) {
                return false;
            }
            winning.add(coordinate);
        }
        this.winningCombination = winning;
        return true;
    }

    private boolean findWinnerColumn(int j) {
        List<Coordinate> winning = new ArrayList<>(this.size);
        for (int step = 0; step < this.size; step++) {
            Coordinate coordinate = new Coordinate(step, j);
            if (this.at(coordinate) != this.currentPlayerMark) {
                return false;
            }
            winning.add(coordinate);
        }
        this.winningCombination = winning;
        return true;
    }

    private boolean findWinnerDiagonal(int i, int j) {
        if (i != j) {
            return false;
        }
        List<Coordinate> winning = new ArrayList<>(this.size);
        for (int step = 0; step < this.size; step++) {
            Coordinate coordinate = new Coordinate(step, step);
            if (this.at(coordinate) != this.currentPlayerMark) {
                return false;
            }
            winning.add(coordinate);
        }
        this.winningCombination = winning;
        return true;
    }

    private boolean findWinnerAntidiagonal(int i, int j) {
        if (i != this.size - j - 1) {
            return false;
        }
        List<Coordinate> winning = new ArrayList<>(this.size);
        for (int step = 0; step < this.size; step++) {
            Coordinate coordinate = new Coordinate(step, this.size - step -1);
            if (this.at(coordinate) != this.currentPlayerMark) {
                return false;
            }
            winning.add(coordinate);
        }
        this.winningCombination = winning;
        return true;
    }


    public boolean isGameOver() {
        return this.isFull() || this.winningCombination != null;
    }

    public boolean isInWinningCombination(Coordinate coordinate) {
        return this.winningCombination != null && this.winningCombination.contains(coordinate);
    }
}
