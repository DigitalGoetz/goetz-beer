package goetzbeer.data;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import goetzbeer.BeerAgent;

public class TestPlacePage {

	@Test
	public void testPageCreation() throws IOException {
		Place place = new Place();
		place.setName("Fox and Hound Aurora ");
		place.setPath("/places/22784-fox-and-hound-aurora");
		place.setType("testbar");
		place.setUpdated(new Date());

		Document testDoc = Jsoup.connect(BeerAgent.BASE_URL + place.getPath()).get();

		PlacePage p = new PlacePage(place, testDoc);
		List<Beer> f = p.featured;
		for (Beer beer : f) {
			System.out.println(beer.toString());
		}

		assertEquals(14, f.size());
	}
}
