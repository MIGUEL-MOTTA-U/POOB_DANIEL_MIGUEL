package test;

import domain.*;
import static org.junit.Assert.*;
import java.awt.Color;
import java.io.File;
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
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerMachine(Color.ORANGE, "domain.Beginner");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.MODE_UNDEFINED, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateModeGameUndefined2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        try {
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerHuman("Player 2", Color.GRAY);
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.MODE_UNDEFINED, e.getMessage());
        }
    }

    @Test
    public void shouldCreateOnePlayerMode1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.ORANGE, "domain.Beginner");
        assertArrayEquals(new String[] { "Player 1", "Machine" }, q.getNames());
    }

    @Test
    public void shouldCreateOnePlayerMode2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerMachine(Color.RED, "domain.Advanced");
        q.createPlayerHuman("Player 2", Color.BLUE);
        assertArrayEquals(new String[] { "Machine", "Player 2" }, q.getNames());
    }

    @Test
    public void shouldNotCreateOnePlayerMode1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        try {
            q.createPlayerHuman("Player 1", Color.BLACK);
            q.createPlayerHuman("Player 2", Color.BLUE);
            fail("SHOULD NOT CREATE TWO HUMAN PLAYERS IN ONE PLAYER MODE");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.ONE_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateOnePlayerMode2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        try {
            q.createPlayerMachine(Color.BLUE, "domain.Advanced");
            q.createPlayerMachine(Color.RED, "domain.Beginner");
            fail("SHOULD NOT CREATE TWO MACHINE PLAYERS IN ONE PLAYER MODE");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.ONE_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldCreateTwoPlayersMode() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 2", Color.GREEN);
        assertArrayEquals(new String[] { "Player 1", "Player 2" }, q.getNames());
    }

    @Test
    public void shouldNotCreateTwoPlayersMode1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        try {
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerMachine(Color.RED, "domain.Beginner");
            fail("SHOULD NOT CREATE ONE MACHINE PLAYER AND ONE HUMAN PLAYER IN TWO PLAYERS MODE");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.TWO_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateTwoPlayersMode2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        try {
            q.createPlayerMachine(Color.RED, "domain.Beginner");
            q.createPlayerHuman("Player 1", Color.BLUE);
            fail("SHOULD NOT CREATE ONE MACHINE PLAYER AND ONE HUMAN PLAYER IN TWO PLAYERS MODE");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.TWO_PLAYER_MODE, e.getMessage());
        }
    }

    @Test
    public void shouldCreatePlayerHuman1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 2", Color.ORANGE);
        assertArrayEquals(new String[] { "Player 1", "Player 2" }, q.getNames());
    }

    @Test
    public void shouldCreateHuman2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 1", Color.ORANGE);
        assertArrayEquals(new String[] { "Player 1", "Player 1" }, q.getNames());
    }

    @Test
    public void shouldNotCreateHuman1() {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        try {
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerHuman("Player 2", Color.BLUE);
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
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerHuman("Player 1", Color.ORANGE);
            q.createPlayerHuman("Player 3", Color.CYAN);
            fail("SHOULD NOT CREATE MORE THAN TWO PLAYERS");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.WRONG_NUMBER_PLAYERS, e.getMessage());
        }
    }

    @Test
    public void shouldCreateHumanAndMachine1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.ORANGE, "domain.Beginner");
        assertArrayEquals(new String[] { "Player 1", "Machine" }, q.getNames());
        assertArrayEquals(new String[] { "Player 1", "Machine" }, q.getNames());
    }

    @Test
    public void shouldCreateHumanAndMachine2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.ORANGE, "domain.Intermediate");
        assertArrayEquals(new String[] { "Player 1", "Machine" }, q.getNames());
    }

    @Test
    public void shouldCreateHumanAndMachine3() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.ORANGE, "domain.Advanced");
        assertArrayEquals(new String[] { "Player 1", "Machine" }, q.getNames());
    }

    @Test
    public void shouldCreateHumanAndMachine4() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.ORANGE, "NOT A MACHINE");
        q.getNames();
        assertArrayEquals(new String[] { "Player 1", null }, q.getNames());
    }

    @Test
    public void shouldNotCreateHumanAndMachine1() {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        try {
            q.createPlayerMachine(Color.BLUE, "domain.Advanced");
            q.createPlayerMachine(Color.ORANGE, "domain.Advanced");
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
            q.createPlayerMachine(Color.BLUE, "domain.Intermediate");
            q.createPlayerMachine(Color.ORANGE, "domain.Advanced");
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
            q.createPlayerMachine(Color.BLUE, "domain.Beginner");
            q.createPlayerMachine(Color.ORANGE, "domain.Advanced");
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
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerHuman("Player 2", Color.ORANGE);
            q.createPlayerMachine(Color.ORANGE, "domain.Advanced");
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
            q.createPlayerHuman("Player 1", Color.BLUE);
            q.createPlayerMachine(Color.BLUE, "domain.Advanced");
            fail("SHOULD NOT LET PLAY TWO MACHINES");
        } catch (QuoriPOOBException e) {
            assertEquals(QuoriPOOBException.SAME_PLAYER_COLOR, e.getMessage());
        }
    }

    @Test
    public void shouldCreateBoard1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Teleporter", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldCreateBoard2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldCreateBoard3() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.DoubleTurn", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldCreateBoard4() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.NormalSquare", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldCreateBoard5() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        q.createBoard(4, null);
        assertEquals(4, q.getBoard().getMatrixBoard().length);
    }

    @Test
    public void shouldVerifyCreatedBoard() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
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
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
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
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Irregular", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(2, specialQuares);
    }

    @Test
    public void shouldNotCreateBoard3() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
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
        q.createPlayerHuman("Player 1", Color.BLUE);
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
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 2", Color.RED);

        q.createBoard(17, null);

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
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");

        HashMap<String, int[][]> specialSquares = new HashMap<>();
        specialSquares.put("domain.Teleporter", new int[][] { { 0, 0 }, { 2, 3 }, { 7, 1 } });
        specialSquares.put("domain.Return", new int[][] { { 1, 1 }, { 3, 1 } });
        specialSquares.put("domain.DoubleTurn", new int[][] { { 2, 2 }, { 5, 5 }, { 1, 3 }, { 6, 2 } });

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
    public void shouldAddWalls1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        q.addWalls(0, 0, 0, 5);
    }

    @Test
    public void shouldAddWalls2() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
        HashMap<String, int[][]> specialQuares = new HashMap<>();
        specialQuares.put("domain.Return", new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });
        q.createBoard(4, specialQuares);
        q.addWalls(1, 2, 1, 1);
    }

    @Test
    public void shouldNotAddWalls1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setOnePlayer();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerMachine(Color.GREEN, "domain.Advanced");
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
    public void shouldNotAddWalls3() throws QuoriPOOBException {
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
    public void shouldNotAddWallsToBoard1() throws QuoriPOOBException {
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 2", Color.ORANGE);

        q.createBoard(10, null);
        q.addWalls(1, 4, 2, 4);
        // @Test
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
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 2", Color.ORANGE);

        q.createBoard(10, null);
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
        q.createPlayerHuman("Player 1", Color.BLUE);
        q.createPlayerHuman("Player 2", Color.ORANGE);

        q.createBoard(10, null);
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
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.BLACK);
        q.createBoard(4, null);
        assertTrue(q.getBoard().getSquare(0, 1).getToken() != null);
        q.moveToken("LEFT");
        assertTrue(q.getBoard().getSquare(0, 0).getToken() != null);
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
    public void shouldNotMoveOrthogonallyAPawn1() throws QuoriPOOBException {
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
    public void shouldNotMoveOrthogonallyAPawn2() throws QuoriPOOBException {
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

    @Test
    public void shouldPlaceANormalBarrier1()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(6, 0,0,0);
        q.addWallToBoard("NormalWall", 2, 2, "UP");

        assertTrue(q.getBoard().getMatrixBoard()[2][2].getWallUp()!=null);
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallUp(),q.getBoard().getMatrixBoard()[2][3].getWallUp());        
        assertEquals(q.getBoard().getMatrixBoard()[1][2].getWallDown(),q.getBoard().getMatrixBoard()[1][3].getWallDown());        
        
        q.addWallToBoard("NormalWall", 2, 2, "LEFT");
        assertTrue(q.getBoard().getMatrixBoard()[2][1].getWallRight()!=null);
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallLeft(),q.getBoard().getMatrixBoard()[3][2].getWallLeft());        
        assertEquals(q.getBoard().getMatrixBoard()[2][1].getWallRight(),q.getBoard().getMatrixBoard()[3][1].getWallRight());        
        
    }

    @Test
    public void shouldPlaceANormalBarrier2()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(6, 0,0,0);
        q.addWallToBoard("NormalWall", 2, 2, "DOWN");

        assertTrue(q.getBoard().getMatrixBoard()[2][2].getWallDown()!=null);
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallDown(),q.getBoard().getMatrixBoard()[2][3].getWallDown());        
        assertEquals(q.getBoard().getMatrixBoard()[3][2].getWallUp(),q.getBoard().getMatrixBoard()[3][3].getWallUp());        
        
        q.addWallToBoard("NormalWall", 2, 2, "RIGHT");
        assertTrue(q.getBoard().getMatrixBoard()[2][2].getWallRight()!=null);
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallRight(),q.getBoard().getMatrixBoard()[3][2].getWallRight());        
        assertEquals(q.getBoard().getMatrixBoard()[2][3].getWallLeft(),q.getBoard().getMatrixBoard()[3][3].getWallLeft());        
        
    }

    @Test
    public void shouldPlaceANormalBarrier3()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        q.addWalls(6, 0,0,0);
        q.addWallToBoard("NormalWall", 2, 2, "DOWN");

        assertTrue(q.getBoard().getMatrixBoard()[2][2].getWallDown()!=null);
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallDown(),q.getBoard().getMatrixBoard()[2][3].getWallDown());        
        assertEquals(q.getBoard().getMatrixBoard()[3][2].getWallUp(),q.getBoard().getMatrixBoard()[3][3].getWallUp());        
        
        q.addWallToBoard("NormalWall", 2, 2, "RIGHT");
        assertTrue(q.getBoard().getMatrixBoard()[2][2].getWallRight()!=null);
        assertEquals(q.getBoard().getMatrixBoard()[2][2].getWallRight(),q.getBoard().getMatrixBoard()[3][2].getWallRight());        
        assertEquals(q.getBoard().getMatrixBoard()[2][3].getWallLeft(),q.getBoard().getMatrixBoard()[3][3].getWallLeft());        
        
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
    public void shouldNotMoveAPawnOverANonAlliedBarrier3()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        Token danielToken = q.getBoard().getMatrixBoard()[0][2].getToken();
        q.addWalls(3, 3, 0, 0);
        q.moveToken("DOWN");
        q.addWallToBoard("Temporary", 1, 2, "DOWN");
        try {
            q.moveToken("DOWN");
            fail("SHOULD NOT JUMP A NONE ALLIED BARRIER");
        } catch (QuoriPOOBException e){
            assertEquals(QuoriPOOBException.FORWARD_WALL, e.getMessage());
        }
        assertEquals(danielToken, q.getBoard().getMatrixBoard()[1][2].getToken());
    }

    @Test
    public void shouldNotMoveAPawnOverANonAlliedBarrier4()throws QuoriPOOBException{
        QuoriPOOB q = QuoriPOOB.getQuoriPOOB();
        q.setTwoPlayers();
        q.createPlayerHuman("Daniel", Color.BLUE);
        q.createPlayerHuman("Miguel", Color.ORANGE);
        q.createBoard(5, null);
        Token danielToken = q.getBoard().getMatrixBoard()[0][2].getToken();
        q.addWalls(3, 3, 0, 0);
        q.moveToken("DOWN");
        q.addWallToBoard("Temporary", 1, 2, "RIGHT");
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
        assertEquals(playersNumberWalls,q.numberWalls());


    }

    //@Test En construccion todavia
    public void shouldBeEquals()throws QuoriPOOBException{
        QuoriPOOB q1 = QuoriPOOB.getQuoriPOOB();
        q1.setTwoPlayers();
        q1.createPlayerHuman("Daniel", Color.BLUE);
        q1.createPlayerHuman("Miguel", Color.ORANGE);
        q1.createBoard(5, null);
        q1.addWalls(3, 2, 0, 1);
        
        Board tablero = q1.getBoard();
        //Player miguel = q1.getCurrentPlayer();

        File copy = new File("./test/guardado.dat");
        q1.saveFile(copy);
        q1 = null;
        QuoriPOOB q2 = QuoriPOOB.openFile(copy);
        // Va a fallar porque el equals lo que hace es comprobar la direccion en memoria
        assertEquals(tablero,q2.getBoard());
        //assertEquals(miguel,q2.getCurrentPlayer());
        
        assertTrue(null == q1);

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
        for(HashMap<String, Integer> walls:q.numberWalls().values()){
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