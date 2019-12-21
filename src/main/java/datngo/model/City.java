package datngo.model;

public class City {
	private Integer id;
	private String name;
	private double lon;
	private double lat;
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public City() {
		super();
	}
	public City(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public City(Integer id, String name, double lon, double lat) {
		super();
		this.id = id;
		this.name = name;
		this.lon = lon;
		this.lat = lat;
	}
	@Override
	public String toString() {
		return id + ","+ name;
	}
}
