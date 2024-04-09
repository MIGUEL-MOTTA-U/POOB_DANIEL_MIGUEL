package test;
import domain.*;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ActivityTest{
   
    
    public ActivityTest(){
    }


    @Before
    public void setUp(){    
    }

    @After
    public void tearDown(){
    }
    
 
    @Test
    public void shouldCalculateTheTimeOfAComposedSecuencialActivity(){
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        try {
           assertEquals(45,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }    
    
    @Test
    public void shouldCalculateTheTimeOfAComposedParallelActivity(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, 20));
        c.add(new Simple("POOB", 10, 15));
        try {
           assertEquals(20,c.time());
        } catch (ProjectException e){
            fail("Threw a exception");
        }    
    }  
    
    
    @Test
    public void shouldThrowExceptionIfComposedIsEmpty(){
        Composed c = new Composed("IS-BASICA", 100 , true);
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.COMPOSED_EMPTY,e.getMessage());
        }    
    }    
    
    
   @Test
    public void shouldThrowExceptionIfThereIsErrorInTime(){
        Composed c = new Composed("IS-BASICA", 100 , false );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, -20));
        c.add(new Simple("POOB", 10, 30));
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_ERROR,e.getMessage());
        }    
    }     
    
   @Test
    public void shouldThrowExceptionIfTimeIsNotKnown(){
        Composed c = new Composed("IS-BASICA", 100 , true );
        c.add(new Simple("AYED", 10, 10));
        c.add(new Simple("MBDA", 10, null));
        c.add(new Simple("POOB", 10, 30));
        try { 
           int time=c.time();
           fail("Did not throw exception");
        } catch (ProjectException e) {
            assertEquals(ProjectException.TIME_EMPTY,e.getMessage());
        }    
    }  
    
}