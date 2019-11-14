package view;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class KanbanMenu extends JMenuBar {

    public KanbanMenu(){
        super();
        initialiseMenu();
    }

    public void initialiseMenu() {

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

        // Menu items for JMenu 'Edit'
        JMenu edit = new JMenu("Edit");

        ImageIcon addIcon = new ImageIcon("src/images/plus-sign.jpg");
        ImageIcon deleteIcon = new ImageIcon("src/images/delete.jpg");
        ImageIcon clearIcon = new ImageIcon("src/images/delete-symbol.jpg");

        JMenuItem insertColumn = new JMenuItem("Insert new column", addIcon);
        JMenuItem insertCard = new JMenuItem("Insert new card", addIcon);
        JMenuItem deleteColumn = new JMenuItem("Remove a column", deleteIcon);
        JMenuItem deleteCard = new JMenuItem("Remove a card", deleteIcon);
        JMenuItem clearBoard = new JMenuItem("Clear board", clearIcon);

        edit.add(insertColumn);
        edit.add(insertCard);
        edit.add(new JSeparator(SwingConstants.HORIZONTAL));
        edit.add(deleteColumn);
        edit.add(deleteCard);
        edit.add(new JSeparator(SwingConstants.HORIZONTAL));
        edit.add(clearBoard);

        // Menu items for JMenu 'kanban'
        JMenu kanban = new JMenu("Kanban");
        JMenuItem team = new JMenuItem("Team");
        JMenuItem settings = new JMenuItem("Settings");
        JMenuItem exitBoard = new JMenuItem("Exit Kanban");

        kanban.add(team);
        kanban.add(settings);
        kanban.add(new JSeparator(SwingConstants.HORIZONTAL));
        kanban.add(exitBoard);

        // JMenus help and view
        JMenu help = new JMenu ("Help");
        JMenu view = new JMenu("View");
        JCheckBoxMenuItem showBar = new JCheckBoxMenuItem("Show bar");
        showBar.setSelected(true);

        showBar.addItemListener((e)->{
            if (e.getStateChange() == ItemEvent.SELECTED) setVisible(true);
            else setVisible(false);
        });

        view.add(showBar);
        add(file);
        add(edit);
        add(kanban);
        add(view);
        add(help);
    }


}
