package datngo.model;

public class Weather {
	private double temp;
	private double wind;
	private double Humidity;
	private double Pressure;
	
	public double getWind() {
		return wind;
	}
	public void setWind(double wind) {
		this.wind = wind;
	}
	
	
	public double getHumidity() {
		return Humidity;
	}
	public void setHumidity(double humidity) {
		Humidity = humidity;
	}
	public double getPressure() {
		return Pressure;
	}
	public void setPressure(double pressure) {
		Pressure = pressure;
	}
	public Weather() {
		super();
	}
	
	public Weather( double temp, double wind, double humidity, double pressure) {
		super();
		this.temp = temp;
		this.wind = wind;
		Humidity = humidity;
		Pressure = pressure;
	}
	@Override
	public String toString() {
		return "Tốc độ gió: " +wind +"m/s, Nhiệt độ thấp nhất: " + temp + "°C, độ ẩm : " + Humidity + "%, áp suất : " + Pressure+ "inHG";
	}
	
	
	
}
