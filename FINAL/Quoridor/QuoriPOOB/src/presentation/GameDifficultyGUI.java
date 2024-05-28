package presentation;

import domain.QuoriPOOBException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Create the screen where the player decides which type of machine will play
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class GameDifficultyGUI extends JPanel {
    private QuoridorGUI quoridorGUI;

    // Title
    private JPanel panelTitle;
    private JLabel labelTitle;

    // Beginner
    private JPanel panelBegginer;
    private JButton buttonBeginner;
    private JLabel labelBeginnerDescription;

    // Intermediate
    private JPanel panelIntermediate;
    private JButton buttonIntermediate;
    private JLabel labelIntermediateDescription;

    // Advanced
    private JPanel panelAdvanced;
    private JButton buttonAdvanced;
    private JLabel labelAdvancedDescription;

    /**
     * Constructor of DefeatScreen
     */
    public GameDifficultyGUI(QuoridorGUI quoridorGUI) {
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
        prepareElementsBeginner(content);
        content.add(Box.createVerticalStrut(20));
        prepareElementsIntermediate(content);
        content.add(Box.createVerticalStrut(20));
        prepareElementsAdvanced(content);

        container.add(content);
        add(container);
    }

    /*
     * Create the title elements
     */
    private void prepareElementsTitle(JPanel content) {
        panelTitle = new JPanel();
        panelTitle.setLayout(new BoxLayout(panelTitle, BoxLayout.Y_AXIS));

        labelTitle = new JLabel("Choose the game difficulty");
        labelTitle.setFont(new Font(QuoridorGUI.FONT_TITLE, Font.BOLD, 40));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelTitle.add(Box.createVerticalStrut(20));
        panelTitle.add(labelTitle);
        panelTitle.add(Box.createVerticalStrut(20));

        content.add(panelTitle);
    }

    /*
     * Create the beginner machine elements
     */
    private void prepareElementsBeginner(JPanel content) {
        panelBegginer = new JPanel();
        panelBegginer.setBorder(new RoundBorder(QuoridorGUI.COLOR_BORDER_PANEL, QuoridorGUI.DEFAULT_BACKGROUND, 10));
        panelBegginer.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        buttonBeginner = createButton("Beginner");
        labelBeginnerDescription = createLabel("The machine moves and sets bridges randomly.");

        container.add(buttonBeginner);
        container.add(labelBeginnerDescription);
        panelBegginer.add(container, BorderLayout.WEST);

        content.add(panelBegginer);
    }

    /*
     * Create the intermediate machine elements
     */
    private void prepareElementsIntermediate(JPanel content) {
        panelIntermediate = new JPanel();
        panelIntermediate
                .setBorder(new RoundBorder(QuoridorGUI.COLOR_BORDER_PANEL, QuoridorGUI.DEFAULT_BACKGROUND, 10));
        panelIntermediate.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        buttonIntermediate = createButton("Intermediate");
        labelIntermediateDescription = createLabel(
                "The movements of the machine will take into account a winning path of the user and its own.");

        container.add(buttonIntermediate);
        container.add(labelIntermediateDescription);
        panelIntermediate.add(container, BorderLayout.WEST);

        content.add(panelIntermediate);
    }

    /*
     * Create the advanced machine elements
     */
    private void prepareElementsAdvanced(JPanel content) {
        panelAdvanced = new JPanel();
        panelAdvanced.setBorder(new RoundBorder(QuoridorGUI.COLOR_BORDER_PANEL, QuoridorGUI.DEFAULT_BACKGROUND, 10));
        panelAdvanced.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        buttonAdvanced = createButton("Advanced");
        labelAdvancedDescription = createLabel(
                "Include an advanced strategy, to be defined according to your creativity.");

        container.add(buttonAdvanced);
        container.add(labelAdvancedDescription);
        panelAdvanced.add(container, BorderLayout.WEST);

        content.add(panelAdvanced);
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

    /*
     * Create a label
     */
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(new EmptyBorder(0, 10, 10, 13));
        label.setFont(new Font(QuoridorGUI.FONT_TEXT, Font.PLAIN, 15));

        return label;
    }

    /*
     * Create the actions
     */
    private void prepareActions() {
        prepareActionsButtons();
    }

    /*
     * Create the button actions
     */
    private void prepareActionsButtons() {
        buttonBeginner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.createBeginnerMachine();
                    quoridorGUI.showGameModeGUI();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonIntermediate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.createIntermediateMachine();
                    quoridorGUI.showGameModeGUI();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonAdvanced.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    quoridorGUI.createAdvancedMachine();
                    quoridorGUI.showGameModeGUI();
                } catch (QuoriPOOBException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}