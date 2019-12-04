package view.frames;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    public MainFrame() {
        JPanel panel = new JPanel;
        add(panel);
        JButton createNew = new JButton("Create new board");
        JButton open = new JButton("Open current board");

        createNew.addActionListener(e->);

        panel.add(createNew);
        panel.add(open);
    }

    public void newProject() {
        ScrollPane pane = new ScrollPane();
        JButton create = new JButton;
        pane.add(create);
    }
}
