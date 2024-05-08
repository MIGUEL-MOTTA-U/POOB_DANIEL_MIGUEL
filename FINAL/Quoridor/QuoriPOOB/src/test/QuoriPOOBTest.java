package test;
import domain.*;
import static org.junit.Assert.*;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void shouldNotCreateModeGameUndefined1() throws QuoriPOOBException {
        QuoriPOOB q = new QuoriPOOB();
        try {
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerMachine(Color.ORANGE,"domain.Beginner");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.MODE_UNDEFINED, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateModeGameUndefined2() throws QuoriPOOBException {
        QuoriPOOB q = new QuoriPOOB();
        try {
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerHuman("Player 2", Color.GRAY);
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.MODE_UNDEFINED, e.getMessage());
        }
    }

    @Test
    public void shouldCreateOnePlayerMode1() throws QuoriPOOBException {
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.ORANGE,"domain.Beginner");
        assertArrayEquals(new String[] {"Player 1", "Machine"}, q.getNames());
    }
    
    @Test
    public void shouldCreateOnePlayerMode2() throws QuoriPOOBException {
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerMachine(Color.RED,"domain.Advanced");
        q.createPlayerHuman("Player 2", Color.BLUE);
        assertArrayEquals(new String[] {"Machine", "Player 2"}, q.getNames());
    }

    @Test
    public void shouldNotCreateOnePlayerMode1() throws QuoriPOOBException {
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        try {
            q.createPlayerHuman("Player 1", Color.BLACK);
            q.createPlayerHuman("Player 2", Color.BLUE);
            fail("SHOULD NOT CREATE TWO HUMAN PLAYERS IN ONE PLAYER MODE");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.ONE_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateOnePlayerMode2() throws QuoriPOOBException {
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        try {
            q.createPlayerMachine(Color.BLUE,"domain.Advanced");
            q.createPlayerMachine(Color.RED,"domain.Beginner");
            fail("SHOULD NOT CREATE TWO MACHINE PLAYERS IN ONE PLAYER MODE");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.ONE_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldCreateTwoPlayersMode() throws QuoriPOOBException {
        QuoriPOOB q = new QuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 2", Color.GREEN);
        assertArrayEquals(new String[] {"Player 1", "Player 2"}, q.getNames());
    }

    @Test
    public void shouldNotCreateTwoPlayersMode1() throws QuoriPOOBException {
        QuoriPOOB q = new QuoriPOOB();
        q.setTwoPlayers();
        try {
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerMachine(Color.RED,"domain.Beginner");
            fail("SHOULD NOT CREATE ONE MACHINE PLAYER AND ONE HUMAN PLAYER IN TWO PLAYERS MODE");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.TWO_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateTwoPlayersMode2() throws QuoriPOOBException {
        QuoriPOOB q = new QuoriPOOB();
        q.setTwoPlayers();
        try {
            q.createPlayerMachine(Color.RED,"domain.Beginner");
            q.createPlayerHuman("Player 1", Color.BLUE);
            fail("SHOULD NOT CREATE ONE MACHINE PLAYER AND ONE HUMAN PLAYER IN TWO PLAYERS MODE");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.TWO_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldCreatePlayerHuman1() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 2", Color.ORANGE);
        assertArrayEquals(new String[] {"Player 1", "Player 2"}, q.getNames());
    }

    @Test
    public void shouldCreateHuman2() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 1", Color.ORANGE);
        assertArrayEquals(new String[] {"Player 1", "Player 1"}, q.getNames());
    }

    @Test
    public void shouldNotCreateHuman1(){
        QuoriPOOB q = new QuoriPOOB();
        q.setTwoPlayers();
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
        q.setTwoPlayers();
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
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.ORANGE,"domain.Beginner");
        assertArrayEquals(new String[] {"Player 1", "Machine"}, q.getNames());
        assertArrayEquals(new String[] {"Player 1", "Machine"}, q.getNames());
    }

    @Test
    public void shouldCreateHumanAndMachine2()throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.ORANGE,"domain.Intermediate");
        assertArrayEquals(new String[] {"Player 1", "Machine"}, q.getNames());
    }

    @Test
    public void shouldCreateHumanAndMachine3()throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.ORANGE,"domain.Advanced");
        assertArrayEquals(new String[] {"Player 1", "Machine"}, q.getNames());
    }

    @Test
    public void shouldCreateHumanAndMachine4()throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.ORANGE,"NOT A MACHINE");
        q.getNames();
        assertArrayEquals(new String[] {"Player 1", null}, q.getNames());
    }


    @Test
    public void shouldNotCreateHumanAndMachine1(){
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        try{
            q.createPlayerMachine(Color.BLUE,"domain.Advanced");
            q.createPlayerMachine(Color.ORANGE,"domain.Advanced");
            fail("SHOULD NOT LET PLAY TWO MACHINES");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.ONE_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateHumanAndMachine2(){
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        try{
            q.createPlayerMachine(Color.BLUE,"domain.Intermediate");
            q.createPlayerMachine(Color.ORANGE,"domain.Advanced");
            fail("SHOULD NOT LET PLAY TWO MACHINES");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.ONE_PLAYER_MODE, e.getMessage());
        }
    }
    
    @Test
    public void shouldNotCreateHumanAndMachine3(){
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        try{
            q.createPlayerMachine(Color.BLUE,"domain.Beginner");
            q.createPlayerMachine(Color.ORANGE,"domain.Advanced");
            fail("SHOULD NOT LET PLAY TWO MACHINES");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.ONE_PLAYER_MODE, e.getMessage());
        }
    }
    
    @Test
    public void shouldNotCreateHumanAndMachine4(){
        QuoriPOOB q = new QuoriPOOB();
        q.setTwoPlayers();
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
        q.setOnePlayer();
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
        q.setOnePlayer();
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
        q.setOnePlayer();
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
        q.setOnePlayer();
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
        q.setOnePlayer();
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
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        q.createBoard(4,null);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldVerifyCreatedBoard() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        
        int[][] normalArray = new int[][] {{0,0},{0,1},{1,0},{1,1}};
        int[][] turnArray = new int[][] {{0,2},{0,3},{1,2},{1,3}};
        int[][] teleporterArray = new int[][] {{2,0},{2,1},{3,0},{3,1}};
        int[][] returnsArray = new int[][] {{2,2},{2,3},{3,2},{3,3}};
        specialQuares.put("domain.NormalSquare",normalArray);
        specialQuares.put("domain.DoubleTurn", turnArray);
        specialQuares.put("domain.Teleporter",teleporterArray);
        specialQuares.put("domain.Return", returnsArray);
        
        q.createBoard(4,specialQuares);
        // Verify the created Board
        Object[][] matrix = q.getBoard().getMatrixBoard();
        for(int i = 0; i< matrix.length; i++){
            for(int j = 0; j< matrix.length; j++){
                Square s = (Square) matrix[i][j];
                if (s instanceof NormalSquare){
                    int[] c = s.getCoordenates();
                    assertEquals(true, Arrays.equals(c,normalArray[0]) ||Arrays.equals(c,normalArray[1]) ||
                    Arrays.equals(c,normalArray[2]) ||Arrays.equals(c,normalArray[3]));
                } else if (s instanceof DoubleTurn){
                    int[] c = s.getCoordenates();
                    assertEquals(true, Arrays.equals(c,turnArray[0]) ||Arrays.equals(c,turnArray[1]) ||
                    Arrays.equals(c,turnArray[2]) ||Arrays.equals(c,turnArray[3]));
                } else if (s instanceof Teleporter){
                    int[] c = s.getCoordenates();
                    assertEquals(true, Arrays.equals(c,teleporterArray[0]) ||Arrays.equals(c,teleporterArray[1]) ||
                    Arrays.equals(c,teleporterArray[2]) ||Arrays.equals(c,teleporterArray[3]));
                } else if (s instanceof Return){
                    int[] c = s.getCoordenates();
                    assertEquals(true, Arrays.equals(c,returnsArray[0]) ||Arrays.equals(c,returnsArray[1]) ||
                    Arrays.equals(c,returnsArray[2]) ||Arrays.equals(c,returnsArray[3]));
                } else {
                    fail("ALL THE POSITIONS SHOULD BE SQUARES");
                }
            }
        }
    }

    @Test
    public void shouldNotCreateBoard1() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        try {
            specialQuares.put("domain.NormalSquare", new int[][] {{0,0},{0,1},{1,0},{1,1},{2,2}});
            q.createBoard(2,specialQuares);
            fail("SHOULD NOT CREATE A BOARD WITH MORE PIECES THAN POSITIONS ON THE BOARD");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WRONG_NUMER_SQUARES,e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateBoard2() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Irregular", new int[][] {{0,0},{0,1},{1,0},{1,1}});
        q.createBoard(2,specialQuares);
    }

    @Test
    public void shouldNotCreateBoard3() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Irregular", new int[][] {{0,0}});
        try{
            q.createBoard(1,specialQuares);
            fail("SHOULD NOT CREATE A BOARD WITH A SIZE LOWER OR EQUAL TO 1");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.WRONG_SIZE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateBoard4() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Irregular", new int[][] {{0,0}});
        try{
            q.createBoard(2,specialQuares);
            fail("SHOULD NOT BE ABLE TO PLAY JUST 1 PLAYER");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.MISSING_PLAYERS, e.getMessage());
        }
    }

    @Test
    public void shouldCreateTokensOnTheBoard1() throws QuoriPOOBException {
        QuoriPOOB q = new QuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 2", Color.RED);

        q.createBoard(17, null);

        Square[][] matrix = q.getBoard().getMatrixBoard();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                Square square = matrix[row][col];
                System.out.println(row + " - " + col);
                if ((col == 8) & (row == 0)) {
                    assertNotNull(square.getToken());
                    assertEquals(q.getColor("Player 1"), square.getToken().getColor());
                } else if ((col == 8) & (row == 16)) {
                    assertNotNull(square.getToken());
                    assertEquals(q.getColor("Player 2"), square.getToken().getColor());
                } else {
                    assertNull(square.getToken());
                }
            }
        }
    }

    @Test
    public void shouldCreateTokensOnTheBoard2() throws QuoriPOOBException {
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");

        HashMap<String, int[][]> specialSquares = new HashMap<>();
        specialSquares.put("domain.Teleporter", new int[][] {{0,0}, {2,3}, {7,1}});
        specialSquares.put("domain.Return", new int[][] {{1,1}, {3,1}});
        specialSquares.put("domain.DoubleTurn", new int[][] {{2,2}, {5,5}, {1,3}, {6,2}});

        q.createBoard(10, specialSquares);

        Square[][] matrix = q.getBoard().getMatrixBoard();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                Square square = matrix[row][col];
                System.out.println(row + " - " + col);
                if ((col == 4) & (row == 0)) {
                    assertNotNull(square.getToken());
                    assertEquals(q.getColor("Player 1"), square.getToken().getColor());
                } else if ((col == 4) & (row == 9)) {
                    assertNotNull(square.getToken());
                    assertEquals(q.getColor("Machine"), square.getToken().getColor());
                } else {
                    assertNull(square.getToken());
                }
            }
        }
    }

    @Test
    public void shouldAddWalls1() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] {{0,0},{0,1},{1,0},{1,1}});
        q.createBoard(4,specialQuares);
        q.addWalls(0, 0, 0, 5);
    }

    @Test
    public void shouldAddWalls2() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] {{0,0},{0,1},{1,0},{1,1}});
        q.createBoard(4,specialQuares);
        q.addWalls(1, 2, 1, 1);
    }

    @Test
    public void shouldNotAddWalls1() throws QuoriPOOBException{
        QuoriPOOB q = new QuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN,"domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] {{0,0},{0,1},{1,0},{1,1}});
        try{
            q.addWalls(1, 2, 1, 1);
            fail("SHOULD NOT ADD THE WALLS IF THERE IS NOT A BOARD DEFINED");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.BOARD_UNDEFINED, e.getMessage());
        }
    }

    @Test
    public void shouldNotAddWalls2() throws QuoriPOOBException{
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
    public void shouldNotAddWalls3() throws QuoriPOOBException{
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
}