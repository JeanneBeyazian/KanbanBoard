package model;

import view.KanbanMenu;

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
        setTitle("Kanban Board");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createMenuBar();

        // Create a panel
        board = new BoardPanel();
        getContentPane().add(board, BorderLayout.SOUTH);
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
