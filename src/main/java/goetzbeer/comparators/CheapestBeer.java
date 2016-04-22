package goetzbeer.comparators;

import java.util.Comparator;

import goetzbeer.data.Beer;

public class CheapestBeer implements Comparator<Beer> {
	@Override
	public int compare(Beer beerOne, Beer beerTwo) {
		Double beerOneCost = beerOne.getCost();
		Double beerTwoCost = beerTwo.getCost();
		if (beerOneCost == beerTwoCost) {
			return 0;
		} else if (beerOneCost < beerTwoCost) {
			return -1;
		} else {
			return 1;
		}
	}
}
