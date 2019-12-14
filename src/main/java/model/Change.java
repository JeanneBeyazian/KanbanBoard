package model;


import controller.exceptions.ChangeTypeNotImplementedException;
import controller.exceptions.UnknownKanbanObjectException;
import view.KanbanBoard;
import view.boardComponents.*;
import annotations.ClassAnnotation;
import view.frames.KanbanCard;

import java.lang.Class;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@ClassAnnotation(
        classAuthors = "Petra",
        classEditors = "Jeanne",
        creationDate = "22/11/2019",
        lastEdit = "14/12/2019"
)


/**
 * This class stores the attributes of a single Change object.
 * The Change objects are then stored in the ChangeLog and displayed to the user.
 * Each Change object has a 'changeType' implemented using enums.
 */

public class Change {

    private static int id = -1;   // Unique ID

    private String objTitle;
    private ChangeType changeType;
    private String className;
    private LocalDateTime date;

    private Object obj;

    // updates
    private String updatedField = "";
    private String updatedValue = "";

    //moves
    private String newParentTitle = "";
    private Object oldParentType = null;
    private Object newParentType = null;


    /**
     * Enums are public, static and final values, here specifying the type
     * of a change which is possible.
     */
    public enum ChangeType{
        ADD, REMOVE, UPDATE, MOVE, CLEAR
    }

    /**
     * Constructor for first-time entry
     * @param changeType
     * @param objTitle
     * @param obj
     * @throws UnknownKanbanObjectException
     */
    public Change(ChangeType changeType, String objTitle, Object obj) throws UnknownKanbanObjectException {
        this.changeType = changeType;
        this.objTitle = objTitle;
        this.date = LocalDateTime.now();

        Class<?> classType = obj.getClass();

        if (classType == KanbanBoard.class || classType == BoardPanel.class) {
            className = "Board";
        } else if (classType == KanbanColumn.class) {
            className = "Column";
        } else if (classType == KanbanCard.class || classType == KanbanCardButton.class) {
            className = "Card";
        } else {
            throw new UnknownKanbanObjectException(obj.getClass());
        }
    }

    /**
     * Constructor for updated entry
     * @param changeType
     * @param objTitle
     * @param obj
     * @param updatedField
     * @param updatedValue
     * @throws UnknownKanbanObjectException
     */
    public Change(ChangeType changeType, String objTitle, Object obj, String updatedField, String updatedValue)
            throws UnknownKanbanObjectException {
        this(changeType, objTitle, obj);
        this.updatedField = updatedField;
        this.updatedValue = updatedValue;

        // truncate if length is too long
        // eg. description
        if(updatedValue.length() >= 25){
            this.updatedValue = updatedValue.substring(0,24) + "...";
        }

    }



    /**
     * Constructor for moved object
     * @param changeType
     * @param objTitle
     * @param obj
     * @param newParentTitle
     * @throws UnknownKanbanObjectException
     */
    public Change(ChangeType changeType, String objTitle, Object obj, String newParentTitle, Object newParentType,
                  Object oldParentType) throws UnknownKanbanObjectException {
        this(changeType, objTitle, obj);
        this.newParentTitle = newParentTitle;
        this.newParentType = newParentType;
        this.oldParentType = oldParentType;
    }

    /**
     * 'Pretty print' the log entries for each change variant
     * @return the 'pretty format' log change
     * @throws ChangeTypeNotImplementedException
     */
        public String formatAsString() throws ChangeTypeNotImplementedException {
            DateTimeFormatter format =  DateTimeFormatter.ofPattern("HH:mm");
            String time = "At " + getTimestamp().format(format) + " : ";

            if (changeType == ChangeType.ADD){
                return time + "Inserted new " + className +" called \"" + objTitle + "\"";
            }
            if (changeType == ChangeType.REMOVE){
                return time + "Removed the " + className +" called \"" + objTitle + "\"";
            }
            if (changeType == ChangeType.UPDATE){
                return time + "Updated the " + updatedField +" of " + className +" \"" + objTitle + "\" to \"" +
                        updatedValue + "\"";
            }
            if (changeType == ChangeType.MOVE){
                return time + "Moved \"" + objTitle +"\" to \"" + newParentTitle + "\"";
            }
            if (changeType == ChangeType.CLEAR){
                return time + "Cleared the " + className +" called \"" + objTitle + "\"";
            }
            throw new ChangeTypeNotImplementedException(changeType);
    }

    /**
     * Returns the object's title
     * @return objTitle
     */
    public String getobjTitle(){
        return objTitle;
    }

    /**
     * Returns the object's change type
     * @return changeType
     */
    public ChangeType getChangeType(){
        return changeType;
    }

    /**
     * Returns the object class' name
     * @return className
     */
    public String getClassName(){
        return className;
    }

    /**
     * Returns the current date and time of when the change was made
     * @return date
     */
    public LocalDateTime getTimestamp(){
        return date;
    }

    /**
     * Returns the reference to the Object this Change is about.
     * Object will need to be cast to e.g. KanbanCard before being used.
     * @return the Object
     */
    public Object getObject() { return obj; }


    /**
     * @return reference to new parent Object of moved Object
     */
    public Object getNewParentType(){return newParentType;}

    /**
     * @return reference to old parent Object of moved Object
     */
    public Object getOldParentType(){return newParentType;}


    public static int getId() {
        return id;
    }
}
