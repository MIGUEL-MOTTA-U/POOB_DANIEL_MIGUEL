package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class GameModeGUI extends JPanel{
    QuoridorGUI quoridorGUI;

    // Title
    private JPanel panelTitle;
    private JLabel labelTitle;

    public GameModeGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        prepareElements();
        setVisible(true);
    }

    private void prepareElements() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        prepareElementsTitle(content); 

        add(content); 
        setVisible(true);
    }

    private void prepareElementsTitle(JPanel content) {
        panelTitle = new JPanel();
        panelTitle.setLayout(new BoxLayout(panelTitle, BoxLayout.Y_AXIS));

        labelTitle = new JLabel("Choose the game mode");
        labelTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
        labelTitle.setBorder(new EmptyBorder(0, 0, 20, 0));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTitle.add(Box.createVerticalStrut(20));
        panelTitle.add(labelTitle);
        panelTitle.add(Box.createVerticalStrut(20));

        content.add(panelTitle);
        setVisible(true);
    }
}
