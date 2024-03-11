import java.util.ArrayList;

public class PortaAviones extends Barco{
	private int capacidad;
	private ArrayList<Avion> aviones;

	@Override
	public boolean esDebil(){
		boolean res = false;
		if(super.esDebil()){
			return true;
		}else{
			for(Avion avion : aviones){
				if(avion.getEnAire() && avion.esDebil()){
					return true;
				}
			}
		}

		return res;
	}
}
