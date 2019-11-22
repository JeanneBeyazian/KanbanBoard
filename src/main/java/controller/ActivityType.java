package controller;

import annotations.ClassAnnotation;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "22/11/2019",
        lastEdit = "22/11/2019"
)

/** This Enum class is used to determine the most recent activities.
 * Each constant has a String field that describes the change made by the user.*/

public enum ActivityType {

    COLUMN_ADD("Added a column", true),
    COLUMN_REMOVE("Removed a column", false),
    CARD_ADD("Added a card", true),
    CARD_REMOVE("Removed a card",false),
    BOARD_RESET("Reset board", false);

    private final String description;   // Description of the change
    private final boolean addition;     // Has something been added to the board?


    ActivityType(String description, boolean addition){
        this.description=description;
        this.addition=addition;
    }

    /**
     * @return description of this ActivityType
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * @return true if something has been added to the board, else return false
     */
    public boolean isAddition(){
        return this.addition;
    }
}
