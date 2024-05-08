package domain;

import java.awt.Color;

public class Beginner extends Machine{

    public Beginner(String name, Color color) {
        super(name, color);
    }

    @Override
    public void moveToken(String direction) {
        // Mover con dificultad baja
    }

    @Override
    public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide){
		
	}
    
}
