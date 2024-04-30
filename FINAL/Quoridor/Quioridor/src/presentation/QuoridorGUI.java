package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class QuoridorGUI extends JFrame{
    private static final int PREFERRED_WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.6);
    private static final int PREFERRED_HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.7);
    private static final Dimension PREFERRED_DIMENSION = new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
    private static final Color BUTTONS_COLOR = new Color(80, 180, 255);
    private static final Color BUTTONS_COLOR_HOVER = new Color(70, 170, 255);

    // Title
    private JLabel labelTitle;
    private JLabel labelNames;

    // Buttons
    private JButton buttonOnePlayer;
    private JButton buttonTwoPlayers;
    private JButton buttonExit;

    private QuoridorGUI() {
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        setTitle("Quoridor");
        setSize(PREFERRED_DIMENSION);
        setLocationRelativeTo(null);

        JPanel container = new JPanel(new GridBagLayout());

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        prepareElementsTitle(content); 
        prepareElementsButtons(content); 

        container.add(content);
        getContentPane().add(container); 
    }

    private void prepareElementsTitle(JPanel content) {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        labelTitle = new JLabel("Quoridor");
        labelTitle.setFont(new Font("Tahoma", Font.BOLD, 60));
        labelTitle.setBorder(new EmptyBorder(0, 0, 20, 0));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelNames = new JLabel("By Daniel and Miguel");
        labelNames.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(Box.createVerticalStrut(20));
        titlePanel.add(labelTitle);
        titlePanel.add(labelNames);
        titlePanel.add(Box.createVerticalStrut(20));

        content.add(titlePanel);
    }

    private void prepareElementsButtons(JPanel content) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JPanel panelButtonOnePlayer = new JPanel();
        panelButtonOnePlayer.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel panelButtonTwoPlayers = new JPanel();
        panelButtonTwoPlayers.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel panelButtonExit = new JPanel();
        panelButtonExit.setBorder(new EmptyBorder(10, 10, 10, 10));

        buttonOnePlayer = createButton("1 player");
        buttonTwoPlayers = createButton("2 players");
        buttonExit = createButton("Exit");

        panelButtonOnePlayer.add(buttonOnePlayer);
        panelButtonTwoPlayers.add(buttonTwoPlayers);
        panelButtonExit.add(buttonExit);
        
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(panelButtonOnePlayer);
        buttonPanel.add(panelButtonTwoPlayers);
        buttonPanel.add(panelButtonExit);       
        buttonPanel.add(Box.createVerticalStrut(20));

        content.add(buttonPanel); 
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setBackground(BUTTONS_COLOR);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(120, 45));
        button.setBorder(new EmptyBorder(10, 10, 10, 10));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent ev) {
                button.setBackground(BUTTONS_COLOR_HOVER);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        
            public void mouseExited(MouseEvent ev) {
                button.setBackground(BUTTONS_COLOR);
                setCursor(Cursor.getDefaultCursor());
            }
        });
    
        return button;
    }

    private void prepareActions() { 
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent windowEvent) { 
                confirmClose(); 
            }
        }); 

        prepareActionsButtons();
    } 

    private void prepareActionsButtons() {
        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                confirmClose();
            }
        });
    }

    private void confirmClose() { 
        int option = JOptionPane.showConfirmDialog(this, "Are you sure do you want to get out of the game?", 
        "Close Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
    
        if (option == JOptionPane.YES_OPTION) { 
            System.exit(0);
        } 
    } 
    
    public static void main(String args[]) {
        QuoridorGUI gui = new QuoridorGUI();
        gui.setVisible(true);
    }
}
