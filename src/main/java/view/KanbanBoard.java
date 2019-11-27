package view;

import annotations.ClassAnnotation;

import view.boardComponents.BoardPanel;
import view.boardComponents.EditorPanel;
import view.boardComponents.KanbanMenu;
import view.frames.AddCardFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


/**
 * This class is the application window.
 * It contains the maine frame and a menu bar.
 */
@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "08/11/2019",
        lastEdit = "22/11/2019"
)

public class KanbanBoard extends JFrame {

    private BoardPanel board;
    private EditorPanel editorPanel;
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 800;

    // TODO (maybe): implement a card layout for boards in use
    // TODO : focus on making tests for GUI and start the model part

    public KanbanBoard() {

        // Set up the JFrame
        setTitle("Kanban Board");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create board panel
        board = new BoardPanel();
        //board.setPreferredSize(new Dimension(WIDTH/4*3, HEIGHT));
        JScrollPane boardScroll = new JScrollPane(board);
        boardScroll.setBorder(new EmptyBorder(0,0,0,0));
        add(boardScroll);

        // Create the editor panel
        editorPanel = new EditorPanel(board);
        editorPanel.setPreferredSize(new Dimension(WIDTH/4,HEIGHT));
        add(editorPanel, BorderLayout.EAST);

        // Create empty box on the west border
        JPanel westBox = new JPanel();
        westBox.setBackground(Color.black);
        westBox.setPreferredSize(new Dimension(10, HEIGHT));
        add(westBox, BorderLayout.WEST);

        // Create empty box on the north border
        JPanel northBox = new JPanel();
        northBox.setBackground(Color.black);
        northBox.setPreferredSize(new Dimension(WIDTH, 10));
        add(northBox, BorderLayout.NORTH);

        // Create the menu bar
        createMenuBar(this);
    }

    /** Sets up the Kanban menu */
    public void createMenuBar(KanbanBoard currentBoard) {
        KanbanMenu menu = new KanbanMenu(currentBoard);
        setJMenuBar(menu);
    }

    /**
     * Opens a new KanbanBoard Window
     */
    public static void newBoard() {
        KanbanBoard newBoardWindow = new KanbanBoard();
    }

    public BoardPanel getBoard(){
        return board;
    }

    public EditorPanel getEditorPanel(){
        return editorPanel;
    }


    public static void main(String[] args) {
        KanbanBoard board = new KanbanBoard();
        AddCardFrame frame = new AddCardFrame();
        frame.setVisible(true);
        board.setVisible(true);
    }

}
