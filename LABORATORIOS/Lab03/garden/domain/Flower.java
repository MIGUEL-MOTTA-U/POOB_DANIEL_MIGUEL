package domain;
import java.awt.Color;

/**Information about a Flower<br>
<b>(garden,row,column,time,state,nextState, color)</b><br>
<br>
 */
public class Flower extends Agent implements Thing{
    protected char nextState;
    protected Color color;
    protected Garden garden;
    protected int row,column;
    
    private int lasTime = 0;


    /**Create a new flower (<b>row,column</b>) in the garden <b>garden</b>.
     * Every new flower is going to be alive in the following state.
     * @param garden 
     * @param row 
     * @param column 
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

    /**Returns the shape
    @return 
     */
    public final int shape(){
        return Thing.FLOWER;
    }
    
    /**Returns the row
    @return 
     */
    public final int getRow(){
        return row;
    }

    /**Returns tha column
    @return 
     */
    public final int getColumn(){
        return column;
    }

    
    /**Returns the color
    @return 
     */
    public final Color getColor(){
        return color;
    }

    public void move(){
    }
    
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
