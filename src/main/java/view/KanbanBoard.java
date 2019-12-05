package view;

import annotations.ClassAnnotation;
import controller.Load;
import view.boardComponents.BoardPanel;
import view.boardComponents.EditorPanel;
import view.boardComponents.KanbanMenu;

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

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String boardName;
    private BoardPanel board;
    private EditorPanel editorPanel;
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 800;

    public KanbanBoard(String title) {

        // Set up the JFrame
        boardName = title;
        setTitle(title);
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
    public static void newBoard(String name) {
        KanbanBoard newBoardWindow = new KanbanBoard(name);
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

    public EditorPanel getEditorPanel(){
        return editorPanel;
    }

    public String getBoardName(){ return boardName; }

    public void setBoardName(String newName){
        boardName = newName;
        setTitle(boardName);
        revalidate();
    }

    public static void main(String[] args) {
        KanbanBoard board = new KanbanBoard("KanbanBoard");
        board.setVisible(true);
    }

}
