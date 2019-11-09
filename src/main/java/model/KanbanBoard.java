package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * This class is the application window.
 * It contains the maine frame and a menu bar.
 */
public class KanbanBoard extends JFrame {

    private JLabel bar;
    private BoardPanel board;
    // make an arraylist of all boards, one only can be visible at a time

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
        board = new BoardPanel();
        getContentPane().add(board, BorderLayout.SOUTH);
    }

    public void createMenuBar() {

        JMenuBar menu = new JMenuBar();
        JMenu kanban = new JMenu("Kanban settings");

        // Items for JMenu 'kanban'
        JMenuItem team = new JMenu("Team");
        JMenuItem newBoard = new JMenu("New");
        JMenuItem renameBoard = new JMenu("Rename");
        JMenuItem settings = new JMenu("Other settings");

        kanban.add(team);
        kanban.add(renameBoard);
        kanban.add(newBoard);
        kanban.add(settings);

        JMenu help = new JMenu("Help");
        JMenu view = new JMenu("View");
        JCheckBoxMenuItem showBar = new JCheckBoxMenuItem("Show bar");
        showBar.setSelected(true);

        showBar.addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) menu.setVisible(true);
            else menu.setVisible(false);
        });

        view.add(showBar);
        menu.add(kanban);
        menu.add(view);
        menu.add(help);
        setJMenuBar(menu);
    }

    public void boardReset() {
        board.reset();
    }


    public static void main(String[] args) {
        KanbanBoard board = new KanbanBoard();
        board.setVisible(true);
    }

}
