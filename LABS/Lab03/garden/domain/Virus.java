package domain;

import java.awt.Color;

/**
 * Represent a virus in the garden
 * 
 * @author  Daniel Diaz && Miguel Motta
 */
public class Virus extends Agent implements Thing {
    private int hunger;

    /**Create a new virus (<b>row,column</b>) in the garden <b>garden</b>.
     * Every new virus is going to be alive in the following state.
     * @param garden    It is the garden where the virus is locate
     * @param row       It is the position on the garden where the virus is locate
     * @param column    It is the position on the garden where the virus is locate
     */
    public Virus(Garden garden,int row, int column){
        this.garden = garden;
        this.row = row;
        this.column = column;
        this.color = Color.GREEN;
        this.state = Agent.ALIVE;
    }

    /**
     * Move the virus along the garden
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
     * find the nearest flower that is not a carnivorou to the virus
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
     * Defines the action to be performed by the virus
     */
    @Override
    public void act(){
        super.turn();

        if (this.hunger > 4) {
            this.state = Agent.DEAD;
            this.garden.setThing(this.row, this.column, new Sand(this.garden, this.row, this.column));
        } else {
            this.hunger++;
            Flower nearestFlower = this.nearestFlower();
            
            if (nearestFlower != null) {
                int rowFlower = nearestFlower.getRow();
                int columnFlower = nearestFlower.getColumn();
                int difColumn = Math.abs(columnFlower - this.column);
                int difRow = Math.abs(rowFlower - this.row);

                if (difColumn <= 1 && difRow <= 1) {
                    garden.setThing(rowFlower, columnFlower, new Carnivorou(this.garden, rowFlower, columnFlower));
                    this.hunger = 0;
                } else {
                    this.move();          
                }
            }
        }
    }

    /**
     * Returns the color
     * @return  an object of type Color representing the color of the virus
     */
    @Override
    public Color getColor(){
        return this.color;
    }

    /**
     * Returns the shape
     * @return  an integer representing the shape of the virus
     */
    @Override
    public int shape(){
        return Thing.ROUND;
    }

    /**
     * Returns the row
     * @return  the row where is locate the virus
     */
    public int getRow(){
        return this.row;
    }

    /**
     * Returns the column
     * @return  the column where is locate the virus
     */
    public int getColumn(){
        return this.column;
    }
}
