package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for the storing of changes in the state of a board.
 */

public class ChangeLog {
    private List<Change> changes = new ArrayList<Change>();

    public ChangeLog(){

    }

    public void addChange(Change change){
        changes.add(change);
    }

}