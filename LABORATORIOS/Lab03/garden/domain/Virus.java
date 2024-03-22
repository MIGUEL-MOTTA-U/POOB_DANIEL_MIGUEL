package domain;

import java.awt.Color;

public class Virus extends Agent implements Thing {
    private int hunger;

    public Virus(Garden garden,int row, int column){
        this.garden = garden;
        this.row = row;
        this.column = column;
        this.color = Color.GREEN;
        this.state = Agent.ALIVE;
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

    @Override
    public Color getColor(){
        return this.color;
    }

    @Override
    public int shape(){
        return Thing.ROUND;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }
}
