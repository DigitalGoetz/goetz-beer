package goetzbeer.comparators;

import java.util.Comparator;

import goetzbeer.data.Beer;

public class StrongestServingBeer implements Comparator<Beer> {

	@Override
	public int compare(Beer beerOne, Beer beerTwo) {

		Double beerOneAlcohol = (beerOne.getAbv() / 100.0) * beerOne.getOunces();
		Double beerTwoAlcohol = (beerTwo.getAbv() / 100.0) * beerTwo.getOunces();
		if (beerOneAlcohol == beerTwoAlcohol) {
			return 0;
		} else if (beerOneAlcohol < beerTwoAlcohol) {
			return -1;
		} else {
			return 1;
		}

	}

}
