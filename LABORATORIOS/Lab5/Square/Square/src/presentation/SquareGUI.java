package presentation;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.*;
import java.io.File;
import java.net.CookieHandler;

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

		prepareElementsMenu();
		prepareElementsNorth();
		prepareElementsEast();
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
		panelHeader.setBackground(Color.WHITE);
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
		panelCreateBoard.setBackground(Color.WHITE);
		panelCreateBoard.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Create board")));

		JPanel panelButton = new JPanel(new BorderLayout());
		panelButton.setBackground(Color.WHITE);
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
		panelEast = new JPanel(new GridLayout(3, 1));

		panelEast.add(prepareElementsMove());

		getContentPane().add(panelEast, BorderLayout.EAST);
	}

	private JPanel prepareElementsMove() {
		panelMove = new JPanel(new BorderLayout());
		panelMove.setBackground(Color.WHITE);
		panelMove.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Move")));

		JPanel panelNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelWest = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelEast = new JPanel(new FlowLayout(FlowLayout.CENTER));

		buttonNorth = new JButton("North");
		buttonWest = new JButton("West");
		buttonSouth = new JButton("South");
		buttonEast = new JButton("East");
		imageCompass = new JLabel("Imagen de brujula");

		panelNorth.add(buttonNorth);
		panelWest.add(buttonWest);
		panelSouth.add(buttonSouth);
		panelEast.add(buttonEast);
		panelMove.add(panelNorth, BorderLayout.NORTH);
		panelMove.add(panelWest, BorderLayout.WEST);
		panelMove.add(panelSouth, BorderLayout.SOUTH);
		panelMove.add(panelEast, BorderLayout.EAST);
		panelMove.add(imageCompass, BorderLayout.CENTER);

		return panelMove;
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