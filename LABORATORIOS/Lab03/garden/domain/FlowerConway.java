package domain;
import java.awt.Color;

/**
 * Write a description of class FlowerConway here.
 * 
 * @author (Daniel Diaz && Miguel Motta) 
 * @version (22/03/2024)
 */
public class FlowerConway extends Flower
{
    public FlowerConway(Garden garden,int row, int column){
        super(garden, row, column);
        color = Color.BLACK;
    }
    
    private int checkCells(){
        Thing[][] things = garden.getThings();
        int cells = 0;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                int roW = this.getRow()-1+i;
                int coL = this.getColumn()-1+j;
                Thing thing = things[roW][coL];
                if(thing instanceof Cell && !(roW == this.getRow() 
                && coL == this.getColumn()) ){
                    cells+=1;
                }
            }
            
        }
        return cells;
    }
    
    private int checkVecinas(){ // Revivir
        Thing[][] things = garden.getThings();
        int flores = 0;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                int roW = this.getRow()-1+i;
                int coL = this.getColumn()-1+j;
                Thing thing = things[roW][coL];
                if(thing instanceof FlowerConway && !(roW == this.getRow() 
                && coL == this.getColumn())){
                    FlowerConway flower = (FlowerConway) thing;
                    if(flower.isAlive()){
                        flores+=1;
                    }
                }
            }
            
        }
        return flores;
    }    
    
    
    /**
     * Defines the action to be performed by the carnivorou
     */
    @Override
    public void act(){
        super.turn();
        int cells = checkCells();
        int vecinas = checkVecinas();
        if(vecinas != 3 && super.isAlive()){
            this.state = Agent.DEAD;
            this.color = Color.ORANGE;
            this.nextState = Agent.ALIVE;
        } else {
            this.state = Agent.ALIVE;
            this.nextState = Agent.DEAD;
            this.color = Color.BLACK;
        }
        
        if((cells == 2 || cells == 3) && super.isAlive()){
            super.state = Agent.ALIVE;
            super.nextState = Agent.ALIVE;
        }
        
        if(!super.isAlive() && cells == 3){
            this.state = Agent.ALIVE;
            this.nextState = Agent.DEAD;
            this.color = Color.BLACK;
        }
        
    }
}