package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.io.File;

import domain.Token;
import domain.Allied;
import domain.NormalWall;
import domain.Player;
import domain.QuoriPOOB;
import domain.QuoriPOOBException;
import domain.Square;
import domain.Temporary;

/**
 * The test class Quopripoobv1.
 *
 * @author POOB
 * @version 2024-1
 */
public class Quopripoobvi {
    public Quopripoobvi() {
    }

    @Before
    public void setUp() {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.resetSingleton();
    }

    @Test
    public void shouldCreateBoardsOfDifferentSizes1() throws QuoriPOOBException {
        // First It has to define the players and the walls
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        // Create a board 4*4
        q.createBoard(4, null);
        // Check the size
        assertEquals(4, q.getBoard().getMatrixBoard().length);
        assertEquals(4, q.getBoard().getMatrixBoard()[0].length);
    }

    @Test
    public void shouldCreateBoardsOfDifferentSizes2() throws QuoriPOOBException {
        // First It has to define the players and the walls
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        // Create a board 50*50
        q.createBoard(50, null);
        // Check the size
        assertEquals(50, q.getBoard().getMatrixBoard().length);
        assertEquals(50, q.getBoard().getMatrixBoard()[0].length);
    }

    @Test
    public void shouldCreateBoardsOfDifferentSizes3() throws QuoriPOOBException {
        // First It has to define the players and the walls
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        // Create a board 4*4
        q.createBoard(2, null);
        // Check the size
        assertEquals(2, q.getBoard().getMatrixBoard().length);
        assertEquals(2, q.getBoard().getMatrixBoard()[0].length);
    }

    @Test
    public void shouldNotCreateABoardIfItsNotPossible1() throws QuoriPOOBException {
        // First It has to define the players and the walls
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        // Create a board 1*1, IMPOSSIBLE
        try {
            q.createBoard(1, null);
            fail("SHOULD NOT BE ABLE TO CREATE A BOARD 1*1");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WRONG_SIZE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateABoardIfItsNotPossible2() throws QuoriPOOBException {
        // First It has to define the players and the walls
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        // Create a board 1*1, IMPOSSIBLE
        try {
            q.createBoard(0, null);
            fail("SHOULD NOT BE ABLE TO CREATE A BOARD WITH SIZE < 1");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WRONG_SIZE, e.getMessage());
        }
    }

    @Test
    public void shouldAssignBarriersToPlayers1() throws QuoriPOOBException {
        // First it has to define the walls, and the Board
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        q.createBoard(4, null);
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
    public void shouldAssignBarriersToPlayers2() throws QuoriPOOBException {
        // First it has to define the walls, and the Board
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        q.createBoard(4, null);
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
    public void shouldNotAssignBarriersToPlayers1() throws QuoriPOOBException {
        // First it has to define the walls, and the Board
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        // Test
        try {
            q.addWalls(1, 2, 1, 1);
            fail("SHOULD NOT ADD THE WALLS IF THERE IS NOT A BOARD DEFINED");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.BOARD_UNDEFINED, e.getMessage());
        }
    }

    @Test
    public void shouldNotAssignBarriersToPlayers2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        try {
            q.addWalls(1, -1, 1, 4);
            fail("SHOULD NOT GIVE NEGATIVE VALUES FOR A TYPE OF WALL");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WRONG_NUMBER_WALLS, e.getMessage());
        }
    }

    @Test
    public void shouldNotAssignBarriersToPlayers3() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        try {
            q.addWalls(1, 7, 1, 4);
            fail("SHOULD NOT ADD A TOTAL OF WALLS DIFFERENT OF N + 1, WHEN N IS THE SIZE OF BOARD");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WRONG_NUMBER_WALLS, e.getMessage());
        }
    }

    @Test
    public void shouldMoveOrthogonallyAPawn1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(4, null);
        assertTrue(q.getBoard().getSquare(0, 1).getToken() != null);
        q.moveToken("LEFT");
        assertTrue(q.getBoard().getSquare(0, 0).getToken() != null);
        assertTrue(q.getBoard().getSquare(0, 1).getToken() == null);
    }

    @Test
    public void shouldMoveOrthogonallyAPawn2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(4, null);
        assertTrue(q.getBoard().getSquare(0, 1).getToken() != null);
        q.moveToken("LEFT");
        assertTrue(q.getBoard().getSquare(0, 0).getToken() != null);
        assertTrue(q.getBoard().getSquare(0, 1).getToken() == null);
        assertTrue(q.getBoard().getSquare(3, 1).getToken() != null);
        q.moveToken("LEFT");
        assertTrue(q.getBoard().getSquare(3, 0).getToken() != null);
        assertTrue(q.getBoard().getSquare(3, 1).getToken() == null);
    }

    @Test
    public void shouldMoveOrthogonallyAPawn3() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(4, null);
        
        // Token Daniel
        assertTrue(q.getBoard().getSquare(0, 1).getToken() != null);
        q.moveToken("LEFT");
        assertTrue(q.getBoard().getSquare(0, 0).getToken() != null);
        assertTrue(q.getBoard().getSquare(0, 1).getToken() == null);
        
        // Token Miguel
        assertTrue(q.getBoard().getSquare(3, 1).getToken() != null);
        q.moveToken("RIGHT");
        assertTrue(q.getBoard().getSquare(3, 2).getToken() != null);
        assertTrue(q.getBoard().getSquare(3, 1).getToken() == null);
        
        // Token Daniel
        assertTrue(q.getBoard().getSquare(0, 0).getToken() != null);
        q.moveToken("DOWN");
        assertTrue(q.getBoard().getSquare(1, 0).getToken() != null);
        assertTrue(q.getBoard().getSquare(0, 0).getToken() == null);
        
        // Token Miguel
        assertTrue(q.getBoard().getSquare(3, 2).getToken() != null);
        q.moveToken("UP");
        assertTrue(q.getBoard().getSquare(2, 2).getToken() != null);
        assertTrue(q.getBoard().getSquare(3, 1).getToken() == null);
    }

    @Test
    public void shouldNotMoveOrthogonallyAPawnIfItsNotPossible1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(4, null);
        q.moveToken("LEFT");
        try{
            q.moveToken("DOWN");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.TOKEN_OUT_OF_RANGE, e.getMessage());
        }
        // Check the position of the Token is the same
        assertTrue(q.getBoard().getSquare(3, 1).getToken() != null);
    }

    @Test
    public void shouldNotMoveOrthogonallyAPawnIfItsNotPossible2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(4, null);
        q.moveToken("LEFT");
        q.moveToken("LEFT");
        try{
            q.moveToken("LEFT");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.TOKEN_OUT_OF_RANGE, e.getMessage());
        }
        // Check the position of the Token is the same
        assertTrue(q.getBoard().getSquare(0, 0).getToken() != null);
    }

    // @Test --> En construccion de verificacion de los casos cuando deba saltar en diagonal
    // public void shouldMoveDiagonallyAPawn() throws QuoriPOOBException{
    //     QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
    //     q.setTwoPlayers();
    //     q.createPlayerHuman("Daniel", Color.BLUE);
    //     q.createPlayerHuman("Miguel", Color.BLACK);
    //     q.createBoard(5, null);
    //     assertTrue(q.getBoard().getSquare(0, 2).getToken() != null);
    //     q.moveToken("DOWNLEFT");// Solo si es diagonal teleporter
    //     assertTrue(q.getBoard().getSquare(1, 1).getToken() != null);
    //     assertTrue(q.getBoard().getSquare(0, 2).getToken() == null);
    // }


    @Test
    public void shouldPlaceANormalBarrier()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);

        q.createBoard(10, null);
        q.addWalls(1, 4, 2, 4);

        q.addWallToBoard("NormalWall", 9, 8, "DOWN");
        q.addWallToBoard("NormalWall", 7, 5, "UP");

        Square[][] matrix = q.getBoard().getMatrixBoard();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                Square square = matrix[row][col];
                if ((row == 9 && col == 8) || (row == 9 & col == 9)) {
                    assertNotNull(square.getWallDown());
                    assertTrue(square.getWallDown() instanceof NormalWall);
                } else if ((row == 7 && col == 5) || (row == 7 & col == 6)) {
                    assertNotNull(square.getWallUp());
                    assertTrue(square.getWallUp() instanceof NormalWall);
                } else if ((row == 6 && col == 5) || (row == 6 & col == 6)) {
                    assertNotNull(square.getWallDown());
                    assertTrue(square.getWallDown() instanceof NormalWall);
                }
            }
        }
    }

    @Test
    public void shouldPlaceANormalBarrier1()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(6, 0,0,0);
        q.addWallToBoard("NormalWall", 0, 0, "DOWN");
        assertEquals(q.getBoard().getMatrixBoard()[0][0].getWallDown(),q.getBoard().getMatrixBoard()[1][0].getWallUp());
        q.addWallToBoard("NormalWall", 2, 2, "LEFT");
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallDown(),q.getBoard().getMatrixBoard()[2][1].getWallUp());
        q.addWallToBoard("NormalWall", 0, 0, "UP");
        q.addWallToBoard("NormalWall", 0, 2, "UP");
        assertEquals(q.getBoard().getMatrixBoard()[0][0].getWallUp(),q.getBoard().getMatrixBoard()[0][1].getWallUp());
        assertEquals(q.getBoard().getMatrixBoard()[0][2].getWallUp(),q.getBoard().getMatrixBoard()[0][3].getWallUp());
    }

    @Test
    public void shouldPlaceANormalBarrier2()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(6, 0,0,0);
        q.addWallToBoard("NormalWall", 0, 0, "DOWN");
        assertEquals(q.getBoard().getMatrixBoard()[0][0].getWallDown(),q.getBoard().getMatrixBoard()[1][0].getWallUp());
        q.addWallToBoard("NormalWall", 2, 2, "LEFT");
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallLeft(),q.getBoard().getMatrixBoard()[2][1].getWallRight());
        q.addWallToBoard("NormalWall", 0, 0, "UP");
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallUp(),q.getBoard().getMatrixBoard()[2][1].getWallUp());
    }

    @Test
    public void shouldPlaceANormalBarrier3()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(6, 0,0,0);
        q.addWallToBoard("NormalWall", 2, 2, "UP");
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallUp(),q.getBoard().getMatrixBoard()[2][3].getWallUp());        
        assertEquals(q.getBoard().getMatrixBoard()[1][2].getWallDown(),q.getBoard().getMatrixBoard()[1][3].getWallDown());        

        q.addWallToBoard("NormalWall", 2, 2, "LEFT");
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallLeft(),q.getBoard().getMatrixBoard()[3][2].getWallLeft());        
        assertEquals(q.getBoard().getMatrixBoard()[2][1].getWallRight(),q.getBoard().getMatrixBoard()[3][1].getWallRight());        
    }

    @Test
    public void shouldNotPlaceANormalBarrierIfItsNotPossible1()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(6, 0,0,0);
        q.addWallToBoard("NormalWall", 2, 2, "UP");
        try{
            q.addWallToBoard("NormalWall", 2, 2, "UP");
            fail("SHOULD NOT LET ADD TWO WALLS AT THE SAME POSITION");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WALL_IN_SQUARE, e.getMessage());
        }
    }

    @Test
    public void shouldNotPlaceANormalBarrierIfItsNotPossible2()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(6, 0,0,0);
        q.addWallToBoard("NormalWall", 2, 2, "UP");
        try{
            q.addWallToBoard("NormalWall", 1, 2, "DOWN");
            fail("SHOULD NOT LET ADD TWO WALLS AT THE SAME POSITION");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WALL_IN_SQUARE, e.getMessage());
        }
    }

    @Test
    public void shouldNotPlaceANormalBarrierIfItsNotPossible3()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(6, 0,0,0);
        q.addWallToBoard("NormalWall", 2, 2, "UP");
        try{
            q.addWallToBoard("NormalWall", 1, 3, "DOWN");
            fail("SHOULD NOT LET ADD TWO WALLS AT THE SAME POSITION");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WALL_IN_SQUARE, e.getMessage());
        }
        assertTrue(q.getBoard().getMatrixBoard()[1][4].getWallDown()==null);
        try{
            q.addWallToBoard("NormalWall", 2, 1, "UP");
            fail("SHOULD NOT LET ADD TWO WALLS AT THE SAME POSITION");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WALL_IN_SQUARE, e.getMessage());
        }
        assertTrue(q.getBoard().getMatrixBoard()[2][1].getWallDown()==null);
    }


    @Test
    public void shouldMoveAPawnOverAPawn1()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(5, null);
        q.moveToken("DOWN");
        Token miguelToken = q.getBoard().getMatrixBoard()[4][2].getToken();
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        assertEquals(miguelToken, q.getBoard().getMatrixBoard()[1][2].getToken());
    }

    @Test
    public void shouldMoveAPawnOverAPawn2()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(5, null);
        q.moveToken("LEFT");
        Token miguelToken = q.getBoard().getMatrixBoard()[4][2].getToken();
        q.moveToken("UP");        
        q.moveToken("DOWN");
        q.moveToken("UP"); //
        q.moveToken("DOWN");
        q.moveToken("LEFT");
        assertEquals(miguelToken, q.getBoard().getMatrixBoard()[2][0].getToken());
    }

    @Test
    public void shouldNotMoveAPawnOverANonAlliedBarrier()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        Token danielToken = q.getBoard().getMatrixBoard()[0][2].getToken();
        q.addWalls(3, 3, 0, 0);
        q.moveToken("DOWN");
        q.addWallToBoard("NormalWall", 1, 2, "DOWN");
        try {
            q.moveToken("DOWN");
            fail("SHOULD NOT JUMP A NONE ALLIED BARRIER");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.FORWARD_WALL, e.getMessage());
        }
        assertEquals(danielToken, q.getBoard().getMatrixBoard()[1][2].getToken());
    }

    @Test
    public void shouldNotMoveAPawnOverANonAlliedBarrier2()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        Token danielToken = q.getBoard().getMatrixBoard()[0][2].getToken();
        q.addWalls(3, 3, 0, 0);
        q.moveToken("DOWN");
        q.addWallToBoard("NormalWall", 1, 2, "RIGHT");
        try {
            q.moveToken("RIGHT");
            fail("SHOULD NOT JUMP A NONE ALLIED BARRIER");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.FORWARD_WALL, e.getMessage());
        }
        assertEquals(danielToken, q.getBoard().getMatrixBoard()[1][2].getToken());
    }

    @Test
    public void shouldMoveAPawnOverAnAlliedBarrier()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        Token danielToken = q.getBoard().getMatrixBoard()[0][2].getToken();
        q.addWalls(3, 2, 0, 1);
        q.addWallToBoard("Allied", 1, 2, "UP");
        q.moveToken("UP");
        q.moveToken("DOWN");
        assertEquals(danielToken, q.getBoard().getMatrixBoard()[1][2].getToken());
    }

    @Test
    public void shouldMoveAPawnOverAnAlliedBarrier2()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        Token danielToken = q.getBoard().getMatrixBoard()[0][2].getToken();
        q.addWalls(3, 2, 0, 1);
        q.addWallToBoard("Allied", 0, 2, "RIGHT");
        q.moveToken("UP");
        q.moveToken("RIGHT");
        assertEquals(danielToken, q.getBoard().getMatrixBoard()[0][3].getToken());
    }

    @Test
    public void shouldKnowTheBarriersLeftForEachPlayer()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(3, 2, 0, 1);
        q.addWallToBoard("Allied", 0, 2, "RIGHT");
        q.addWallToBoard("Allied", 0, 1, "RIGHT");
        HashMap<String, Integer> danielWalls = new HashMap<>();
        HashMap<String, Integer> miguelWalls = new HashMap<>();
        HashMap<Color, HashMap<String, Integer>> playersNumberWalls = new HashMap<>();

        danielWalls.put("NormalWall", 3);
        danielWalls.put("Temporary", 2);
        danielWalls.put("LongWall", 0);
        danielWalls.put("Allied", 0);
        playersNumberWalls.put(Color.BLUE, danielWalls);
        miguelWalls.put("NormalWall", 3);
        miguelWalls.put("Temporary", 1);
        miguelWalls.put("LongWall", 0);
        miguelWalls.put("Allied", 0);
        playersNumberWalls.put(Color.ORANGE, miguelWalls);

        q.moveToken("DOWN");
        q.addWallToBoard("Temporary", 3, 3, "UP");
        assertEquals(playersNumberWalls,q.numerWalls());


    }

    @Test
    public void shouldMeetTemporalBarrierConditions1()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(3, 2, 0, 1);
        q.addWallToBoard("Temporary", 0, 2, "RIGHT");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("DOWN");

        assertTrue(q.getBoard().getMatrixBoard()[0][2].getWallRight()!=null);
        assertTrue(q.getBoard().getMatrixBoard()[1][2].getWallRight()!=null);
        assertTrue(q.getBoard().getMatrixBoard()[0][3].getWallLeft()!=null);
        assertTrue(q.getBoard().getMatrixBoard()[1][3].getWallLeft()!=null);

        q.moveToken("UP");
        
        assertTrue(q.getBoard().getMatrixBoard()[0][2].getWallRight()==null);
        assertTrue(q.getBoard().getMatrixBoard()[1][2].getWallRight()==null);
        assertTrue(q.getBoard().getMatrixBoard()[0][3].getWallLeft()==null);
        assertTrue(q.getBoard().getMatrixBoard()[1][3].getWallLeft()==null);
    }

    @Test
    public void shouldMeetTemporalBarrierConditions2()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(3, 2, 0, 1);
        q.addWallToBoard("Temporary", 0, 2, "RIGHT");
        q.addWallToBoard("Temporary", 3, 2, "RIGHT");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("UP");
        
        assertTrue(q.getBoard().getMatrixBoard()[0][2].getWallRight()!=null);
        assertTrue(q.getBoard().getMatrixBoard()[1][2].getWallRight()!=null);
        assertTrue(q.getBoard().getMatrixBoard()[0][3].getWallLeft()!=null);
        assertTrue(q.getBoard().getMatrixBoard()[1][3].getWallLeft()!=null);
        
        q.moveToken("DOWN");
        
        assertTrue(q.getBoard().getMatrixBoard()[0][2].getWallRight()==null);
        assertTrue(q.getBoard().getMatrixBoard()[1][2].getWallRight()==null);
        assertTrue(q.getBoard().getMatrixBoard()[0][3].getWallLeft()==null);
        assertTrue(q.getBoard().getMatrixBoard()[1][3].getWallLeft()==null);
    }

    // 12/05/2024
    @Test
    public void shouldWin()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        try{
            q.moveToken("UP");
        } catch(QuoriPOOBException e){
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        assertEquals(q.getWinner().getName(), "Miguel");
    }

    @Test
    public void shouldNotLetPlayAfterWin()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        try{
            q.moveToken("UP");
            fail("SHOULD NOT BE ABLE TO PLAY AFTER IT IS OVER");
        } catch(QuoriPOOBException e){
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        assertEquals(q.getWinner().getName(), "Miguel");
        try{
            q.moveToken("RIGHT");
            fail("SHOULD NOT BE ABLE TO PLAY AFTER IT IS OVER");
        } catch(QuoriPOOBException e){
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        try{
            q.addWallToBoard(null, 0, 0, null);;
            fail("SHOULD NOT BE ABLE TO PLAY AFTER IT IS OVER");
        } catch(QuoriPOOBException e){
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        
    }

    @Test
    public void shouldBeAbleToConsultAfterWin()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        try{
            q.moveToken("UP");
            fail("SHOULD NOT BE ABLE TO PLAY AFTER IT IS OVER");
        } catch(QuoriPOOBException e){
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        assertArrayEquals(new String[] {"Daniel","Miguel" }, q.getNames());
        assertEquals(Color.ORANGE, q.getColor("Miguel"));
        assertEquals(Color.BLUE, q.getColor("Daniel"));
    }

    @Test
    public void shouldBeAbleToSaveAfterWin()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        try{
            q.moveToken("UP");
            fail("SHOULD NOT BE ABLE TO PLAY AFTER IT IS OVER");
        } catch(QuoriPOOBException e){
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        File copy = new File("./test/guardado.dat");
        q.saveFile(copy);
        q.resetSingleton();
        q =null;
        assertEquals(q,null);
        q = QuoriPOOB.openFile(copy);
        assertArrayEquals(new String[] {"Daniel","Miguel" }, q.getNames());
        assertEquals(Color.ORANGE, q.getColor("Miguel"));
        assertEquals(Color.BLUE, q.getColor("Daniel"));
        assertEquals(q.getWinner().getName(), "Miguel");
    }

    @Test
    public void shouldResetQuoriPOOB() throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        
        q.resetSingleton();
        q =null;
        assertEquals(q,null);
        q = QuoriPOOB.getQuoriPOOB();
        assertEquals(q.getBoard(),null);
        assertEquals(q.getWinner(),null);
        for(String s:q.getNames()){
            assertEquals(s,null);
        }
        for(HashMap<String, Integer> walls:q.numerWalls().values()){
            assertEquals(walls,null);
        }
    }
    @Test
    public void shouldNotWinIfWallAhead()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.addWallToBoard("Temporary", 0, 2, "UP");
        q.moveToken("UP");
        assertArrayEquals(new String[] {"Daniel","Miguel" }, q.getNames());
        assertEquals(Color.ORANGE, q.getColor("Miguel"));
        assertEquals(Color.BLUE, q.getColor("Daniel"));
        assertEquals(q.getWinner(), null);
    }
    
}


