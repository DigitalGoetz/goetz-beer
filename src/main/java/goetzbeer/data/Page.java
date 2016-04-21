package goetzbeer.data;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import goetzbeer.agents.AgentUtils;

public class Page {

	Document source;

	List<Place> places;

	boolean hasPlaces;
	boolean hasNext = false;
	boolean deepSearch;

	public Page(Document source) {
		setSource(source);
		setDeepSearch(false);
		parse();
	}

	public Page(Document source, boolean deepSearch) {
		setSource(source);
		setDeepSearch(deepSearch);
		parse();
	}

	private void parse() {
		
		Elements Buttons = getSource().select(".pure-button");
		for (Element button : Buttons) {
			if (button.hasAttr("rel")) {
				if (button.attr("rel").equals("next")) {
					if (!button.hasClass("pure-button-disabled")) {
						hasNext = true;
						break;
					}
				}
			}
		}

		Elements listings = source.getElementsByClass("pure-u-1-2");
		for (Element listing : listings) {
			if (AgentUtils.isListingPlaces(listing.children())) {
				for (Element child : listing.children()) {
					if (child.hasClass("pure-list")) {
						for (Element listItem : child.children()) {
							String name;
							String type;
							String updated;
							
							
							Elements nameEls = listItem.select(".pure-g > pure-u-1 > a");
							if(nameEls.size() == 1){
								name = nameEls.get(0).text();
							}
							
							Elements typeEls = listItem.select(".pure-g > pure-u-1 > span");
							// TODO here
							System.out.println(listItem.toString());
							System.exit(0);
						}
					}
				}
			}
		}

	}

	public Document getSource() {
		return source;
	}

	public void setSource(Document source) {
		this.source = source;
	}

	public List<Place> getPlaces() {
		if (places == null) {
			places = new ArrayList<>();
		}
		return places;
	}

	public void setDeepSearch(boolean deepSearch) {
		this.deepSearch = deepSearch;
	}

	public boolean getDeepSearch() {
		return this.deepSearch;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
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

}
