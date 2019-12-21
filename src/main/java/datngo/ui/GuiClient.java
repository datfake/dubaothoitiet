package datngo.ui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import datngo.model.City;


@SuppressWarnings("serial")
public class GuiClient extends JFrame implements ActionListener {
	Client t;
	static List<City> listCity = new ArrayList<City>();  
	@SuppressWarnings("rawtypes")
	static JComboBox jComboBox = new JComboBox();
	static JLabel jLabel = new JLabel("Vui lòng chọn tên Thành phố và nhấn OK");
	private JButton button = new JButton("OK");
	private JPanel main = new JPanel();
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();

	// ---- ham dung ko doi so--------------------
	@SuppressWarnings("deprecation")
	public GuiClient() throws Exception {
		super("Weather");
		t = new Client();
		t.start();
		jp1.add(jComboBox);
		jp2.add(jLabel);
		jp3.add(button);
		main.setLayout(new BorderLayout());
		main.add(jp1, BorderLayout.NORTH);
		main.add(jp2, BorderLayout.CENTER);
		main.add(jp3, BorderLayout.SOUTH);
		button.addActionListener(this);
		this.setContentPane(main);
		this.setSize(900, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
		this.show();
		this.setVisible(true);
	}

	// ---- ham lang nghe su kien--------------------
	public void actionPerformed(ActionEvent ae) {
		try {
			if (ae.getSource() == button) {
				for (City city : GuiClient.listCity) {
					if (city.getName().equals(jComboBox.getSelectedItem().toString())) {
						t.sendMessLog(city.getId().toString());
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	// -----ham main---------------------------------
	@SuppressWarnings("unused")
	public static void main(String arg[]) throws Exception {
		GuiClient client = new GuiClient();
	}
}