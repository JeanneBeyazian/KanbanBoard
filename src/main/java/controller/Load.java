package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import view.boardComponents.BoardPanel;

public class Load {
   public Load() {
   }

   public static BoardPanel loadBoard(String boardName) {
       Load obj = new Load();
       String filename = boardName + ".json";
       BoardPanel board = obj.deserialise(filename);
       
       return board;
   }

   public BoardPanel deserialise(String fName) {

		BoardPanel board = null;

		FileInputStream fileName = null;
		ObjectInputStream input = null;

		try {
			fileName = new FileInputStream(fName);
			input = new ObjectInputStream(fileName);
			board = (BoardPanel) input.readObject();

		} catch (Exception ex) {
			System.out.println("here i am");
			ex.printStackTrace();
		} finally {

			if (fileName != null) {
				try {
					fileName.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return board;

   }


}
