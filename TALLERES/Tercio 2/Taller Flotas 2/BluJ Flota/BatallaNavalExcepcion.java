
/**
 * Write a description of class BatallaNavalExcepcion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BatallaNavalExcepcion extends Exception
{
    public static String ERROR_NORTE = "Una maquina no se pudo mover al norte";
    public static String PROBLEMA_POTENCIA = "Mas de la mitad de las flotas tienen problemas de potencia";
    public static String INCONSISTENCIA_PILOTO_FLOTA = "Hay un piloto que no pertenece a la flota";
    public static String MARINOS_INSUFICIENTES = "Hay menos marinos que maquinas";
    public static String SIN_MARINOS = "La flota no tiene marineros asignados";
    public static String INCONSISTENCIA_PILOTO_PORTAAVIONES = "Hay un piloto que no es marino del porta avion";

    public BatallaNavalExcepcion(String message){
        super(message);
    }
}
