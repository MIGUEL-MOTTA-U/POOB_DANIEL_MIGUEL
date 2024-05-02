package domain;

import java.awt.Color;

/**
 * It is the entity that interact with the garden
 *
 * @author Daniel Diaz && Miguel Motta
 */
public abstract class Agent{
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
}
