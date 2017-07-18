package model;

public class Point {
	private String pointid;
	private String pointname;
	private String longitude;
	private String latitude;
	private int equipment;
	
	public Point(){
		pointid="";
		longitude = "";
		latitude = "";
		equipment = 0;
	}
	
	public String getPointid() {
		return pointid;
	}
	public void setPointid(String pointid) {
		this.pointid = pointid;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public int getEquipment() {
		return equipment;
	}
	public void setEquipment(int equipment) {
		this.equipment = equipment;
	}

	public String getPointname() {
		return pointname;
	}

	public void setPointname(String pointname) {
		this.pointname = pointname;
	}

}
