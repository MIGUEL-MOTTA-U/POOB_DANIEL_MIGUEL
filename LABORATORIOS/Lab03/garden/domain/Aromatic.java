package domain;

import java.awt.Color;
import java.util.ArrayList;

public class Aromatic extends Flower{
    private int emissions;

    public Aromatic(Garden garden,int row, int column){
        super(garden, row, column);
        this.color = Color.CYAN;
        this.emissions = 0;
    }

    private ArrayList<Carnivorou> nearestCarnivorous(){
        ArrayList<Carnivorou> nearestCarnivorous = new ArrayList<>();
        Thing[][] things = this.garden.getThings();

        for (int r = 0; r < Garden.LENGTH; r++) {
            for (int c = 0; c < Garden.LENGTH; c++) {
                Thing thing = things[r][c];
                if (thing instanceof Carnivorou) {
                    Carnivorou carnivorou = (Carnivorou) thing;
                    if (inRange(carnivorou)) {
                        nearestCarnivorous.add(carnivorou);
                    }
                }
            }
        }

        return nearestCarnivorous;
    }

    private boolean inRange(Carnivorou carnivorou){
        int row = carnivorou.getRow();
        int column = carnivorou.getColumn();

        for (int r = this.row - 3; r <= this.row + 3; r++) {
            for (int c = this.column - 3; c <= this.column + 3; c++) {
                if (r == row && c == column) {
                    return true;
                }
            }
        }
        return false;
    }

    private void emissions(){
        this.emissions++;
    }

    @Override
    public void act(){
        ArrayList<Carnivorou> carnivorous = nearestCarnivorous();
        if (!carnivorous.isEmpty()) {
            if (this.emissions < 3) {
                this.emissions();
                for (Carnivorou carnivorou : carnivorous) {
                    int row = carnivorou.getRow();
                    int column = carnivorou.getColumn();
                    garden.setThing(row, column, new Flower(this.garden, row, column));
                }
            }   
            if (this.emissions == 3) {
                this.state = Agent.DEAD;
                this.color = Color.ORANGE;
            }
        }
    }
}
