package domain;

import java.awt.*;
import javax.sound.midi.Soundbank;

public class Human extends Player{
    public Human(String name, Color color) {
        super(name, color);
    }

    @Override
    public void play(String direction) throws QuoriPOOBException{
        moveToken(direction);
        
    }

    @Override
    public void play(String type, int initialRow, int initialColumn, String squareSide) throws QuoriPOOBException {
        addWallToBoard(type, initialRow, initialColumn, squareSide);
    }
}
