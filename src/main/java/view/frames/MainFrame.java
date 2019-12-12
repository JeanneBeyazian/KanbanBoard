package view.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private JPanel container;

    public MainFrame() {
        setTitle("Welcome to Kanban Board Indigo");
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initialise();
        pack();
    }

    private void initialise() {

        container = new JPanel();

        JLabel welcomeLabel = new JLabel("Welcome to the Kanban Board Indigo App !");

        //JPanel buttonPanel = new JPanel();
        //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        JButton newBoard = new JButton("Create new board");
        newBoard.addActionListener(e-> new CreateFrame().setVisible(true));
        //buttonPanel.add(newBoard);

        //buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        JButton open = new JButton("Open existing board");
        open.addActionListener(e-> new OpenFrame().setVisible(true));
        //buttonPanel.add(open);

        container.add(new JLabel(new ImageIcon("src/images/kanban_logo.png")));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(welcomeLabel);
        panel.add(newBoard);
        panel.add(open);
        container.add(new JSeparator());
        container.add(panel);
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(container);

        pack();

        setResizable(true);

    }


    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
