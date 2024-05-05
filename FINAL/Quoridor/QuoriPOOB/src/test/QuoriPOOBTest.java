package test;
import domain.*;
import static org.junit.Assert.*;
import java.awt.Color;
import java.awt.desktop.QuitEvent;
import java.util.ArrayList;
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
    public void shouldCreateHumanAndMachine1()throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerMachine(Color.ORANGE,"domain.Beginner");
            assertArrayEquals(new String[] {"Player 1", "Machine"}, q.getNames());
            assertArrayEquals(new String[] {"Player 1", "Machine"}, q.getNames());
        }
    @Test
    public void shouldCreateHumanAndMachine2()throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerMachine(Color.ORANGE,"domain.Intermediate");
            assertArrayEquals(new String[] {"Player 1", "Machine"}, q.getNames());
        }
    @Test
    public void shouldCreateHumanAndMachine3()throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerMachine(Color.ORANGE,"domain.Advanced");
            assertArrayEquals(new String[] {"Player 1", "Machine"}, q.getNames());
        }

    @Test
    public void shouldNotCreateHumanAndMachine1(){
        QuoriPOOB q = new QuoriPOOB();
            try{
                q.createPlayerMachine(Color.BLUE,"domain.Advanced");
                q.createPlayerMachine(Color.ORANGE,"domain.Advanced");
                fail("SHOULD NOT LET PLAY TWO MACHINES");
            } catch (QuoriPOOBException e){
                assertEquals(QuoriPOOBException.TWO_MACHINES, e.getMessage());
            }
        }

    @Test
    public void shouldNotCreateHumanAndMachine2(){
        QuoriPOOB q = new QuoriPOOB();
            try{
                q.createPlayerMachine(Color.BLUE,"domain.Intermediate");
                q.createPlayerMachine(Color.ORANGE,"domain.Advanced");
                fail("SHOULD NOT LET PLAY TWO MACHINES");
            } catch (QuoriPOOBException e){
                assertEquals(QuoriPOOBException.TWO_MACHINES, e.getMessage());
            }
        }
    
    @Test
    public void shouldNotCreateHumanAndMachine3(){
        QuoriPOOB q = new QuoriPOOB();
            try{
                q.createPlayerMachine(Color.BLUE,"domain.Beginner");
                q.createPlayerMachine(Color.ORANGE,"domain.Advanced");
                fail("SHOULD NOT LET PLAY TWO MACHINES");
            } catch (QuoriPOOBException e){
                assertEquals(QuoriPOOBException.TWO_MACHINES, e.getMessage());
            }
        }
    
    @Test
    public void shouldNotCreateHumanAndMachine4(){
        QuoriPOOB q = new QuoriPOOB();
            try{
                q.createPlayerHuman("Player 1", Color.BLUE);
                q.createPlayerHuman("Player 2", Color.ORANGE);
                q.createPlayerMachine(Color.ORANGE,"domain.Advanced");
                fail("SHOULD NOT LET PLAY TWO MACHINES");
            } catch (QuoriPOOBException e){
                assertEquals(QuoriPOOBException.WRONG_NUMBER_PLAYERS, e.getMessage());
            }
        }
    
    @Test
    public void shouldNotCreateHumanAndMachine5(){
        QuoriPOOB q = new QuoriPOOB();
            try{
                q.createPlayerHuman("Player 1", Color.BLUE);
                q.createPlayerMachine(Color.BLUE,"domain.Advanced");
                fail("SHOULD NOT LET PLAY TWO MACHINES");
            } catch (QuoriPOOBException e){
                assertEquals(QuoriPOOBException.SAME_PLAYER_COLOR, e.getMessage());
            }
        }
    
    @Test
    public void shouldCreateBoard1() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Teleporter", new int[][] {{0,0},{0,1},{1,0},{1,1}});
        q.createBoard(4,specialQuares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }
    @Test
    public void shouldCreateBoard2() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] {{0,0},{0,1},{1,0},{1,1}});
        q.createBoard(4,specialQuares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldCreateBoard3() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.DoubleTurn", new int[][] {{0,0},{0,1},{1,0},{1,1}});
        q.createBoard(4,specialQuares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldCreateBoard4() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.NormalSquare", new int[][] {{0,0},{0,1},{1,0},{1,1}});
        q.createBoard(4,specialQuares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

@Test
    public void shouldCreateBoard5() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        q.createBoard(4,null);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldVerifyCreatedBoard() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.NormalSquare",new int[][] {{0,0},{0,1},{1,0},{1,1}});
        specialQuares.put("domain.DoubleTurn", new int[][] {{0,2},{0,3},{1,2},{1,3}});
        specialQuares.put("domain.Teleporter",new int[][] {{2,0},{2,1},{3,0},{3,1}});
        specialQuares.put("domain.Return", new int[][] {{2,2},{2,3},{3,2},{3,3}});
        q.createBoard(4,specialQuares);

        // Verificar que se crearon efectivamente esas casillas en sus respectivas posiciones.
    }




    
    
    
}