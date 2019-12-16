package model;

import annotations.ClassAnnotation;

@ClassAnnotation(
        classAuthors = "Jeanne",
        classEditors = "",
        creationDate = "16/12/2019",
        lastEdit = "16/12/2019"
)

/**
 * Simple object coordinates class used for placing components on editor frames
 */
public class Coordinates {

    private int x;          // x axis coordinate
    private int y;          // y axis coordinate

    // Constructor
    public Coordinates(int x, int y) {
        this.x=x;
        this.y=y;
    }

    /**
     * Get x axis coordinate
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Get y axis coordinate
     * @return y
     */
    public int getY() {
        return y;
    }
}
