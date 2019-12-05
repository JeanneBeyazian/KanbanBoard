package model;

import view.BoardPanel;

/**
 * This class stores the attributes a single Change object
 * requires before it is used withing the change log.
 */

public class Change{

    private String objID;
    private ChangeType changeType;
    private String className;

    /**
     * Enums are public, static and final values, here specifying the type
     * of a change which is possible.
     */
    enum ChangeType{
        ADD, REMOVE, UPDATE
    }

    public Change(ChangeType changeType, String objID, Class<?> classType) {
        this.changeType = changeType;
        this.objID = objID;
        if (classType == BoardPanel.class) { //check for the class names passed TODO: add all types of objects that we use
            className = "BoardPanel";
        }
        //else if (cls == int.class) { ... }
    }

    //if change is add/update this will acc create a new obj with the relevant change
    //public Object createObject(){
        // if classname e.g. BoardPanel
        // create new BoardPanel with stored values and return
    //}


}