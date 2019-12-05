package view.frames;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    public MainFrame() {
        JPanel panel = new JPanel();
        add(panel);
        panel.setSize(500,500);
        JButton createNew = new JButton("Create new board");
        JButton open = new JButton("Open current board");

        //createNew.addActionListener();

        setResizable(true);
        panel.add(createNew);
        panel.add(open);

        add(makeContainerPanel());
        initialise();
    }

    private void initialise() {
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();

    }

    private JPanel makeContainerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        return panel;

    }


    public void newProject() {
        ScrollPane pane = new ScrollPane();
        JButton create = new JButton();
        pane.add(create);
    }


//    public static void main(String[] args) {
////        KanbanBoard board = new KanbanBoard("KanbanBoard");
////        board.setVisible(true);
//        MainFrame frame = new MainFrame();
//        frame.setVisible(true);
//    }
//}
