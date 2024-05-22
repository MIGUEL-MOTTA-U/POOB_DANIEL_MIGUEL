package domain;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Constructor;
import java.util.List;

public abstract class Player implements Serializable {
	protected String name;
	protected Color color;
	protected String time;
	protected Board board;
	protected ArrayList<Wall> walls;

	public Player(String name, Color color) {
		this.color = color;
		this.name = name;
		this.walls = new ArrayList<>();
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void addWalls(int normal, int temporary, int WLong, int allied) throws QuoriPOOBException {
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

	// En Construccion checkear que siempre haya un camino para ganar

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

	protected void addWallToBoard(String type, int initialRow, int initialColumn, String squareSide)
			throws QuoriPOOBException {
		if (!numberWalls().containsKey(type))
			throw new QuoriPOOBException(QuoriPOOBException.WALL_NOT_EXIST);
		if (numberWalls().get(type) <= 0)
			throw new QuoriPOOBException(QuoriPOOBException.INSUFFICIENT_WALLS);
		
		Wall wallToPut = null;

		for (Wall w : this.walls) {
			if (w.getClass().getSimpleName().toUpperCase().equals(type.toUpperCase())) {
				wallToPut = w;
				break;
			}
		}
		wallToPut.addWallToBoard(initialRow, initialColumn, squareSide, this.board);
		Grafo mappedBoard = mapBoard();
		if(blockWay(wallToPut, mappedBoard) || getOtherPlayer().blockWay(wallToPut, mappedBoard)){
			System.out.println("INTENTO PONER UN PUENTE EN ("+initialRow+","+initialColumn+") DIRECCION: "+squareSide);
			wallToPut.delWallFromBoard();
			wallToPut = null;
			throw new QuoriPOOBException(QuoriPOOBException.BLOCK_THE_WAY);
		}
		delWall(wallToPut);
	}

	protected boolean blockWay(Wall w, Grafo g){
		boolean res = true;
		Token playerToken = board.getTokens().get(color);
		int destiny = playerToken.getDestiny(), size = board.getSize();
		int currentNode = ((playerToken.getRow()) * size) + playerToken.getColumn();
		// Recorro las casillas de meta
		for (int i = destiny * size; i < destiny * size + size; i++) {
				if(g.isConnected(currentNode, i) && checkDestinySquare((Square)g.getNodes().get(i), destiny)) return false;
		}
		return res;
	}


	protected ArrayList<Square> calculateMyShorestPath(){
		Grafo graph = mapBoard();
		ArrayList<Square> res = new ArrayList<>();
		List<Integer> minPath = new ArrayList<>(), path = new ArrayList<>();
		Token playerToken = board.getTokens().get(color);
		int destiny = playerToken.getDestiny(), size = board.getSize();
		int currentNode = ((playerToken.getRow()) * size) + playerToken.getColumn();
		for (int i = destiny * size; i < destiny * size + size; i++) {
			try{
				path = graph.shortestWay(currentNode, i);
			} catch(QuoriPOOBException e){}
			
			if(minPath.size() == 0) minPath = path;

			if ((path.size() < minPath.size() && path.size() > 1)&&
					checkDestinySquare((Square) graph.getNodes().get(i), destiny))
				minPath = path;
		}
		for (int node : minPath) {
			res.add((Square) graph.getNodes().get(node));
		}
		return res;
	}


	public boolean checkWin() {
		return board.checkWin(color);
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	protected Player getOtherPlayer() {
		return board.getOtherPlayer();
	}

	public void setTime(String time) {
		this.time = time;
	}

	private void createWalls(int quantity, String type) throws QuoriPOOBException {
		Wall wall = null;

		for (int i = 0; i < quantity; i++) {
			wall = createWall(type);
			this.walls.add(wall);
		}
	}

	private Wall createWall(String type) throws QuoriPOOBException {
		Wall wall = null;

		try {
			Class<?> cls = Class.forName(type);
			Constructor<?> constructor = cls.getDeclaredConstructor(Color.class);
			constructor.setAccessible(true);
			wall = (Wall) constructor.newInstance(this.color);
		} catch (Exception e) {
			throw new QuoriPOOBException(QuoriPOOBException.WALL_NOT_EXIST);
		}

		return wall;
	}

	private boolean checkDestinySquare(Square box, int destiny) {
		if (destiny == 0) {
			return box.getWallUp() == null;
		} else {
			return box.getWallDown() == null;
		}
	}

	private Grafo mapBoard() {
		Grafo graph = new Grafo(board.getSize() * board.getSize());
		int id = 0;
		int size = board.getSize();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Square box = board.getMatrixBoard()[i][j];
				graph.addNode(id, box);
					if (makeConectionLeft(box)) {// No hay ni muro a la izquierda de box, o un borde
						graph.addVertex(id, id - 1, 1);
					}
					if (makeConectionUp(box)) {
						graph.addVertex(id, id - size, 1);
					}
				id++;
			}
		}
		return graph;
	}

	private boolean makeConectionUp(Square box) {
		return box.getCoordenates()[0] != 0 && box.getWallUp() == null;
	}

	private boolean makeConectionLeft(Square box) {
		return box.getCoordenates()[1] != 0 && box.getWallLeft() == null;
	}

}
