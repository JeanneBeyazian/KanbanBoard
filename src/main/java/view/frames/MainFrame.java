package view.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame{
    private JPanel container;

    public MainFrame() {
        setTitle("KanbanBoard App");
        setLocationRelativeTo(null);
        //setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initialise();
        pack();
    }

    private void initialise() {

        container = new JPanel();
        container.setPreferredSize(new Dimension(500, 500));

        JLabel welcomeLabel = new JLabel("Welcome to the Kanban Board Indigo App !");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        JButton newBoard = new JButton("Create new board");
        newBoard.addActionListener(e-> new CreateFrame().setVisible(true));
        buttonPanel.add(newBoard);

        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        JButton open = new JButton("Open current board");
        buttonPanel.add(open);

        container.add(welcomeLabel, BorderLayout.NORTH);
        container.add(buttonPanel, BorderLayout.CENTER);
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(container);
//        add(makeContainerPanel());


        //createNew.addActionListener(newProject());

        setResizable(true);

    }


    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
