import java.util.ArrayList;

public class Troncal {
    private String nombre;
    private int velocidadProm;
    private ArrayList<Integer> tramos;

    public Troncal(String nombre) {
        this.nombre = nombre;
        this.tramos = new ArrayList<>();
    }

    public String getName() {
        return this.nombre;
    }
}
