import java.util.ArrayList;
/**
 * Clase Nodriza
 *
 * @author (Daniel Diaz & Miguel Motta)
 * @version (a version number or a date)
 */
public abstract class nodriza extends Maquina{
    protected boolean destruido;
    
    public abstract String autodestruir(String razon);
}