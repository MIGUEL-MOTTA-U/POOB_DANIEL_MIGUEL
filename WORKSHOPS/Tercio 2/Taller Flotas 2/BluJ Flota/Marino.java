 

public class Marino {
    private String nombre;
    private int rango;
    private boolean destruido = false;
    private boolean infiltrado;
    private String razon;
    
    
    public boolean isInfiltrado(){
        return infiltrado;
    }
    
    
    public void autodestruir(String razon){
        if(!this.destruido){
            this.destruido = true;
            this.razon = razon;
        }
    }
    
    public String getRazon(){
        return this.razon;
    }
}
