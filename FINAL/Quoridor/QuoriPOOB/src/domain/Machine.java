package domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Machine extends Player {
    public Machine(String name, Color color) {
        super(name, color);
    }
    protected Square lastSquare(){
		int lastAdded = board.getTokens().get(color).getLastMovements().size() - 1;
		int[] lastCoordenates = board.getTokens().get(color).getLastMovements().get(lastAdded);
		int row = lastCoordenates[0], column = lastCoordenates[1];
		return board.getMatrixBoard()[row][column];
	}
	protected String getDirection(Square square) throws QuoriPOOBException {
        return board.getDirection(color, square);
    }
    

	protected void alternativeMove(){
        String direction;
        try{
            direction = getDirection(lastSquare());
            super.moveToken(direction);
        } catch (Exception e){
            ArrayList<String> directions = new ArrayList<>(Arrays.asList("UP", "DOWN", "RIGHT", "LEFT"));
            boolean moved = false;
            int i = 0;
            while(!moved) {
                try{
                    super.moveToken(directions.get(i));
                    moved = true;
                } catch(QuoriPOOBException q){
                    i++;
                }
            }
        }
    }
}
