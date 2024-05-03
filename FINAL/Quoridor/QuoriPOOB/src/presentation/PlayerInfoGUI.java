package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class PlayerInfoGUI extends JPanel{
    QuoridorGUI quoridorGUI;

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

    public PlayerInfoGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    private void prepareElements() {
        JPanel container = new JPanel();

        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(new RoundBorder(Color.WHITE, Color.WHITE, 20));

        prepareElementsWest(content);
        prepareElementsEast(content);

        container.add(content);
        add(container);
    }

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

        labelTitle = new JLabel("Player info");
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

        textName = new JTextField("Name");
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

    private void prepareActions()  {
        prepareActionsTextFields();
        prepareActionsButtons();
    }

    private void prepareActionsTextFields() {
        textName.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent ev) {
                if (textName.getText().equals("Name")) {
                    textName.setText("");
                }
            }

            public void focusLost(FocusEvent ev) {
                if (textName.getText().isEmpty()) {
                    textName.setText("Name");
                }
            }
        });
    }

    private void prepareActionsButtons() {
        buttonColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JColorChooser chooser = new JColorChooser();
                Color selectedColor = chooser.showDialog(PlayerInfoGUI.this, "Select a color", Color.BLUE);
            }
        });
    }

    private void createImage(JLabel label, String path) {
		URL url = getClass().getResource(path);
		if (url != null) {
			ImageIcon img = new ImageIcon(url);
			label.setIcon(new ImageIcon(img.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH)));
			label.setHorizontalAlignment(SwingConstants.CENTER);
        	label.setVerticalAlignment(SwingConstants.CENTER);
		}
	}
}