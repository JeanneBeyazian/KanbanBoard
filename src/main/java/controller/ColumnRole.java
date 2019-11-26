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
    INFORMATIVE, BACKLOG, IN_PROGRESS, COMPLETED;
}
