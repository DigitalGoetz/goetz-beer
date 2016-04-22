package goetzbeer.data;

public class Beer {

	String name;
	String type;
	String origin;

	Double abv;
	Double ounces;
	Double cost;

	public Beer(String name, String type, Double abv, String origin, Double ounces, Double cost) {
		this.name = name;
		this.type = type;
		this.origin = origin;

		this.cost = cost;
		this.ounces = ounces;
		this.abv = abv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Double getAbv() {
		return abv;
	}

	public void setAbv(Double abv) {
		this.abv = abv;
	}

	public Double getOunces() {
		return ounces;
	}

	public void setOunces(Double ounces) {
		this.ounces = ounces;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String toString() {
		return "[" + name + " , " + abv + "% , $" + cost + " " + ounces + "oz ]";
	}

}
