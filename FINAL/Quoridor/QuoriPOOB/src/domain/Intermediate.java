package domain;

import java.awt.Color;

public class Intermediate extends Machine{

    public Intermediate(String name, Color color) {
        super(name, color);
    }

    @Override
    public void moveToken(String direction) {
        // Mover con algoritmo de dificultad media y poner puentes
    }

    @Override
    public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide){
        
	}
}
