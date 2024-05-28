package presentation;

import domain.QuoriPOOBException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
/**
 * Create the player information of walls, name and color section
 * 
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 25-05-2024
 */
public class PlayerInfoGUI extends JPanel {
    private QuoridorGUI quoridorGUI;
    private Color playerColor;

    // West
    private JPanel panelWest;
    private JLabel imageGame;
    private JLabel labelTitle;
    private JLabel labelDescription;

    // East
    private JPanel panelEast;
    private JTextField textName;
    private JButton buttonColor;
    private JButton buttonNext;

    /**
     * Constructor of PlayerInfoGUI
     */
    public PlayerInfoGUI(QuoridorGUI quoridorGUI) {
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

        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(new RoundBorder(Color.WHITE, Color.WHITE, 20));

        prepareElementsWest(content);
        prepareElementsEast(content);

        container.add(content);
        add(container);
    }

    /*
     * Create the west elements
     */
    private void prepareElementsWest(JPanel content) {
        panelWest = new JPanel();
        panelWest.setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);
        container.setBorder(new EmptyBorder(0, 0, 0, 50));

        imageGame = new JLabel();
        imageGame.setSize(50, 50);
        createImage(imageGame, "assets/Logo.png");

        String numPlayer = (!quoridorGUI.getPlayerTwo()) ? "1" : "2";
        labelTitle = new JLabel("Player " + numPlayer + " info");
        labelTitle.setFont(new Font(QuoridorGUI.FONT_TITLE, Font.BOLD, 25));

        labelDescription = new JLabel("Enter you data");
        labelDescription.setFont(new Font(QuoridorGUI.FONT_TEXT, Font.PLAIN, 15));

        container.add(imageGame);
        container.add(Box.createVerticalStrut(10));
        container.add(labelTitle);
        container.add(Box.createVerticalStrut(10));
        container.add(labelDescription);
        container.add(Box.createVerticalStrut(10));
        panelWest.add(container);

        content.add(panelWest, BorderLayout.WEST);
    }

    /*
     * Create the East elements
     */
    private void prepareElementsEast(JPanel content) {
        panelEast = new JPanel();
        panelEast.setBackground(Color.WHITE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);

        JPanel panelButtonColor = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelButtonColor.setBackground(Color.WHITE);
        JPanel panelButtonNext = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelButtonNext.setBackground(Color.WHITE);

        textName = createTextField("Name");
        textName.setPreferredSize(new Dimension(250, 30));

        buttonColor = createButton("color of token and walls", Color.WHITE, Color.BLACK, new Color(240, 240, 240));
        buttonColor.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        buttonColor.setPreferredSize(new Dimension(250, 30));

        buttonNext = createButton("Next", QuoridorGUI.BUTTONS_COLOR, Color.WHITE, QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonNext.setBorder(new EmptyBorder(7, 7, 7, 7));

        panelButtonColor.add(buttonColor);
        panelButtonNext.add(buttonNext);
        container.add(Box.createVerticalStrut(70));
        container.add(textName);
        container.add(Box.createVerticalStrut(10));
        container.add(panelButtonColor);
        container.add(Box.createVerticalStrut(50));
        container.add(panelButtonNext);
        panelEast.add(container);

        content.add(panelEast, BorderLayout.EAST);
    }

    /*
     * Creates a button
     */
    private JButton createButton(String text, Color background, Color foreGround, Color hover) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(background);
        button.setForeground(foreGround);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent ev) {
                button.setBackground(hover);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent ev) {
                button.setBackground(background);
                setCursor(Cursor.getDefaultCursor());
            }
        });

        return button;
    }

    /*
     * Create space to place messages
     */
    private JTextField createTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setFont(new Font("Arial", Font.PLAIN, 12));
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent ev) {
                if (textField.getText().equals(text)) {
                    textField.setText("");
                }
            }

            public void focusLost(FocusEvent ev) {
                if (textField.getText().isEmpty()) {
                    textField.setText(text);
                }
            }
        });

        return textField;
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
        buttonColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                playerColor = JColorChooser.showDialog(PlayerInfoGUI.this, "Select a color", Color.BLUE);
            }
        });

        buttonNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (!emptyInfo()) {
                    try {
                        quoridorGUI.createPlayerHuman(textName.getText(), playerColor.toString());
                        if (quoridorGUI.twoPlayers() & !quoridorGUI.getPlayerTwo()) {
                            quoridorGUI.setPlayerTwo();
                            quoridorGUI.restartPlayerInfoGUI();
                            quoridorGUI.showPlayerInfoGUI();
                        } else {
                            quoridorGUI.showSetUpGameGUI();
                        }
                    } catch (QuoriPOOBException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    /*
     * Create the image
     */
    private void createImage(JLabel label, String path) {
        URL url = getClass().getResource(path);
        
        if (url != null) {
            ImageIcon img = new ImageIcon(url);
            label.setIcon(new ImageIcon(
                    img.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
        }
    }

    /*
     * Create the alert of empty information for the player info section
     */
    private boolean emptyInfo() {
        boolean empty = false;

        if (playerColor == null) {
            JOptionPane.showMessageDialog(null, "You have to choose your color", "color not selected",
                    JOptionPane.INFORMATION_MESSAGE);
            empty = true;
        } else if (textName.getText().equals("Name")) {
            JOptionPane.showMessageDialog(null, "You must enter your name", "name not entered",
                    JOptionPane.INFORMATION_MESSAGE);
            empty = true;
        }

        return empty;
    }
}