package domain;

import java.awt.Color;
import java.util.ArrayList;

public class Advanced extends Machine {
    public Advanced(String name, Color color) {
        super(name, color);
    }

    @Override
    protected void moveToken(String direction) throws QuoriPOOBException {
        play(direction);
    }

    @Override
    protected void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide)
            throws QuoriPOOBException {
        play(null);
    }

    private void play(String direction) throws QuoriPOOBException {
        ArrayList<Square> machinePath = calculateMyShorestPath();
        ArrayList<Square> humanPath = getOtherPlayer().calculateMyShorestPath();
        if(walls.size() < 1||!putWall(humanPath)){
            try{
                direction = getDirection(machinePath.get(1));
            } catch (Exception e){
                direction = getDirection(lastSquare());
            }
            super.moveToken(direction);
        }
    }

    private boolean putWall(ArrayList<Square> squares){
        int initialRow, initialColumn;
        String side;
        for(int i= 0; i < squares.size() -1; i++){
            side = getWallSide(squares.get(i), squares.get(i+1));
            
            initialRow = squares.get(i).getCoordenates()[0];
            initialColumn = squares.get(i).getCoordenates()[1];
            String type = walls.get(0).getClass().getSimpleName();
            try{
                super.addWallToBoard(type, initialRow, initialColumn, side);
                return true;
            } catch (QuoriPOOBException e){
                try{
                    if(side.equals("UP") || side.equals("DOWN")){
                        initialColumn -=1;
                    } else {
                        initialRow-=1;
                    }
                    super.addWallToBoard(type, initialRow, initialColumn, side);
                    return true;
                } catch (QuoriPOOBException q){}
            }
        }
        return false;
    }


    private String getWallSide(Square square, Square nextSquare){
        String res;
        int[] coordenatesSquare = square.getCoordenates();
        int[] coordenatesNext = nextSquare.getCoordenates();
        if(coordenatesSquare[0]==coordenatesNext[0]){
            res = (coordenatesSquare[1]-coordenatesNext[1]>0)?"LEFT":"RIGHT";
        } else {
            res = (coordenatesSquare[0]-coordenatesNext[0]>0)?"UP":"DOWN";
        }
        return res;
    }
    private String getDirection(Square square) throws QuoriPOOBException {
        return board.getDirection(color, square);
    }
}
