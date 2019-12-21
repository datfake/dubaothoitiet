package datngo.ui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import datngo.api.ReadAPI;
import datngo.dao.CityDao;
import datngo.model.City;
import datngo.model.Weather;

public class Server {
	private ServerSocket server;
	DataOutputStream dos;
	DataInputStream din;
	ArrayList<City> lists =null;;
	// --- ham dung 1 doi so--------------------------
	public Server(int so) throws Exception {
		server = new ServerSocket(so);
		System.out.println("server started!!!");
	}

	// --- ham tra ve xau chua ten cac city phan biet boi ki tu '@'-----

	public String getCity() throws IOException {
		String json = "";
		CityDao cityDao = new  CityDao();
		lists = cityDao.getCitys();
		for (City city : lists) {
			json += "@" + city.toString();
		}
		return json;
	}

	// -----ham chay cua server---------------------------------------
	public void run() {
		try {
			Weather weather = null;
			boolean ck = true;
			Socket sk = server.accept();
			dos = new DataOutputStream(sk.getOutputStream());
			din = new DataInputStream(sk.getInputStream());
			while (true) {
				if (ck) {
					dos.writeUTF(getCity());
					ck = false;
				}
				String idCity = din.readUTF();
				for (City city : lists) {
					if(city.getId() == Integer.parseInt(idCity) )
					{
						weather = ReadAPI.getWeather(city.getLat(), city.getLon());
					}
				}
				
				dos.writeUTF(weather.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// --- ham main--------------------------------------------------
	public static void main(String[] args) throws Exception {
		Server ser = new Server(7887);
		ser.run();
	}
}