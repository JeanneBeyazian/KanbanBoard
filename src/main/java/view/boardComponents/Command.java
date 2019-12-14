package view.boardComponents;

import java.time.LocalDateTime;

/**
 * This class registers any change made on the board and classifies it as a 'Command' action.
 */
public class Command {    
	
   LocalDateTime now;
   String action;
   KanbanColumn col;
   KanbanCardButton card;

	/**
	 * Constructor for the creation of new columns.
	 * @param cmd
	 * @param newCol
	 */
	public Command(String cmd, KanbanColumn newCol){
	   now = LocalDateTime.now();  
	   action = cmd;
	   col = newCol;
   }

	/**
	 * Constructor for the creation of new cards.
	 * @param cmd
	 * @param newCard
	 */
	public Command(String cmd, KanbanCardButton newCard){
	   now = LocalDateTime.now();  
	   action = cmd;
	   card = newCard;
   }

	
	
	public LocalDateTime getNow() {
	return now;
	}
	
	public void setNow(LocalDateTime now) {
		this.now = now;
	}

	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public KanbanColumn getCol() {
		return col;
	}
	
	public void setCol(KanbanColumn col) {
		this.col = col;
	}
	
	public KanbanCardButton getCard() {
		return card;
	}
	
	public void setCard(KanbanCardButton card) {
		this.card = card;
	}
   
   
   
}    
