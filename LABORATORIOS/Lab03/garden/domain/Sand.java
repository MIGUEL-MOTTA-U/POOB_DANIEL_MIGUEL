package domain;
import java.awt.Color;

/**
 * Represent sand in the garden
 * 
 * @author  Daniel Diaz && Miguel Motta
 */
public class Sand extends Agent implements Thing{
    /**Create a new sand (<b>row,column</b>) in the garden <b>garden</b>.
     * @param garden    It is the garden where the sand is locate
     * @param row       It is the position on the garden where the sand is locate
     * @param column    It is the position on the garden where the sand is locate
     */
    public Sand(Garden garden,int row, int column){
        this.garden = garden;
        this.row = row;
        this.column = column;
        this.color = new Color(0.57f, 0.57f, 0.57f);
    }

    /**
     * Defines the action to be performed by the sand
     */
    @Override
    public void act(){
        this.turn();

        if (super.getTime() != 100) {  
            float fadeRate = 0.005f;
            float red = Math.min((this.color.getRed() / 255.0f) + fadeRate, 1.0f);
            float green = Math.min((this.color.getGreen() / 255.0f) + fadeRate, 1.0f);
            float blue = Math.min((this.color.getBlue() / 255.0f) + fadeRate, 1.0f);

            this.color = new Color(red, green, blue);
        } else {
            garden.setThing(this.row, this.column, null);
        }
    }

    /**
     * Returns the color
     * @return  an object of type Color representing the color of the sand
     */
    @Override
    public Color getColor(){
        return this.color;
    }

    /**
     * Move the sand along the garden
     */
    public void move(){

    }
}
