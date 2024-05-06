package domain;

import java.awt.Color;

public class Temporary extends Wall {
    private int turn;

    public Temporary(Color color) {
        super(color);
        this.turn = 0;
    }

    @Override
    public void act() throws QuoriPOOBException {
        if (this.board != null) {
            this.turn++;
            if (this.turn == 4) delWallFromBoard();
        }
    }

    @Override
    public void delWallFromBoard() throws QuoriPOOBException {
        super.delWallFromBoard();
        this.turn = 0;
    }
}
