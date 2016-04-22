package goetzbeer.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Place {
	String name;
	String type;
	Date updated;
	String path;

	public Place() {

	}

	public Place(String name, String type, String path, String updated) {
		this.name = name;
		this.type = type;
		this.path = path;

		try {
			if (updated.equals("")) {
				this.updated = new Date();
			} else {
				this.updated = format.parse(updated);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.updated = new Date();
		}

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

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public void setUpdated(String updated) {
		try {
			this.updated = format.parse(updated);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.updated = new Date();
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public static SimpleDateFormat getFormat() {
		return format;
	}

	public static void setFormat(SimpleDateFormat format) {
		Place.format = format;
	}

	public String toString() {
		return "[" + getName() + " , " + getType() + " , " + getUpdated() + "]";
	}

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
}
