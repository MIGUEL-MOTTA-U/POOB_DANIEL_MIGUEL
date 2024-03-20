 

public class Marino {
    private String nombre;
    private int rango;
    private boolean destruido = false;
    
    public String autodestruir(String razon){
        String res = null;
        if(!this.destruido){
            this.destruido = true;
            res = razon;
        }
        return res;
    }
}
