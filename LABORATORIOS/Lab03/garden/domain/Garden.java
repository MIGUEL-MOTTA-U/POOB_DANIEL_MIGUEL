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

        Carnivorou venus = new Carnivorou(this, 18, 2);
        setThing(venus.getRow(), venus.getColumn(), venus);

        Carnivorou sundeuos = new Carnivorou(this, 14, 14);
        setThing(sundeuos.getRow(), sundeuos.getColumn(), sundeuos);

        Carnivorou narciso = new Carnivorou(this, 31, 5);
        setThing(narciso.getRow(), narciso.getColumn(), narciso);

        Carnivorou gladiolo = new Carnivorou(this, 35, 12);
        setThing(gladiolo.getRow(), gladiolo.getColumn(), gladiolo);

        //Tatacoa
        setThing(1, 0, new Sand());
        for (int r = 0; r < 2; r++) {
            for (int c = 1; c < 3; c++) {
                setThing(r, c, new Sand());
            }
        }
        
        //Sahara
        for (int r = 0; r < 3; r++) {
            for (int c = 35; c < LENGTH; c++) {
                setThing(r, c, new Sand());
            }
        }

        Aromatic tulipan = new Aromatic(this, 20, 11);
        setThing(tulipan.getRow(), tulipan.getColumn(), tulipan);

        Aromatic azela = new Aromatic(this, 17, 30);
        setThing(azela.getRow(), azela.getColumn(), azela);
    }
    
    public void ticTac(){
        Thing[][] copyGarden = this.copyGarden();

        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                Thing thing = copyGarden[r][c];
                if (!(thing instanceof Aromatic) && thing != null) {
                    thing.act();
                }
            }
        }

        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                Thing thing = copyGarden[r][c];
                if (thing instanceof Aromatic && thing != null) {
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
