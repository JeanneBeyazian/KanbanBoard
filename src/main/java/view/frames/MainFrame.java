package view.frames;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    public MainFrame() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton createNew = new JButton("Create new board");
        JButton open = new JButton("Open current board");
        panel.add(createNew);
        panel.add(open);
        add(panel);
//        add(makeContainerPanel());
        panel.setSize(500,500);


        //createNew.addActionListener(newProject());

        setResizable(true);
        initialise();
    }

    private void initialise() {
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();

    }

//    private JPanel makeContainerPanel() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.add(Box.createRigidArea(new Dimension(0, 5)));
//
//        return panel;
//
//    }


    public void newProject() {

    }


    public static void main(String[] args) {

        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
