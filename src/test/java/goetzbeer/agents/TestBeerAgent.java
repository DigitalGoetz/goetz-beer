package goetzbeer.agents;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class TestBeerAgent {

	public static final String BASE_URL = "https://www.beermenus.com";

	@Test
	public void testCanCreateQueryUrl() {
		BeerAgent testPlaceFinder = new BeerAgent();

		List<String> testTerms = new ArrayList<>();
		testTerms.add("kings");
		testTerms.add("table");

		String createdUrl = testPlaceFinder.getQueryUrl(testTerms);

		assertEquals("https://www.beermenus.com/search?q=kings+table", createdUrl);
	}

	@Test
	public void testCanConnectToBeerMenus() throws IOException {
		String testUrl = "https://www.beermenus.com/search?q=kings+table";
		Connection connection = Jsoup.connect(testUrl);
		Document page = connection.get();
		assertEquals(1, page.getElementsByTag("title").size());
		assertEquals("<title>BeerMenus Search Results for kings table</title>",
				page.getElementsByTag("title").get(0).toString());
	}

	@Test
	public void testCanGetListOfPlaces() {
		List<String> testTerms = new ArrayList<>();
		testTerms.add("pub");

		BeerAgent testPlaceFinder = new BeerAgent();
		testPlaceFinder.findPlaces(testTerms);
	}
}
