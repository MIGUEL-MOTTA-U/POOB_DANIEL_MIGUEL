package test;

import domain.*;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.File;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuoriPOOBTest {
    private HashMap<String, int[][]> noSpecialSquares;

    public QuoriPOOBTest() {
        noSpecialSquares = new HashMap<>();
        noSpecialSquares.put("domain.Return", null);
        noSpecialSquares.put("domain.DoubleTurn", null);
        noSpecialSquares.put("domain.Teleporter", null);
    }

    @Before
    public void setUp() {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.resetSingleton();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldNotCreateModeGameUndefined1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        try {
            q.setMode("domain.NormalMode");
            q.createPlayerHuman("Player 1", Color.BLUE.toString());
            q.createPlayerMachine(Color.ORANGE.toString(), "domain.Beginner");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.MODE_UNDEFINED, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateModeGameUndefined2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        try {
            q.setOnePlayer();
            q.createPlayerHuman("Player 1", Color.BLUE.toString());
            q.createPlayerHuman("Player 2", Color.GRAY.toString());
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.MODE_UNDEFINED, e.getMessage());
        }
    }

    @Test
    public void shouldCreateOnePlayerMode1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerMachine(Color.ORANGE.toString(), "domain.Beginner");
        assertArrayEquals(new String[] { "Player 1", "Machine" }, q.getNames());
    }

    @Test
    public void shouldCreateOnePlayerMode2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerMachine(Color.RED.toString(), "domain.Advanced");
        q.createPlayerHuman("Player 2", Color.BLUE.toString());
        assertArrayEquals(new String[] { "Machine", "Player 2" }, q.getNames());
    }

    @Test
    public void shouldNotCreateOnePlayerMode1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        try {
            q.createPlayerHuman("Player 1", Color.BLACK.toString());
            q.createPlayerHuman("Player 2", Color.BLUE.toString());
            fail("SHOULD NOT CREATE TWO HUMAN PLAYERS IN ONE PLAYER MODE");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.ONE_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateOnePlayerMode2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        try {
            q.createPlayerMachine(Color.BLUE.toString(), "domain.Advanced");
            q.createPlayerMachine(Color.RED.toString(), "domain.Beginner");
            fail("SHOULD NOT CREATE TWO MACHINE PLAYERS IN ONE PLAYER MODE");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.ONE_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldCreateTwoPlayersMode() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerHuman("Player 2", Color.GREEN.toString());
        assertArrayEquals(new String[] { "Player 1", "Player 2" }, q.getNames());
    }

    @Test
    public void shouldNotCreateTwoPlayersMode1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        try {
            q.createPlayerHuman("Player 1", Color.BLUE.toString());
            q.createPlayerMachine(Color.RED.toString(), "domain.Beginner");
            fail("SHOULD NOT CREATE ONE MACHINE PLAYER AND ONE HUMAN PLAYER IN TWO PLAYERS MODE");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.TWO_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateTwoPlayersMode2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        try {
            q.createPlayerMachine(Color.RED.toString().toString(), "domain.Beginner");
            q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
            fail("SHOULD NOT CREATE ONE MACHINE PLAYER AND ONE HUMAN PLAYER IN TWO PLAYERS MODE");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.TWO_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldCreatePlayerHuman1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
        q.createPlayerHuman("Player 2", Color.ORANGE.toString().toString());
        assertArrayEquals(new String[] { "Player 1", "Player 2" }, q.getNames());
    }

    @Test
    public void shouldCreateHuman2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
        q.createPlayerHuman("Player 1", Color.ORANGE.toString().toString());
        assertArrayEquals(new String[] { "Player 1", "Player 1" }, q.getNames());
    }

    @Test
    public void shouldNotCreateHuman1() {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        try {
            q.setMode("domain.NormalMode");
            q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
            q.createPlayerHuman("Player 2", Color.BLUE.toString().toString());
            fail("SHOULD NOT CREATE TWO PLAYERS WITH THE SAME COLOR");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.SAME_PLAYER_COLOR, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateHuman2() {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        try {
            q.setMode("domain.NormalMode");
            q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
            q.createPlayerHuman("Player 1", Color.ORANGE.toString().toString());
            q.createPlayerHuman("Player 3", Color.CYAN.toString().toString());
            fail("SHOULD NOT CREATE MORE THAN TWO PLAYERS");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WRONG_NUMBER_PLAYERS, e.getMessage());
        }
    }

    @Test
    public void shouldCreateHumanAndMachine1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
        q.createPlayerMachine(Color.ORANGE.toString().toString(), "domain.Beginner");
        assertArrayEquals(new String[] { "Player 1", "Machine" }, q.getNames());
        assertArrayEquals(new String[] { "Player 1", "Machine" }, q.getNames());
    }

    @Test
    public void shouldCreateHumanAndMachine2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
        q.createPlayerMachine(Color.ORANGE.toString().toString(), "domain.Intermediate");
        assertArrayEquals(new String[] { "Player 1", "Machine" }, q.getNames());
    }

    @Test
    public void shouldCreateHumanAndMachine3() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
        q.createPlayerMachine(Color.ORANGE.toString().toString(), "domain.Advanced");
        assertArrayEquals(new String[] { "Player 1", "Machine" }, q.getNames());
    }

    @Test
    public void shouldNotCreateHumanAndMachine1() {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        try {
            q.setMode("domain.NormalMode");
            q.createPlayerMachine(Color.BLUE.toString().toString(), "domain.Advanced");
            q.createPlayerMachine(Color.ORANGE.toString().toString(), "domain.Advanced");
            fail("SHOULD NOT LET PLAY TWO MACHINES");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.ONE_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateHumanAndMachine2() {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        try {
            q.setMode("domain.NormalMode");
            q.createPlayerMachine(Color.BLUE.toString().toString(), "domain.Intermediate");
            q.createPlayerMachine(Color.ORANGE.toString().toString(), "domain.Advanced");
            fail("SHOULD NOT LET PLAY TWO MACHINES");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.ONE_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateHumanAndMachine3() {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        try {
            q.setMode("domain.NormalMode");
            q.createPlayerMachine(Color.BLUE.toString().toString(), "domain.Beginner");
            q.createPlayerMachine(Color.ORANGE.toString().toString(), "domain.Advanced");
            fail("SHOULD NOT LET PLAY TWO MACHINES");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.ONE_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateHumanAndMachine4() {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        try {
            q.setMode("domain.NormalMode");
            q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
            q.createPlayerHuman("Player 2", Color.ORANGE.toString().toString());
            q.createPlayerMachine(Color.ORANGE.toString().toString(), "domain.Advanced");
            fail("SHOULD NOT LET PLAY TWO MACHINES");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WRONG_NUMBER_PLAYERS, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateHumanAndMachine5() {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        try {
            q.setMode("domain.NormalMode");
            q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
            q.createPlayerMachine(Color.BLUE.toString().toString(), "domain.Advanced");
            fail("SHOULD NOT LET PLAY TWO MACHINES");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.SAME_PLAYER_COLOR, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateHumanAndMachine6() {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        try {
            q.setMode("domain.NormalMode");
            q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
            q.createPlayerMachine(Color.RED.toString().toString(), "Not machine");
            fail("THIS MACHINE DOESN'T EXIST");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.PLAYER_NOT_EXIST, e.getMessage());
        }
    }

    @Test
    public void shouldCreateBoard1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
        q.createPlayerMachine(Color.GREEN.toString().toString(), "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Teleporter", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldCreateBoard2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
        q.createPlayerMachine(Color.GREEN.toString().toString(), "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldCreateBoard3() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
        q.createPlayerMachine(Color.GREEN.toString().toString(), "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.DoubleTurn", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldCreateBoard4() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
        q.createPlayerMachine(Color.GREEN.toString().toString(), "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.NormalSquare", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldCreateBoard5() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
        q.createPlayerMachine(Color.GREEN.toString().toString(), "domain.Advanced");
        q.createBoard(4, noSpecialSquares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldVerifyCreatedBoard() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString().toString());
        q.createPlayerMachine(Color.GREEN.toString().toString(), "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();

        int[][] normalArray = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
        int[][] turnArray = new int[][] { { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 } };
        int[][] teleporterArray = new int[][] { { 2, 0 }, { 2, 1 }, { 3, 0 }, { 3, 1 } };
        int[][] returnsArray = new int[][] { { 2, 2 }, { 2, 3 }, { 3, 2 }, { 3, 3 } };
        specialQuares.put("domain.NormalSquare", normalArray);
        specialQuares.put("domain.DoubleTurn", turnArray);
        specialQuares.put("domain.Teleporter", teleporterArray);
        specialQuares.put("domain.Return", returnsArray);

        q.createBoard(4, specialQuares);
        // Verify the created Board
        Object[][] matrix = q.getBoard().getMatrixBoard();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                Square s = (Square) matrix[i][j];
                if (s instanceof NormalSquare) {
                    int[] c = s.getCoordenates();
                    assertEquals(true, Arrays.equals(c, normalArray[0]) || Arrays.equals(c, normalArray[1]) ||
                            Arrays.equals(c, normalArray[2]) || Arrays.equals(c, normalArray[3]));
                } else if (s instanceof DoubleTurn) {
                    int[] c = s.getCoordenates();
                    assertEquals(true, Arrays.equals(c, turnArray[0]) || Arrays.equals(c, turnArray[1]) ||
                            Arrays.equals(c, turnArray[2]) || Arrays.equals(c, turnArray[3]));
                } else if (s instanceof Teleporter) {
                    int[] c = s.getCoordenates();
                    assertEquals(true, Arrays.equals(c, teleporterArray[0]) || Arrays.equals(c, teleporterArray[1]) ||
                            Arrays.equals(c, teleporterArray[2]) || Arrays.equals(c, teleporterArray[3]));
                } else if (s instanceof Return) {
                    int[] c = s.getCoordenates();
                    assertEquals(true, Arrays.equals(c, returnsArray[0]) || Arrays.equals(c, returnsArray[1]) ||
                            Arrays.equals(c, returnsArray[2]) || Arrays.equals(c, returnsArray[3]));
                } else {
                    fail("ALL THE POSITIONS SHOULD BE SQUARES");
                }
            }
        }
    }

    @Test
    public void shouldNotCreateBoard1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerMachine(Color.GREEN.toString(), "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        try {
            specialQuares.put("domain.NormalSquare", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 }, { 2, 2 } });
            q.createBoard(2, specialQuares);
            fail("SHOULD NOT CREATE A BOARD WITH MORE PIECES THAN POSITIONS ON THE BOARD");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WRONG_NUMER_SQUARES, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateBoard2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        try {
            q.setOnePlayer();
            q.setMode("domain.NormalMode");
            q.createPlayerHuman("Player 1", Color.BLUE.toString());
            q.createPlayerMachine(Color.GREEN.toString(), "domain.Advanced");
            HashMap<String, int[][]> specialQuares = new HashMap<>();
            specialQuares.put("domain.Irregular", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
            q.createBoard(2, specialQuares);
            fail("SHOULD NOT CREATE A BOARD WITH A NONEXISTENT SQUARE");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.SQUARE_NOT_EXIST, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateBoard3() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerMachine(Color.GREEN.toString(), "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Irregular", new int[][] { { 0, 0 } });
        try {
            q.createBoard(1, specialQuares);
            fail("SHOULD NOT CREATE A BOARD WITH A SIZE LOWER OR EQUAL TO 1");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WRONG_SIZE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateBoard4() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Irregular", new int[][] { { 0, 0 } });
        try {
            q.createBoard(2, specialQuares);
            fail("SHOULD NOT BE ABLE TO PLAY JUST 1 PLAYER");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.MISSING_PLAYERS, e.getMessage());
        }
    }

    @Test
    public void shouldCreateTokensOnTheBoard1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerHuman("Player 2", Color.RED.toString());
        q.createBoard(17, noSpecialSquares);

        Square[][] matrix = q.getBoard().getMatrixBoard();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                Square square = matrix[row][col];
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
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerMachine(Color.GREEN.toString(), "domain.Advanced");

        HashMap<String, int[][]> specialSquares = new HashMap<>();
        specialSquares.put("domain.Teleporter", new int[][] { { 0, 0 }, { 2, 3 }, { 7, 1 } });
        specialSquares.put("domain.Return", new int[][] { { 1, 1 }, { 3, 1 } });
        specialSquares.put("domain.DoubleTurn", new int[][] { { 2, 2 }, { 5, 5 }, { 1, 3 }, { 6, 2 } });

        q.createBoard(10, specialSquares);

        Square[][] matrix = q.getBoard().getMatrixBoard();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                Square square = matrix[row][col];
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
    public void shouldAddWalls1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerMachine(Color.GREEN.toString(), "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        q.addWalls(0, 0, 0, 5);
    }

    @Test
    public void shouldAddWalls2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerMachine(Color.GREEN.toString(), "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        q.addWalls(1, 2, 1, 1);
    }

    @Test
    public void shouldNotAddWalls1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerMachine(Color.GREEN.toString(), "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        try {
            q.addWalls(1, 2, 1, 1);
            fail("SHOULD NOT ADD THE WALLS IF THERE IS NOT A BOARD DEFINED");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.BOARD_UNDEFINED, e.getMessage());
        }
    }

    @Test
    public void shouldNotAddWalls2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerMachine(Color.GREEN.toString(), "domain.Advanced");
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
    public void shouldNotAddWalls3() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerMachine(Color.GREEN.toString(), "domain.Advanced");
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
    public void shouldNotAddWallsToBoard1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerHuman("Player 2", Color.ORANGE.toString());
        q.createBoard(10, noSpecialSquares);
        q.addWalls(1, 4, 2, 4);
        try {
            q.addWallToBoard("Wrong Type", 0, 0, "UP");
            fail("SHOULD NOT BE ABLE TO ADD A WALL THAT IS NOT DEFINED AS WALL OF THE PLAYER.");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WALL_NOT_EXIST, e.getMessage());
        }
    }

    @Test
    public void shouldCheckCurrentPlayerAfterAddWall() throws QuoriPOOBException {
        // En este voy a poner una verificacion, que el jugador ya no tenga esos puentes
        // luego de agregarlos.
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerHuman("Player 2", Color.ORANGE.toString());
        q.createBoard(10, noSpecialSquares);
        q.addWalls(1, 4, 2, 4);
        Player firstPlayer = q.getCurrentPlayer();
        // Tiene 1 normal, 4 temporales, 2 largos y 4 aliados
        q.addWallToBoard("Temporary", 0, 0, "UP");
        assertNotEquals(firstPlayer, q.getCurrentPlayer());
        q.addWallToBoard("Allied", 4, 2, "LEFT");
        assertEquals(firstPlayer, q.getCurrentPlayer());
        q.addWallToBoard("NormalWall", 9, 8, "DOWN");
        assertNotEquals(firstPlayer, q.getCurrentPlayer());

    }

    @Test
    public void shouldCheckAddWallsToBoard() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createPlayerHuman("Player 2", Color.ORANGE.toString());
        q.createBoard(10, noSpecialSquares);
        q.addWalls(1, 4, 2, 4);

        q.addWallToBoard("Temporary", 0, 0, "UP");
        q.addWallToBoard("Allied", 4, 2, "LEFT");
        q.addWallToBoard("NormalWall", 9, 8, "DOWN");
        q.addWallToBoard("NormalWall", 7, 5, "UP");
        // Con longWall cuando este implementado

        Square[][] matrix = q.getBoard().getMatrixBoard();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                Square square = matrix[row][col];
                if ((row == 0 && col == 0) || (row == 0 & col == 1)) {
                    assertNotNull(square.getWallUp());
                    assertTrue(square.getWallUp() instanceof Temporary);
                } else if ((row == 4 && col == 2) || (row == 5 & col == 2)) {
                    assertNotNull(square.getWallLeft());
                    assertTrue(square.getWallLeft() instanceof Allied);
                } else if ((row == 4 && col == 1) || (row == 5 & col == 1)) {
                    assertNotNull(square.getWallRight());
                    assertTrue(square.getWallRight() instanceof Allied);
                } else if ((row == 9 && col == 8) || (row == 9 & col == 9)) {
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
    public void shouldMoveOrthogonallyAPawn1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        q.createBoard(4, noSpecialSquares);
        assertTrue(q.getBoard().getSquare(0, 1).getToken() != null);
        q.moveToken("LEFT");
        assertTrue(q.getBoard().getSquare(0, 0).getToken() == null);
    }

    @Test
    public void shouldMoveOrthogonallyAPawn2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        q.createBoard(4, noSpecialSquares);
        assertTrue(q.getBoard().getSquare(0, 1).getToken() != null);
        q.moveToken("LEFT");
        assertTrue(q.getBoard().getSquare(0, 0).getToken() == null);
        assertTrue(q.getBoard().getSquare(0, 1).getToken() != null);
        assertTrue(q.getBoard().getSquare(3, 1).getToken() == null);
        q.moveToken("LEFT");
        assertTrue(q.getBoard().getSquare(3, 0).getToken() != null);
        assertTrue(q.getBoard().getSquare(3, 1).getToken() == null);
    }

    @Test
    public void shouldMoveOrthogonallyAPawn3() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        q.createBoard(4, noSpecialSquares);

        // Token Daniel
        assertTrue(q.getBoard().getSquare(0, 1).getToken() != null);
        q.moveToken("LEFT");
        assertTrue(q.getBoard().getSquare(0, 0).getToken() == null);
        assertTrue(q.getBoard().getSquare(0, 1).getToken() != null);

        assertTrue(q.getBoard().getSquare(3, 1).getToken() == null);
        q.moveToken("RIGHT");
        assertTrue(q.getBoard().getSquare(3, 2).getToken() == null);
        assertTrue(q.getBoard().getSquare(3, 1).getToken() == null);
        assertTrue(q.getBoard().getSquare(0, 0).getToken() == null);
        q.moveToken("UP");
        assertTrue(q.getBoard().getSquare(2, 0).getToken() != null);
        assertTrue(q.getBoard().getSquare(3, 3).getToken() == null);

        q.moveToken("LEFT");
        assertTrue(q.getBoard().getSquare(0, 1).getToken() != null);
        assertTrue(q.getBoard().getSquare(2, 0).getToken() != null);
        assertTrue(q.getBoard().getSquare(3, 1).getToken() == null);

    }

    @Test
    public void shouldNotMoveOrthogonallyAPawn1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        q.createBoard(4, noSpecialSquares);
        q.moveToken("LEFT");
        try {
            q.moveToken("UP");
            fail("SHOULD NOT MOVE");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.TOKEN_OUT_OF_RANGE, e.getMessage());
        }
        assertTrue(q.getBoard().getSquare(0, 1).getToken() != null);
    }

    @Test
    public void shouldNotMoveOrthogonallyAPawn2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        q.createBoard(4, noSpecialSquares);
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
    public void shouldPlaceANormalBarrier1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(6, 0, 0, 0);
        q.addWallToBoard("NormalWall", 2, 2, "UP");

        assertTrue(q.getBoard().getMatrixBoard()[2][2].getWallUp() != null);
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallUp(), q.getBoard().getMatrixBoard()[2][3].getWallUp());
        assertEquals(q.getBoard().getMatrixBoard()[1][2].getWallDown(),
                q.getBoard().getMatrixBoard()[1][3].getWallDown());

        q.addWallToBoard("NormalWall", 2, 2, "LEFT");
        assertTrue(q.getBoard().getMatrixBoard()[2][1].getWallRight() != null);
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallLeft(),
                q.getBoard().getMatrixBoard()[3][2].getWallLeft());
        assertEquals(q.getBoard().getMatrixBoard()[2][1].getWallRight(),
                q.getBoard().getMatrixBoard()[3][1].getWallRight());

    }



    @Test
    public void shouldNotPlaceANormalBarrierIfItsNotPossible1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(6, 0, 0, 0);
        q.addWallToBoard("NormalWall", 2, 2, "UP");
        try {
            q.addWallToBoard("NormalWall", 2, 2, "UP");
            fail("SHOULD NOT LET ADD TWO WALLS AT THE SAME POSITION");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WALL_IN_SQUARE, e.getMessage());
        }
    }

    @Test
    public void shouldNotPlaceANormalBarrierIfItsNotPossible2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(6, 0, 0, 0);
        q.addWallToBoard("NormalWall", 2, 2, "UP");
        try {
            q.addWallToBoard("NormalWall", 1, 2, "DOWN");
            fail("SHOULD NOT LET ADD TWO WALLS AT THE SAME POSITION");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WALL_IN_SQUARE, e.getMessage());
        }
    }

    @Test
    public void shouldNotPlaceANormalBarrierIfItsNotPossible3() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
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
    public void shouldMoveAPawnOverAPawn1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        q.createBoard(5, noSpecialSquares);
        q.moveToken("UP");
        Token miguelToken = q.getBoard().getMatrixBoard()[4][2].getToken();
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        assertEquals(miguelToken, q.getBoard().getMatrixBoard()[1][2].getToken());
    }

    @Test
    public void shouldMoveAPawnOverAPawn2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        q.createBoard(5, noSpecialSquares);
        Token miguelToken = q.getBoard().getMatrixBoard()[0][2].getToken();
        q.moveToken("UP");
        q.moveToken("LEFT");
        q.moveToken("UP"); //
        q.moveToken("DOWN");
        q.moveToken("LEFT");
        q.moveToken("DOWN");
        assertEquals(miguelToken, q.getBoard().getMatrixBoard()[3][1].getToken());
    }

    @Test
    public void shouldNotMoveAPawnOverANonAlliedBarrier() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        Token danielToken = q.getBoard().getMatrixBoard()[0][2].getToken();
        q.addWalls(3, 3, 0, 0);
        q.addWallToBoard("NormalWall", 1, 2, "DOWN");
        q.moveToken("DOWN");
        q.moveToken("UP");
        try {
            q.moveToken("DOWN");
            fail("SHOULD NOT JUMP A NONE ALLIED BARRIER");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.FORWARD_WALL, e.getMessage());
        }
        assertEquals(danielToken, q.getBoard().getMatrixBoard()[1][2].getToken());
    }

    @Test
    public void shouldNotMoveAPawnOverANonAlliedBarrier2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        Token danielToken = q.getBoard().getMatrixBoard()[0][2].getToken();
        q.addWalls(3, 3, 0, 0);
        q.addWallToBoard("NormalWall", 1, 2, "RIGHT");
        q.moveToken("DOWN");
        q.moveToken("LEFT");
        try {
            q.moveToken("RIGHT");
            fail("SHOULD NOT JUMP A NONE ALLIED BARRIER");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.FORWARD_WALL, e.getMessage());
        }
        assertEquals(danielToken, q.getBoard().getMatrixBoard()[1][2].getToken());
    }

    @Test
    public void shouldNotMoveAPawnOverANonAlliedBarrier3() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        Token danielToken = q.getBoard().getMatrixBoard()[0][2].getToken();
        q.addWalls(3, 3, 0, 0);
        q.addWallToBoard("Temporary", 1, 2, "DOWN");
        q.moveToken("DOWN");
        q.moveToken("UP");
        try {
            q.moveToken("DOWN");
            fail("SHOULD NOT JUMP A NONE ALLIED BARRIER");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.FORWARD_WALL, e.getMessage());
        }
        assertEquals(danielToken, q.getBoard().getMatrixBoard()[1][2].getToken());
    }

    @Test
    public void shouldNotMoveAPawnOverANonAlliedBarrier4() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        Token danielToken = q.getBoard().getMatrixBoard()[0][2].getToken();
        q.addWalls(3, 3, 0, 0);
        q.addWallToBoard("Temporary", 1, 2, "RIGHT");
        q.moveToken("DOWN");
        q.moveToken("UP");
        try {
            q.moveToken("RIGHT");
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
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        Token danielToken = q.getBoard().getMatrixBoard()[0][2].getToken();
        q.addWalls(3, 2, 0, 1);
        q.moveToken("LEFT");
        q.addWallToBoard("Allied", 1, 2, "UP");
        q.moveToken("UP");
        q.moveToken("DOWN");
        assertEquals(danielToken, q.getBoard().getMatrixBoard()[1][2].getToken());
    }

    @Test
    public void shouldMoveAPawnOverAnAlliedBarrier2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        Token danielToken = q.getBoard().getMatrixBoard()[4][2].getToken();
        q.addWalls(3, 2, 0, 1);
        q.addWallToBoard("Allied", 3, 2, "RIGHT");
        q.moveToken("DOWN");
        q.moveToken("RIGHT");
        assertEquals(danielToken, q.getBoard().getMatrixBoard()[4][3].getToken());
    }

    @Test
    public void shouldMeetTemporalBarrierConditions1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        q.addWallToBoard("Temporary", 0, 2, "RIGHT");
        q.moveToken("DOWN");
        q.moveToken("UP");
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
    public void shouldMeetTemporalBarrierConditions2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        q.addWallToBoard("Temporary", 0, 2, "RIGHT");
        q.addWallToBoard("Temporary", 3, 2, "RIGHT");
        q.moveToken("UP");
        q.moveToken("DOWN");
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
    }

    @Test
    public void shouldKnowTheBarriersLeftForEachPlayer() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        q.addWallToBoard("Allied", 0, 2, "RIGHT");
        q.addWallToBoard("Allied", 0, 1, "RIGHT");
        HashMap<String, Integer> danielWalls = new HashMap<>();
        HashMap<String, Integer> miguelWalls = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> playersNumberWalls = new HashMap<>();

        danielWalls.put("NormalWall", 3);
        danielWalls.put("Temporary", 2);
        danielWalls.put("LongWall", 0);
        danielWalls.put("Allied", 0);
        playersNumberWalls.put(Color.BLUE.toString(), danielWalls);
        miguelWalls.put("NormalWall", 3);
        miguelWalls.put("Temporary", 1);
        miguelWalls.put("LongWall", 0);
        miguelWalls.put("Allied", 0);
        playersNumberWalls.put(Color.ORANGE.toString(), miguelWalls);

        q.addWallToBoard("Temporary", 3, 3, "UP");
        q.moveToken("DOWN");
        assertEquals(playersNumberWalls, q.numberWalls());

    }

    // 12/05/2024
    @Test
    public void shouldWin() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        try {
            q.moveToken("DOWN");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        assertEquals(q.getWinner().getName(), "Miguel");
    }

    @Test
    public void shouldNotLetPlayAfterWin() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        try {
            q.moveToken("DOWN");
            fail("SHOULD WIN");
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

    }

    @Test
    public void shouldBeAbleToConsultAfterWin() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        try {
            q.moveToken("DOWN");
            fail("SHOULD WIN");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        try {
            q.moveToken("UP");
            fail("SHOULD NOT BE ABLE TO PLAY AFTER IT IS OVER");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        assertArrayEquals(new String[] { "Miguel", "Daniel" }, q.getNames());
        assertEquals(Color.ORANGE.toString(), q.getColor("Miguel"));
        assertEquals(Color.BLUE.toString(), q.getColor("Daniel"));
    }

    @Test
    public void shouldBeAbleToSaveAfterWin() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        

        File copy = new File("./quoridor.txt");
        
        q.saveFile(copy);
        q.resetSingleton();
        q = null;
        assertEquals(q, null);
        q = QuoriPOOB.getQuoriPOOB();
        q.setQuoridor(QuoriPOOB.openFile(copy));
        assertArrayEquals(new String[] { "Daniel", "Miguel" }, q.getNames());
        assertEquals(Color.ORANGE.toString(), q.getColor("Miguel"));
        assertEquals(Color.BLUE.toString(), q.getColor("Daniel"));
    }

    @Test
    public void shouldResetQuoriPOOB() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("DOWN");

        q.resetSingleton();
        q = null;
        assertEquals(q, null);
        q = QuoriPOOB.getQuoriPOOB();
        assertEquals(q.getBoard(), null);
        assertEquals(q.getWinner(), null);
        for (String s : q.getNames()) {
            assertEquals(s, null);
        }
        for (HashMap<String, Integer> walls : q.numberWalls().values()) {
            assertEquals(walls, null);
        }
    }

    @Test
    public void shouldNotWinIfWallAhead() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.ORANGE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.moveToken("UP");
        q.moveToken("DOWN");
        q.addWallToBoard("Temporary", 0, 2, "UP");
        q.moveToken("UP");
        q.moveToken("DOWN");
        assertArrayEquals(new String[] { "Daniel", "Miguel" }, q.getNames());
        assertEquals(Color.ORANGE.toString(), q.getColor("Miguel"));
        assertEquals(Color.BLUE.toString(), q.getColor("Daniel"));
        assertEquals(q.getWinner(), null);
    }

    @Test
    public void shouldPlayWithMachineChangingPosition() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerMachine(Color.ORANGE.toString(), "domain.Beginner");
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        // Daniel moves
        int pasx = 4, pasy = 2;
        int x = pasx, y = pasy;
        q.moveToken("DOWN");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((q.getBoard().getMatrixBoard()[i][j].getToken() != null)
                        && q.getBoard().getMatrixBoard()[i][j].getToken().getColor().equals(Color.orange))
                    pasx = x;
                pasy = y;
                x = i;
                y = j;
            }
        }
        assertFalse(pasx == x && pasy == y);
        try{
            q.moveToken("DOWN");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if ((q.getBoard().getMatrixBoard()[i][j].getToken() != null)
                            && q.getBoard().getMatrixBoard()[i][j].getToken().getColor().equals(Color.orange))
                        pasx = x;
                    pasy = y;
                    x = i;
                    y = j;
                }
            }
        assertFalse(pasx == x && pasy == y);
        } catch (QuoriPOOBException e) {
            assertTrue(QuoriPOOBException.FORWARD_WALL.equals(e.getMessage())||
            QuoriPOOBException.GAME_OVER("Daniel").equals(e.getMessage()));
        }
        
        try {
            q.moveToken("DOWN");
        } catch (QuoriPOOBException e) {
            assertTrue(QuoriPOOBException.FORWARD_WALL.equals(e.getMessage())||
            QuoriPOOBException.GAME_OVER("Daniel").equals(e.getMessage()));
        }
    }

    @Test
    public void probarGrafo() throws QuoriPOOBException {
        Grafo grafo = new Grafo(15); // Crear un grafo con 5 nodos

        // Agregar nodos con sus objetos asociados 


        grafo.addNode(0, "A");
        grafo.addNode(1, "B");
        grafo.addNode(2, "C");
        grafo.addNode(3, "D");
        grafo.addNode(4, "E");
        grafo.addNode(5, "F");
        grafo.addNode(6, "G");
        grafo.addNode(7, "H");
        grafo.addNode(8, "I");
        grafo.addNode(9, "J");
        grafo.addNode(10, "K");
        grafo.addNode(11, "L");
        grafo.addNode(12, "M");
        grafo.addNode(13, "N");
        grafo.addNode(14, "P");

        // Agregar aristas con sus pesos
        grafo.addVertex(0, 1, 8);

        grafo.addVertex(2, 5, 9);
        grafo.addVertex(2, 6, 11);

        grafo.addVertex(3, 4, 9);
        grafo.addVertex(3, 7, 6);

        grafo.addVertex(4, 8, 8);
        grafo.addVertex(4, 9, 5);
        grafo.addVertex(4, 5, 3);

        grafo.addVertex(5, 10, 8);
        grafo.addVertex(5, 6, 1);

        grafo.addVertex(6, 10, 8);
        grafo.addVertex(6, 11, 7);

        grafo.addVertex(7, 8, 2);
        grafo.addVertex(7, 12, 7);

        grafo.addVertex(8, 9, 10);
        grafo.addVertex(8, 12, 6);

        grafo.addVertex(9, 13, 9);
        grafo.addVertex(9, 10, 6);

        grafo.addVertex(10, 14, 7);
        grafo.addVertex(10, 11, 5);

        grafo.addVertex(11, 14, 6);

        grafo.addVertex(12, 13, 2);

        grafo.addVertex(13, 14, 2);
        grafo.addVertex(14, 13, 12);
        // Encontrar el camino más corto entre los nodos 5 y 13 ()
        try{
            grafo.shortestWay(5, 13);
        } catch(QuoriPOOBException e){
            assertEquals(QuoriPOOBException.IMPPOSSIBLE_TO_REACH,e.getMessage());
        }

    }

    @Test
    public void shouldWinIfItsPosssible() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerMachine(Color.ORANGE.toString(), "domain.Intermediate");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        // Daniel moves
        q.moveToken("LEFT");

        q.moveToken("LEFT");

        q.moveToken("RIGHT");
        q.moveToken("LEFT");
        q.moveToken("RIGHT");
        q.moveToken("LEFT");
        q.moveToken("RIGHT");
        try {
            q.moveToken("LEFT");
            fail("SHOULD WIN");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Machine"), e.getMessage());
        }
        try {
            q.moveToken("LEFT");
            fail("SHOULD NOT LET PLAYER PLAY AFTER THE MACHINE WON");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Machine"), e.getMessage());
        }
        
    }

    @Test
    public void shouldWinIfItsPosssibleWithWallAhead() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerMachine(Color.ORANGE.toString(), "domain.Intermediate");
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        // Daniel moves
        q.addWallToBoard("NormalWall", 4, 2, "UP");
        q.moveToken("RIGHT");
        q.moveToken("DOWN");

        q.moveToken("DOWN");

        try {
            q.moveToken("DOWN");

        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Machine"), e.getMessage());
        }

    }

    @Test
    public void shouldWinIfItsPosssibleWithWallLEFT() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerMachine(Color.ORANGE.toString(), "domain.Intermediate");
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        // Daniel moves
        q.addWallToBoard("NormalWall", 3, 2, "RIGHT");
        q.moveToken("RIGHT");
        q.moveToken("LEFT");

        try {
            q.moveToken("RIGHT");

        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Machine"), e.getMessage());
        }

    }

    @Test
    public void shouldWinIfItsPosssibleWithWallright() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerMachine(Color.ORANGE.toString(), "domain.Intermediate");
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        // Daniel moves
        q.addWallToBoard("Allied", 3, 2, "RIGHT");
        q.moveToken("RIGHT");
        q.moveToken("LEFT");

        try {
            q.moveToken("RIGHT");

        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Machine"), e.getMessage());
        }

    }

    @Test
    public void shouldWinIfItsPosssibleWithSomeWallsBlockingOtherPlayer1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerMachine(Color.ORANGE.toString(), "domain.Intermediate");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createBoard(5, noSpecialSquares);
        q.addWalls(3, 2, 0, 1);
        q.addWallToBoard("NormalWall", 1, 2, "UP");
        q.addWallToBoard("NormalWall", 0, 2, "LEFT");

        q.moveToken("RIGHT");
        q.moveToken("LEFT");

        try {
            q.moveToken("RIGHT");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Machine"), e.getMessage());
        }

    }

    // Test de Squares
    @Test
    public void shouldAddSpecialSquares() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setMode("domain.NormalMode");
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Daniel", Color.BLACK.toString());
        HashMap<String, int[][]> squares = new HashMap<>();
        int[][] teleporterArray = new int[][] { { 2, 2 } };
        squares.put("domain.Teleporter", teleporterArray);
        q.createBoard(5, squares);
        q.moveToken("UP");
        q.moveToken("RIGHT");
        q.moveToken("UP");
        q.moveToken("LEFT");
        q.moveToken("UPRIGHT");
        q.moveToken("DOWN");
        assertEquals(q.getBoard().getMatrixBoard()[1][3].getToken().getColor(), Color.BLACK.toString());
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getClass().getSimpleName(), "Teleporter");
    }

    @Test
    public void shouldNotJumpTheSpecialSquareIfOutOfRange() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Daniel", Color.BLACK.toString());
        HashMap<String, int[][]> squares = new HashMap<>();
        int[][] teleporterArray = new int[][] { { 3, 0 } };
        squares.put("domain.Teleporter", teleporterArray);
        q.createBoard(5, squares);

        q.moveToken("LEFT");
        q.moveToken("LEFT");

        q.moveToken("LEFT");
        q.moveToken("RIGHT");

        q.moveToken("UP");
        q.moveToken("LEFT");
        try {
            q.moveToken("UPLEFT");
            fail();
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.TOKEN_OUT_OF_RANGE, e.getMessage());
        }
        assertEquals(q.getBoard().getMatrixBoard()[3][0].getClass().getSimpleName(), "Teleporter");
        assertEquals(q.getBoard().getMatrixBoard()[3][0].getToken().getColor(), Color.BLACK.toString());

    }

    @Test
    public void shouldAddDoubleTurnSquare() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        HashMap<String, int[][]> squares = new HashMap<>();
        int[][] turnArray = new int[][] { { 3, 2 } };
        squares.put("domain.DoubleTurn", turnArray);
        q.createBoard(5, squares);

        q.moveToken("UP"); 
        q.moveToken("LEFT");
        assertTrue(q.getBoard().getMatrixBoard()[3][1].getToken().getColor().equals(Color.BLACK.toString()));
        assertTrue(q.getBoard().getMatrixBoard()[0][2].getToken().getColor().equals(Color.BLUE.toString()));
        q.moveToken("DOWN"); 
        assertTrue(q.getBoard().getMatrixBoard()[1][2].getToken().getColor().equals(Color.BLUE.toString()));
        assertTrue(q.getBoard().getMatrixBoard()[3][1].getToken().getColor().equals(Color.BLACK.toString()));
        q.moveToken("UP");

        q.moveToken("DOWN");
        assertEquals(q.getBoard().getMatrixBoard()[2][1].getToken().getColor(), Color.BLACK.toString());
        q.moveToken("UP");
        q.moveToken("DOWN");


        try {
            q.moveToken("DOWN");
            fail("SHOUL WIN");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Daniel"), e.getMessage());
        }
        assertEquals(q.getBoard().getMatrixBoard()[3][2].getClass().getSimpleName(), "DoubleTurn");
        assertEquals(q.getBoard().getMatrixBoard()[4][2].getToken().getColor(), Color.BLUE.toString());
    }

    @Test
    public void shouldJumpReturnSquare() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Daniel", Color.BLACK.toString());
        HashMap<String, int[][]> squares = new HashMap<>();
        int[][] turnArray = new int[][] { { 3, 0 } };
        squares.put("domain.Return", turnArray);
        q.createBoard(5, squares);
        q.createBoard(5, squares);

        q.moveToken("LEFT");
        q.moveToken("LEFT");

        q.moveToken("LEFT");
        q.moveToken("RIGHT");

        q.moveToken("UP");
        q.moveToken("LEFT");
        assertTrue(q.getBoard().getMatrixBoard()[4][1].getToken().getColor().equals(Color.BLACK.toString()));

        q.moveToken("UP");
        q.moveToken("RIGHT");
        q.moveToken("LEFT");
        q.moveToken("LEFT");

        assertEquals(q.getBoard().getMatrixBoard()[3][0].getClass().getSimpleName(), "Return");
        assertEquals(q.getBoard().getMatrixBoard()[4][1].getToken().getColor(), Color.BLACK.toString());

    }

    @Test
    public void shouldJumpPlayerDiagonalUPRIGHT() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        HashMap<String, int[][]> squares = new HashMap<>();
        int[][] teleporterArray = new int[][] { { 2, 0 } };
        squares.put("domain.Teleporter", teleporterArray);
        q.createBoard(3, squares);
        q.addWalls(3, 1, 0, 0);
        // Daniel moves
        q.moveToken("LEFT");
        q.moveToken("DOWN");
        q.addWallToBoard("NormalWall", 0, 0, "LEFT");
        q.addWallToBoard("NormalWall", 0, 0, "UP");

        
        try {
            q.moveToken("UPRIGHT");
            fail();
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        assertEquals(q.getBoard().getMatrixBoard()[2][0].getClass().getSimpleName(), "Teleporter");
        assertEquals(q.getBoard().getMatrixBoard()[0][2].getToken().getColor(), Color.BLACK.toString());
    }

    @Test
    public void shouldJumpPlayerDiagonalUPLEFT() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        HashMap<String, int[][]> squares = new HashMap<>();
        int[][] teleporterArray = new int[][] { { 2, 2 } };
        squares.put("domain.Teleporter", teleporterArray);
        q.createBoard(3, squares);
        q.addWalls(3, 1, 0, 0);
        q.moveToken("RIGHT");
        q.moveToken("DOWN");
        q.addWallToBoard("NormalWall", 0, 0, "LEFT");
        q.addWallToBoard("NormalWall", 0, 2, "RIGHT");;
        
        try {
            q.moveToken("UPLEFT");
            fail();
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Miguel"), e.getMessage());
        }
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getClass().getSimpleName(), "Teleporter");
        assertEquals(q.getBoard().getMatrixBoard()[0][0].getToken().getColor(), Color.BLACK.toString());
    }
    @Test
    public void shouldJumpPlayerDiagonalDOWNRIGHT() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        HashMap<String, int[][]> squares = new HashMap<>();
        int[][] teleporterArray = new int[][] { { 0, 0 } };
        squares.put("domain.Teleporter", teleporterArray);
        q.createBoard(3, squares);
        q.addWalls(3, 1, 0, 0);
        // Daniel moves
        q.moveToken("UP");
        q.moveToken("LEFT");
        q.addWallToBoard("NormalWall", 0, 0, "UP");
        
        try {
            q.moveToken("DOWNRIGHT");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Daniel"), e.getMessage());
        }
        assertEquals(q.getBoard().getMatrixBoard()[0][0].getClass().getSimpleName(), "Teleporter");
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getToken().getColor(), Color.BLUE.toString());
    }
    @Test
    public void shouldJumpPlayerDiagonalDOWNLEFT() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        HashMap<String, int[][]> squares = new HashMap<>();
        int[][] teleporterArray = new int[][] { { 0, 2 } };
        squares.put("domain.Teleporter", teleporterArray);
        q.createBoard(3, squares);
        q.addWalls(3, 1, 0, 0);
        q.moveToken("UP");
        q.moveToken("RIGHT");
        q.addWallToBoard("NormalWall", 0, 0, "UP");
        
        try {
            q.moveToken("DOWNLEFT");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.GAME_OVER("Daniel"), e.getMessage());
        }
        assertEquals(q.getBoard().getMatrixBoard()[0][2].getClass().getSimpleName(), "Teleporter");
        assertEquals(q.getBoard().getMatrixBoard()[2][0].getToken().getColor(), Color.BLUE.toString());
    }
    @Test
    public void shouldDeleteTemporary() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.setMode("domain.NormalMode");
        q.createPlayerHuman("Daniel", Color.BLUE.toString());
        q.createPlayerHuman("Miguel", Color.BLACK.toString());
        q.createBoard(3, noSpecialSquares);
        q.addWalls(3, 1, 0, 0);
        // Daniel moves
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                assertNull(q.getMatrixBoard()[i][j].getWallDown());
                assertNull(q.getMatrixBoard()[i][j].getWallLeft());
                assertNull(q.getMatrixBoard()[i][j].getWallUp());
                assertNull(q.getMatrixBoard()[i][j].getWallRight());
            }
        }
        q.addWallToBoard("Temporary", 1, 1, "UP");
        assertNotNull(q.getMatrixBoard()[0][1].getWallDown());
        assertNotNull(q.getMatrixBoard()[1][1].getWallUp());
        q.moveToken("LEFT");
        q.moveToken("LEFT");
        q.moveToken("RIGHT");
        q.moveToken("RIGHT");
        q.moveToken("LEFT");
        q.moveToken("LEFT");
        q.moveToken("RIGHT");
        assertNotNull(q.getMatrixBoard()[0][1].getWallDown());
        assertNotNull(q.getMatrixBoard()[1][1].getWallUp());
        q.moveToken("RIGHT");
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                assertNull(q.getMatrixBoard()[i][j].getWallDown());
                assertNull(q.getMatrixBoard()[i][j].getWallLeft());
                assertNull(q.getMatrixBoard()[i][j].getWallUp());
                assertNull(q.getMatrixBoard()[i][j].getWallRight());
            }
        }
    }

    //@Test
    public void shouldPlayMachine() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.setMode("domain.NormalMode");
        q.createPlayerMachine(Color.GREEN.toString(), "domain.Intermediate");
        q.createPlayerHuman("Player 1", Color.BLUE.toString());
        q.createBoard(8, noSpecialSquares);
        q.addWalls(9, 0, 0, 0);
        q.moveToken("RIGHT");
        q.addWallToBoard("NormalWall", 1, 3, "DOWN");
        q.addWallToBoard("NormalWall", 1, 1, "DOWN");
        q.addWallToBoard("NormalWall", 0, 0, "RIGHT");
        assertNotNull(q.getMatrixBoard()[7][3].getWallUp());
        assertNotNull(q.getMatrixBoard()[7][4].getWallUp());
        q.addWallToBoard("NormalWall", 7, 1, "UP");
        assertNotNull(q.getMatrixBoard()[7][1].getWallUp());
        assertNotNull(q.getMatrixBoard()[7][2].getWallUp());
        assertNotNull(q.getMatrixBoard()[7][3].getWallRight());
        assertNotNull(q.getMatrixBoard()[6][3].getWallRight());
        q.moveToken("LEFT");
        assertNotNull(q.getMatrixBoard()[7][1].getWallUp());
        assertNotNull(q.getMatrixBoard()[7][2].getWallUp());
        
        
    }

    
}