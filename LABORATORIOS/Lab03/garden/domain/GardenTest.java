package domain;

import static org.junit.Assert.assertTrue;

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
    public void ShouldCreateFlower(){
        Thing rose = garden.getThing(10, 10);
        Thing violet = garden.getThing(15, 15);
        Assert.assertTrue(rose instanceof Flower);
        Assert.assertTrue(violet instanceof Flower);
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
}
