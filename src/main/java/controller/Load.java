package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import view.KanbanBoard;
import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;

public class Load {
   public Load() {
   }

   public static BoardPanel loadBoard() {

       Load obj = new Load();
       BoardPanel board = obj.deserialise("Boards.json");
       
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