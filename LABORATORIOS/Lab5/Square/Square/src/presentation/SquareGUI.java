package presentation;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.*;
import javax.swing.JOptionPane;
import java.awt.event.*;

public class SquareGUI extends JFrame {
	private static final int PREFERRED_WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.5);
	private static final int PREFERRED_HEIGH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5);
	private static final Dimension PREFERRED_DIMENSION = new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGH);
	
	// Barra menu
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newItem;
	private JMenuItem OpenItem;
	private JMenuItem saveItem;
	private JMenuItem exitItem;
	
	private SquareGUI() {
		prepareElements();
		prepareActions();
	}
	
	private void prepareElements() {
		setTitle("Square");
		setSize(PREFERRED_DIMENSION);
		setLocationRelativeTo(null);
		prepareElementsMenu();
		
	}
	
	private void prepareElementsMenu(){
		// Crear la barra de menú
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		newItem = new JMenuItem("New");
		OpenItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save");
		exitItem = new JMenuItem("Exit");
		// Agregar los elementos al menú "Archivo"
		fileMenu.add(newItem);
		fileMenu.add(OpenItem);
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
	}
	


	private void prepareActions() { 
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		addWindowListener(new WindowAdapter() { 
			@Override
			public void windowClosing(WindowEvent windowEvent) { 
				confirmClose(); 
			}
		}); 
		prepareActionsMenu();
	}   

	private void prepareActionsMenu(){
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev){
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