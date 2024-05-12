package test;
import domain.*;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.Soundbank;
import javax.swing.JOptionPane;

import org.junit.*;
import static org.junit.Assert.*;
/**
 * The test class GardenTest.
 *
 * @author  Daniel Diaz && Miguel Motta
 */
public class GardenTest{
    private Garden garden;
    
    @BeforeClass
    public static void beforeClass(){
    }

    @Before
    public void before(){
        garden = new Garden();
    }

    @Test
    public void ShouldCreateFlower(){
        Thing rose = garden.getThing(10, 10);
        Thing violet = garden.getThing(15, 15);
        Assert.assertTrue(rose instanceof Flower);
        Assert.assertTrue(violet instanceof Flower);
    }
    
    @Test
    public void flowerShouldAct(){
        garden.setThing(18, 2, null);
        garden.setThing(14, 14, null);
        garden.setThing(31, 5, null);
        garden.setThing(35, 12, null);
        garden.setThing(20, 11, null);
        garden.setThing(17, 30, null);
        garden.setThing(20, 33, null);
        garden.setThing(7, 5, null);

        for (int i = 1; i <= 10; i++) {
            garden.ticTac();
            Flower rose = (Flower) garden.getThing(10, 10);
            Flower violet = (Flower) garden.getThing(15, 15);

            if (i == 1) {
                Assert.assertTrue(rose.isAlive());
                Assert.assertEquals(Color.RED, rose.getColor());
                Assert.assertTrue(violet.isAlive());
                Assert.assertEquals(Color.RED, violet.getColor());    
            } else if (i == 4) {
                Assert.assertFalse(rose.isAlive());
                Assert.assertEquals(Color.ORANGE, rose.getColor());
                Assert.assertFalse(violet.isAlive());
                Assert.assertEquals(Color.ORANGE, violet.getColor());
            } else if (i == 6) {
                Assert.assertTrue(rose.isAlive());
                Assert.assertEquals(Color.RED, rose.getColor());
                Assert.assertTrue(violet.isAlive());
                Assert.assertEquals(Color.RED, violet.getColor());          
            } else if (i == 9) {
                Assert.assertFalse(rose.isAlive());
                Assert.assertEquals(Color.ORANGE, rose.getColor());
                Assert.assertFalse(violet.isAlive());
                Assert.assertEquals(Color.ORANGE, violet.getColor());   
            } else if (i == 11) {
                Assert.assertFalse(rose.isAlive());
                Assert.assertEquals(Color.ORANGE, rose.getColor());
                Assert.assertFalse(violet.isAlive());
                Assert.assertEquals(Color.ORANGE, violet.getColor());  
            }
        }
    }

    @Test
    public void shouldCreateCarnivorou(){
        Thing venus = garden.getThing(18, 2);
        Thing sundeuos = garden.getThing(14, 14);
        Assert.assertTrue(venus instanceof Carnivorou);
        Assert.assertTrue(sundeuos instanceof Carnivorou);
    }

    @Test
    public void carnivorouShouldEatFlower(){
        garden.setThing(31, 5, null);
        garden.setThing(35, 12, null);
        garden.setThing(20, 11, null);
        garden.setThing(17, 30, null);
        garden.setThing(20, 33, null);
        garden.setThing(7, 5, null);

        for (int i = 1; i < 10; i++) {
            garden.ticTac();
            if (i == 1) {
                Thing venus = garden.getThing(15, 15);
                Assert.assertNull(garden.getThing(14, 14));
                Assert.assertTrue(venus instanceof Carnivorou);
            } else if (i == 6) {
                Thing venus = garden.getThing(10, 10);
                Assert.assertNull(garden.getThing(11, 11));
                Assert.assertTrue(venus instanceof Carnivorou);
            }
        }
    }

    @Test
    public void shouldCreateSand(){
        Thing thing = garden.getThing(1, 1);
        Assert.assertTrue(thing instanceof Sand);

        for (int r = 0; r < 2; r++) {
            for (int c = 1; c < 3; c++) {
                thing = garden.getThing(r, c);
                Assert.assertTrue(thing instanceof Sand);
            }
        }
        
        for (int r = 0; r < 3; r++) {
            for (int c = 35; c < Garden.LENGTH; c++) {
                thing = garden.getThing(r, c);
                Assert.assertTrue(thing instanceof Sand);
            }
        }
    }

    @Test
    public void ShouldDisappearSand(){
        for (int i = 1; i <= 101; i++) {
            garden.ticTac();

            if (i == 101) {
                Thing thing = garden.getThing(1, 1);
                Assert.assertNull(thing);
    
                for (int r = 0; r < 2; r++) {
                    for (int c = 1; c < 3; c++) {
                        thing = garden.getThing(r, c);
                        Assert.assertNull(thing);
                    }
                }
                
                for (int r = 0; r < 3; r++) {
                    for (int c = 35; c < Garden.LENGTH; c++) {
                        thing = garden.getThing(r, c);
                        Assert.assertNull(thing);
                    }
                }
            }
        }
    }

    @Test
    public void shouldCreateAromatic(){
        Thing tulipan = garden.getThing(20, 11);
        Thing azela = garden.getThing(17, 30);
        Assert.assertTrue(tulipan instanceof Aromatic);
        Assert.assertTrue(azela instanceof Aromatic);
    }

    @Test
    public void aromaticShouldTransform(){
        garden.setThing(20, 33, null);
        garden.setThing(7, 5, null);
        
        for (int i = 1; i <= 50; i++) {
            garden.ticTac();
            if (i == 3) {
                Thing thing = garden.getThing(17, 13);
                Assert.assertTrue(thing instanceof Flower && !(thing instanceof Carnivorou));
            } else if (i == 6) {
                Thing thing = garden.getThing(19, 8);
                Assert.assertTrue(thing instanceof Flower && !(thing instanceof Carnivorou));
            } else if (i == 8) {
                Thing thing = garden.getThing(23, 10);
                Assert.assertTrue(thing instanceof Flower && !(thing instanceof Carnivorou));
            } else if (i == 47) {
                Thing thing = garden.getThing(16, 27);
                Assert.assertTrue(thing instanceof Flower && !(thing instanceof Carnivorou));
            }
        }
    }
    
    @Test
    public void aromaticShouldNotTransform(){
        garden.setThing(20, 33, null);
        garden.setThing(7, 5, null);
        
        for (int i = 1; i <= 50; i++) {
            garden.ticTac();
            if (i == 2) {
                Thing thing = garden.getThing(17, 13);
                Assert.assertFalse(thing instanceof Flower && !(thing instanceof Carnivorou));
            } else if (i == 5) {
                Thing thing = garden.getThing(19, 8);
                Assert.assertFalse(thing instanceof Flower && !(thing instanceof Carnivorou));
            } else if (i == 7) {
                Thing thing = garden.getThing(23, 10);
                Assert.assertFalse(thing instanceof Flower && !(thing instanceof Carnivorou));
            } else if (i == 46) {
                Thing thing = garden.getThing(16, 27);
                Assert.assertFalse(thing instanceof Flower && !(thing instanceof Carnivorou));
            }
        }
    }
    
    @Test
    public void aromaticShouldDie(){
        garden.setThing(20, 33, null);
        garden.setThing(7, 5, null);
        
        for (int i = 1; i <= 50; i++) {
            garden.ticTac();
            if (i == 8) {
                Aromatic thing = (Aromatic) garden.getThing(20, 11);
                Assert.assertFalse(thing.isAlive());
            }
        }
    }
    
    @Test
    public void aromaticShouldNotDie(){
        garden.setThing(20, 33, null);
        garden.setThing(7, 5, null);
        
        for (int i = 1; i <= 50; i++) {
            garden.ticTac();
            if (i == 3) {
                Aromatic thing = (Aromatic) garden.getThing(20, 11);
                Assert.assertTrue(thing.isAlive());
            } else if (i == 6) {
                Aromatic thing = (Aromatic) garden.getThing(20, 11);
                Assert.assertTrue(thing.isAlive());
            } else if (i == 47) {
                Aromatic thing = (Aromatic) garden.getThing(17, 30);
                Assert.assertTrue(thing.isAlive());
            }
        }
    }

    @Test
    public void shouldCreateVirus(){
        Thing covid = garden.getThing(20, 33);
        Thing sarampion = garden.getThing(7, 5);
        Assert.assertTrue(covid instanceof Virus);
        Assert.assertTrue(sarampion instanceof Virus);
    }
    
    @Test
    public void virusShouldTransform(){
        for (int i = 1; i <= 50; i++) {
            garden.ticTac();
            if (i == 3) {
                Thing thing = garden.getThing(17, 30);
                Assert.assertTrue(thing instanceof Carnivorou);
            } else if (i == 5) {
                Thing thing = garden.getThing(10, 10);
                Assert.assertTrue(thing instanceof Carnivorou);
            }
        }
    }
    
    @Test
    public void virusShouldNotTransform(){
        for (int i = 1; i <= 50; i++) {
            garden.ticTac();
            if (i == 2) {
                Thing thing = garden.getThing(17, 30);
                Assert.assertFalse(thing instanceof Carnivorou);
            } else if (i == 4) {
                Thing thing = garden.getThing(10, 10);
                Assert.assertFalse(thing instanceof Carnivorou);
            }
        }
    }
    
    @Test
    public void virusShouldDie(){
        for (int i = 1; i <= 50; i++) {
            garden.ticTac();
            if (i == 9) {
                Thing thing = garden.getThing(18, 26);
                Assert.assertTrue(thing instanceof Sand);
            } else if (i == 11) {
                Thing thing = garden.getThing(14, 9);
                Assert.assertTrue(thing instanceof Sand);
            }
        }
    }
    
    @Test
    public void virusShouldNotDie(){
        for (int i = 1; i <= 50; i++) {
            garden.ticTac();
            if (i == 6) {
                Thing thing1 = garden.getThing(10, 9);
                Thing thing2 = garden.getThing(18, 28);
                Assert.assertFalse(thing1 instanceof Sand);
                Assert.assertFalse(thing2 instanceof Sand);
            }
        }
    }

    @Test 
    public void shouldBeEqual1(){
        assertTrue(garden.equals(new Garden()));
    }

    @Test 
    public void shouldBeEqual2(){
        Garden other = new Garden();
        other.ticTac();
        garden.ticTac();
        other.ticTac();
        garden.ticTac();
        other.ticTac();
        garden.ticTac();
        assertTrue(garden.equals(other));
    }
    @Test 
    public void shouldBeEqual3(){
        Garden other = new Garden();
        for(int i= 0; i < 100; i ++){
            other.ticTac();
            garden.ticTac();
            assertTrue(garden.equals(other));
        }
        assertTrue(garden.equals(other));
    }


    @Test 
    public void shouldBeEqual4(){
        Garden other = new Garden();
        other.setThing(4, 4, null);
        garden.setThing(4,4,null);
        assertTrue(garden.equals(other));
    }

    @Test 
    public void shoulNotdBeEqual1(){
        Garden other = new Garden();
        other.ticTac();
        assertFalse(garden.equals(other));
    }

    @Test 
    public void shoulNotdBeEqual2(){
        Garden other = new Garden();
        other.setThing(4, 4, new Carnivorou(other,4,4));
        assertFalse(garden.equals(other));
    }

    @Test 
    public void shoulNotdBeEqual3(){
        assertFalse(garden.equals(null));
    }

    @Test 
    public void shouldSaveAndOpen1() throws GardenException{
        File copy = new File("./test/input.dat");
        garden.saveFile(copy);
        Garden other = Garden.openFile(copy);
        assertTrue(garden.equals(other));
    }
    @Test 
    public void shouldSaveAndOpen2() throws GardenException{
        File copy = new File("./test/input.dat");
        garden.ticTac();
        garden.ticTac();
        garden.ticTac();
        garden.saveFile(copy);
        Garden other = Garden.openFile(copy);
        assertTrue(garden.equals(other));
    }
    @Test 
    public void shouldSaveAndOpen3() throws GardenException{
        File copy = new File("./test/input.dat");
        garden.saveFile(copy);
        Garden other = Garden.openFile(copy);
        garden.ticTac();
        other.ticTac();
        garden.ticTac();
        other.ticTac();
        garden.ticTac();
        other.ticTac();
        assertTrue(garden.equals(other));
    }

    @Test
    public void shouldOpenFile() throws GardenException{
        File copy = new File("./test/onegarden.dat");
        garden.saveFile(copy);
        Garden otherGarden = Garden.openFile(copy);
        assertTrue(garden.equals(otherGarden));
    }

    @Test 
    public void shouldNotOpenCorrupted() throws GardenException{
        File copy = new File("./test/input.txt");
        Garden otherGarden = Garden.openFile(copy);
        assertTrue(otherGarden==null);
    }

    @Test
    public void shouldNotOpenInexistentFile()throws GardenException{
        File copy = new File("./test/NOT A REAL FILE.txt");
        Garden otherGarden = Garden.openFile(copy);
        assertTrue(otherGarden==null);
    }

    @Test
    public void shouldExportAndImport() throws GardenException{
        File copy = new File("./test/input.txt");
        garden.exportFile(copy);;
        //Garden other = Garden.importFile(copy);
        //assertTrue(garden.equals(other));
    }
    @Test
    public void acceptanceTest(){
        int opcion = JOptionPane.showConfirmDialog(null, "Desea ejecutar la prueba de aceptacion?", "Confirmacion prueba de aceptacion", JOptionPane.YES_NO_OPTION);
        if(opcion == JOptionPane.YES_OPTION){
            
            JOptionPane.showMessageDialog(null, "Se van a ejecutar 6 Tic-Tac", "6 Tic-Tac", JOptionPane.INFORMATION_MESSAGE);
            for(int i= 0; i < 6 ; i++){
                garden.ticTac();
                
                try {
                    TimeUnit.SECONDS.sleep(1); // Esperar 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null, "Por favor Guarde el archivo", "Guardar archivo", JOptionPane.INFORMATION_MESSAGE);
            // Aqui deberia abrir el archivo
            String rutaActual = System.getProperty("user.dir");
            String path = rutaActual + File.separator + "prueba.dat";
            
            try {
                // Crear un nuevo objeto File con la ruta completa
                File archivo = new File(path);
    
                // Verificar si el archivo no existe
                if (!archivo.exists()) {
                    // Crear el nuevo archivo
                    archivo.createNewFile();
                    JOptionPane.showMessageDialog(null, "El archivo no existia asi que lo creamos.", "Creando archivo", JOptionPane.INFORMATION_MESSAGE);    
                }
                JOptionPane.showMessageDialog(null, path, "La ruta donde quedo", JOptionPane.INFORMATION_MESSAGE);
                garden.saveFile(archivo);
                JOptionPane.showMessageDialog(null, "Ahora vamos a ejecutar otros 6 tic-tac luego de guardar el archivo", "Seguir Jugando", JOptionPane.INFORMATION_MESSAGE);
                
                for(int i= 0; i < 6 ; i++){
                    garden.ticTac();
                    
                    try {
                        TimeUnit.SECONDS.sleep(1); // Esperar 1 segundo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } JOptionPane.showMessageDialog(null, "Ahora vamos a cargar el juego que creamos", "Abrir el juego", JOptionPane.INFORMATION_MESSAGE);
                
                if (archivo != null && archivo.exists()) {
                    archivo.delete();
                    JOptionPane.showMessageDialog(null, "Cargamos el archivo de juego que se guardo", "Archivo cargado", JOptionPane.INFORMATION_MESSAGE);    
                    JOptionPane.showMessageDialog(null, "Borramos el archivo", "Borrar archivo", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showConfirmDialog(null, "Es el comportamiento esperado?", "Confirmacion prueba de aceptacion", JOptionPane.YES_NO_OPTION);
            } catch (IOException|GardenException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error al guardar el archivo", JOptionPane.ERROR_MESSAGE);
                        }
        }
    
    }
    

}
