package presentation;
import domain.*;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GardenGUI extends JFrame{  
    public static final int SIDE=21;
    public static final int SIZE=Garden.LENGTH+1;

    private JButton buttonTicTac;
    private JPanel panelControl;
    private PhotoGarden photo;
    private Garden garden;

    // Menu
    private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newItem;
	private JMenuItem openItem;
	private JMenuItem saveAsItem;
	private JMenuItem importItem;
	private JMenuItem exportAsItem;
	private JMenuItem exitItem;

    private GardenGUI() {
        garden=new Garden();
        prepareElements();
        prepareActions();
    }
    
    private void prepareElements() {
        setTitle("Garden");
        prepareElementsMenu();
        photo=new PhotoGarden(this);
        buttonTicTac=new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo,BorderLayout.NORTH);
        add(buttonTicTac,BorderLayout.SOUTH);
        setSize(new Dimension(SIDE*SIZE,SIDE*SIZE+50)); 
        setResizable(false);
        photo.repaint();
    }

    private void prepareElementsMenu() {
        menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		newItem = new JMenuItem("New");
		openItem = new JMenuItem("Open");
		saveAsItem = new JMenuItem("Save as");
        importItem = new JMenuItem("Import");
        exportAsItem = new JMenuItem("Export as");
		exitItem = new JMenuItem("Exit");

		fileMenu.add(newItem);
        fileMenu.addSeparator();
		fileMenu.add(openItem);
		fileMenu.add(saveAsItem);
		fileMenu.addSeparator();
		fileMenu.add(importItem);
		fileMenu.add(exportAsItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);


		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
    }

    private void prepareActions(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        buttonTicTac.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    buttonTicTacAction();
                }
            });

        prepareActionsMenu();
    }

    private void prepareActionsMenu() {
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                optionOpen();
            }
        });

        saveAsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                optionSave();
            }
        });

        importItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                optionImport();
            }
        });

        exportAsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                optionExport();
            }
        });

        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                optionNew();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                optionExit();
            }
        });
    }

    private void buttonTicTacAction() {
        garden.ticTac();
        photo.repaint();
    }

    private void optionOpen() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                garden = Garden.openFile(file);
                photo.repaint();
            } catch (GardenException e) {
                JOptionPane.showMessageDialog(this, GardenException.ERROR_OPEN("open", file.getName()), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void optionSave(){
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                garden.saveFile(file);
            } catch (GardenException e) {
                JOptionPane.showMessageDialog(this, GardenException.ERROR_OPEN("save", file.getName()), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void optionImport() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                garden = Garden.importFile(file);
                photo.repaint();
            } catch (GardenException e) {
                JOptionPane.showMessageDialog(this, GardenException.ERROR_OPEN("import", file.getName()), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void optionExport(){
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                garden.exportFile(file);
            } catch (GardenException e) {
                JOptionPane.showMessageDialog(this, GardenException.ERROR_OPEN("export", file.getName()), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void optionNew() {
        garden = new Garden();
        photo.repaint();
    }

    private void optionExit() { 
		int option = JOptionPane.showConfirmDialog(this, "Are you sure do you want to close the window?", 
		"Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
	
		if (option == JOptionPane.YES_OPTION) { 
			System.exit(0);
		} 
	} 
    
    public Garden getGarden(){
        return garden;
    }
    
    public static void main(String[] args) {
        GardenGUI cg=new GardenGUI();
        cg.setVisible(true);
    }  
}

class PhotoGarden extends JPanel{
    private GardenGUI gui;

    public PhotoGarden(GardenGUI gui) {
        this.gui=gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE*gui.SIZE, gui.SIDE*gui.SIZE));         
    }


    public void paintComponent(Graphics g){
        Garden garden=gui.getGarden();
        super.paintComponent(g);
        int[][] deltas={{gui.SIDE/3, 0},{0,gui.SIDE/6},{2*gui.SIDE/3,gui.SIDE/6},{0,3*gui.SIDE/6},{2*gui.SIDE/3,3*gui.SIDE/6},{gui.SIDE/3,2*gui.SIDE/3}} ;    
        for (int f=0;f<=garden.getLength();f++){
            g.drawLine(f*gui.SIDE,0,f*gui.SIDE,garden.getLength()*gui.SIDE);
        }
        for (int c=0;c<=garden.getLength();c++){
            g.drawLine(0,c*gui.SIDE,garden.getLength()*gui.SIDE,c*gui.SIDE);
        }       
        for (int f=0;f<garden.getLength();f++){
            for(int c=0;c<garden.getLength();c++){
                if (garden.getThing(f,c)!=null){
                    g.setColor(garden.getThing(f,c).getColor());
                    if (garden.getThing(f,c).shape()==Thing.FLOWER){
                        g.setColor(garden.getThing(f,c).getColor());
                        for (int i=0; i<deltas.length; i++){
                                g.drawOval(gui.SIDE*c+deltas[i][0],gui.SIDE*f+deltas[i][1],gui.SIDE/3-1,gui.SIDE/3-1);
                        }
                        g.setColor(Color.YELLOW);
                        g.drawOval(gui.SIDE*c+gui.SIDE/3,gui.SIDE*f+gui.SIDE/3,gui.SIDE/3,gui.SIDE/3);
                        if (garden.getThing(f,c).is()){
                            g.setColor(garden.getThing(f,c).getColor());
                            for (int i=0; i<deltas.length; i++){
                                g.fillOval(gui.SIDE*c+deltas[i][0],gui.SIDE*f+deltas[i][1],gui.SIDE/3-1,gui.SIDE/3-1);
                            }
                            g.setColor(Color.YELLOW);
                            g.fillOval(gui.SIDE*c+gui.SIDE/3,gui.SIDE*f+gui.SIDE/3,gui.SIDE/3,gui.SIDE/3);
                        }
                    }else if (garden.getThing(f,c).shape()==Thing.SQUARE){  
                        g.drawRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2); 
                        if (garden.getThing(f,c).is()){
                            g.fillRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);
                        }
                    }else{
                        g.drawOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        if (garden.getThing(f,c).is()){
                            g.fillOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                        } 
                    }
                }
            }
        }
    }
}