package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import annotations.ClassAnnotation;
import view.boardComponents.BoardPanel;

@ClassAnnotation(
        classAuthors = {"Trey"},
        creationDate = "22/11/2019",
        lastEdit = "14/12/2019"
)
/**
 * Class which deals with saving a board object.
 */
public class Save {
   public Save() {
   }

    /**
     * Method to be called when the board is saved.
     * @param board
     * @param name
     */
   public static void saveBoard(BoardPanel board, String name) {

       Save obj = new Save();
       obj.serialise(board, name);
   }

    /**
     * Saves the object to a JSON type file and checks if it is of an appropriate type.
     * @param board1
     * @param boardName
     */
   public void serialise(BoardPanel board1, String boardName) {
	   
	   String fName = boardName + ".json";
       FileOutputStream fileName = null;
       ObjectOutputStream output = null;

       try {
           fileName = new FileOutputStream(fName);
           output = new ObjectOutputStream(fileName);
           output.writeObject(board1);
       } catch (Exception var17) {
           var17.printStackTrace();
       } finally {
           if (fileName != null) {
               try {
                   fileName.close();
               } catch (IOException var16) {
                   var16.printStackTrace();
               }
           }

           if (output != null) {
               try {
                   output.close();
               } catch (IOException var15) {
                   var15.printStackTrace();
               }
           }

       }

   }
}
