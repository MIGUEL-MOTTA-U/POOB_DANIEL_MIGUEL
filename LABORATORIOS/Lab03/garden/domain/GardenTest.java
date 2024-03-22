package domain;

import org.junit.*;

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
}
