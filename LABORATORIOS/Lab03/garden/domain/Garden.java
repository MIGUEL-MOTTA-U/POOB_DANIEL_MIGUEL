package domain;
import java.util.*;
import java.util.random.RandomGenerator.LeapableGenerator;

import javax.swing.tree.VariableHeightLayoutCache;


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
        //Flores normales
        Flower rose = new Flower(this, 10, 10);
        setThing(rose.row, rose.getColumn(), rose);

        Flower violet = new Flower(this, 15, 15);
        setThing(violet.getRow(), violet.getColumn(), violet);

        //Flores carnivoras
        Carnivorou venus = new Carnivorou(this, 18, 2);
        setThing(venus.getRow(), venus.getColumn(), venus);

        Carnivorou sundeuos = new Carnivorou(this, 14, 14);
        setThing(sundeuos.getRow(), sundeuos.getColumn(), sundeuos);

        Carnivorou narciso = new Carnivorou(this, 31, 5);
        setThing(narciso.getRow(), narciso.getColumn(), narciso);

        Carnivorou gladiolo = new Carnivorou(this, 35, 12);
        setThing(gladiolo.getRow(), gladiolo.getColumn(), gladiolo);

        //Tatacoa
        setThing(1, 0, new Sand(this, 1, 0));
        for (int r = 0; r < 2; r++) {
            for (int c = 1; c < 3; c++) {
                setThing(r, c, new Sand(this, r, c));
            }
        }
        
        //Sahara
        for (int r = 0; r < 3; r++) {
            for (int c = 35; c < LENGTH; c++) {
                setThing(r, c, new Sand(this, r, c));
            }
        }

        //Flores aromaticas
        Aromatic tulipan = new Aromatic(this, 20, 11);
        setThing(tulipan.getRow(), tulipan.getColumn(), tulipan);

        Aromatic azela = new Aromatic(this, 17, 30);
        setThing(azela.getRow(), azela.getColumn(), azela);

        //Virus
        Virus covid = new Virus(this, 20, 33);
        setThing(covid.getRow(), covid.getColumn(), covid);

        Virus sarampion = new Virus(this, 7, 5);
        setThing(sarampion.getRow(), sarampion.getColumn(), sarampion);
    }
    
    public void ticTac(){
        ArrayList<Thing> copyGarden = this.copyGarden();

        for (Thing thing : copyGarden) {
            thing.act();
        }
    }

    public ArrayList<Thing> copyGarden(){
        ArrayList<Thing> copy = new ArrayList<>();
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                Thing thing = garden[r][c];
                if (thing != null) {                
                    copy.add(thing);
                }
            }
        }

        Collections.sort(copy, new Comparator<Thing>() {
            @Override
            public int compare(Thing t1, Thing t2) {
                if (!(t1 instanceof Aromatic) && !(t1 instanceof Virus)) {
                    if (t2 instanceof Aromatic || t2 instanceof Virus) {
                        return -1;
                    }
                    return 0;
                }
                else if (t1 instanceof Aromatic && !(t2 instanceof Aromatic)) {
                    return 1;
                }
                else if (t1 instanceof Virus && !(t2 instanceof Virus)) {
                    return 1;
                }
                return 0;
            }
        });

        return copy;
    }

    public Thing[][] getThings(){
        return this.garden;
    }
}
