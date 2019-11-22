package controller;

/** This Enum class is used to determine the most recent activities*/

public enum ActivityType {
    COLUMN_ADD("Added a column"),
    COLUMN_REMOVE("Removed a column"),
    CARD_ADD("Added a card"),
    CARD_REMOVE("Removed a card"),
    BOARD_RESET("Reset board");

    private final String description;

    ActivityType(String description){
        this.description=description;
    }

    public String getDescription(){
        return this.description;
    }
}
