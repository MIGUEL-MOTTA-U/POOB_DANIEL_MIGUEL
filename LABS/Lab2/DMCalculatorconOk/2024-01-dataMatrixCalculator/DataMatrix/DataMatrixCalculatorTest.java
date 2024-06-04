import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The test class DataMatrixCalculatorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DataMatrixCalculatorTest{
    private static DataMatrixCalculator calculator;
    
    @BeforeClass
    public static void beforeClass(){
        calculator = new DataMatrixCalculator();
    }
    
    @Before
    public void before(){
        calculator.assign("a", new String[][] {
            {"TRUE", "a", "24"}, 
            {"hola", "7", "10"}, 
            {"FALSE", "TRUE", "b"}
        });
        calculator.assign("b", new String[][] {
            {"halo", "true", "false"}, 
            {"24", "25.6", "0"}, 
            {"false", "24.4.4", "a"}
        });
        calculator.assign("c", new String[][] {
            {"b", "f", "c"}, 
            {"FALSE", "10", "TRUE"}, 
            {"FALSE", "hola", "z"}
        });
    }

    @Test
    public void shouldRepresentAMatrixAsAString() {
        assertEquals(calculator.toString("a"),"[[TRUE, a, 24], [FALSE, 7, 10], [FALSE, TRUE, b]]");
        assertEquals(calculator.toString("b"),"[[FALSE, TRUE, FALSE], [24, 25.6, 0], [FALSE, FALSE, a]]"); 
        assertEquals(calculator.toString("c"),"[[b, f, c], [FALSE, 10, TRUE], [FALSE, FALSE, z]]");  
        
        assertNotEquals(calculator.toString("a"),"[[TRUE, a, 24], [hola, 7, 10], [FALSE, TRUE, b]]");
        assertNotEquals(calculator.toString("b"),"[[FALSE, TRUE, FALSE], [24, 25.6, 0], [FALSE, 24.4.4, a]]"); 
        assertNotEquals(calculator.toString("c"),"[[b, f, c], [FALSE, 10, true], [FALSE, FALSE, z]]");  
    }   
    
    
    @Test
    public void shouldShowASpecificValue() {
        assertEquals(calculator.consult("a", 0, 1),"a");
        assertEquals(calculator.consult("b", 0, 0),"FALSE");
        assertEquals(calculator.consult("c", 1, 1),"10");
        
        assertNotEquals(calculator.consult("a", 2, 1),"true");
        assertNotEquals(calculator.consult("b", 2, 1),"24.4.4");
        assertNotEquals(calculator.consult("c", 0, 1),"F");
    }
    
    @Test
    public void shouldAddMatrices() {
        calculator.assign("c", "a", '+', "b"); 
        assertEquals(calculator.toString("c"),"[[TRUE, a, FALSE], [FALSE, 32.6, 10.0], [FALSE, TRUE, b]]");
    }
    
    @Test
    public void shouldSubMatrices() {
        calculator.assign("a", "b", '-', "c"); 
        assertEquals(calculator.toString("a"),"[[F, F, F], [FALSE, 15.6, TRUE], [FALSE, FALSE, a]]");
        calculator.assign("a", "c", '-', "b"); 
        assertEquals(calculator.toString("a"),"[[F, F, F], [FALSE, -15.6, TRUE], [FALSE, FALSE, a]]");
    }
    
    @Test
    public void shouldTheStatusOfAOperation() {
        calculator.assign("a", "b", '-', "c"); 
        assertTrue(calculator.ok());
        calculator.assign("a", "c", '-', "b"); 
        assertTrue(calculator.ok());
        
        calculator.assign("a", "b", '-', "f"); 
        assertFalse(calculator.ok());
    }
    
    @Test
    public void shouldShowShapeAndReshape(){
        // assertEquals();
        // calculator.assign("Matrix_1", "[["1","1","1"],["2","2","2"],["3","3","3"]]");
        calculator.assign("MA", new String[][]
        {{"0","1","1"},
        {"2","0","1"},
        {"2","2","0"}});
        calculator.assign("result", new String [][] {{}});
        calculator.assign("result", 's',"MA");
        assertEquals(calculator.toString("result"), "[[3, 3]]");
        calculator.assign("MB", new String[][]
        {{"2","0","1"},
        {"2","2","0"}});
        
        calculator.assign("result", 's',"MB");
        assertEquals(calculator.toString("result"), "[[2, 3]]");
        calculator.assign("result", 'r',"MA");
        assertEquals(calculator.toString("result"), "[[0, 2, 2], [1, 0, 2], [1, 1, 0]]");
        
    }
}
