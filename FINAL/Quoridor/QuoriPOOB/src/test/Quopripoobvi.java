package test;

import domain.*;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

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
    public void shouldCreateBoardsOfDifferentSizes() throws QuoriPOOBException {
        // First It has to define the players and the walls
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setNormalMode();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        // Create a board 4*4
        q.createBoard(4, null);
        // Check the size
        assertEquals(4, q.getBoard().getMatrixBoard().length);
        assertEquals(4, q.getBoard().getMatrixBoard()[0].length);
        // Reset QuoriPOOB
        q.resetSingleton();
        q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setNormalMode();
        q.createPlayerHuman("Player 2", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Beginner");
        // Create a board 9*9
        q.createBoard(9, null);
        // Check the size
        assertEquals(9, q.getBoard().getMatrixBoard().length);
        assertEquals(9, q.getBoard().getMatrixBoard()[0].length);
        // Reset QuoriPOOB
        q.resetSingleton();
        q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setNormalMode();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        // Create a board 4*4
        q.createBoard(2, null);
        // Check the size
        assertEquals(2, q.getBoard().getMatrixBoard().length);
        assertEquals(2, q.getBoard().getMatrixBoard()[0].length);
    }

    @Test
    public void shouldNotCreateABoardIfItsNotPossible()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setNormalMode();
        q.createPlayerHuman("Player 2", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Intermediate");
        // Should not let create a board 1*1
        try{
            q.createBoard(1, null);
            fail("SHOULD NOT LET CREATE A BOARD 1*1");
        } catch(QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WRONG_SIZE, e.getMessage());
        }
        // Should not let create a board of negative size
        try{
            q.createBoard(-1, null);
            fail("SHOULD NOT LET CREATE A BOARD 1*1");
        } catch(QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WRONG_SIZE, e.getMessage());
        }
        // Create a board 0*0, IMPOSSIBLE
        try {
            q.createBoard(0, null);
            fail("SHOULD NOT BE ABLE TO CREATE A BOARD WITH SIZE < 1");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WRONG_SIZE, e.getMessage());
        }
    }

    @Test
    public void shouldAssignBarriersToPlayers() throws QuoriPOOBException {
        // First it has to define the walls, and the Board
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setNormalMode();
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

        // reset QuoriPOOB
        q.resetSingleton();
        q = QuoriPOOB.getQuoriPOOB();
        
        // Add Barriers (Variety)
        q.setOnePlayer();
        q.setNormalMode();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        q.createBoard(4, null);
        // Add the barriers to players, the total of barriers is always n + 1: 4 + 1 = 5
        q.addWalls(2, 1, 1, 1);
        // Test
        barriers = new HashMap<>();
        barriers.put("LongWall", 1);
        barriers.put("Temporary", 1);
        barriers.put("Allied", 1);
        barriers.put("NormalWall", 2);
        assertEquals(q.getCurrentPlayer().numberWalls(), barriers);

        // Reset QuoriPOOB
        q.resetSingleton();
        q = QuoriPOOB.getQuoriPOOB();

        // Should not 
        q.setOnePlayer();
        q.setNormalMode();
        q.createPlayerHuman("Player 13", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Beginner");
        // Test
        try {
            q.addWalls(1, 2, 1, 1);
            fail("SHOULD NOT ADD THE WALLS IF THERE IS NOT A BOARD DEFINED");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.BOARD_UNDEFINED, e.getMessage());
        }
        q.createBoard(4, null);
        // Should not give negative number of walls
        try {
            q.addWalls(1, -1, 1, 4);
            fail("SHOULD NOT GIVE NEGATIVE VALUES FOR A TYPE OF WALL");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WRONG_NUMBER_WALLS, e.getMessage());
        }
        // Should not let add a numeber of walls different to n + 1 (where n = size = 4)
        try {
            q.addWalls(1, 7, 1, 4);
            fail("SHOULD NOT ADD A TOTAL OF WALLS DIFFERENT OF N + 1, WHEN N IS THE SIZE OF BOARD");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WRONG_NUMBER_WALLS, e.getMessage());
        }
    }

    @Test
    public void shouldMoveOrthogonallyAPawn() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
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
    public void shouldNotMoveOrthogonallyAPawnIfItsNotPossible() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(4, null);
        q.moveToken("LEFT");
        try {
            q.moveToken("DOWN");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.TOKEN_OUT_OF_RANGE, e.getMessage());
        }
        // Check the position of the Token is the same
        assertTrue(q.getBoard().getSquare(3, 1).getToken() != null);
        // Reset QuoriPOOB
        q.resetSingleton();
        q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(4, null);
        q.moveToken("LEFT");
        q.moveToken("LEFT");
        try {
            q.moveToken("LEFT");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.TOKEN_OUT_OF_RANGE, e.getMessage());
        }
        // Check the position of the Token is the same
        assertTrue(q.getBoard().getSquare(0, 0).getToken() != null);
    }

    @Test
    public void shouldPlaceANormalBarrier() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
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
    public void shouldNotPlaceANormalBarrierIfItsNotPossible() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(6, 0, 0, 0);
        q.addWallToBoard("NormalWall", 2, 2, "UP");
        try {
            q.addWallToBoard("NormalWall", 2, 2, "UP");
            fail("SHOULD NOT LET ADD TWO WALLS AT THE SAME POSITION");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WALL_IN_SQUARE, e.getMessage());
        }
        q.resetSingleton();
        q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(6, 0, 0, 0);
        q.addWallToBoard("NormalWall", 2, 2, "UP");
        try {
            q.addWallToBoard("NormalWall", 1, 2, "DOWN");
            fail("SHOULD NOT LET ADD TWO WALLS AT THE SAME POSITION");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WALL_IN_SQUARE, e.getMessage());
        }
        q.resetSingleton();
        q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(6, 0, 0, 0);
        q.addWallToBoard("NormalWall", 2, 2, "UP");
        try {
            q.addWallToBoard("NormalWall", 1, 3, "DOWN");
            fail("SHOULD NOT LET ADD TWO WALLS AT THE SAME POSITION");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WALL_IN_SQUARE, e.getMessage());
        }
        assertTrue(q.getBoard().getMatrixBoard()[1][4].getWallDown() == null);
        try {
            q.addWallToBoard("NormalWall", 2, 1, "UP");
            fail("SHOULD NOT LET ADD TWO WALLS AT THE SAME POSITION");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WALL_IN_SQUARE, e.getMessage());
        }
        assertTrue(q.getBoard().getMatrixBoard()[2][1].getWallDown() == null);
    }

    @Test
    public void shouldNotMoveAPawnOverANonAlliedBarrier() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
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
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.FORWARD_WALL, e.getMessage());
        }
        assertEquals(danielToken, q.getBoard().getMatrixBoard()[1][2].getToken());
    }

    @Test
    public void shouldMoveAPawnOverAnAlliedBarrier() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
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
    public void shouldKnowTheBarriersLeftForEachPlayer() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
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
        assertEquals(playersNumberWalls, q.numberWalls());

    }

    @Test
    public void shouldMeetTemporalBarrierConditions2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(3, 2, 0, 1);
        q.addWallToBoard("Temporary", 0, 2, "RIGHT");
        q.addWallToBoard("Temporary", 3, 2, "RIGHT");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("UP");

        assertTrue(q.getBoard().getMatrixBoard()[0][2].getWallRight() != null);
        assertTrue(q.getBoard().getMatrixBoard()[1][2].getWallRight() != null);
        assertTrue(q.getBoard().getMatrixBoard()[0][3].getWallLeft() != null);
        assertTrue(q.getBoard().getMatrixBoard()[1][3].getWallLeft() != null);

        q.moveToken("DOWN");
        q.addWallToBoard("Temporary", 0, 3, "RIGHT");
        q.moveToken("LEFT");
        q.moveToken("LEFT");
        q.moveToken("RIGHT");
        q.moveToken("RIGHT");
        q.moveToken("LEFT");

        assertTrue(q.getBoard().getMatrixBoard()[0][2].getWallRight() == null);
        assertTrue(q.getBoard().getMatrixBoard()[1][2].getWallRight() == null);
        assertTrue(q.getBoard().getMatrixBoard()[0][3].getWallLeft() == null);
        assertTrue(q.getBoard().getMatrixBoard()[1][3].getWallLeft() == null);

        q.resetSingleton();
        q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(3, 2, 0, 1);
        q.addWallToBoard("Temporary", 0, 2, "RIGHT");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("DOWN");

        assertTrue(q.getBoard().getMatrixBoard()[0][2].getWallRight() != null);
        assertTrue(q.getBoard().getMatrixBoard()[1][2].getWallRight() != null);
        assertTrue(q.getBoard().getMatrixBoard()[0][3].getWallLeft() != null);
        assertTrue(q.getBoard().getMatrixBoard()[1][3].getWallLeft() != null);

        q.moveToken("UP");
        q.addWallToBoard("Temporary", 0, 3, "RIGHT");
        q.moveToken("LEFT");
        q.moveToken("LEFT");
        q.moveToken("RIGHT");
        q.moveToken("RIGHT");
        assertTrue(q.getBoard().getMatrixBoard()[0][2].getWallRight() == null);
        assertTrue(q.getBoard().getMatrixBoard()[1][2].getWallRight() == null);
        assertTrue(q.getBoard().getMatrixBoard()[0][3].getWallLeft() == null);
        assertTrue(q.getBoard().getMatrixBoard()[1][3].getWallLeft() == null);
    }

    @Test
    public void shouldBeAbleToConsultAfterWin() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        try {
            q.moveToken("DOWN");
            fail("SHOULD NOT BE ABLE TO PLAY AFTER IT IS OVER");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        try {
            q.moveToken("UP");
            fail("SHOULD NOT BE ABLE TO PLAY AFTER IT IS OVER");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        assertEquals(q.getWinner().getName(), "Miguel");
        try {
            q.moveToken("RIGHT");
            fail("SHOULD NOT BE ABLE TO PLAY AFTER IT IS OVER");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        try {
            q.addWallToBoard(null, 0, 0, null);
            ;
            fail("SHOULD NOT BE ABLE TO PLAY AFTER IT IS OVER");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        assertArrayEquals(new String[] { "Daniel", "Miguel" }, q.getNames());
        assertEquals(Color.ORANGE, q.getColor("Miguel"));
        assertEquals(Color.BLUE, q.getColor("Daniel"));
    }


    @Test
    public void shouldNotBlockThePassageOfAPlayer() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(6, null);
        q.addWalls(4, 2, 0, 1);
        q.addWallToBoard("NormalWall", 0, 0, "DOWN");
        q.addWallToBoard("NormalWall", 0, 2, "DOWN");
        try{
            q.addWallToBoard("NormalWall", 0, 4, "DOWN");
            fail("SHOULD NOT BLOCK THE WAY");
        } catch(QuoriPOOBException e){
            assertEquals(QuoriPOOBException.BLOCK_THE_WAY, e.getMessage());
        }
    }

    @Test
    public void shouldMoveDiagonallyAPawn() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        HashMap<String, int[][]> squares = new HashMap<>();
        int[][] teleporterArray = new int[][] { { 2, 0 } };
        squares.put("domain.Teleporter", teleporterArray);
        q.createBoard(3, squares);
        q.addWalls(3, 1, 0, 0);
        q.moveToken("DOWN");
        q.moveToken("LEFT");
        q.addWallToBoard("NormalWall", 0, 0, "LEFT");
        q.moveToken("UPRIGHT");
        
        assertEquals(q.getBoard().getMatrixBoard()[2][0].getClass().getSimpleName(), "Teleporter");
        assertEquals(q.getBoard().getMatrixBoard()[0][2].getToken().getColor(), Color.BLACK);
    }

    @Test
    public void shouldMeetNormalBarrierConditions() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(5, null);
        q.addWalls(6, 0, 0, 0);
        q.moveToken("DOWN");
        q.addWallToBoard("NormalWall", 1, 2, "DOWN");
        try{
            q.moveToken("DOWN");
            fail();
        } catch(QuoriPOOBException e ){
            assertEquals(QuoriPOOBException.FORWARD_WALL, e.getMessage());
        }
    }

    @Test
    public void shouldMeetLongBarrierConditions() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(5, null);
        q.addWalls(0, 0, 6, 0);
        q.moveToken("DOWN");
        q.addWallToBoard("LongWall", 1, 2, "DOWN");
        try{
            q.moveToken("DOWN");
            fail("SHOULD NOT LET PASS BY A LONG WALL");
        } catch(QuoriPOOBException e ){
            assertEquals(QuoriPOOBException.FORWARD_WALL, e.getMessage());
        }
        assertNotNull(q.getMatrixBoard()[1][2].getWallDown());
        assertNotNull(q.getMatrixBoard()[1][3].getWallDown());
        assertNotNull(q.getMatrixBoard()[1][4].getWallDown());
    }

    @Test
    public void shouldNotMoveDiagonallyAPawnIfItsNotPossible() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(5, null);
        q.addWalls(0, 0, 6, 0);
        try{
            q.moveToken("DOWNRIGHT");
            fail("SHOULD NOT LET JUMP DIAGONAL IF NOT ON TELEPORTER");
        } catch(QuoriPOOBException e ){
            assertEquals(QuoriPOOBException.DIAGONAL_MOVES_BLOCK, e.getMessage());
        }
        try{
            q.moveToken("DOWNLEFT");
            fail("SHOULD NOT LET JUMP DIAGONAL IF NOT ON TELEPORTER");
        } catch(QuoriPOOBException e ){
            assertEquals(QuoriPOOBException.DIAGONAL_MOVES_BLOCK, e.getMessage());
        }
        assertNotNull(q.getMatrixBoard()[0][2].getToken());
    }
    @Test
    public void shouldNotMoveAPawnOverAPawnIfItsNotPossible() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setNormalMode();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(5, null);
        q.addWalls(6, 0, 0, 0);
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.addWallToBoard("NormalWall", 3, 2, "DOWN");
        try{
            q.moveToken("DOWN");
            fail("SHOULD NOT LET JUMP DIAGONAL IF NOT ON TELEPORTER");
        } catch(QuoriPOOBException e ){
            assertEquals(QuoriPOOBException.FORWARD_WALL, e.getMessage());
        }
        assertNotNull(q.getMatrixBoard()[2][2].getToken());
        assertNotNull(q.getMatrixBoard()[3][2].getToken());
    }
}