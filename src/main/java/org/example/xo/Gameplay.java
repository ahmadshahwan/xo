package org.example.xo;

public abstract class Gameplay {

    protected GameEngine engine;
    protected GameView view;
    protected Player playerX;
    protected Player playerO;
    public void run() {
        this.engine = new GameEngine(this.readSize());
        this.view = this.createView(this.engine);
        this.playerX = this.acceptPlayer(Mark.X);
        this.playerO = this.acceptPlayer(Mark.O);
        this.view.refresh();
        while (!this.engine.isGameOver()) {
            this.chooseMove();
            this.engine.confirmMove();
            this.view.refresh();
        }
        if (this.engine.isFull()) {
            this.declareDraw();
        } else {
            this.declareWinner();
        }
    }

    protected abstract void declareWinner();

    protected abstract void declareDraw();


    private Coordinate chooseMove() {
        Coordinate coordinate = this.getCurrentPlayer().chooseMove();
        if (!this.engine.updateNextMove(coordinate)) {
            return this.chooseMove();
        }
        this.view.refresh();
        if (this.getCurrentPlayer().confirmMove(coordinate)) {
            return coordinate;
        } else {
            return this.chooseMove();
        }
    }

    protected Player getCurrentPlayer() {
        return this.engine.getCurrentPlayerMark() == Mark.X ? this.playerX : this.playerO;
    }

    protected abstract Player acceptPlayer(Mark mark);

    protected abstract GameView createView(GameEngine engine);

    protected abstract int readSize();
}
