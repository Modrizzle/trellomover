package trellomover;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class TrelloClient {

	// TODO: Put in properties file and dont commit the key and token
	private static final String KEY = "<APIKEY>";
	private static final String TOKEN = "<TOKEN>";
	private static final String TODO_LIST_ID = "<TODO_LIST_ID>";
	private static final String DONE_LIST_ID = "<DONE_LIST_ID>";

	private CloseableHttpClient httpClient;

	public TrelloClient() {
		httpClient = HttpClientBuilder.create().build();
	}
	
	public String getToDoCards() {
		String responseString = "";
		
		HttpGet cardsRequest = new HttpGet("https://api.trello.com/1/lists/" + TODO_LIST_ID + "/cards"
				+ "?key=" + KEY
				+ "&token=" + TOKEN);
		
		try (final CloseableHttpResponse response = httpClient.execute(cardsRequest)) {
			int statusCode = response.getCode();

			if (statusCode == HttpStatus.SC_OK) {
				responseString = EntityUtils.toString(response.getEntity());
			}
		} catch (final IOException | ParseException e) {
			System.err.println("Error getting to do cards: " + e.getMessage());
		}
		System.out.println("Response: " + responseString);
		return responseString;
	}
	
	public boolean moveCard(String cardId) {
		boolean success = false;
		
		HttpPut moveCardRequest = new HttpPut("https://api.trello.com/1/cards/" + cardId
				+ "?idList=" + DONE_LIST_ID
				+ "&key=" + KEY
				+ "&token=" + TOKEN);
		
		try (final CloseableHttpResponse response = httpClient.execute(moveCardRequest)) {
			int statusCode = response.getCode();

			if (statusCode == HttpStatus.SC_OK) {
				success = true;
			}
		} catch (final IOException e) {
			System.err.println("Error moving card: " + e.getMessage());
		}
		return success;
	}
}
