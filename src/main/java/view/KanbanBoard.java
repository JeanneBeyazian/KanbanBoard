package view;

import view.KanbanMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * This class is the application window.
 * It contains the maine frame and a menu bar.
 */
public class KanbanBoard extends JFrame {

    private BoardPanel board;
    // TODO (Jeanne) : implement button functions and set up controllers
    // TODO (maybe): implement a card layout for boards in use
    // i.e only one board can be seen at a time, but several are open.
    // It is possible to use JSwing tabs

    public KanbanBoard() {

        // Set up the JFrame
        setTitle("Kanban Board");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the menu bar
        createMenuBar();

        // Create the editor panel
        add(new EditorPanel(), BorderLayout.EAST);

        // Create board panel
        board = new BoardPanel();
        add(board);

        // Testing purpose : create a new frame containing a 'card'
        /** TESTING
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(Box.createVerticalGlue());
        JScrollPane scroll = new JScrollPane(new KanbanCard("Name", "Description", 50));
        pane.add(scroll);
        frame.getContentPane().add(pane);
        frame.setVisible(true); */

    }


    public void createMenuBar() {

        KanbanMenu menu = new KanbanMenu();
        setJMenuBar(menu);

    }

    //TODO (Jeanne) : methods below
    public void newBoard() {}

    public void boardReset() {
        //board.reset();
    }


    public static void main(String[] args) {
        KanbanBoard board = new KanbanBoard();
        board.setVisible(true);
    }

}
