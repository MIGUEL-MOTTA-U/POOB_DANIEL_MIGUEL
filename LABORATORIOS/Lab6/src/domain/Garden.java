package domain;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.*;
import java.util.random.RandomGenerator.LeapableGenerator;

import javax.swing.JOptionPane;
import javax.swing.tree.VariableHeightLayoutCache;


/**
 * Represent a garden with objects
 * 
 * @author  Daniel Diaz && Miguel Motta
 */
public class Garden{
    static public int LENGTH=40;
    private Thing[][] garden;
    
    /**Create a new garden with object
     */
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

    /**
     * Return the length of the garden
     * @return  an integer representing the length of the garden
     */
    public int  getLength(){
        return LENGTH;
    }

    /**
     * Return a thing that is locate in the garden
     * @param r it is the position on the garden where the thing is locate
     * @param c it is the position on the garden where the thing is locate
     * @return  the Thing that is in the given position
     */
    public Thing getThing(int r,int c){
        return garden[r][c];
    }

    /**
     * Add one thing on the garden
     * @param r it is the position on the garden where the thing is locate
     * @param c it is the position on the garden where the thing is locate
     * @param e the thing to add
     */
    public void setThing(int r, int c, Thing e){
        garden[r][c]=e;
    }

    /**
     * Create the things on the garden
     */
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
        
        // Flores de Conway tu rela
        // FlowerConway f1 = new FlowerConway(this, 7, 15);
        // setThing(f1.getRow(), f1.getColumn(), f1);
        
        // FlowerConway f2 = new FlowerConway(this, 7, 16);
        // setThing(f2.getRow(), f2.getColumn(), f2);
        
        // FlowerConway f3 = new FlowerConway(this, 7, 17);
        // setThing(f3.getRow(), f3.getColumn(), f3);
        
        // FlowerConway f4 = new FlowerConway(this, 8, 16);
        // setThing(f4.getRow(), f4.getColumn(), f4);
        
        // Celulas
        
        Cell c1 = new Cell(this, 19, 19);
        setThing(c1.getRow(), c1.getColumn(), c1);
        
        
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
    
    /**
     * Give the instruction to each thing in the garden to perform its action.
     */
    public void ticTac(){
        ArrayList<Thing> copyGarden = this.copyGarden();

        for (Thing thing : copyGarden) {
            thing.act();
        }
    }

    /*
     * Make a copy of the things that are locate in the garden
     */
    private ArrayList<Thing> copyGarden(){
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

    /**
     * Return the things in the garden
     * @return  an array containing the things in the garden
     */
    public Thing[][] getThings(){
        return this.garden;
    }

    /**
     * Open a file given by the user
     * @param file  the file to open
     * @return  the garden saved in the file
     * @throws GardenException
     */
    public static Garden openFile(File file) throws GardenException{
        return null;
    }

    /**
     * Open a file given by the user
     * @param file  the file to open
     * @return  the garden saved in the file
     * @throws GardenException
     */
    public static Garden openFile00(File file) throws GardenException{
        return null;
    }

    /**
     * Save the game in a file
     * @param file  the file to save
     * @throws GardenException
     */
    public void saveFile(File file) throws GardenException{
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(file.getName()));
            for (int row = 0; row < garden.length; row++) {
                for (int col = 0; col < garden.length; col++) {
                    //pw.println(null);
                }  
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "File not found", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Save the game in a file
     * @param file  the file to save
     * @throws GardenException
     */
    public void saveFile00(File file) throws GardenException{
        
    }

    /**
     * Import a file
     * @param file  the file to import
     * @return  the garden saved in the file
     * @throws GardenException
     */
    public static Garden importFile(File file) throws GardenException{
        
        return null;
    }

    /**
     * Export a file
     * @param file  the file to export
     * @throws GardenException
     */
    public void exportFile(File file) throws GardenException{
        
    }
}
