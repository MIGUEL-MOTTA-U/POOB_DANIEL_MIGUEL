package presentation;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;

public class VictoryScreen extends JPanel {
    private BoardGUI boardGUI;
    private String winner;
    private Clip clip;

    //Message
    private JPanel panelMessage;
    private JLabel labelMessage;

    //Image
    private JPanel panelImage;
    private JLabel labelImage;

    //Buttons
    private JPanel panelButtons;
    private JButton buttonNew;
    private JButton buttonExit;

    public VictoryScreen(BoardGUI boardGUI, String winner) {
        this.boardGUI = boardGUI;
        this.winner = winner;
        prepareElements();
        prepareAction();
        setVisible(true);
        playSound();
    }

    private void prepareElements() {
        JPanel content = new JPanel();
        
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        prepareElementsMessage(container);
        container.add(Box.createVerticalStrut(35));
        prepareElementsImage(container);
        container.add(Box.createVerticalStrut(35));
        prepareElementsButtons(container);

        content.add(container);
        add(content);
    }

    private void prepareElementsMessage(JPanel content) {
        panelMessage = new JPanel();

        labelMessage = new JLabel("¡Congratulations! " + winner + " has won the game");
        labelMessage.setFont(new Font(QuoridorGUI.FONT_TITLE, Font.BOLD, 20));

        panelMessage.add(labelMessage);
        content.add(panelMessage);
    }

    private void prepareElementsImage(JPanel content) {
        panelImage = new JPanel();

        labelImage = new JLabel();
        labelImage.setSize(250, 200);
        createImage(labelImage, "assets/Ronaldo.jpg");

        panelImage.add(labelImage);

        content.add(panelImage);
    }

    private void prepareElementsButtons(JPanel content) {
        JPanel container = new JPanel();

        panelButtons = new JPanel();
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));

        buttonNew = createButton("New game", QuoridorGUI.BUTTONS_COLOR, Color.WHITE, QuoridorGUI.BUTTONS_COLOR_HOVER);
        buttonExit = createButton("Exit", QuoridorGUI.BUTTONS_COLOR, Color.WHITE, QuoridorGUI.BUTTONS_COLOR_HOVER);

        panelButtons.add(buttonNew);
        panelButtons.add(Box.createHorizontalStrut(10));
        panelButtons.add(buttonExit);

        container.add(panelButtons);
        content.add(container);
    }

    private JButton createButton(String text, Color background, Color foreGround, Color hover) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(background);
        button.setForeground(foreGround);
        button.setBorderPainted(false);

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

    private void prepareAction() {
        prepareActionsButtons();
    }

    private void prepareActionsButtons() {
        buttonNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                boardGUI.newGame();
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                boardGUI.exit();
            }
        });
    }

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

    private void playSound() {
        try {
            File soundFile = new File("presentation\\assets\\victory sound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            if (clip != null) {
                clip.setFramePosition(0);
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
