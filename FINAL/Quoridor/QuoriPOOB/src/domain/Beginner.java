package domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Beginner extends Machine{

    public Beginner(String name, Color color) {
        super(name, color);
    }

    @Override
    public void play(String direction) throws QuoriPOOBException{
        Random random = new Random();
        int option = random.nextInt(2);
        if(option ==0) {
            jump();
        } else {
            putWall();
        }
    }

    private void putWall()throws QuoriPOOBException{
        ArrayList<String> types = new ArrayList<>(Arrays.asList("Temporary", "NormalWall", "Allied"));
        ArrayList<String> orientation = new ArrayList<>(Arrays.asList("UP", "DOWN", "LEFT", "RIGHT"));
        int type,oriented, row, column;
        boolean created = false;
        Random random = new Random();
        while (!created) {
            type = random.nextInt(types.size());
            oriented = random.nextInt(orientation.size());
            row = random.nextInt(board.getSize());
            column = random.nextInt(board.getSize());
            try{
                addWallToBoard(types.get(type), row, column, orientation.get(oriented));
                created = true;
            } catch(QuoriPOOBException e){}
        }
    }

    private void jump() throws QuoriPOOBException{
        ArrayList<String> possibilities = new ArrayList<>(Arrays.asList("UP", "DOWN", "LEFT", "RIGHT"));
        int option;
        boolean moved = false;
        Random random = new Random();
        while (possibilities.size()>0 && !moved) {
            option = random.nextInt(possibilities.size());
            try{
                moveToken(possibilities.get(option));
                moved = true;
            } catch(QuoriPOOBException e){
                possibilities.remove(option);
            }
        } if (possibilities.size() == 0 && !moved) throw new QuoriPOOBException(QuoriPOOBException.RETURN_MOVES_NOT_POSSIBLE);
    }

    @Override
    public void play(String type, int initialRow, int initialColumn, String squareSide) throws QuoriPOOBException {
        play(null);
    }
}
