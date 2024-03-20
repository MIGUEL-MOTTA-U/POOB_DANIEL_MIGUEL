package domain;
import java.util.*;
import java.util.random.RandomGenerator.LeapableGenerator;


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
        setThing(rose.row, rose.getColumn(), rose);

        Flower violet = new Flower(this, 15, 15);
        setThing(violet.getRow(), violet.getColumn(), violet);

        Carnivorous venus = new Carnivorous(this, 7, 12);
        setThing(venus.getRow(), venus.getColumn(), venus);

        Carnivorous sundeuos = new Carnivorous(this, 14, 14);
        setThing(sundeuos.getRow(), sundeuos.getColumn(), sundeuos);
    }
    
    public void ticTac(){
        Thing[][] copyGarden = this.copyGarden();

        for (int r = 0; r < LENGTH; r++){
            for (int c = 0; c < LENGTH; c++){
                Thing thing = copyGarden[r][c];
                if (thing != null) {
                    thing.act();
                }
            }
        }
    }

    public Thing[][] getThings(){
        return this.garden;
    }

    public Thing[][] copyGarden(){
        Thing[][] copy = new Thing[LENGTH][LENGTH];
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                copy[r][c]=garden[r][c];
            }
        }

        return copy;
    }
}
