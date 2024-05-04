package domain;

import java.awt.Color;

public class Intermediate extends Machine{

    public Intermediate(String name, Color color) {
        super(name, color);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void moveToken(Color color, String direction) {
        // Mover con algoritmo de dificultad media y poner puentes
    }

    @Override
    public void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide){
        // Esta parte la debe calcular si es maquina
        // Cuando es a maquina los parametros son nulos
        // Como lo calcula ella determina el tipo, el initialRow e initialColumn, tambien el squareSide.
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
