package goetzbeer;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;

import com.google.common.base.Joiner;

import goetzbeer.comparators.CheapestBeer;
import goetzbeer.comparators.MostEfficientBeer;
import goetzbeer.comparators.StrongestBeer;
import goetzbeer.comparators.StrongestServingBeer;
import goetzbeer.data.Beer;
import goetzbeer.data.Page;
import goetzbeer.data.Place;
import goetzbeer.data.PlacePage;

public class BeerAgent {
	public static final String SEARCH_URL = "https://www.beermenus.com/search?q=";
	public static final String BASE_URL = "https://www.beermenus.com";

	String getQueryUrl(List<String> terms) {
		if (terms.isEmpty()) {
			return "";
		}
		String join = Joiner.on('+').join(terms);
		return SEARCH_URL + join;
	}

	public List<Place> findPlaces(List<String> terms, boolean fetchAll) throws IOException {
		return new Page(Jsoup.connect(getQueryUrl(terms)).get(), fetchAll).getPlaces();
	}

	public PlacePage findBeers(Place place) throws IOException {
		return new PlacePage(place, Jsoup.connect(BASE_URL + place.getPath()).get());
	}

	public List<Beer> rankBeers(PlacePage place, Comparator<Beer> comparator) {
		List<Beer> all = place.getAll();
		Collections.sort(all, comparator);
		return all;
	}

	private Map<String, Comparator<Beer>> comparators = null;

	public Map<String, Comparator<Beer>> getComparators() {
		if (comparators == null) {
			comparators = new HashMap<>();
			comparators.put("Cheapest Beer", new CheapestBeer());
			comparators.put("Strongest Beer", new StrongestBeer());
			comparators.put("Strongest Beer by Serving", new StrongestServingBeer());
			comparators.put("Most Efficient Beer", new MostEfficientBeer());
		}
		return comparators;
	}

}
