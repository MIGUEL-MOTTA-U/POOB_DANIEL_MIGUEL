package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class StartGUI extends JPanel{
    private QuoridorGUI quoridorGUI;

    // Title
    private JPanel panelTitle;
    private JLabel labelTitle;
    private JLabel labelNames;

    // Buttons
    private JPanel panelButtons;
    private JButton buttonOnePlayer;
    private JButton buttonTwoPlayers;
    private JButton buttonExit;

    public StartGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    private void prepareElements() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        prepareElementsTitle(content); 
        prepareElementsButtons(content); 

        add(content); 
    }

    private void prepareElementsTitle(JPanel content) {
        panelTitle = new JPanel();
        panelTitle.setLayout(new BoxLayout(panelTitle, BoxLayout.Y_AXIS));

        labelTitle = new JLabel("Quoridor");
        labelTitle.setFont(new Font(QuoridorGUI.FONT_TITLE, Font.BOLD, 60));
        labelTitle.setBorder(new EmptyBorder(0, 0, 20, 0));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelNames = new JLabel("By Daniel and Miguel");
        labelNames.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTitle.add(Box.createVerticalStrut(20));
        panelTitle.add(labelTitle);
        panelTitle.add(labelNames);
        panelTitle.add(Box.createVerticalStrut(20));

        content.add(panelTitle);
    }

    private void prepareElementsButtons(JPanel content) {
        panelButtons = new JPanel();
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));

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
        
        panelButtons.add(Box.createVerticalStrut(20));
        panelButtons.add(panelButtonOnePlayer);
        panelButtons.add(panelButtonTwoPlayers);
        panelButtons.add(panelButtonExit);       
        panelButtons.add(Box.createVerticalStrut(20));

        content.add(panelButtons); 
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(QuoridorGUI.BUTTONS_COLOR);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(120, 45));
        button.setBorder(new EmptyBorder(10, 10, 10, 10));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent ev) {
                button.setBackground(QuoridorGUI.BUTTONS_COLOR_HOVER);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        
            public void mouseExited(MouseEvent ev) {
                button.setBackground(QuoridorGUI.BUTTONS_COLOR);
                setCursor(Cursor.getDefaultCursor());
            }
        });
    
        return button;
    }

    private void prepareActions() { 
        prepareActionsButtons();
    } 

    private void prepareActionsButtons() {
        buttonOnePlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                quoridorGUI.showGameDifficultyGUI();
            }
        });
        
        buttonTwoPlayers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                quoridorGUI.showGameModeGUI();
            }
        });

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
}