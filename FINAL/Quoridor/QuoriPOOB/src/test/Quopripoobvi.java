package test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;

import domain.Player;
import domain.QuoriPOOB;
import domain.QuoriPOOBException;

/**
 * The test class Quopripoobv1.
 *
 * @author  POOB
 * @version 2024-1
 */
public class Quopripoobvi
{
    /**
     * Default constructor for test class Qupripoobv1
     */
    public Quopripoobvi()
    {
    }

    @Test
    public void shouldCreateBoardsOfDifferentSizes1()throws QuoriPOOBException{
        // First It has to define the players and the walls
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        // Create a board 4*4
        q.createBoard(4,null);
        // Check the size
        assertEquals(4, q.getBoard().getMatrixBoard().length);
        assertEquals(4, q.getBoard().getMatrixBoard()[0].length);
    }

    @Test
    public void shouldCreateBoardsOfDifferentSizes2()throws QuoriPOOBException{
        // First It has to define the players and the walls
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        // Create a board 50*50
        q.createBoard(50,null);
        // Check the size
        assertEquals(50, q.getBoard().getMatrixBoard().length);
        assertEquals(50, q.getBoard().getMatrixBoard()[0].length);
    }

    @Test
    public void shouldCreateBoardsOfDifferentSizes3()throws QuoriPOOBException{
        // First It has to define the players and the walls
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        // Create a board 4*4
        q.createBoard(2,null);
        // Check the size
        assertEquals(2, q.getBoard().getMatrixBoard().length);
        assertEquals(2, q.getBoard().getMatrixBoard()[0].length);
    }

    @Test
    public void shouldNotCreateBoardsOfDifferentSizes1()throws QuoriPOOBException{
        // First It has to define the players and the walls
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        // Create a board 1*1, IMPOSSIBLE
        try{
            q.createBoard(1,null);
            fail("SHOULD NOT BE ABLE TO CREATE A BOARD 1*1");
        }catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WRONG_SIZE,e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateBoardsOfDifferentSizes2()throws QuoriPOOBException{
        // First It has to define the players and the walls
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        // Create a board 1*1, IMPOSSIBLE
        try{
            q.createBoard(0,null);
            fail("SHOULD NOT BE ABLE TO CREATE A BOARD 1*1");
        }catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WRONG_SIZE,e.getMessage());
        }
    }

    @Test
    public void shouldAssignBarriersToPlayers1()throws QuoriPOOBException{
        // First it has to define the walls, and the Board
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        q.createBoard(4,null);
        // Add the barriers to players, the total of barriers is always n + 1: 4 + 1 = 5
        q.addWalls(0, 0, 0, 5);
        // Test
        Map<String, Integer> barriers = new HashMap<>();
        barriers.put("LongWall", 0);
        barriers.put("Temporary", 0);
        barriers.put("Allied", 5);
        barriers.put("NormalWall", 0);
        assertEquals(q.getCurrentPlayer().numberWalls(), barriers);
    }  
    
    @Test
    public void shouldAssignBarriersToPlayers2()throws QuoriPOOBException{
        // First it has to define the walls, and the Board
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        q.createBoard(4,null);
        // Add the barriers to players, the total of barriers is always n + 1: 4 + 1 = 5
        q.addWalls(2, 1, 1, 1);
        // Test
        Map<String, Integer> barriers = new HashMap<>();
        barriers.put("LongWall", 1);
        barriers.put("Temporary", 1);
        barriers.put("Allied", 1);
        barriers.put("NormalWall", 2);
        assertEquals(q.getCurrentPlayer().numberWalls(), barriers);
    }

    @Test
    public void shouldNotAssignBarriersToPlayers1()throws QuoriPOOBException{
        // First it has to define the walls, and the Board
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] {{0,0},{0,1},{1,0},{1,1}});
        // Test
        try{
            q.addWalls(1, 2, 1, 1);
            fail("SHOULD NOT ADD THE WALLS IF THERE IS NOT A BOARD DEFINED");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.BOARD_UNDEFINED, e.getMessage());
        }
    }

    @Test
    public void shouldNotAssignBarriersToPlayers2() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] {{0,0},{0,1},{1,0},{1,1}});
        q.createBoard(4,specialQuares);
        try{
            q.addWalls(1, -1, 1, 4);
            fail("SHOULD NOT GIVE NEGATIVE VALUES FOR A TYPE OF WALL");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WRONG_NUMBER_WALLS, e.getMessage());
        }
    }

    @Test
    public void shouldNotAssignBarriersToPlayers3() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] {{0,0},{0,1},{1,0},{1,1}});
        q.createBoard(4,specialQuares);
        try{
            q.addWalls(1, 7, 1, 4);
            fail("SHOULD NOT ADD A TOTAL OF WALLS DIFFERENT OF N + 1, WHEN N IS THE SIZE OF BOARD");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WRONG_NUMBER_WALLS, e.getMessage());
        }
    }

    @Test
    public void shouldMoveOrthogonallyAPawn(){
        
    }     

    /* 
    
    
    @Test
    public void shouldMoveOrthogonallyAPawn(){
        fail();
    }     
    

    @Test
    public void shouldMoveDiagonallyAPawn(){
        fail();
    }  
    
    @Test
    public void shouldPlaceANormalBarrier(){
        fail();
    }     
    
    
    @Test
    public void shouldMoveAPawnOverAPawn(){
        fail();
    } 
    
    
    @Test
    public void shouldNotMoveAPawnOverANonAlliedBarrier(){
        fail();
    }  
    
    @Test
    public void shouldMoveAPawnOverAnAlliedBarrier(){
        fail();
    } 
    
    
    @Test
    public void shouldKnowWhenSomeoneWonTheGame(){
        fail();
    } 
    
    @Test
    public void shouldKnowTheBarriersLeftForEachPlayer(){
        fail();
    } 
    
    @Test
    public void shouldNotBlockThePassageOfAPlayer(){
        fail();
    } 
    
    @Test
    public void shouldMeetNormalBarrierConditions(){
        fail();
    } 
    
    @Test
    public void shouldMeetTemporalBarrierConditions(){
        fail();
    } 
    
    @Test
    public void shouldMeetLongBarrierConditions(){
        fail();
    } 
    
    @Test
    public void shouldMeetAlliedBarrierConditions(){
        fail();
    } 
    
    
    @Test
    public void shouldNotCreateABoardIfItsNotPossible(){
        fail();
    }     


    
    @Test
    public void shouldNotMoveOrthogonallyAPawnIfItsNotPossible(){
        fail();
    }     
    

    @Test
    public void shouldNotMoveDiagonallyAPawnIfItsNotPossible(){
        fail();
    }  
    
    @Test
    public void shouldNotPlaceANormalBarrierIfItsNotPossible(){
        fail();
    }     
    
    
    @Test
    public void shouldNotMoveAPawnOverAPawnIfItsNotPossible(){
        fail();
    } 
    
    --> Borra este --> */
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    
    // @Before
    // public void setUp()
    // {
    // }

    // /**
    //  * Tears down the test fixture.
    //  *
    //  * Called after every test case method.
    //  */
    // @After
    // public void tearDown()
    // {
    // }
}