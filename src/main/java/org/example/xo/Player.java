package org.example.xo;

public interface Player {

    Coordinate chooseMove();

    boolean confirmMove(Coordinate move);
}
