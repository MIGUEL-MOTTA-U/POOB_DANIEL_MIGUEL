package domain;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represent an aromatic in the garden
 * 
 * @author  Daniel Diaz && Miguel Motta
 */
public class Aromatic extends Flower{
    private int emissions;

    /**Create a new carnivorou (<b>row,column</b>) in the garden <b>garden</b>.
     * Every new carnivorou is going to be alive in the following state.
     * @param garden    It is the garden where the carnivorou is locate
     * @param row       It is the position on the garden where the carnivorou is locate
     * @param column    It is the position on the garden where the carnivorou is locate
     */
    public Aromatic(Garden garden,int row, int column){
        super(garden, row, column);
        this.color = Color.CYAN;
        this.emissions = 0;
    }

    /*
     * find the carnivorous found in the range of the aromatic
     */
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

    /*
     * Verify if a carnivorou is in the range of the aromatic
     */
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

    /*
     * Count the times that the aromatic sprays its aroma
     */
    private void emissions(){
        this.emissions++;
    }

    /**
     * Defines the action to be performed by the aromatic
     */
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
