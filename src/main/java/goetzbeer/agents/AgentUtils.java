package goetzbeer.agents;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AgentUtils {

	public static boolean isListingBeer(Elements listing) {
		for (Element element : listing) {
			if (element.hasText()) {
				if (element.text().equals("Beers")) {
					return true;
				}
			}
		}

		return false;

	}

	public static boolean isListingPlaces(Elements listing) {
		for (Element element : listing) {
			if (element.hasText()) {
				if (element.text().equals("Places")) {
					return true;
				}
			}
		}

		return false;

	}
}
