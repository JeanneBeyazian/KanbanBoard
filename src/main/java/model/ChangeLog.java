package model;

import java.util.ArrayList;
import java.util.List;
import annotations.ClassAnnotation;
import view.containers.LogPanel;

@ClassAnnotation(
        classAuthors = "Petra",
        classEditors = "Jeanne",
        creationDate = "08/12/2019",
        lastEdit = "08/12/2019"
)

/**
 * This class is responsible for the storing of changes in the state of a board.
 * The class is implemented using the Singleton pattern, meaning there is only
 * one instance of the class which is accessible globally.
 *
 */

public class ChangeLog {
    private List<Change> changes;
    private static ChangeLog instance = null; // store the single instance of this class
    private List<LogPanel> listeners; // store the log panel(s) listening for log changes

    /**
     * Private constructor so that other code cannot create new instances of
     * this class aside from the singleton instance.
     */
    private ChangeLog(){
        changes = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    /**
     * Observer design pattern.
     * Add a log panel to listen for updates.
     * @param panel
     */
    public void addListener(LogPanel panel){
        listeners.add(panel);
    }

    /**
     * Observer design pattern.
     * Trigger listener log panel updates.
     */
    public void updateLogListeners(){
        for (LogPanel l : listeners){
            l.updateLog();
        }
    }

    /**
     * Get the list of changes stored.
     * @return changes
     */
    public List<Change> getChanges(){
        return changes;
    }

    /**
     * Get the last change that happened on the board.
     * @return Change object
     */
    public Change getLastChange() {
        return changes.get(changes.size()-1);
    }

    /**
     * Get the single instance of this class and create it if it doesn't exist.
     * @return instance
     */
    public static ChangeLog getInstance(){
        if(instance == null){
            instance = new ChangeLog();
        }
        return instance;
    }

    /**
     * Add a single Change object to the 'changes' list.
     * @param change
     */
    public void addChange(Change change){
        changes.add(change);
        updateLogListeners();

    }

    /**
     * Find a specific change and its position in teh changes list using its ID
     * @param changeID unique id of change to find
     * @return index of this change in log list
     */
    public int findByID(int changeID) {

        int index = -1;
        for (Change change : changes) {
            if (change.getId() == changeID) index = changes.indexOf(change);
        }
        return index;
    }

}