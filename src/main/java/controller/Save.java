package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import view.boardComponents.BoardPanel;

public class Save {
   public Save() {
   }

   public static void saveBoard(BoardPanel board, String name) {

       Save obj = new Save();
       obj.serialise(board, name);
   }

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
