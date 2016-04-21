package goetzbeer.agents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.google.common.base.Joiner;

import goetzbeer.data.Page;
import goetzbeer.data.Place;

public class BeerAgent {
	public static final String SEARCH_URL = "https://www.beermenus.com/search?q=";

	public String getQueryUrl(List<String> terms) {
		if (terms.isEmpty()) {
			return "";
		}
		String join = Joiner.on('+').join(terms);
		return SEARCH_URL + join;
	}

	public List<Place> findPlaces(List<String> terms) {

		List<Place> places = new ArrayList<>();

		try {
			Connection connection = Jsoup.connect(getQueryUrl(terms));
			Page page = new Page(connection.get());
			if (page.hasNext()) {
				System.out.println("has more");
			} else {
				System.out.println("has no more");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return places;
	}

}
