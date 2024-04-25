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

    public SquareTest(){
    }
    @Before
    public void setUp(){
    }
    @After
    public void tearDown(){
    }
    @Test
    public void shouldCreateSquareWithRandomTokensAndHollows()throws SquareException{
        Square s1 = new  Square(16);
        HashMap<String, int[]> hollowsS= s1.getHollows();
        HashMap<String, int[]> tokensS=s1.getTokens();
        assertEquals(tokensS.size(),hollowsS.size());
        Square s2 = new Square(1);
        hollowsS= s2.getHollows();
        tokensS=s2.getTokens();
        assertEquals(tokensS.size(),hollowsS.size());
    }

    @Test
    public void shouldCheckTokensAndHollowsCreated()throws SquareException{
        Square s1 = new  Square(10);
        String[][][] board = s1.getBoard();
        int rows = board.length;
        int tokens = 0;
        int hollows = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<rows;j++){
                if(board[i][j][0].equals("T")) tokens++;
                if(board[i][j][0].equals("H")) hollows++;
            }
        }
        assertEquals(tokens, hollows);
    }
    
    @Test
    public void shouldCheckSameColorsOfTokensAndHollows()throws SquareException{
        Square s1 = new  Square(6);
        String[][][] board = s1.getBoard();
        int rows = board.length;
        ArrayList<String> tColors = new ArrayList<>(), hColors = new ArrayList<>();
        for(int i=0;i<rows;i++){
            for(int j=0;j<rows;j++){
                if(board[i][j][0].equals("T")) tColors.add(board[i][j][1]);
                if(board[i][j][0].equals("H")) hColors.add(board[i][j][1]);
            }
        }
        Collections.sort(hColors);
        Collections.sort(tColors);
        assertEquals(hColors,tColors);
    }

    @Test
    public void shouldNotCreateSquare()throws SquareException{
        try{
            Square s1 = new  Square(17);
            fail("It should not create a Square with more than 16 rows");
        } catch (SquareException e){
            assertEquals(e.getMessage(), SquareException.WRONG_DIMENSIONS);
        }
        try{
            Square s2 = new Square(0);
            fail("It should not create a Square with less than 1 row");
        } catch(SquareException e){
            assertEquals(e.getMessage(), SquareException.WRONG_DIMENSIONS);
        }
    }



}