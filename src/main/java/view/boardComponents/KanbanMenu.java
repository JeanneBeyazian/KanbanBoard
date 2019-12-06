package view.boardComponents;

import annotations.ClassAnnotation;
import controller.Load;
import controller.Save;
import view.KanbanBoard;
import view.frames.KanbanCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.awt.event.*;

/**
 * This class is responsible for the creation of the menu
 * bar at the top of the board.
 */
@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "13/11/2019",
        lastEdit = "22/11/2019"
)
public class KanbanMenu extends JMenuBar {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	KanbanBoard currentBoard;

    public KanbanMenu(KanbanBoard currentBoard) {

        super();
        this.currentBoard = currentBoard;

        // Aesthetics
        setBackground(new java.awt.Color(10, 15, 67));
        setBorder(new EmptyBorder(0,0,0,0));

        // Create all JMenuItems for the Kanban Menu
        add(createFileMenu());
        add(createEditMenu());
        add(createKanbanMenu());
        add(createViewMenu());
        add(createHelpMenu());
    }

    public JMenu makeMenu(String menuName){
        JMenu menu = new JMenu(menuName);
        menu.setForeground(new java.awt.Color(255, 255, 255));
        return menu;
    }

    public JMenuItem makeItem(String menuItemName, ImageIcon icon){
        JMenuItem menu = new JMenuItem(menuItemName, icon);
        menu.setForeground(new java.awt.Color(228, 228, 228));
        menu.setBackground(new java.awt.Color(33, 42, 101));
        return menu;
    }

    private JMenu createFileMenu(){
        // Menu items for JMenu 'file'
        JMenu file = makeMenu("File");

        ImageIcon newIcon = new ImageIcon("src/images/newdocument.jpg");
        ImageIcon openIcon = new ImageIcon("src/images/open-folder-with-document.jpg");
        ImageIcon renameIcon = new ImageIcon("src/images/rename.jpg");
        ImageIcon saveIcon = new ImageIcon("src/images/save.jpg");

        JMenuItem newBoard = new JMenuItem("New", newIcon);
        JMenuItem openBoard = new JMenuItem("Open", openIcon);
        openBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            	BoardPanel newBoard = Load.loadBoard();
            	
            	
            	ArrayList<KanbanColumn> cols = newBoard.getColumns();
                
                
                for(int i = 0; i < cols.size(); i++) {
             	   KanbanColumn col = cols.get(i);
             	   KanbanColumn boardCol = new KanbanColumn(col.getColumnTitle(), col.getRole());
             	   ArrayList<KanbanCardButton> cards = cols.get(i).getCards();
             	   if(cards.size() != 0) {
             		  for(int j = 0; j < cards.size(); j++) {
                		   //KanbanCardButton cardButton = cards.get(j);
                		   
                		  KanbanCardButton cardButton = new KanbanCardButton(cards.get(j));

                		   boardCol.addCard(cardButton);
                	   }
             	   }
             	   currentBoard.getBoard().addColumn(boardCol);
                }

            }
        });
        JMenuItem renameBoard = new JMenuItem("Rename", renameIcon);
        JMenuItem saveBoard = new JMenuItem("Save", saveIcon);
        saveBoard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                BoardPanel board = currentBoard.getBoard();
                Save.saveBoard(board);
            }
        });


        file.add(newBoard);
        file.add(new JSeparator(SwingConstants.HORIZONTAL));
        file.add(openBoard);
        file.add(new JSeparator(SwingConstants.HORIZONTAL));
        file.add(renameBoard);
        file.add(saveBoard);

        return file;
    }


    private JMenu createEditMenu() {

        // Menu items for JMenu 'Edit'
        JMenu edit = makeMenu("Edit");

        ImageIcon addIcon = new ImageIcon("src/images/plus-sign.jpg");
        ImageIcon deleteIcon = new ImageIcon("src/images/delete.jpg");
        ImageIcon clearIcon = new ImageIcon("src/images/delete-symbol.jpg");

        JMenu insertMenu = new JMenu("Add");
        insertMenu.setIcon(addIcon);
        JMenuItem insertColumn = new JMenuItem("Insert new column");
        JMenuItem insertCard = new JMenuItem("Insert new card");
        insertMenu.add(insertColumn);
        insertMenu.add(insertCard);

        JMenu deleteMenu = new JMenu("Delete");
        deleteMenu.setIcon(deleteIcon);
        JMenuItem deleteColumn = new JMenuItem("Remove a column");
        JMenuItem deleteCard = new JMenuItem("Remove a card");
        deleteMenu.add(deleteColumn);
        deleteMenu.add(deleteCard);

        JMenuItem clearBoard = new JMenuItem("Clear board", clearIcon);

        edit.add(insertMenu);
        edit.add(new JSeparator(SwingConstants.HORIZONTAL));
        edit.add(deleteMenu);
        edit.add(new JSeparator(SwingConstants.HORIZONTAL));
        edit.add(clearBoard);

        return edit;
    }

    private JMenu createKanbanMenu() {

        // Menu items for JMenu 'kanban'
        JMenu kanban = makeMenu("Kanban");
        JMenuItem team = new JMenuItem("Team");
        JMenuItem settings = new JMenuItem("Settings");
        JMenuItem exitBoard = new JMenuItem("Exit Kanban");

        kanban.add(team);
        kanban.add(settings);
        kanban.add(new JSeparator(SwingConstants.HORIZONTAL));
        kanban.add(exitBoard);

        return kanban;
    }


    private JMenu createViewMenu() {

        // JMenu items for View menu
        JMenu view = makeMenu("View");
        JCheckBoxMenuItem showBar = new JCheckBoxMenuItem("Show Editor Panel");
        showBar.setSelected(true);

        showBar.addItemListener((e)->{
            if (e.getStateChange() == ItemEvent.SELECTED) currentBoard.getEditorPanel().setVisible(true);
            else currentBoard.getEditorPanel().setVisible(false);
        });

        view.add(showBar);

        return view;
    }


    private JMenu createHelpMenu() {

        // JMenu help
        JMenu help = makeMenu("Help");
        ImageIcon helpIcon = new ImageIcon("src/images/help.png");
        help.setIcon(helpIcon);

        return help;
    }

    class removeCardEvent implements ActionListener {

        public void actionPerformed(ActionEvent e) {

        }
    }

}
