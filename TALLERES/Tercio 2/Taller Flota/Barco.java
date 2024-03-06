 

import java.util.ArrayList;

public class Barco extends Maquina{
	private int numero;
	private ArrayList<Marino> marinos;

	@Override
	public boolean esDebil(){
		return (marinos.size() < 5) ? true : false;
	}
}
