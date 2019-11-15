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
    // make an array list of all boards, one only can be visible at a time

    public KanbanBoard() {

        // Set up the JFrame
        setTitle("Kanban Board");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the menu bar
        createMenuBar();

        // Create the editor panel
        add(new EditorPanel(), BorderLayout.EAST);

        // Create board panel
        board = new BoardPanel();
        add(board, BorderLayout.CENTER);
    }


    public void createMenuBar() {

        KanbanMenu menu = new KanbanMenu();
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
