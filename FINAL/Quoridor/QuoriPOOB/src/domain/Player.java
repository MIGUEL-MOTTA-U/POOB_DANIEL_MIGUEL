package domain;
import java.awt.*;
import java.util.ArrayList;
import java.lang.reflect.Constructor;

public abstract class Player {
	protected String name;
	protected Color color;
	protected String time;
	protected Board board;
	protected ArrayList<Wall> walls;

	public Player(String name, Color color) {
		this.color = color;
		this.name = name;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void addWalls(int normal, int temporary, int WLong, int allied) {
		createWalls(normal, "NormalWall");
		createWalls(temporary, "Temporary");
		createWalls(WLong, "LongWall");
		createWalls(allied, "Allied");
	}
	
	
	// Abstract Methods
	public abstract void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide);

	public abstract void moveToken(Color color, String direction)throws QuoriPOOBException;


	// Getters and Setters
	public String getName(){
		return name;
	}

	public Color getColor(){
		return color;
	}

	public void  setTime(String time){
		this.time = time;
	}

	// Private methods

	private void createWalls(int quantity, String type) {
		Wall wall = null;
		for (int i = 0; i < quantity; i++) {
			wall = createWall(type);
			this.walls.add(wall);
		}
	}

	private Wall createWall(String type) {
		Wall wall = null;
		try {
			Class<?> cls = Class.forName(type);
			if (!Wall.class.isAssignableFrom(cls)) throw new QuoriPOOBException(QuoriPOOBException.WALL_NOT_EXIST);
			Constructor<?> constructor = cls.getDeclaredConstructor();
			constructor.setAccessible(true);
			wall = (Wall) constructor.newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return wall;
	}
}
