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
        JMenuItem newBoard = new JMenu("New");
        JMenuItem openBoard = new JMenu("Open");
        JMenuItem renameBoard = new JMenu("Rename");
        JMenuItem saveBoard = new JMenu("Save");

        file.add(newBoard);
        file.add(openBoard);
        file.add(renameBoard);
        file.add(saveBoard);

        // Menu items for JMenu 'Edit'
        JMenu edit = new JMenu("Edit");
        JMenuItem insertColumn = new JMenuItem("Insert new column");
        JMenuItem insertCard = new JMenuItem("Insert new card");
        JMenuItem clearBoard = new JMenuItem("Clear board");

        edit.add(insertColumn);
        edit.add(insertCard);
        edit.add(clearBoard);

        // Menu items for JMenu 'kanban'
        JMenu kanban = new JMenu("Kanban");
        JMenuItem team = new JMenu("Team");
        JMenuItem settings = new JMenu("Settings");
        JMenuItem exitBoard = new JMenu("Exit Kanban");

        kanban.add(team);
        kanban.add(settings);
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
