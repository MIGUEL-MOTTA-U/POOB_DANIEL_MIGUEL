package domain;  
/**
 * The class Simple extends the abstract class Activity. 
 * It is a regular activity with a determined time to be completed.
 * @autors: Daniel Diaz and Miguel Motta
 * @version: 09/04/2024
 */

public class Simple extends Activity{
    private Integer time;
    
    public Simple(String name, Integer cost, Integer time){
        super(name,cost);
        this.time=time;
    }    
    

    /**
     * This function return
     */
    @Override
    public int time() throws ProjectException{
       if (time == null) throw new ProjectException(ProjectException.TIME_EMPTY);
       if (time < 1 || time>24) throw new ProjectException(ProjectException.TIME_ERROR);
       return time;
    }    
    
    
    @Override
    public int cost() throws ProjectException{
       return (cost == null || cost<0) ? 0 : cost;
    }   
    
    @Override
    public String data(){
        return name+". Costo:" +cost+".Tiempo:"+time;
    }
    
    
    /**
     * CHanges the time of the Simple activity
     * @param t, is the new value of the time of the Simple activity.
     */
    public void setTime(Integer t){
        this.time = t;
    }
}
