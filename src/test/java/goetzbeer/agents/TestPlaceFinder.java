package goetzbeer.agents;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestPlaceFinder {

	public static final String BASE_URL = "https://www.beermenus.com";

	@Test
	public void testCanCreateQueryUrl() {
		PlaceFinder testPlaceFinder = new PlaceFinder();

		List<String> testTerms = new ArrayList<>();
		testTerms.add("kings");
		testTerms.add("table");

		String createdUrl = testPlaceFinder.getQueryUrl(testTerms);

		assertEquals("https://www.beermenus.com/search?q=kings+table", createdUrl);
	}

	@Test
	public void testCanConnectToBeerMenus() {

	}
}
