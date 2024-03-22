package domain;
import java.awt.Color;

/**
 * Write a description of class Cell here.
 * 
 * @author (Daniel Diaz && Miguel Motta) 
 * @version (22/03/2024)
 */
public class Cell extends Agent implements Thing
{
    protected char nextState;
    /**
     * Constructor for objects of class Cell
     */
    /**Create a new cell (<b>row,column</b>) in the garden <b>garden</b>.
     * Every new flower is going to be alive in the following state.
     * @param garden    It is the garden where the cell is locate
     * @param row       It is the position on the garden where the cell is locate
     * @param column    It is the position on the garden where the cell is locate
     */
    public Cell(Garden garden,int row, int column){
        this.garden=garden;
        this.row=row;
        this.column=column;
        this.state = Agent.ALIVE;
        nextState=Agent.DEAD;
        garden.setThing(row,column,(Thing)this);  
        color=Color.BLUE;
    }
    
    /**
     * Returns the shape
     * @return  an integer representing the shape of the cell
     */
    public final int shape(){
        return Thing.ROUND;
    }
    
    /**
     * Returns the row
     * @return  the row where is locate the cell
     */
    public final int getRow(){
        return row;
    }

    /**
     * Returns the column
     * @return  the column where is locate the cell
     */
    public final int getColumn(){
        return column;
    }
    
    
    /**
     * Returns the color
     * @return  an object of type Color representing the color of the cell
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
     * Defines the action to be performed by the cell
     */
    @Override
    public void act(){
        super.turn();
        Thing[][] things = garden.getThings();
        
        for (int r = 0; r < Garden.LENGTH; r++) {
            for (int c = 0; c < Garden.LENGTH; c++) {
                Thing thing = things[r][c];
                int cells = 0;                
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        int roW = r-1+i;
                        int colum = c-1+j;
                        if(roW >= 0  && roW < garden.LENGTH && colum >= 0  
                        && colum < garden.LENGTH && thing == null){
                            Thing aroundThing = things[roW][colum];
                            if(aroundThing instanceof Cell){
                                Cell aroundCell = (Cell) thing;
                                if( aroundCell != null && aroundCell.isAlive()){
                                    cells+=1;
                                }
                            }   
                        }
                        
                    }
                    
                }
                if(cells == 3){
                Cell bornCell = new Cell(garden,r, c);
                //garden.setThing(bornCell.getRow(), bornCell.getColumn(), bornCell);
                }
            }
        }
    }
}
