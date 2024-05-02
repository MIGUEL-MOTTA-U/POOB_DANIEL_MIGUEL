package domain;
import java.awt.Color;

/**
 * Represent a flower in the garden
 * 
 * @author  Daniel Diaz && Miguel Motta
 */
public class Flower extends Agent implements Thing{
    protected char nextState;
    
    private int lasTime = 0;


    /**Create a new flower (<b>row,column</b>) in the garden <b>garden</b>.
     * Every new flower is going to be alive in the following state.
     * @param garden    It is the garden where the flower is locate
     * @param row       It is the position on the garden where the flower is locate
     * @param column    It is the position on the garden where the flower is locate
     */
    public Flower(Garden garden,int row, int column){
        this.garden=garden;
        this.row=row;
        this.column=column;
        this.state = Agent.ALIVE;
        nextState=Agent.DEAD;
        garden.setThing(row,column,(Thing)this);  
        color=Color.red;
    }

    /**
     * Returns the shape
     * @return  an integer representing the shape of the flower
     */
    public final int shape(){
        return Thing.FLOWER;
    }
    
    /**
     * Returns the row
     * @return  the row where is locate the flower
     */
    public final int getRow(){
        return row;
    }

    /**
     * Returns the column
     * @return  the column where is locate the flower
     */
    public final int getColumn(){
        return column;
    }

    
    /**
     * Returns the color
     * @return  an object of type Color representing the color of the flower
     */
    public final Color getColor(){
        return color;
    }

    /**
     * Move the flower along the garden
     */
    public void move(){
    }
    
    /**
     * Defines the action to be performed by the flower
     */
    @Override
    public void act(){
        super.turn();

        int time = super.getTime();
        if (super.isAlive() && time == this.lasTime + 3) {
            state = Agent.DEAD;
            color = Color.ORANGE;
            this.nextState = Agent.ALIVE;
            this.lasTime = time;
        } else if (!super.isAlive() && time == this.lasTime + 2) {
            state = Agent.ALIVE;
            color = Color.RED;
            this.nextState = Agent.DEAD;
            this.lasTime = time;
        }
    }
}
