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
    
    
    @Test
    public void shouldThrowExceptionIfTimeIsEmpty(){
       Simple s = new Simple("IS-BASICA", 100 , null);
       try { 
          int time=s.time();
          fail("Did not throw exception (It was suposed to throw.)");
       } catch (ProjectException e) {
           assertEquals(ProjectException.TIME_EMPTY,e.getMessage());
       }    
    }
    
    @Test
    public void shouldThrowExceptionIfTimeError(){
       Simple s = new Simple("IS-BASICA", 100 , 0);
       Simple a = new Simple("AYED", 15 , 0);
       try { 
          int time=s.time();
          fail("Did not throw exception (It was suposed to throw.)");
       } catch (ProjectException e) {
           assertEquals(ProjectException.TIME_ERROR,e.getMessage());
       }
       try { 
          int time=a.time();
          fail("Did not throw exception (It was suposed to throw.)");
       } catch (ProjectException e) {
           assertEquals(ProjectException.TIME_ERROR,e.getMessage());
       }    
    } 
    
    @Test
    public void shouldChangeDefaultValues(){
       Composed c = new Composed("IS-BASICA", 100 , true );
       c.add(new Simple("AYED", 10, 10));
       c.add(new Simple("MBDA", 10, null));
       c.add(new Simple("POOB", 10, 30));
       try{
           int time=c.time(1,5,15);
           assertEquals(15,time);
       } catch (ProjectException e){
           fail("Didn't replaced the values.");
       }
       Composed f = new Composed("IS-ADVANCED", 100 , false );
       f.add(new Simple("CNVS", 10, 10));
       f.add(new Simple("RECO", 10, null));
       f.add(new Simple("TSOR", 10, 33));
       try{
           int timef=f.time(0,30,15);
           assertEquals(55,timef); // 55 = 10+30+15
       } catch (ProjectException e){
           fail("Didn't replaced the values.");
       }
    }
    
    @Test
    public void shouldRecalculateTime(){
       Composed c = new Composed("IS-BASICA", 100 , true );
       c.add(new Simple("AYED", 10, 10));
       c.add(new Simple("MBDA", 10, null)); // error
       c.add(new Simple("POOB", 10, 30)); // error
       c.add(new Simple("ACSO", 10, -30)); // error
       c.add(new Simple("MATD", 10, 0)); // error
       c.add(new Simple("AFSA", 10, 15));
       int expected =(int) (25/2);
       try{
           assertEquals(expected, c.time('A'));
       } catch (ProjectException e){
           fail("Didn't replaced the values.");
       }
       expected = 15;
       try{
           assertEquals(expected, c.time('M'));
       } catch (ProjectException e){
           fail("Didn't replaced the values.");
       }
    }
    
    @Test
    public void shouldThrowErrorIMPOSSIBLE(){
       Composed c = new Composed("IS-BASICA", 100 , true );
       try{
           c.time('M');
           fail("This should throw de Exception IMPOSSIBLE");
       } catch (ProjectException e){
            assertEquals(ProjectException.IMPOSSIBLE, e.getMessage());
       }
       
       c = new Composed("IS-BASICA", 100 , false );
       try{
           c.time('A');
           fail("This should throw de Exception IMPOSSIBLE");
       } catch (ProjectException e){
            assertEquals(ProjectException.IMPOSSIBLE, e.getMessage());
       }
       c.add(new Simple("AFSA", 10, null));
       c.add(new Simple("POOB", 10, 0));
       c.add(new Simple("CVST", 10, -30));
       c.add(new Simple("RECO", 10, 30)); 
       
       try{
           c.time('A');
           fail("This should throw de Exception IMPOSSIBLE");
       } catch (ProjectException e){
            assertEquals(ProjectException.IMPOSSIBLE, e.getMessage());
       }
       
       try{
           c.time('M');
           fail("This should throw de Exception IMPOSSIBLE");
       } catch (ProjectException e){
            assertEquals(ProjectException.IMPOSSIBLE, e.getMessage());
       }
       
    }
}