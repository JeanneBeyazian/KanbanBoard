package controller.exceptions;
import annotations.ClassAnnotation;

@ClassAnnotation(
        classAuthors = {"Petra"},
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
    //if you're looking for an object by ID
    private int objectId = -1;

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
     * @param objectId
     */
    public KanbanObjectNotFoundException(int objectId){
        super();
        this.objectId = objectId;

    }

    /**
     * Constructor for when searched object's class and ID are known
     * @param objectType
     * @param objectId
     */
    public KanbanObjectNotFoundException(Class objectType, int objectId){
        super();
        this.objectType = objectType.getSimpleName(); //gets the name of the class
        this.objectId = objectId;

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
     * @return objectId, the ID of the searched object
     */
    public int getObjectId(){
        return objectId;
    }

}
