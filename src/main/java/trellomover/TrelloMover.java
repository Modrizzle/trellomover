package trellomover;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrelloMover {
	
	private TrelloClient client;

	public TrelloMover()
	{
		client = new TrelloClient();
	}

	public void moveToDoToDone()
	{
		// Get cards from to do list
		String responseString = client.getToDoCards();
		
		// Move cards that have "Done" in name to Done list
		JSONArray jsonArray = new JSONArray(responseString);

		for (int i = 0; i < jsonArray.length(); i++)
		{
			JSONObject card = jsonArray.getJSONObject(i);
			String cardName = card.getString("name");
			if (cardName.contains("Done")) {
				client.moveCard(card.getString("id"));
				System.out.println("Moved card " + cardName + " to done list!");
			}
		}
	}
}
