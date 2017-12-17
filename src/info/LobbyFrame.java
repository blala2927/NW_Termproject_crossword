package info;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class LobbyFrame extends JFrame {
	public JButton createRoomBtn;
	public JList list;
	private HashMap<Integer, String> roomList;
	private String[] roomNames;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private int selectedNum;

	public LobbyFrame() {
		super("Lobby");
		setBounds(600, 600, 600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((dim.width/2)-(getWidth()/2), (dim.height/2)-(getHeight()/2));
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JButton logoutBtn = new JButton("로그아웃");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(logoutBtn, BorderLayout.NORTH);

		createRoomBtn = new JButton("방만들기");
		panel.add(createRoomBtn, BorderLayout.SOUTH);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
	}

	public void setList(HashMap<Integer, String> o) {
		roomList = o;
		roomNames = new String[roomList.size()];

		Iterator<Integer> it = roomList.keySet().iterator();
		int i = 0;
		while(it.hasNext()) {
			int roomNum = it.next();
			roomNames[i++] = String.valueOf(roomNum) + " " + roomList.get(roomNum);
		}

		list.setListData(roomNames);
		scrollPane.setViewportView(list);
	}
}
