package domain;



public abstract class Activity{
    
    protected String name;
    protected Integer cost;

    
    public Activity(String name, Integer cost){
        this.name=name;
        this.cost=cost;
    }
    
    /**
     * Return the name
     * @return
     */
    public String name(){
        return name;
    }

 
    
    /**
     * Return time
     * @return
     * @throws ProjectException, if the time is not available or has an error
     */
    public abstract int time() throws ProjectException;
    
    
    /**
     * Return cost
     * @return
     * @throws ProjectException, if the cost is not available or has an error
     */
    public abstract int cost() throws ProjectException;
    
    
    /**
     * Return the representation as string
     * @return
     * @throws ProjectException, if the data is not complete
     */    
    public abstract String data() throws ProjectException;

}
