package domain;
import java.awt.*;
public class Human extends Player{
    public Human(String name, Color color){
        super(name, color);
    }

    @Override
    public void moveToken(Color color, String direction) throws QuoriPOOBException{
        switch (direction.toUpperCase()) {
            case "UP":
                board.moveTokenUp(color);
                break;
            case "DOWN":
                board.moveTokenDown(color);
                break;
            case "LEFT":
                board.moveTokenLeft(color);
                break;
            case "RIGHT":
                board.moveTokenRight(color);
                break;
            case "UPLEFT":
                board.moveTokenUpLeft(color);
                break;
            case "UPRIGHT":
                board.moveTokenUpRight(color);
                break;
            case "DOWNLEFT":
                board.moveTokenDownLeft(color);
                break;
            case "DOWNRIGHT":
                board.moveTokenDownRight(color);
                break;
            default:
                throw new QuoriPOOBException(QuoriPOOBException.TOKEN_WRONG_DIRECTION);
        }
        
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
