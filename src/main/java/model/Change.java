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
        classAuthors = {"Petra"},
        creationDate = "22/11/2019",
        lastEdit = "08/12/2019"
)


/**
 * This class stores the attributes a single Change object
 * requires before it is used withing the change log.
 */

public class Change {

    private String objTitle;
    private ChangeType changeType;
    private String className;
    private LocalDateTime date;

    // updates
    private String updatedField = "";
    private String updatedValue = "";

    //moves
    private String newParentTitle = "";


    /**
     * Enums are public, static and final values, here specifying the type
     * of a change which is possible.
     */
    public enum ChangeType{
        ADD, REMOVE, UPDATE, MOVE
    }

    /**
     * Constructor for first-time entry
     * @param changeType
     * @param objTitle
     * @param classType
     * @throws UnknownKanbanObjectException
     */
    public Change(ChangeType changeType, String objTitle, Class<?> classType) throws UnknownKanbanObjectException {
        this.changeType = changeType;
        this.objTitle = objTitle;
        this.date = LocalDateTime.now();

        if (classType == KanbanBoard.class) {
            className = "Board";
        } else if (classType == KanbanColumn.class) {
            className = "Column";
        } else if (classType == KanbanCard.class) {
            className = "Card";
        } else {
            throw new UnknownKanbanObjectException(classType);
        }
    }

    /**
     * Constructor for updated entry
     * @param changeType
     * @param objTitle
     * @param classType
     * @param updatedField
     * @param updatedValue
     * @throws UnknownKanbanObjectException
     */
    public Change(ChangeType changeType, String objTitle, Class<?> classType, String updatedField, String updatedValue)
            throws UnknownKanbanObjectException {
        this(changeType, objTitle, classType);
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
     * @param classType
     * @param newParentTitle
     * @throws UnknownKanbanObjectException
     */
    public Change(ChangeType changeType, String objTitle, Class<?> classType, String newParentTitle)
            throws UnknownKanbanObjectException {
        this(changeType, objTitle, classType);
        this.newParentTitle = newParentTitle;

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

}