package domain;
import java.util.*;


/*No olviden adicionar la documentacion*/
public class Garden{
    static public int LENGTH=40;
    private Thing[][] garden;
	
    public Garden() {
        garden=new Thing[LENGTH][LENGTH];
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                garden[r][c]=null;
            }
        }
        setThing(0,0,new Water());
        for (int i=1;i<5;i++){
            for (int j=1;j<5;j++){
                setThing(LENGTH-i,LENGTH-j,new Water());
            }
        }
        someThings();
    }

    public int  getLength(){
        return LENGTH;
    }

    public Thing getThing(int r,int c){
        return garden[r][c];
    }

    public void setThing(int r, int c, Thing e){
        garden[r][c]=e;
    }

    public void someThings(){
        Flower rose = new Flower(this, 10, 10);
        setThing(10, 10, rose);

        Flower violet = new Flower(this, 15, 15);
        setThing(15, 15, violet);
    }
    
    public void ticTac(){
    }

}
