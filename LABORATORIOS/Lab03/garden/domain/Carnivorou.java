package domain;

import java.awt.Color;

public class Carnivorou extends Flower{
    public Carnivorou(Garden garden,int row, int column){
        super(garden, row, column);
        color = Color.BLUE;
    }

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
