package domain;

import java.awt.Color;


public abstract class Agent{
    
    public final static char UNKNOWN='u', ALIVE='a', DEAD='d';
    protected char state;
    private int time;

    /**Create a new agent
     * 
     */
    public Agent(){
        state=UNKNOWN;
        time=0;
    }


    /**The agent turns one life span old
     * 
     */
    protected void turn(){
        time++;
    }    
    
    
    /** The agent moves
     */
    public abstract void move();
    
     /**Returns the time
    @return 
     */   
    public final int getTime(){
        return time;
    }    

    /**Returns if alive
    @return true, if ALIVE; false, otherwise
     */
    public final boolean isAlive(){
        return (state == Agent.ALIVE) ;
    }
    
}
