package model;

import java.sql.Date;

public class Location {
	
	private String Latitude;
	private String longitude;
	private String date;
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longtitude) {
		longitude = longtitude;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


}
