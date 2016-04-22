package goetzbeer.data;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PlacePage {

	Place place;
	Document source;
	String address;
	String website;
	List<Beer> featured;
	List<Beer> onTap;
	List<Beer> cans;
	List<Beer> bottles;
	List<Beer> growlers;

	public PlacePage(Place place, Document source) {
		this.place = place;
		this.source = source;
		this.featured = new ArrayList<>();
		this.onTap = new ArrayList<>();
		this.cans = new ArrayList<>();
		this.bottles = new ArrayList<>();
		this.growlers = new ArrayList<>();

		parse();
	}

	public List<Beer> getAll() {
		List<Beer> allBeer = new ArrayList<>();
		allBeer.addAll(featured);
		allBeer.addAll(onTap);
		allBeer.addAll(cans);
		allBeer.addAll(bottles);
		allBeer.addAll(growlers);
		return allBeer;
	}

	public PlaceDetails getDetails() {
		PlaceDetails details = new PlaceDetails();

		if (featured.size() > 0) {
			details.setFeaturedCount(featured.size());
		}
		if (onTap.size() > 0) {
			details.setOnTapCount(onTap.size());
		}
		if (bottles.size() > 0) {
			details.setBottleCount(bottles.size());
		}
		if (cans.size() > 0) {
			details.setCanCount(cans.size());
		}
		if (growlers.size() > 0) {
			details.setGrowlerCount(growlers.size());
		}

		return details;
	}

	private void loadVariety(String variety, List<Beer> varietyListing) {
		Elements featuredEls = source.select("#" + variety);
		if (featuredEls.size() == 1) {
			for (Element featuredBeer : featuredEls.get(0).children()) {
				if (featuredBeer.tagName().equals("li")) {
					String type = null;
					String name = null;
					Double abv = null;
					String origin = null;

					Elements nameEls = featuredBeer.select(".pure-g > .pure-u-2-3 > h3 > a");
					if (nameEls.size() == 1) {
						name = nameEls.get(0).text();
					}

					Elements typeEls = featuredBeer.select(".pure-g > .pure-u-2-3 > p");
					if (typeEls.size() > 0) {
						String base = typeEls.get(0).text();
						String[] details = base.split(" · ");
						if (details.length == 1) {
							type = details[0];
						} else if (details.length == 2) {
							String one = details[0];
							String two = details[1];

							if (one.contains("%")) {
								abv = Double.parseDouble(one.substring(0, one.length() - 2));
								origin = two;
							} else {
								type = one;
								abv = Double.parseDouble(two.substring(0, two.length() - 2));
							}

						} else if (details.length == 3) {
							type = details[0];
							abv = Double.parseDouble(details[1].substring(0, details[1].length() - 2));
							origin = details[2];
						}
					}

					Elements servingEls = featuredBeer.select(".pure-g > .pure-u-1-3 > p");
					for (Element serving : servingEls) {
						String[] servingDetails = serving.text().split(" ");
						Double ounces = Double.parseDouble(
								servingDetails[0].trim().substring(0, servingDetails[0].trim().length() - 2));
						Double cost = Double
								.parseDouble(servingDetails[2].trim().substring(1, servingDetails[2].trim().length()));

						if (name != null && abv != null && cost != null && ounces != null) {
							Beer beer = new Beer(name, type, abv, origin, ounces, cost);
							varietyListing.add(beer);
						}

					}
				}
			}
		}
	}

	private void parse() {
		loadVariety("featured", featured);
		loadVariety("on_tap", onTap);
		loadVariety("bottles", bottles);
		loadVariety("cans", cans);
		loadVariety("growlers", growlers);
		loadVariety("growlers", featured);

		loadWebsite();
		loadAddress();
	}

	private void loadWebsite() {
		// TODO
	}

	private void loadAddress() {
		// TODO
	}

}
