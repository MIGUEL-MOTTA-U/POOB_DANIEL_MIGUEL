package domain;

import java.awt.Color;
import java.util.ArrayList;


public class Intermediate extends Machine{

    public Intermediate(String name, Color color) {
        super(name, color);
    }

    @Override
    protected void moveToken(String direction) throws QuoriPOOBException {
        play(direction);
    }

    @Override
	protected void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide) throws QuoriPOOBException {
        play(null);
    }

    private void play(String direction) throws QuoriPOOBException{
        // Este metodo debe calcular la distancia minima del otro jugador y la propia para llegar al final
        // Si la distancia menor para llegar al destino es mia o igual, yo me muevo adelante
        // Si no coloco un muro buscando obstaculizar el del contrincante en su camino mas corto
        // Si es posible, si no es posible debo moverme yo.
        ArrayList<Square> machinePath = calculateMyShorestPath();
        ArrayList<Square> humanPath = getOtherPlayer().calculateMyShorestPath();
        if(machinePath.size()<= humanPath.size()){
            direction = getDirection(machinePath.get(1)); // Si toma con indice 0, toma la casilla actual
            super.moveToken(direction);
        }else {
            System.out.println("Comportamiento EN CONSTRUCCION");
            //addWallToBoard(direction, 0, 0, direction);
            super.moveToken("DOWN");
            
        }

    }

    private String getDirection(Square square) throws QuoriPOOBException{
        return board.getDirection(color, square);
    }    
}
