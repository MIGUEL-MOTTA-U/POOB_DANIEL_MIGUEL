package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.*;
//import java.util.random.RandomGenerator.LeapableGenerator;

import javax.swing.JOptionPane;
import javax.swing.tree.VariableHeightLayoutCache;

/**
 * Represent a garden with objects
 * 
 * @author Daniel Diaz && Miguel Motta
 */
public class Garden implements Serializable {
    static public int LENGTH = 40;
    private Thing[][] garden;

    /**
     * Create a new garden with object
     */
    public Garden() {
        garden = new Thing[LENGTH][LENGTH];
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                garden[r][c] = null;
            }
        }
        setThing(0, 0, new Water());
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                setThing(LENGTH - i, LENGTH - j, new Water());
            }
        }
        someThings();
    }

    /**
     * Return the length of the garden
     * 
     * @return an integer representing the length of the garden
     */
    public int getLength() {
        return LENGTH;
    }

    /**
     * Return a thing that is locate in the garden
     * 
     * @param r it is the position on the garden where the thing is locate
     * @param c it is the position on the garden where the thing is locate
     * @return the Thing that is in the given position
     */
    public Thing getThing(int r, int c) {
        return garden[r][c];
    }

    /**
     * Add one thing on the garden
     * 
     * @param r it is the position on the garden where the thing is locate
     * @param c it is the position on the garden where the thing is locate
     * @param e the thing to add
     */
    public void setThing(int r, int c, Thing e) {
        garden[r][c] = e;
    }

    /**
     * Create the things on the garden
     */
    public void someThings() {
        // Flores normales
        Flower rose = new Flower(this, 10, 10);
        setThing(rose.row, rose.getColumn(), rose);

        Flower violet = new Flower(this, 15, 15);
        setThing(violet.getRow(), violet.getColumn(), violet);

        // Flores carnivoras
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

        // Tatacoa
        setThing(1, 0, new Sand(this, 1, 0));
        for (int r = 0; r < 2; r++) {
            for (int c = 1; c < 3; c++) {
                setThing(r, c, new Sand(this, r, c));
            }
        }

        // Sahara
        for (int r = 0; r < 3; r++) {
            for (int c = 35; c < LENGTH; c++) {
                setThing(r, c, new Sand(this, r, c));
            }
        }

        // Flores aromaticas
        Aromatic tulipan = new Aromatic(this, 20, 11);
        setThing(tulipan.getRow(), tulipan.getColumn(), tulipan);

        Aromatic azela = new Aromatic(this, 17, 30);
        setThing(azela.getRow(), azela.getColumn(), azela);

        // Virus
        Virus covid = new Virus(this, 20, 33);
        setThing(covid.getRow(), covid.getColumn(), covid);

        Virus sarampion = new Virus(this, 7, 5);
        setThing(sarampion.getRow(), sarampion.getColumn(), sarampion);
    }

    /**
     * Give the instruction to each thing in the garden to perform its action.
     */
    public void ticTac() {
        ArrayList<Thing> copyGarden = this.copyGarden();

        for (Thing thing : copyGarden) {
            thing.act();
        }
    }

    /*
     * Make a copy of the things that are locate in the garden
     */
    private ArrayList<Thing> copyGarden() {
        ArrayList<Thing> copy = new ArrayList<>();
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
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
                } else if (t1 instanceof Aromatic && !(t2 instanceof Aromatic)) {
                    return 1;
                } else if (t1 instanceof Virus && !(t2 instanceof Virus)) {
                    return 1;
                }
                return 0;
            }
        });

        return copy;
    }

    /**
     * Return the things in the garden
     * 
     * @return an array containing the things in the garden
     */
    public Thing[][] getThings() {
        return this.garden;
    }

    /**
     * Save the game in a file
     * 
     * @param file the file to save
     * @throws GardenException
     */
    public void saveFile00(File file) throws GardenException {

    }

    /**
     * Open a file given by the user
     * 
     * @param file the file to open
     * @return the garden saved in the file
     * @throws GardenException
     */
    public static Garden openFile00(File file) throws GardenException {
        return null;
    }

    /**
     * Save the game in a file
     * 
     * @param file the file to save
     * @throws GardenException
     */
    public void saveFile01(File file) throws GardenException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject("Garden storage\n");
            out.writeObject(this);
            out.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al guardar el archivo",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Open a file given by the user
     * 
     * @param file the file to open
     * @return the garden saved in the file
     * @throws GardenException
     */
    public static Garden openFile01(File file) throws GardenException {
        Garden garden = null;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            String s = (String) in.readObject();
            garden = (Garden) in.readObject();
            in.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al abrir el archivo", JOptionPane.ERROR_MESSAGE);
        }

        return garden;
    }

    /**
     * Save the game in a file
     * 
     * @param file the file to save
     * @throws GardenException
     */
    public void saveFile(File file) throws GardenException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject("Garden storage\n");
            out.writeObject(this);
            out.close();
        } catch (FileNotFoundException e) {
            throw new GardenException(GardenException.FILE_NOT_FOUND(file.getName()));
        } catch (IOException e) {
            throw new GardenException(GardenException.SAVE_FILE_ERROR(file.getName()));
        } catch (NullPointerException e) {
            throw new GardenException(GardenException.FILE_NULL);
        }
    }

    /**
     * Open a file given by the user
     * 
     * @param file the file to open
     * @return the garden saved in the file
     * @throws GardenException
     */
    public static Garden openFile(File file) throws GardenException {
        Garden garden = null;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            String s = (String) in.readObject();
            garden = (Garden) in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            throw new GardenException(GardenException.FILE_NOT_FOUND(file.getName()));
        } catch (IOException e) {
            throw new GardenException(GardenException.OPEN_FILE_ERROR(file.getName()));
        } catch (ClassNotFoundException e) {
            throw new GardenException(GardenException.CLASS_NOT_FOUND);
        } catch (NullPointerException e) {
            throw new GardenException(GardenException.FILE_NULL);
        }

        return garden;
    }

    /**
     * Export a file
     * 
     * @param file the file to export
     * @throws GardenException
     */
    public void exportFile00(File file) throws GardenException {

    }

    /**
     * Import a file
     * 
     * @param file the file to import
     * @return the garden saved in the file
     * @throws GardenException
     */
    public static Garden importFile00(File file) throws GardenException {
        return null;
    }

    /**
     * Export a file
     * 
     * @param file the file to export
     * @throws GardenException
     */
    public void exportFile01(File file) throws GardenException {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(file));
            for (int row = 0; row < LENGTH; row++) {
                for (int column = 0; column < LENGTH; column++) {
                    if (garden[row][column] != null) {
                        String type = garden[row][column].getClass().getSimpleName();
                        pw.println(type + " " + row + " " + column);
                    } else {
                        pw.println("Null" + " " + row + " " + column);
                    }
                }
            }

            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al exportar el archivo",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Import a file
     * 
     * @param file the file to import
     * @return the garden saved in the file
     * @throws GardenException
     */
    public static Garden importFile01(File file) throws GardenException {
        Garden newGarden = new Garden();

        try {
            BufferedReader bIn = new BufferedReader(new FileReader(file));
            String line = bIn.readLine();
            while (line != null) {
                String[] data = line.split("\\s+");
                for (String string : data) {
                    string = string.trim();
                }
                addThingToBoard(data, newGarden);
                line = bIn.readLine();
            }

            bIn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al importar el archivo",
                    JOptionPane.ERROR_MESSAGE);
        }

        return newGarden;
    }

    /**
     * Export a file
     * 
     * @param file the file to export
     * @throws GardenException
     */
    public void exportFile02(File file) throws GardenException {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(file));
            for (int row = 0; row < LENGTH; row++) {
                for (int column = 0; column < LENGTH; column++) {
                    if (garden[row][column] != null) {
                        String type = garden[row][column].getClass().getSimpleName();
                        pw.println(type + " " + row + " " + column);
                    } else {
                        pw.println("Null" + " " + row + " " + column);
                    }
                }
            }

            pw.close();
        } catch (FileNotFoundException e) {
            throw new GardenException(GardenException.FILE_NOT_FOUND(file.getName()));
        }

    }

    /**
     * Import a file
     * 
     * @param file the file to import
     * @return the garden saved in the file
     * @throws GardenException
     */
    public static Garden importFile02(File file) throws GardenException {
        Garden newGarden = new Garden();

        try {
            BufferedReader bIn = new BufferedReader(new FileReader(file));
            String line = bIn.readLine();
            while (line != null) {
                String[] data = line.split("\\s+");
                for (String string : data) {
                    string = string.trim();
                }
                addThingToBoard(data, newGarden);
                line = bIn.readLine();
            }

            bIn.close();
        } catch (FileNotFoundException e) {
            throw new GardenException(GardenException.FILE_NOT_FOUND(file.getName()));
        } catch (IOException e) {
            throw new GardenException(GardenException.IMPORT_FILE_ERROR(file.getName()));
        }

        return newGarden;
    }

    private static void addThingToBoard(String[] data, Garden garden) throws GardenException{
        try {
            int row = Integer.parseInt(data[1]);
            int column = Integer.parseInt(data[2]);

            if (data[0].equals("Null")) {
                garden.setThing(row, column, null);
            } else {
                garden.setThing(row, column, createThing(data[0], garden, row, column));
            }
        } catch (NumberFormatException e) {
            throw new GardenException(GardenException.NUMBER_ERROR);
        }

    }

    private static Thing createThing(String type, Garden garden, int row, int column) throws GardenException{
        Thing thing = null;
        type = "domain." + type;
    
        try {
            Class<?> cls = Class.forName(type);
            if (type.equals("domain.Water")) {
                Constructor<?> constructor = cls.getDeclaredConstructor();
                constructor.setAccessible(true);
                thing = (Thing) constructor.newInstance();
            } else {
                Constructor<?> constructor = cls.getDeclaredConstructor(Garden.class, int.class, int.class);
                constructor.setAccessible(true);
                thing = (Thing) constructor.newInstance(garden, row, column);
            }
        }  catch (ClassNotFoundException e) {
            throw new GardenException(GardenException.CLASS_NOT_FOUND(type));
        } catch (NoSuchMethodException e) {
            throw new GardenException(GardenException.CONSTRUCTOR_NOT_FOUND(type));
        } catch (InstantiationException e) {
            throw new GardenException(GardenException.INSTANTIATION_ERROR(type));
        } catch (IllegalAccessException e) {
            throw new GardenException(GardenException.CONSTRUCTOR_ACCESS_FOUND(type));
        } catch (IllegalArgumentException e) {
            throw new GardenException(GardenException.ILLEGAL_ARGUMENT(type));
        } catch (InvocationTargetException e) {
            throw new GardenException(GardenException.INVOCATION_ERROR(type));
        }
    
        return thing;
    }

    /**
     * Export a file
     * 
     * @param file the file to export
     * @throws GardenException
     */
    public void exportFile(File file) throws GardenException {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(file));
            for (int row = 0; row < LENGTH; row++) {
                for (int column = 0; column < LENGTH; column++) {
                    if (garden[row][column] != null) {
                        String type = garden[row][column].getClass().getSimpleName();
                        pw.println(type + " " + row + " " + column);
                    } else {
                        pw.println("Null" + " " + row + " " + column);
                    }
                }
            }

            pw.close();
        } catch (FileNotFoundException e) {
            throw new GardenException(GardenException.FILE_NOT_FOUND(file.getName()));
        }

    }

    /**
     * Import a file
     * 
     * @param file the file to import
     * @return the garden saved in the file
     * @throws GardenException
     */
    public static Garden importFile(File file) throws GardenException {
        Garden newGarden = new Garden();
        int lineNumber = 0;

        try {
            BufferedReader bIn = new BufferedReader(new FileReader(file));
            String line = bIn.readLine();
            while (line != null) {
                lineNumber++;
                String[] data = line.split("\\s+");
                for (String string : data) {
                    string = string.trim();
                }

                addThingToBoard01(data, newGarden, lineNumber, line);
                line = bIn.readLine();
            }

            bIn.close();
        } catch (FileNotFoundException e) {
            throw new GardenException(GardenException.FILE_NOT_FOUND(file.getName()));
        } catch (IOException e) {
            throw new GardenException(GardenException.IMPORT_FILE_ERROR(file.getName()));
        }

        return newGarden;
    }

    private static void addThingToBoard01(String[] data, Garden garden, int lineNumber, String line) throws GardenException{
        try {
            int row = Integer.parseInt(data[1]);
            int column = Integer.parseInt(data[2]);

            if (data[0].equals("Null")) {
                garden.setThing(row, column, null);
            } else {
                garden.setThing(row, column, createThing01(data[0], garden, row, column, lineNumber, line));
            }
        } catch (NumberFormatException e) {
            throw new GardenException(GardenException.IMPORT_ERROR(lineNumber, e.getMessage(), line));
        }

    }

    private static Thing createThing01(String type, Garden garden, int row, int column, int lineNumber, String line) throws GardenException{
        Thing thing = null;
        type = "domain." + type;
    
        try {
            Class<?> cls = Class.forName(type);
            if (type.equals("domain.Water")) {
                Constructor<?> constructor = cls.getDeclaredConstructor();
                constructor.setAccessible(true);
                thing = (Thing) constructor.newInstance();
            } else {
                Constructor<?> constructor = cls.getDeclaredConstructor(Garden.class, int.class, int.class);
                constructor.setAccessible(true);
                thing = (Thing) constructor.newInstance(garden, row, column);
            }
        }  catch (ClassNotFoundException e) {
            throw new GardenException(GardenException.IMPORT_ERROR(lineNumber, e.getMessage(), line));
        } catch (NoSuchMethodException e) {
            throw new GardenException(GardenException.IMPORT_ERROR(lineNumber, e.getMessage(), line));
        } catch (InstantiationException e) {
            throw new GardenException(GardenException.IMPORT_ERROR(lineNumber, e.getMessage(), line));
        } catch (IllegalAccessException e) {
            throw new GardenException(GardenException.IMPORT_ERROR(lineNumber, e.getMessage(), line));
        } catch (IllegalArgumentException e) {
            throw new GardenException(GardenException.IMPORT_ERROR(lineNumber, e.getMessage(), line));
        } catch (InvocationTargetException e) {
            throw new GardenException(GardenException.IMPORT_ERROR(lineNumber, e.getMessage(), line));
        }
    
        return thing;
    }

    /**
     * Method to check the equality between two gardens
     * 
     * @return the boolean
     */
    public boolean equals(Garden g) {
        boolean res = true;
        if (g == null)
            res = false;
        if (g != null && g.getThings().length == this.getThings().length) {
            for (int i = 0; i < Garden.LENGTH; i++) {
                for (int j = 0; j < Garden.LENGTH; j++) {
                    if (g.getThings()[i][j] != null) {
                        if (!g.getThings()[i][j].equals(this.getThings()[i][j]))
                            res = false;
                    }

                }
            }
        }
        return res;

    }
}
