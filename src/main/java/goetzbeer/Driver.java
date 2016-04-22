package goetzbeer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import goetzbeer.comparators.MostEfficientBeer;
import goetzbeer.data.Beer;
import goetzbeer.data.Place;
import goetzbeer.data.PlacePage;

public class Driver {

	public static void main(String[] args) throws IOException {
		BeerAgent agent = new BeerAgent();
		Random random = new Random(new Date().getTime());

		List<String> terms = new ArrayList<>();
		terms.add("world");
		terms.add("of");
		terms.add("beer");
		terms.add("the");
		terms.add("vista");

		List<Place> places = agent.findPlaces(terms, false);

		Place place = places.get(random.nextInt(places.size()));

		PlacePage location = agent.findBeers(place);

		List<Beer> rankedList = agent.rankBeers(location, new MostEfficientBeer());
		for (Beer beer : rankedList) {
			System.out.println(beer.toString());
		}
	}

}
