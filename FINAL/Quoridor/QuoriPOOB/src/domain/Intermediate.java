package domain;

import java.awt.Color;

public class Intermediate extends Machine{

    public Intermediate(String name, Color color) {
        super(name, color);
    }

    @Override
    public void play(String direction) throws QuoriPOOBException{
        moveToken(direction);
    }

    @Override
    public void play(String type, int initialRow, int initialColumn, String squareSide) throws QuoriPOOBException {
        play(null);
    }
    
}
