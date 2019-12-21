package datngo.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import datngo.model.Weather;


public class ReadAPI {
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      System.out.println(json.toString());
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	  
	  public static Weather getWeather(double lat, double lon) throws JSONException, IOException
	  {
		  String url = "http://api.openweathermap.org/data/2.5/weather?lat="+ lat+"&lon="+ lon+"&appid=37c901a1dac54b0eb153143ddda9b861";
		  JSONObject json = readJsonFromUrl(url);
		  JSONObject main =  json.getJSONObject("main");
		  double temp = main.getDouble("temp");
		  double humidity = main.getDouble("humidity");
		  double pressure = main.getDouble("pressure");
		  JSONObject jsonwind =  json.getJSONObject("wind");
		  double wind = jsonwind.getDouble("speed");
		  Weather weather = new Weather( temp-273, wind, humidity, pressure);
		  return weather;
	  }
	 

}
