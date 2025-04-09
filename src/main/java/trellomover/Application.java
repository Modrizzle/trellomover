package trellomover;

public class Application {
	public static void main(String[] args) {
		System.out.println("Trello Mover application start!");
		
		TrelloMover trelloMover = new TrelloMover();
		trelloMover.moveToDoToDone();
		
		System.out.println("Trello Mover application end!");
	}
}
