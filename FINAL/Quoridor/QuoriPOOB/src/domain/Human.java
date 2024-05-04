package domain;
import java.awt.*;
public class Human extends Player{
    public Human(String name, Color color){
        super(name, color);
    }

    @Override
    public void moveToken(Color color, String direction) {
        // Move the token in the board
        
    }

    @Override
    public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide){
		Wall wallToPut = null;
		for(Wall w:walls){
			if(w.getClass().getSimpleName().toUpperCase().equals(type.toUpperCase())){
				wallToPut = w;
			}
		}
		wallToPut.addWall(initialRow, initialColumn, squareSide);
		board.addWallToBoard(wallToPut);
	}
}
