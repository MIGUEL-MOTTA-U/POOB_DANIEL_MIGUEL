package domain;  

public class Simple extends Activity{
    private Integer time;
    
    public Simple(String name, Integer cost, Integer time){
        super(name,cost);
        this.time=time;
    }    
    

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
}
