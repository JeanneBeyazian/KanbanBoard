package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class KanbanBoard extends JFrame {

    private JLabel bar;

    public KanbanBoard() {
        initialise();
    }

    public void initialise() {
        // Set up the JFrame
        setTitle("First Try using Java Swing");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createMenuBar();

        // Create a panel
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        JButton button = new JButton("Exit");
        button.setBounds(60, 60, 80, 30);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        button.setToolTipText("Wow that's really cool");
        panel.add(button, BorderLayout.SOUTH);
    }

    public void createMenuBar() {

        JMenuBar menu = new JMenuBar();

        JMenu kanban = new JMenu("Kanban settings");
        JMenuItem team = new JMenu("Team");
        JMenuItem newBoard = new JMenu("New");
        JMenuItem renameBoard = new JMenu("Rename");
        JMenuItem settings = new JMenu("Other settings");
        kanban.add(team);
        kanban.add(renameBoard);
        kanban.add(newBoard);
        kanban.add(settings);


        JMenu help = new JMenu ("Help");
        JMenu view = new JMenu("View");
        JCheckBoxMenuItem showBar = new JCheckBoxMenuItem("Show bar");
        showBar.setSelected(true);

        showBar.addItemListener((e)->{
            if (e.getStateChange() == ItemEvent.SELECTED) menu.setVisible(true);
            else menu.setVisible(false);
        });
        view.add(showBar);
        menu.add(kanban);
        menu.add(view);
        menu.add(help);
        setJMenuBar(menu);

    }

    public static void main(String[] args) {
        KanbanBoard board = new KanbanBoard();
        board.setVisible(true);
    }

}




