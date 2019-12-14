package controller.exceptions;
import annotations.ClassAnnotation;

@ClassAnnotation(
        classAuthors = "Petra",
        classEditors = "",
        creationDate = "22/11/2019",
        lastEdit = "22/11/2019"
)

/**
 *  Exception to be thrown when searched Kanban object cannot be found.
 *
 *  Optional constructor for object class name / ID
 */
public class KanbanObjectNotFoundException extends Exception {

    //class name of searched object
    private String objectType;
    //if you're looking for an object in a parent with ID
    private int parentId = -1;

    /**
     * Default constructor
     */
    public KanbanObjectNotFoundException(){
        super();
    }

    /**
     * Constructor for when searched object's class is known
     * @param objectType
     */
    public KanbanObjectNotFoundException(Class objectType){
        super();
        this.objectType = objectType.getSimpleName(); //gets the name of the class
    }

    /**
     * Constructor for when searched object's ID is known
     * @param parentId
     */
    public KanbanObjectNotFoundException(int parentId){
        super();
        this.parentId = parentId;

    }

    /**
     * Constructor for when searched object's class and parent's ID are known
     * @param objectType
     * @param parentId
     */
    public KanbanObjectNotFoundException(Class objectType, int parentId){
        super();
        this.objectType = objectType.getSimpleName(); //gets the name of the class
        this.parentId = parentId;

    }

    /**
     * Get the class name of searched object
     * @return objectType, the name of the searched object's class
     */
    public String getObjectClassName(){
        return objectType;
    }

    /**
     * Get the ID of searched object
     * @return parentId, the ID of the searched object's parent
     */
    public int getParentId(){
        return parentId;
    }

}
