package goetzbeer.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Page {

	Document source;

	boolean hasPlaces = false;
	List<Place> places;

	boolean fetchAll;

	boolean hasNext = false;
	String nextPath;
	Page nextPage;

	public Page(Document source, boolean fetchAll) {
		places = new ArrayList<>();
		setSource(source);
		fetchAll(fetchAll);
		parse();
	}

	private void parse() {

		Elements Buttons = getSource().select(".pure-button");
		for (Element button : Buttons) {
			if (button.hasAttr("rel")) {
				if (button.attr("rel").equals("next")) {
					if (!button.hasClass("pure-button-disabled")) {
						hasNext = true;
						nextPath = button.attr("href");
						break;
					}
				}
			}
		}

		Elements listings = source.getElementsByClass("pure-u-1-2");
		for (Element listing : listings) {
			if (isListingPlaces(listing.children())) {
				for (Element child : listing.children()) {
					if (child.hasClass("pure-list")) {
						for (Element listItem : child.children()) {
							String name = null;
							String type = null;
							String path = null;
							String updated = null;

							Elements nameEls = listItem.select(".pure-g > .pure-u-1 > h3 > a");
							if (nameEls.size() == 1) {
								name = nameEls.get(0).text();
								path = nameEls.get(0).attr("href");
							}

							Elements typeEls = listItem.select(".pure-g > .pure-u-1 > h3 > span");
							if (typeEls.size() == 1) {
								String typeString = typeEls.get(0).text();
								type = typeString.substring(2, typeString.length());
							}

							Elements dateEls = listItem.select(".pure-g > .pure-u-1 > p.mb-0 > time");
							if (dateEls.size() == 1) {
								updated = dateEls.get(0).attr("datetime");
							}

							if (name != null && path != null && updated != null) {
								if (type == null) {
									type = "";
								}
								Place place = new Place(name, type, path, updated);
								places.add(place);
							}
						}
					}
				}
			}
		}

		if (hasNext() && isFetchAll()) {
			try {
				nextPage = new Page(Jsoup.connect(BASE_URL + getNextPath()).get(), isFetchAll());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
	}

	public Document getSource() {
		return source;
	}

	public void setSource(Document source) {
		this.source = source;
	}

	public void fetchAll(boolean fetchAll) {
		this.fetchAll = fetchAll;
	}

	public boolean isFetchAll() {
		return this.fetchAll;
	}

	public boolean hasPlaces() {
		return hasPlaces;
	}

	public void hasPlaces(boolean hasPlaces) {
		this.hasPlaces = hasPlaces;
	}

	public boolean hasNext() {
		return hasNext;
	}

	public void hasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public String getNextPath() {
		return this.nextPath;
	}

	public void setNextPath(String nextPath) {
		this.nextPath = nextPath;
	}

	public List<Place> getPlaces() {
		if (nextPage != null) {
			places.addAll(nextPage.getPlaces());
		}
		return places;
	}

	private boolean isListingPlaces(Elements listing) {
		for (Element element : listing) {
			if (element.hasText()) {
				if (element.text().equals("Places")) {
					return true;
				}
			}
		}
		return false;
	}

	public static String BASE_URL = "https://www.beermenus.com";
}
