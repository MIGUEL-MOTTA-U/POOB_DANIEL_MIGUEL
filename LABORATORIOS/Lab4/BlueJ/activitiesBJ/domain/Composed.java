package domain;  
 
import java.util.ArrayList;

public class Composed extends Activity{
   
    private boolean parallel;
    private ArrayList<Activity> activities;
    
    /**
     * Constructs a new composed activity
     * @param name 
     * @param cost
     * @param parallel
     */
    public Composed(String name, Integer cost, boolean parallel){
        super(name,cost);
        this.parallel=parallel;
        activities= new ArrayList<Activity>();
    }

     /**
     * Add a new activity
     * @param a
     */   
    public void add(Activity a){
        activities.add(a);
    }
       
 
    @Override
    public int cost(){
        return 0;
    }
    
    
    @Override
    public int time() throws ProjectException{
        int time = 0;

        if (this.activities.isEmpty()) throw new ProjectException(ProjectException.COMPOSED_EMPTY);

        if (this.parallel) {
            time = Integer.MIN_VALUE;
            for (Activity activity : activities) {
                if (activity.time() > time) time = activity.time();
            }
        } else {
            for (Activity activity : activities) {
                time += activity.time();
            }
        }

        return time;
    };
    
    
     /**
     * Calculates an estimated price using default values when necessary
     * @param dUnknown
     * @param dError
     * @param dEmpty
     * @return 
     */
    public int time(int dUnknow, int dError, int dEmpty)throws ProjectException {
        int time = 0;
        if (this.activities.isEmpty()) throw new ProjectException(ProjectException.COMPOSED_EMPTY);
        if (this.parallel) {
            time = Integer.MIN_VALUE;
            for (Activity activity : activities) {
                try{
                    if (activity.time() > time) time = activity.time();
                } catch (ProjectException e){
                    if (e.getMessage().equals( ProjectException.TIME_EMPTY)){
                        if (dEmpty > time) time = dEmpty;
                    } else if(e.getMessage().equals( ProjectException.TIME_ERROR)){ 
                        if (dError > time) time = dError;
                    } else if(e.getMessage().equals( ProjectException.COMPOSED_EMPTY)){ 
                        if(dUnknow > time) time = dUnknow;
                    } else throw e;
                }
            }
        } else {
            for (Activity activity : activities) {
                try{
                    time += activity.time();
                } catch (ProjectException e){
                    if (e.getMessage().equals( ProjectException.TIME_EMPTY)) {
                        time += dEmpty; 
                    } else if(e.getMessage().equals( ProjectException.TIME_ERROR)){
                        time += dError;
                    } else if (e.getMessage().equals( ProjectException.COMPOSED_EMPTY)) {
                        if(dUnknow > time) time = dUnknow;
                    } else throw e;   
                } 
            }
        }
        return time;
    } 
    
    
     /**
     * Calculate an estimated price considering the modality, if is possible.
     * @param modality ['A'(verage), 'M' (ax)] Use the average or maximum time of known activities to estimate unknown ones or those with error.
     * @return 
     * @throws ProjectException  IMPOSSIBLE, if it can't be calculated
     */
    public int price(char modality){
        return 0;
    } 
    
     /**
     * Calculates an time of a subactivity
     * @return 
     * @throws ProjectException UNKNOWN, if it doesn't exist. IMPOSSIBLE, if it can't be calculated
     */
    public int price(String activity) throws ProjectException{
        return 0;
    }
     
    @Override
    public String data() throws ProjectException{
        StringBuffer answer=new StringBuffer();
        answer.append(name+". Tipo "+ (parallel ? "Paralela": "Secuencial")+". ");
        for(Activity b: activities) {
            answer.append("\n\t"+b.data());
        }
        return answer.toString();
    }
}