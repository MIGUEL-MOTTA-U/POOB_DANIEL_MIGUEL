package test;
import domain.*;
import static org.junit.Assert.*;
import java.awt.Color;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuoriPOOBTest {
    public QuoriPOOBTest() {
    }
    @Before
    public void setUp() {
    }
    @After
    public void tearDown() {
    }
    @Test
    public void shouldCreatePlayerHuman1() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 2", Color.ORANGE);
        assertArrayEquals(new String[] {"Player 1", "Player 2"}, q.getNames());
    }
    @Test
    public void shouldCreateHuman2() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 1", Color.ORANGE);
        assertArrayEquals(new String[] {"Player 1", "Player 1"}, q.getNames());
    }
    @Test
    public void shouldNotCreateHuman1(){
        QuoriPOOB q = new QuoriPOOB();
        try {
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerHuman("Player 2", Color.BLUE);
            fail("SHOULD NOT CREATE TWO PLAYERS WITH THE SAME COLOR");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.SAME_PLAYER_COLOR, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateHuman2(){
        QuoriPOOB q = new QuoriPOOB();
        try {
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerHuman("Player 1", Color.ORANGE);
            q.createPlayerHuman("Player 3", Color.CYAN);
            fail("SHOULD NOT CREATE MORE THAN TWO PLAYERS");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WRONG_NUMBER_PLAYERS, e.getMessage());
        }
    }

    @Test
    public void shouldCreateHumanAndMachine1(){
        QuoriPOOB q = new QuoriPOOB();
        try {
            
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerMachine(Color.ORANGE,"asdfasdfasdf");
            
            //fail("SHOULD NOT CREATE MORE THAN TWO PLAYERS");
        } catch (QuoriPOOBException e){
            System.out.println(e.getMessage());
            //assertEquals(QuoriPOOBException.WRONG_NUMBER_PLAYERS, e.getMessage());
        }
    }




    
    
}