package controller;

import annotations.ClassAnnotation;

@ClassAnnotation(
        classAuthors = {"Jeanne"},
        creationDate = "14/11/2019",
        lastEdit = "22/11/2019"
)
/** Enumeration class role. This class pre-defines the range of roles
 * for columns the user can choose from.
 */

public enum ColumnRole {
    INFORMATIVE("Informative"),
    BACKLOG("Backlog"),
    IN_PROGRESS("In progress"),
    COMPLETED("Completed");

    private final String role;

    ColumnRole(String role){
        this.role=role;
    }

    /**
     * Get the column roles as an array of String
     * @return the array of roles
     */

    public String getColumnRole(){
        return role;
    }

    public static String[] getRoles() {

        ColumnRole[] roles = values();
        String[] rolesToStr = new String[roles.length];

        for (int i = 0; i < roles.length; i++) {
            rolesToStr[i] = roles[i].role;
        }
        return rolesToStr;

    }

}
