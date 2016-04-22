package goetzbeer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import goetzbeer.BeerAgent;
import goetzbeer.data.Place;

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
	public void testCanGetSmallListOfPlaces() throws IOException {
		List<String> testTerms = new ArrayList<>();
		testTerms.add("dublin");
		testTerms.add("pub");

		BeerAgent testPlaceFinder = new BeerAgent();
		List<Place> places = testPlaceFinder.findPlaces(testTerms, true);

		assertTrue(placesContains("The Dublin Pub", places));
	}

	@Test
	public void testCanGetLargeListOfPlaces() throws IOException {
		List<String> testTerms = new ArrayList<>();
		testTerms.add("kings");

		BeerAgent testPlaceFinder = new BeerAgent();
		List<Place> places = testPlaceFinder.findPlaces(testTerms, true);
		assertEquals(213, places.size());
	}

	private boolean placesContains(String name, List<Place> places) {
		for (Place place : places) {
			if (place.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
