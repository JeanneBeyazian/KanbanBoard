package controller;

import annotations.ClassAnnotation;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "14/11/2019",
        lastEdit = "10/12/2019"
)
/** Enumeration class role. This class pre-defines the range of roles for columns the user can choose from :
 * Informative, Backlog, In progress, Completed.
 * Each of them has an associated java color.
 */
public enum ColumnRole {

    INFORMATIVE("Informative", new java.awt.Color(69, 175, 233)),
    BACKLOG("Backlog", new java.awt.Color(161, 83, 102)),
    IN_PROGRESS("In progress", new java.awt.Color(161, 116, 69)),
    COMPLETED("Completed", new java.awt.Color(88, 161, 79));

    private final String role;
    private final java.awt.Color columnColour;

    ColumnRole(String role, java.awt.Color columnColour) {
        this.role=role;
        this.columnColour = columnColour;
    }


    public String getColumnRole(){
        return role;
    }

    public java.awt.Color getColumnColour(){
        return columnColour;
    }

    /**
     * Get the column roles as an array of String
     * @return the array of roles
     */
    public static String[] getRoles() {

        ColumnRole[] roles = values();
        String[] rolesToStr = new String[roles.length];

        for (int i = 0; i < roles.length; i++) {
            rolesToStr[i] = roles[i].role;
        }
        return rolesToStr;
    }

}
