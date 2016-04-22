package goetzbeer.comparators;

import java.util.Comparator;

import goetzbeer.data.Beer;

public class MostEfficientBeer implements Comparator<Beer> {

	@Override
	public int compare(Beer beerOne, Beer beerTwo) {
		Double beerOneAlcohol = (beerOne.getAbv() / 100.0) * beerOne.getOunces();
		Double beerTwoAlcohol = (beerTwo.getAbv() / 100.0) * beerTwo.getOunces();

		Double beerOneCPA = beerOneAlcohol / beerOne.getCost();
		Double beerTwoCPA = beerTwoAlcohol / beerTwo.getCost();

		if (beerOneCPA == beerTwoCPA) {
			return 0;
		} else if (beerOneCPA < beerTwoCPA) {
			return 1;
		} else {
			return -1;
		}
	}

}
