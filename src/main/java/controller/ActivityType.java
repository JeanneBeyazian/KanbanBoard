package controller;

/** This Enum class is used to determine the most recent activities*/

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

    public String getDescription(){
        return this.description;
    }

    public boolean isAddition(){
        return this.addition;
    }
}
