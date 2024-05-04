package domain;

import java.awt.Color;

public class Advanced extends Machine{

    public Advanced(String name, Color color) {
        super(name, color);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void moveToken(Color color, String direction) {
        // Mover y poner puentes con algoritmos de estrategia predefinida basado en el comportamiento del contrincante
    }

    @Override
    public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide){
		
	}
}
