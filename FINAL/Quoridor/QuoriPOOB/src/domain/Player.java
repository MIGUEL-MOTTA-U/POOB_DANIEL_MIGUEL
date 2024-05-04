package domain;
import java.awt.Color;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
public class Player {
	private String name;
	private Color color;
	private Board board;
	private ArrayList<Wall> walls;
	public Player(String name, Color color) {
		this.color = color;
		this.name = name;
	}
	
	public void setBoard(Board b) {
		this.board = b;
	}
	public void addWall(String type){
		try {
            // Obtén la clase "Wall" mediante reflexión
            Class<?> wallClass = Class.forName("com.domain.Wall"); // Reemplaza con el paquete y nombre real de tu clase Wall

            // Obtén el constructor adecuado según el tipo
            Constructor<?> constructor = wallClass.getConstructor(String.class);

            // Crea una instancia de Wall con el constructor apropiado
            //return (Wall) constructor.newInstance(type);
        
		} catch (ClassNotFoundException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            // Maneja las excepciones según tus necesidades
            //return null;
        }
	}
}
