package presentation;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class BoardGUI extends JPanel{
    private QuoridorGUI quoridorGUI;

    public BoardGUI(QuoridorGUI quoridorGUI) {
        this.quoridorGUI = quoridorGUI;
        prepareElements();
        prepareActions();
        setVisible(true);
    }

    private void prepareElements() {
        JPanel content = new JPanel();

        JPanel container = new JPanel();
        container.setPreferredSize(QuoridorGUI.PREFERRED_DIMENSION);
        container.setBackground(Color.RED);

        content.add(container);
        add(content);
    }

    private void prepareActions() {

    }
}
