package domain;

import java.awt.Color;

/**
 * Represent a Carnivorou in the garden
 * 
 * @author  Daniel Diaz && Miguel Motta
 */
public class Carnivorou extends Flower{
    /**Create a new carnivorou (<b>row,column</b>) in the garden <b>garden</b>.
     * Every new carnivorou is going to be alive in the following state.
     * @param garden    It is the garden where the carnivorou is locate
     * @param row       It is the position on the garden where the carnivorou is locate
     * @param column    It is the position on the garden where the carnivorou is locate
     */
    public Carnivorou(Garden garden,int row, int column){
        super(garden, row, column);
        color = Color.BLUE;
    }

    /**
     * Move the carnivorou along the garden
     */
    @Override
    public void move(){
        Flower nearestFlower = this.nearestFlower();
        int rowFlower = nearestFlower.getRow();
        int columnFlower = nearestFlower.getColumn();
        garden.setThing(this.row, this.column, null);
        
        if (this.column < columnFlower - 1) {
            this.column += 1;
        } else if (this.column > columnFlower + 1) {
            this.column -= 1;
        }
        
        if (this.row < rowFlower - 1) {
            this.row += 1;
        } else if (this.row > rowFlower + 1) {
            this.row -= 1;
        }
        
        garden.setThing(this.row, this.column, this);
    }

    /*
     * find the nearest flower to the carnivorou
     */
    private Flower nearestFlower(){
        Flower nearestFlower = null;
        Thing[][] things = garden.getThings();
        int minDistance = Integer.MAX_VALUE;

        for (int r = 0; r < Garden.LENGTH; r++) {
            for (int c = 0; c < Garden.LENGTH; c++) {
                Thing thing = things[r][c];
                if ((thing instanceof Flower) && !(thing instanceof Carnivorou)) {
                    Flower flower = (Flower) thing;
                    int distance = Math.abs(this.column - flower.getColumn()) + Math.abs(this.row - flower.getRow());
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestFlower = flower;
                    }
                }
            }
        }

        return nearestFlower;
    }

    /**
     * Defines the action to be performed by the carnivorou
     */
    @Override
    public void act(){
        super.turn();
        Flower nearestFlower = this.nearestFlower();
        
        if (nearestFlower != null) {
            int rowFlower = nearestFlower.getRow();
            int columnFlower = nearestFlower.getColumn();
            int difColumn = Math.abs(columnFlower - this.column);
            int difRow = Math.abs(rowFlower - this.row);

            if (difColumn <= 1 && difRow <= 1) {
                garden.setThing(this.row, this.column, null);
                this.row = rowFlower; this.column = columnFlower;
                garden.setThing(this.row, this.column, this);
            } else {
                this.move();          
            }
        }
    }
}
