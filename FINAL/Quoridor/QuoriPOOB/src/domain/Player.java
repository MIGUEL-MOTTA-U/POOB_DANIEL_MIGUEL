package domain;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Constructor;

public abstract class Player implements Serializable{
	protected String name;
	protected Color color;
	protected String time;
	protected Board board;
	protected ArrayList<Wall> walls;


	public boolean checkWin(){
		return board.checkWin(color);
	}

	public Player(String name, Color color) {
		this.color = color;
		this.name = name;
		this.walls = new ArrayList<>();
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void addWalls(int normal, int temporary, int WLong, int allied) {
		createWalls(normal, "domain.NormalWall");
		createWalls(temporary, "domain.Temporary");
		createWalls(WLong, "domain.LongWall");
		createWalls(allied, "domain.Allied");
	}

	public void delWall(Wall wall) {
		this.walls.remove(wall);

	}
	
	public HashMap<String, Integer> numberWalls() {
		HashMap<String, Integer> numberWalls = new HashMap<>();
		int normalWalls = 0;
		int temporaryWalls = 0;
		int longWalls = 0;
		int alliedWalls = 0;
		for (Wall wall : this.walls) {
			String cls = wall.getClass().getSimpleName().toUpperCase();
			if (cls.equals("NORMALWALL")) {
				normalWalls++;
			} else if (cls.equals("TEMPORARY")) {
				temporaryWalls++;
			} else if (cls.equals("LONGWALL")) {
				longWalls++;
			} else {
				alliedWalls++;
			}
		}

		numberWalls.put("NormalWall", normalWalls);
		numberWalls.put("Temporary", temporaryWalls);
		numberWalls.put("LongWall", longWalls);
		numberWalls.put("Allied", alliedWalls);

		return numberWalls;
	}
	
	protected void moveToken(String direction) throws QuoriPOOBException {
        switch (direction.toUpperCase()) {
            case "UP":
                board.moveTokenUp(this.color);
                break;
            case "DOWN":
                board.moveTokenDown(this.color);
                break;
            case "LEFT":
                board.moveTokenLeft(this.color);
                break;
            case "RIGHT":
                board.moveTokenRight(this.color);
                break;
            case "UPLEFT":
                board.moveTokenUpLeft(this.color);
                break;
            case "UPRIGHT":
                board.moveTokenUpRight(this.color);
                break;
            case "DOWNLEFT":
                board.moveTokenDownLeft(this.color);
                break;
            case "DOWNRIGHT":
                board.moveTokenDownRight(this.color);
                break;
            default:
                throw new QuoriPOOBException(QuoriPOOBException.WRONG_TOKEN_DIRECTION);
        }

    }
	protected void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide) throws QuoriPOOBException {
        if(!numberWalls().containsKey(type)) throw new QuoriPOOBException(QuoriPOOBException.WALL_NOT_EXIST);
        if (numberWalls().get(type) <= 0) throw new QuoriPOOBException(QuoriPOOBException.INSUFFICIENT_WALLS);

        Wall wallToPut = null;
        for (Wall w : this.walls) {
            if (w.getClass().getSimpleName().toUpperCase().equals(type.toUpperCase())) {
                wallToPut = w;
                break;
            }
        }

        wallToPut.addWallToBoard(initialRow, initialColumn, squareSide, this.board);
        
        delWall(wallToPut);
        
    }
	
	// Abstract Methods
	public abstract void play(String direction) throws QuoriPOOBException;
	public abstract void play(String type, int initialRow, int initialColumn, String squareSide) throws QuoriPOOBException;
	

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
			Constructor<?> constructor = cls.getDeclaredConstructor(Color.class);
			constructor.setAccessible(true);
			wall = (Wall) constructor.newInstance(this.color);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return wall;
	}

}
