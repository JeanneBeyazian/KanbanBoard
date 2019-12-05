package controller;

import java.time.format.DateTimeFormatter;

import view.boardComponents.KanbanCardButton;
import view.boardComponents.KanbanColumn;

import java.time.LocalDateTime; 

public class Command {    
	
   //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
   LocalDateTime now;
   String action;
   KanbanColumn col;
   KanbanCardButton card;
   
   public Command(String cmd, KanbanColumn newCol){
	   now = LocalDateTime.now();  
	   action = cmd;
	   col = newCol;
   }
   
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
