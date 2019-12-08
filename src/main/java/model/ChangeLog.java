package model;

import java.util.ArrayList;
import java.util.List;
import annotations.ClassAnnotation;

@ClassAnnotation(
        classAuthors = {"Petra"},
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

    /**
     * Private constructor so that other code cannot create new instances of
     * this class aside from the singleton instance.
     */
    private ChangeLog(){
        changes = new ArrayList<>();
    }

    /**
     * Get the list of changes stored.
     * @return changes
     */
    public List getChanges(){
        return changes;
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
    }

}