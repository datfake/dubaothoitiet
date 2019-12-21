package datngo.ui;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import datngo.model.City;


public class Client extends Thread {
	DataInputStream din;
	DataOutputStream dos;
	Socket socket;
	@SuppressWarnings("unused")
	private String sendMess;
	private String revMess;

	// --- ham dung khong doi so----------------------------------
	public Client() throws Exception {
		socket = new Socket("localhost", 7887);
		din = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		sendMess = "Wellcome";
	}

	// --- ham gan gia tri xau se duoc gui di---------------
	public void setSendMess(String mes) {
		sendMess = mes;
	}

	// --- ham gan gia tri khac cho xau nhan----------------
	public void setRevMess(String mes) {
		revMess = mes;
	}

	// -------ham lay gia tri cua revMess----
	public String getRevMess() {
		return revMess;
	}

	// --- ham gui xau messLog len server------------------
	public void sendMessLog(String messLog) throws Exception {
		dos.writeUTF(messLog);
	}

	// --- ham run (overwrite)------------------
	@SuppressWarnings("unchecked")
	public void run() {
		try {
			while (true) {
				revMess = din.readUTF();
				String st = revMess.substring(0, 1);
				if (st.equals("@")) {
					String[] arrct = revMess.split("@");
					for (int i = 1; i < arrct.length; i++) {
						String[] arrObj = arrct[i].split(",");
						City obj = new City(Integer.parseInt(arrObj[0]), arrObj[1]);
						GuiClient.listCity.add(obj);
						GuiClient.jComboBox.addItem(arrObj[1]);
					}
				} else
					GuiClient.jLabel.setText(revMess);
				dos.flush();
			}
		} catch (Exception e) {
		}
	}
}