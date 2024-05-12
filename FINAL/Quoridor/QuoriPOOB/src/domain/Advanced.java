package domain;

import java.awt.Color;

public class Advanced extends Machine {

    public Advanced(String name, Color color) {
        super(name, color);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void play(String direction) throws QuoriPOOBException{
        // Calculate the best direction or movement
        moveToken("UP");
    }

    @Override
    public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide){
		
	}
}
