package controller.exceptions;
import annotations.ClassAnnotation;

@ClassAnnotation(
        classAuthors = "Petra",
        classEditors = "",
        creationDate = "22/11/2019",
        lastEdit = "08/12/2019"
)

/**
 *  Exception to be thrown when logging a Kanban Object change when object type isn't recognised.
 *
 *  Optional constructor for incorrect Object class.
 */
public class UnknownKanbanObjectException extends Exception {

    //class name of searched object
    private String objectType;

    /**
     * Default constructor
     */
    public UnknownKanbanObjectException(){
        super();
    }

    /**
     * Constructor for when failed log object's class is known
     * @param objectType
     */
    public UnknownKanbanObjectException(Class objectType){
        super();
        this.objectType = objectType.getSimpleName(); //gets the name of the class
    }

    /**
     * Get the class name of logged object
     * @return objectType, the name of the logged object's class
     */
    public String getObjectClassName(){
        return objectType;
    }


}
