package domain;

import java.awt.Color;

public class Begginer extends Machine{

    public Begginer(String name, Color color) {
        super(name, color);
    }

    @Override
    public void moveToken(Color color, String direction) {
        // Mover con dificultad baja
    }

    @Override
    public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide){
		
	}
    
}
