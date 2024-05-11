package presentation;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class GardenGUI extends JFrame{  
    public static final int SIDE=21;
    public static final int SIZE=Garden.LENGTH+1;

    private JButton buttonTicTac;
    private JPanel panelControl;
    private PhotoGarden photo;
    private Garden garden;

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

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
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void optionSave() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                garden.saveFile(file);
            } catch (GardenException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        int opcion = JOptionPane.showConfirmDialog(null, "Desea ejecutar la prueba de aceptacion?", "Confirmacion prueba de aceptacion", JOptionPane.YES_NO_OPTION);
        if(opcion == JOptionPane.YES_OPTION){
            
            JOptionPane.showMessageDialog(null, "Se van a ejecutar 6 Tic-Tac", "6 Tic-Tac", JOptionPane.INFORMATION_MESSAGE);
            for(int i= 0; i < 6 ; i++){
                cg.getGarden().ticTac();
                cg.repaint();
                try {
                    TimeUnit.SECONDS.sleep(1); // Esperar 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(null, "Por favor Guarde el archivo", "Guardar archivo", JOptionPane.INFORMATION_MESSAGE);
            // Aqui deberia abrir el archivo
            String rutaActual = System.getProperty("user.dir");
            String path = rutaActual + File.separator + "prueba.dat";
            
            try {
                // Crear un nuevo objeto File con la ruta completa
                File archivo = new File(path);
    
                // Verificar si el archivo no existe
                if (!archivo.exists()) {
                    // Crear el nuevo archivo
                    archivo.createNewFile();
                    JOptionPane.showMessageDialog(null, "El archivo no existia asi que lo creamos.", "Creando archivo", JOptionPane.INFORMATION_MESSAGE);    
                }
                JOptionPane.showMessageDialog(null, path, "La ruta donde quedo", JOptionPane.INFORMATION_MESSAGE);
                cg.getGarden().saveFile(archivo);
                JOptionPane.showMessageDialog(null, "Ahora vamos a ejecutar otros 6 tic-tac luego de guardar el archivo", "Seguir Jugando", JOptionPane.INFORMATION_MESSAGE);
                
                for(int i= 0; i < 6 ; i++){
                    cg.getGarden().ticTac();
                    cg.repaint();
                    try {
                        TimeUnit.SECONDS.sleep(1); // Esperar 1 segundo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } JOptionPane.showMessageDialog(null, "Ahora vamos a cargar el juego que creamos", "Abrir el juego", JOptionPane.INFORMATION_MESSAGE);
                cg.setGarden(Garden.openFile(archivo));;
                cg.repaint();
                if (archivo != null && archivo.exists()) {
                    archivo.delete();
                    JOptionPane.showMessageDialog(null, "Cargamos el archivo de juego que se guardo", "Archivo cargado", JOptionPane.INFORMATION_MESSAGE);    
                    JOptionPane.showMessageDialog(null, "Borramos el archivo", "Borrar archivo", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showConfirmDialog(null, "Es el comportamiento esperado?", "Confirmacion prueba de aceptacion", JOptionPane.YES_NO_OPTION);
            } catch (IOException|GardenException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error al guardar el archivo", JOptionPane.ERROR_MESSAGE);
                        }
        } System.exit(0);
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