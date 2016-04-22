package goetzbeer.comparators;

import java.util.Comparator;

import goetzbeer.data.Beer;

public class StrongestBeer implements Comparator<Beer> {
	@Override
	public int compare(Beer beerOne, Beer beerTwo) {
		Double beerOneAbv = beerOne.getAbv();
		Double beerTwoAbv = beerTwo.getAbv();

		if (beerOneAbv == beerTwoAbv) {
			return 0;
		} else if (beerOneAbv < beerTwoAbv) {
			return -1;
		} else {
			return 1;
		}
	}
}
