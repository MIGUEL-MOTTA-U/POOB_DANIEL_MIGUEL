
/**
 * Clase cápsula, es inmune a los ataques y NUNCA es débil si está bajo 8000 metros
 * @author (Daniel Diaz y Miguel Motta)
 * @version (1.2)
 */
public abstract class capsula extends nodriza
{
    private int profundidad;
    @Override
    public boolean esDebil(){
        boolean res = true;
        if (profundidad >= 8000){
            res = false;
        }
        return res;
    }
    
    @Override
    public String autodestruir(String razon){
        String res = "Mi nave Nodriza fue destruida";
        return res; 
    }
}
