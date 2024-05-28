package presentation;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.QuoriPOOBException;

import java.awt.*;
import java.awt.event.*;

/**
 * Create the screen where the player decides the game mode
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class GameModeGUI extends JPanel {
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

    /**
     * Constructor of BoardGUI
     */
    public GameModeGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    /*
     * Create all the elements
     */
    private void prepareElements() {
        JPanel container = new JPanel();

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        prepareElementsTitle(content);
        content.add(Box.createVerticalStrut(40));
        prepareElementsNormalMode(content);
        content.add(Box.createVerticalStrut(20));
        prepareElementsClockMode(content);
        content.add(Box.createVerticalStrut(20));
        prepareElementsTimedMode(content);

        container.add(content);
        add(container);
    }

    /*
     * Create the title elements
     */
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

    /*
     * Create the normal mode elements
     */
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

    /*
     * Create the clock mode elements
     */
    private void prepareElementsClockMode(JPanel content) {
        panelClockMode = new JPanel();
        panelClockMode.setBorder(new RoundBorder(QuoridorGUI.COLOR_BORDER_PANEL, QuoridorGUI.DEFAULT_BACKGROUND, 10));
        panelClockMode.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        buttonClockMode = createButton("Against the clock");
        labelClockModeDescription = createLabel(
                "Players have a parameterized time limit to perform an action or else they will lose their turn.");

        container.add(buttonClockMode);
        container.add(labelClockModeDescription);
        panelClockMode.add(container, BorderLayout.WEST);

        content.add(panelClockMode);
    }

    /*
     * Create the timed mode elements
     */
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

    /*
     * Create a button
     */
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

    /*
     * Create a label
     */
    private void prepareActions() {
        prepareActionsButtons();
    }

    /*
     * Create the button actions
     */
    private void prepareActionsButtons() {
        buttonNormalMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.setNormalMode();
                    quoridorGUI.showPlayerInfoGUI();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonClockMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.setTimeTrialMode();
                    quoridorGUI.showPlayerInfoGUI();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonTimedMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.setTimedMode();
                    quoridorGUI.showPlayerInfoGUI();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}