package goetzbeer.agents;

import java.net.URL;
import java.util.List;

import com.google.common.base.Joiner;

public class PlaceFinder {
	public static final String SEARCH_URL = "https://www.beermenus.com/search?q=";

	public String getQueryUrl(List<String> terms) {
		if (terms.isEmpty()) {
			return "";
		}
		String join = Joiner.on('+').join(terms);
		return SEARCH_URL + join;
	}

}
