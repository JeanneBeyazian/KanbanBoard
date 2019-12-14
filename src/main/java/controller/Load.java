package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import annotations.ClassAnnotation;
import view.boardComponents.BoardPanel;

@ClassAnnotation(
		classAuthors = "Trey",
		classEditors = "",
		creationDate = "22/11/2019",
		lastEdit = "10/12/2019"
)

/**
 * Class Load handles the loading of a file the user chooses.
 */
public class Load {
   public Load() {
   }

	/**
	 * Deals with the loading of a board.
	 * @param boardName
	 * @return board, a BoardPanel object
	 */
   public static BoardPanel loadBoard(String boardName) {
       Load obj = new Load();
       String filename = boardName + ".json";
       BoardPanel board = obj.deserialise(filename);
       
       return board;
   }

	/**
	 * Ensures the file chosen is the right format for the application.
	 * @param fName
	 * @return board, a BoardPanel object
	 */
   public BoardPanel deserialise(String fName) {

		BoardPanel board = null;

		FileInputStream fileName = null;
		ObjectInputStream input = null;

		try {
			fileName = new FileInputStream(fName);
			input = new ObjectInputStream(fileName);
			board = (BoardPanel) input.readObject();

		} catch (Exception ex) {
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
