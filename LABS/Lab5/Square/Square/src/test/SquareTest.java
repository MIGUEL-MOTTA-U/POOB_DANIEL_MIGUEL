package test;

import domain.*;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SquareTest {
    public SquareTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldCreateSquareWithRandomTokensAndHollows() throws SquareException {
        Square s1 = new Square(16, 11);
        HashMap<String, int[]> hollowsS = s1.getHollows();
        HashMap<String, int[]> tokensS = s1.getTokens();
        assertEquals(tokensS.size(), hollowsS.size());
        Square s2 = new Square(2, 1);
        hollowsS = s2.getHollows();
        tokensS = s2.getTokens();
        assertEquals(tokensS.size(), hollowsS.size());
    }

    @Test
    public void shouldCheckTokensAndHollowsCreated() throws SquareException {
        Square s1 = new Square(10, 9);
        String[][][] board = s1.getBoard();
        int rows = board.length;
        int tokens = 0;
        int hollows = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (board[i][j][0].equals("T"))
                    tokens++;
                if (board[i][j][0].equals("H"))
                    hollows++;
            }
        }
        assertEquals(tokens, hollows);
    }

    @Test
    public void shouldCheckSameColorsOfTokensAndHollows() throws SquareException {
        Square s1 = new Square(6, 5);
        String[][][] board = s1.getBoard();
        int rows = board.length;
        ArrayList<String> tColors = new ArrayList<>(), hColors = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (board[i][j][0].equals("T"))
                    tColors.add(board[i][j][1]);
                if (board[i][j][0].equals("H"))
                    hColors.add(board[i][j][1]);
            }
        }
        Collections.sort(hColors);
        Collections.sort(tColors);
        assertEquals(hColors, tColors);
    }

    @Test
    public void shouldNotCreateSquare() throws SquareException {
        try {
            new Square(17, 17);
            fail("It should not create a Square with more than 16 rows");
        } catch (SquareException e) {
            assertEquals(e.getMessage(), SquareException.LIMIT_TOKENS);
        }
        try {
            new Square(0, 0);
            fail("It should not create a Square with less than 1 row");
        } catch (SquareException e) {
            assertEquals(e.getMessage(), SquareException.WRONG_DIMENSIONS);
        }
    }

    @Test
    public void shouldChangeColor() throws SquareException {
        Square s1 = new Square(5, 1);
        HashMap<String, int[]> tokens = s1.getTokens();
        int[] expected = new int[2];
        for (String color : tokens.keySet()) {
            expected = tokens.get(color);
            s1.changeTokenColor(color, "Rojo Carmesi");
        }
        assertArrayEquals(expected, s1.getTokens().get("Rojo Carmesi"));
    }
    @Test
    public void shouldNotShangeColor1()throws SquareException {
        Square s1 = new Square(5, 3);
        try{
            s1.changeTokenColor("Color inexistente", "");
            fail("Should Fail if the given color does not exist in the Square.");
        } catch (SquareException e){
            assertEquals(e.getMessage(), SquareException.UNKNOWN_TOKEN);
        }
    }
    @Test
    public void shouldNotShangeColor2()throws SquareException {
        Square s1 = new Square(5, 1);
        HashMap<String, int[]> tokens = s1.getTokens();
        String oldColor="";
        for (String color : tokens.keySet()) {
            oldColor=color;
        }
        try{
            s1.changeTokenColor(oldColor,oldColor);
            fail("Should Fail if the given color does not exist in the Square.");
        } catch (SquareException e){
            assertEquals(e.getMessage(), SquareException.TOKEN_EXISTENT);
        }
    }
    
    @Test
    public void shouldMove()throws SquareException{
        Square s1 = new Square(5, 2);
        s1.move("NORTH");
        assertEquals(true, s1.ok());
        s1.move("SOUTH");
        assertEquals(true, s1.ok());
        s1.move("WEST");
        assertEquals(true, s1.ok());
        s1.move("EAST");
        assertEquals(true, s1.ok());
        s1.move("sOuTH");
        assertEquals(true, s1.ok());
    }

    @Test
    public void shouldNotMove1()throws SquareException{
        Square s1 = new Square(5, 2);
        try {
            s1.move("NORT");
        } catch (SquareException e){
            assertEquals(SquareException.WRONG_DIRECTION, e.getMessage());
        }
        assertEquals(false, s1.ok());
    }
    @Test
    public void shouldNotMove2()throws SquareException{
        Square s1 = new Square(12, 8);
        try {
            s1.move("notCorrect");
        } catch (SquareException e){
            assertEquals(SquareException.WRONG_DIRECTION, e.getMessage());
        }
        assertEquals(false, s1.ok());
    }
    @Test
    public void shouldNotMove3()throws SquareException{
        Square s1 = new Square(12, 8);
        try {
            s1.move("");
        } catch (SquareException e){
            assertEquals(SquareException.WRONG_DIRECTION, e.getMessage());
        }
        assertEquals(false, s1.ok());
    }
}