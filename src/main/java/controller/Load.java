package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import view.boardComponents.BoardPanel;
import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;

public class Load {
   public Load() {
   }

   public static void loadBoard() {

       Load obj = new Load();
       BoardPanel board = obj.deserialise("Boards.json");
       
       ArrayList<KanbanColumn> cols = board.getColumns();
       
       BoardPanel board1 = new BoardPanel();
       
       for(int i = 0; i < cols.size(); i++) {
    	   KanbanColumn col = cols.get(i);
    	   ArrayList<KanbanCardButton> cards = cols.get(i).getCards();
    	   for(int j = 0; j < cards.size(); j++) {
    		   KanbanCardButton card = cards.get(j);
    		   KanbanColumn boardCol = new KanbanColumn(col.getColumnTitle(), col.getRole());
    		   boardCol.addCard(card);
    	   }
    	   board1.addColumn(col);
       }
       
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