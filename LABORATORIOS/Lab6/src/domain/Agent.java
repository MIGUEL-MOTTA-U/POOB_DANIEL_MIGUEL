package domain;

import java.awt.Color;
import java.io.Serializable;

/**
 * It is the entity that interact with the garden
 *
 * @author Daniel Diaz && Miguel Motta
 */
public abstract class Agent implements Serializable, Thing {
    public final static char UNKNOWN='u', ALIVE='a', DEAD='d';
    protected char state;
    private int time;

    protected Color color;
    protected Garden garden;
    protected int row; protected int column;

    /**Create a new agent
     * 
     */
    public Agent(){
        state=UNKNOWN;
        time=0;
    }

    /**
     * The agent turns one life span old 
     */
    protected void turn(){
        time++;
    }    
    
    
    /** 
     * The agent moves
     */
    public abstract void move();
    
    /**
      * Returns the time
      * @return an integer representing the time
     */   
    public final int getTime(){
        return time;
    }    

    /**R
     * eturns if alive
      * @return true, if ALIVE; false, otherwise
     */
    public final boolean isAlive(){
        return (state == Agent.ALIVE) ;
    }  

    /**
     * Return the color
     * @return the color of the agent
     */
    public Color getColor(){
        return this.color;
    }
    
    /**
     * Return the state
     * @return the state of the agent
     */
    public char getState(){
        return this.state;
    }

    /**
     * Returns the row
     * @return  the row where is locate the Agent
     */
    public int getRow(){
        return this.row;
    }

    /**
     * Returns the column
     * @return  the column where is locate the Agent
     */
    public int getColumn(){
        return this.column;
    }

    /**
     * 
     */
    public boolean isEqual(Agent s){
        boolean res = false;
        if(s.getState()== this.getState() && 
            s.getColor().equals(this.getColor()) && 
            s.getTime() == this.getTime() &&
            s.isAlive() == this.isAlive() && 
            s.getColumn() == this.getColumn() && 
            s.getRow() == this.getRow()){
            res = true;
        }
        return res;
    }
}
