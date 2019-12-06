package view;

import annotations.ClassAnnotation;
import controller.Load;
import view.boardComponents.BoardPanel;
import view.boardComponents.EditorPanel;
import view.boardComponents.KanbanMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * This class is the application window.
 * It contains the maine frame and a menu bar.
 */
public class KanbanBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private String boardName;
    private BoardPanel board;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 750;

    public KanbanBoard(String title) {

        // Set up the JFrame
        setTitle("Kanban Board");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create board panel
        board = new BoardPanel();
        add(board);
        //board.setPreferredSize(new Dimension(WIDTH/4*3, HEIGHT));
        JScrollPane boardScroll = new JScrollPane(board);
        boardScroll.setBorder(new EmptyBorder(0,0,0,0));
        add(boardScroll);

        // Create the editor panel
        EditorPanel editorPanel = new EditorPanel();
        editorPanel.setPreferredSize(new Dimension(WIDTH/4,HEIGHT));
        add(editorPanel, BorderLayout.EAST);

        // Create board panel
        board = new BoardPanel();
        board.setPreferredSize(new Dimension(WIDTH/4*3, HEIGHT));
        add(board);

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

    public BoardPanel getBoard(){
        return board;
    }

    public void setBoard(BoardPanel newBoard){
        board = newBoard;
        board.setVisible(true);
        revalidate();
        repaint();

    }

    public void boardReset() {
        //board.reset();
    }

    public String getBoardName(){ return boardName; }

    public void setBoardName(String newName){
        boardName = newName;
        setTitle(boardName);
        revalidate();
    }

    public static void main(String[] args) {
        KanbanBoard board = new KanbanBoard();
        board.setVisible(true);
    }

}
