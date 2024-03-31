import java.util.ArrayList;
/**
 * Clase Nodriza
 *
 * @author (Daniel Diaz & Miguel Motta)
 * @version (a version number or a date)
 */
public abstract class nodriza extends Maquina{
    protected boolean destruido;
    protected ArrayList<Capsula> capsulas; 
    
    @Override
    public void autodestruir(String razon){
        for(Capsula c : this.capsulas){
            c.autodestruir(null);
        }
    }
}