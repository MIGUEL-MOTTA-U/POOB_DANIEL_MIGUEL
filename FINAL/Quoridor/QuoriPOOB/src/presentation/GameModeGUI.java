package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class GameModeGUI extends JPanel{
    private QuoridorGUI quoridorGUI;

    // Title
    private JPanel panelTitle;
    private JLabel labelTitle;

    // Normal mode
    private JPanel panelNormalMode;
    private JButton buttonNormalMode;
    private JLabel labelNormalModeDescription;

    // Against the clock mode
    private JPanel panelClockMode;
    private JButton buttonClockMode;
    private JLabel labelClockModeDescription;

    // Timed mode
    private JPanel panelTimedMode;
    private JButton buttonTimedMode;
    private JLabel labelTimedModeDescription;

    public GameModeGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    private void prepareElements() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        prepareElementsTitle(content); 
        content.add(Box.createVerticalStrut(40));
        prepareElementsNormalMode(content);
        content.add(Box.createVerticalStrut(20));
        prepareElementsClockMode(content);
        content.add(Box.createVerticalStrut(20));
        prepareElementsTimedMode(content);

        add(content); 
    }

    private void prepareElementsTitle(JPanel content) {
        panelTitle = new JPanel();
        panelTitle.setLayout(new BoxLayout(panelTitle, BoxLayout.Y_AXIS));

        labelTitle = new JLabel("Choose the game mode");
        labelTitle.setFont(new Font(QuoridorGUI.FONT_TITLE, Font.BOLD, 40));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTitle.add(Box.createVerticalStrut(20));
        panelTitle.add(labelTitle);
        panelTitle.add(Box.createVerticalStrut(20));

        content.add(panelTitle);
    }

    private void prepareElementsNormalMode(JPanel content) {
        panelNormalMode = new JPanel();
        panelNormalMode.setBorder(new RoundBorder(QuoridorGUI.COLOR_BORDER_PANEL, QuoridorGUI.DEFAULT_BACKGROUND, 10));
        panelNormalMode.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        buttonNormalMode = createButton("Normal");
        labelNormalModeDescription = createLabel("Players have no time limit when performing actions.");

        container.add(buttonNormalMode);
        container.add(labelNormalModeDescription);
        panelNormalMode.add(container, BorderLayout.WEST);

        content.add(panelNormalMode);
    }

    private void prepareElementsClockMode(JPanel content) {
        panelClockMode = new JPanel();
        panelClockMode.setBorder(new RoundBorder(QuoridorGUI.COLOR_BORDER_PANEL, QuoridorGUI.DEFAULT_BACKGROUND, 10));
        panelClockMode.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        buttonClockMode = createButton("Against the clock");
        labelClockModeDescription = createLabel("Players have a parameterized time limit to perform an action or else they will lose their turn.");

        container.add(buttonClockMode);
        container.add(labelClockModeDescription);
        panelClockMode.add(container, BorderLayout.WEST);

        content.add(panelClockMode);
    }

    private void prepareElementsTimedMode(JPanel content) {
        panelTimedMode = new JPanel();
        panelTimedMode.setBorder(new RoundBorder(QuoridorGUI.COLOR_BORDER_PANEL, QuoridorGUI.DEFAULT_BACKGROUND, 10));
        panelTimedMode.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        buttonTimedMode = createButton("Timed");
        labelTimedModeDescription = createLabel("A total time limit for each player.");

        container.add(buttonTimedMode);
        container.add(labelTimedModeDescription);
        panelTimedMode.add(container, BorderLayout.WEST);

        content.add(panelTimedMode);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setOpaque(false); 
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 10, 10, 0));
        button.setFont(new Font(QuoridorGUI.FONT_SUBTITLE, Font.PLAIN, 17));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent ev) {
                button.setFont(button.getFont().deriveFont(Font.BOLD));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                button.setFont(button.getFont().deriveFont(Font.PLAIN));
                setCursor(Cursor.getDefaultCursor());
            }
        });

        return button;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(new EmptyBorder(0, 10, 10, 13));
        label.setFont(new Font(QuoridorGUI.FONT_TEXT, Font.PLAIN, 15));

        return label;
    }

    private void prepareActions() { 
        prepareActionsButtons();
    } 

    private void prepareActionsButtons() {
        buttonNormalMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                quoridorGUI.showPlayerInfoGUI();
            }
        });
        
        buttonClockMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                quoridorGUI.showPlayerInfoGUI();
            }
        });

        buttonTimedMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                quoridorGUI.showPlayerInfoGUI();
            }
        });
    }
}