package model;

public class Change{

    private String objID;
    private ChangeType changeType;
    private String className;

    public Change(ChangeType changeType, String objID, Class<?> classType) {
        this.changeType = changeType;
        this.objID = objID;
        if (classType == BoardPanel.class) {
            className = "BoardPanel";
        }
        //else if (cls == int.class) { ... }
    }

    enum ChangeType{
        ADD, REMOVE, UPDATE
    }

}