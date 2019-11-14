package view;

import javax.swing.*;
import java.awt.event.ItemEvent;

/**
 * This class is responsible for the creation of the menu
 * bar at the top of the board.
 */
public class KanbanMenu extends JMenuBar {

    public KanbanMenu(){
        super();
        add(createFileMenu());
        add(createEditMenu());
        add(createKanbanMenu());
        add(createViewMenu());
        add(createHelpMenu());
    }

    JMenu createFileMenu(){
        // Menu items for JMenu 'file'
        JMenu file = new JMenu("File");

        ImageIcon newIcon = new ImageIcon("src/images/newdocument.jpg");
        ImageIcon openIcon = new ImageIcon("src/images/open-folder-with-document.jpg");
        ImageIcon renameIcon = new ImageIcon("src/images/rename.jpg");
        ImageIcon saveIcon = new ImageIcon("src/images/save.jpg");

        JMenuItem newBoard = new JMenuItem("New", newIcon);
        JMenuItem openBoard = new JMenuItem("Open", openIcon);
        JMenuItem renameBoard = new JMenuItem("Rename", renameIcon);
        JMenuItem saveBoard = new JMenuItem("Save", saveIcon);

        file.add(newBoard);
        file.add(new JSeparator(SwingConstants.HORIZONTAL));
        file.add(openBoard);
        file.add(new JSeparator(SwingConstants.HORIZONTAL));
        file.add(renameBoard);
        file.add(saveBoard);

        return file;
    }


    JMenu createEditMenu() {

        // Menu items for JMenu 'Edit'
        JMenu edit = new JMenu("Edit");

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

    JMenu createKanbanMenu() {

        // Menu items for JMenu 'kanban'
        JMenu kanban = new JMenu("Kanban");
        JMenuItem team = new JMenuItem("Team");
        JMenuItem settings = new JMenuItem("Settings");
        JMenuItem exitBoard = new JMenuItem("Exit Kanban");

        kanban.add(team);
        kanban.add(settings);
        kanban.add(new JSeparator(SwingConstants.HORIZONTAL));
        kanban.add(exitBoard);

        return kanban;
    }


    JMenu createViewMenu() {

        // JMenu items for View menu
        JMenu view = new JMenu("View");
        JCheckBoxMenuItem showBar = new JCheckBoxMenuItem("Show bar");
        showBar.setSelected(true);

        showBar.addItemListener((e)->{
            if (e.getStateChange() == ItemEvent.SELECTED) setVisible(true);
            else setVisible(false);
        });

        view.add(showBar);

        return view;
    }


    JMenu createHelpMenu() {

        // JMenu help
        JMenu help = new JMenu ("Help");
        ImageIcon helpIcon = new ImageIcon("src/images/question-mark.jpg");
        help.setIcon(helpIcon);

        return help;
    }

}
