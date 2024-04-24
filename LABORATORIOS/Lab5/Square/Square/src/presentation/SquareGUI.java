package presentation;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class SquareGUI extends JFrame {
	private static final int PREFERRED_WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.8);
	private static final int PREFERRED_HEIGH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.8);
	private static final Dimension PREFERRED_DIMENSION = new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGH);

	// Panels
	private JPanel panelNorth;
	private JPanel panelEast;
	private JPanel panelBoard;
	private JPanel panelHeader;
	private JPanel panelCreateBoard;
	private JPanel panelMove;
	private JPanel panelInfo;
	private JPanel panelChangeColor;
	private ArrayList<JPanel> boxes;
	
	// Menu
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newItem;
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem exitItem;

	// Header
	private JLabel imageGame;
	private JLabel labelTitle;
	
	// Create board
	private JLabel labelBoardSize;
	private JLabel labelNumberTokens;
	private JTextField boardSize;
	private JTextField numberTokens;
	private JButton buttonCreateBoard;
	
	// Move
	private JButton buttonNorth;
	private JButton buttonSouth;
	private JButton buttonWest;
	private JButton buttonEast;
	private JLabel imageCompass;

	// Info
	private JLabel labelMoves;
	private JLabel labelPercentage;

	// Change color
	private JLabel labelOldColor;
	private JLabel labelNewColor; 
	private JButton buttonOldColor;
	private JButton buttonNewColor;
	
	private SquareGUI() {
		prepareElements();
		prepareActions();
	}
	
	private void prepareElements() {
		setTitle("Square");
		getContentPane().setBackground(Color.WHITE);
		setSize(PREFERRED_DIMENSION);
		setLocationRelativeTo(null);
		defaultBackground();

		prepareElementsMenu();
		prepareElementsNorth();
		prepareElementsEast();
		prepareElementsBoard();
	}

	private void defaultBackground() {
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("Label.background", Color.WHITE);
    }
	
	private void prepareElementsMenu(){
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		newItem = new JMenuItem("New");
		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save");
		exitItem = new JMenuItem("Exit");

		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
	}

	private void prepareElementsNorth() {
		panelNorth = new JPanel(new GridLayout(2, 1));

		panelNorth.add(prepareElementsHeader());
		panelNorth.add(prepareElementsCreateBoard());

		getContentPane().add(panelNorth, BorderLayout.NORTH);
	}

	private JPanel prepareElementsHeader() {
		panelHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

		imageGame = new JLabel(new ImageIcon("../../assets/imageHeader.png"));
		labelTitle = new JLabel("Square");
		labelTitle.setBorder(new EmptyBorder(10, 10, 10, 10));
		labelTitle.setFont(new Font("Arial", Font.PLAIN, 50));

		panelHeader.add(imageGame);
		panelHeader.add(labelTitle);

		return panelHeader;
	}

	private JPanel prepareElementsCreateBoard() {
		panelCreateBoard = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
		panelCreateBoard.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Create board")));

		JPanel panelButton = new JPanel(new BorderLayout());
		panelButton.setBorder(new EmptyBorder(0, 70, 0, 0));

		labelBoardSize = new JLabel("Board size: ");
		labelBoardSize.setBorder(new EmptyBorder(0, 30, 0, 0));
		boardSize = new JTextField(10);
		labelNumberTokens = new JLabel("Number of tokens: ");
		labelNumberTokens.setBorder(new EmptyBorder(0, 70, 0, 0));
		numberTokens = new JTextField(10);
		buttonCreateBoard = new JButton("Create");

		panelButton.add(buttonCreateBoard, BorderLayout.CENTER);
		panelCreateBoard.add(labelBoardSize);
		panelCreateBoard.add(boardSize);
		panelCreateBoard.add(labelNumberTokens);
		panelCreateBoard.add(numberTokens);
		panelCreateBoard.add(panelButton);

		return panelCreateBoard;
	}

	private void prepareElementsEast() {
		panelEast = new JPanel();

		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		container.add(prepareElementsMove());
		container.add(prepareElementsInfo());
		container.add(prepareElementsChangeColor());
		panelEast.add(container);

		getContentPane().add(panelEast, BorderLayout.EAST);
	}

	private JPanel prepareElementsMove() {
		panelMove = new JPanel(new BorderLayout());
		panelMove.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Move")));

		JPanel panelN = new JPanel(new GridBagLayout());
		JPanel panelW = new JPanel(new GridBagLayout());
		JPanel panelS = new JPanel(new GridBagLayout());
		JPanel panelE = new JPanel(new GridBagLayout());
		JPanel panelButtonNorth = new JPanel();
		panelButtonNorth.setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel panelButtonWest = new JPanel();
		panelButtonWest.setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel panelButtonSouth = new JPanel();
		panelButtonSouth.setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel panelButtonEast = new JPanel();
		panelButtonEast.setBorder(new EmptyBorder(10, 10, 10, 10));

		buttonNorth = new JButton("North");
		buttonWest = new JButton("West");
		buttonSouth = new JButton("South");
		buttonEast = new JButton("East");
		imageCompass = new JLabel("Imagen de brujula");

		panelButtonNorth.add(buttonNorth);
		panelButtonWest.add(buttonWest);
		panelButtonSouth.add(buttonSouth);
		panelButtonEast.add(buttonEast);
		panelN.add(panelButtonNorth);
		panelW.add(panelButtonWest);
		panelS.add(panelButtonSouth);
		panelE.add(panelButtonEast);
		panelMove.add(panelN, BorderLayout.NORTH);
		panelMove.add(panelW, BorderLayout.WEST);
		panelMove.add(panelS, BorderLayout.SOUTH);
		panelMove.add(panelE, BorderLayout.EAST);
		panelMove.add(imageCompass, BorderLayout.CENTER);

		return panelMove;
	}

	private JPanel prepareElementsInfo() {
		panelInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelInfo.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Information")));

		labelMoves = new JLabel("Moves: 12");
		labelMoves.setBorder(new EmptyBorder(20, 20, 20, 20));
		labelPercentage = new JLabel("Percentage: 30%");
		labelPercentage.setBorder(new EmptyBorder(20, 20, 20, 20));

		panelInfo.add(labelMoves);
		panelInfo.add(labelPercentage);

		return panelInfo;
	}

	private JPanel prepareElementsChangeColor() {
		panelChangeColor = new JPanel();
		panelChangeColor.setLayout(new BoxLayout(panelChangeColor, BoxLayout.Y_AXIS));
		panelChangeColor.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Change color")));

		JPanel panelLabels = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelButtonOldColor = new JPanel();
		panelButtonOldColor.setBorder(new EmptyBorder(0, 0, 20, 0));
		JPanel panelButtonNewColor = new JPanel();
		panelButtonNewColor.setBorder(new EmptyBorder(0, 0, 20, 0));


		labelOldColor = new JLabel("Old color");
		labelOldColor.setBorder(new EmptyBorder(20, 20, 0, 20));
		labelNewColor = new JLabel("New color");
		labelNewColor.setBorder(new EmptyBorder(20, 20, 0, 20));
		buttonOldColor = new JButton("color");
		buttonNewColor = new JButton("color");

		panelButtonOldColor.add(buttonOldColor);
		panelButtonNewColor.add(buttonNewColor);
		panelLabels.add(labelOldColor);
		panelLabels.add(labelNewColor);
		panelButtons.add(panelButtonOldColor);
		panelButtons.add(Box.createHorizontalStrut(20));
		panelButtons.add(panelButtonNewColor);
		panelChangeColor.add(panelLabels);
		panelChangeColor.add(panelButtons);

		return panelChangeColor;
	}

	private void prepareElementsBoard() {
		panelBoard = new JPanel();

		JPanel container = new JPanel(new GridLayout(9, 9));
		container.setPreferredSize(new Dimension(430, 430));
		container.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

		boxes = new ArrayList<>();
		for (int i = 0; i < 81; i++) {
			JPanel box = new JPanel();
			box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			container.add(box);
			boxes.add(box);
		}

		panelBoard.add(container);
		getContentPane().add(panelBoard, BorderLayout.CENTER);
	}
	
	private void prepareActions() { 
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent windowEvent) { 
				confirmClose(); 
			}
		}); 
		prepareActionsMenu();
	}   

	private void prepareActionsMenu(){
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(SquareGUI.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File fileSelected = chooser.getSelectedFile();
					JOptionPane.showMessageDialog(SquareGUI.this, "You chose open this file: " + fileSelected.getName(), "Action under construction", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showSaveDialog(SquareGUI.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File fileSelected = chooser.getSelectedFile();
					JOptionPane.showMessageDialog(SquareGUI.this, "You chose save this file: " + fileSelected.getName(), "Action under construction", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				confirmClose();
			}
		});	
	}

	private void confirmClose() { 
		int option = JOptionPane.showConfirmDialog(this, "Are you sure do you want to close the window?", 
		"Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
	
		if (option == JOptionPane.YES_OPTION) { 
			System.exit(0);
		} 
	} 

	public static void main(String args[]) {
		SquareGUI gui = new SquareGUI();
		gui.setVisible(true);
	}
} 