package info;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.Color;

public class LobbyFrame extends JFrame {
	public JButton createRoomBtn;
	public JList list;
	private HashMap<Integer, String> roomList;
	private String[] roomNames;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private int selectedNum;
	private ImageIcon icon1;

	public LobbyFrame() {
		super("Lobby");
		setBounds(600, 600, 616, 571);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((dim.width/2)-(getWidth()/2), (dim.height/2)-(getHeight()/2));

		icon1 = new ImageIcon("C:\\Users\\kjy79\\workspace\\CrossPuzzle\\src\\info\\back2.png");
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon1.getImage(), 0, 0, 600, 560, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(50, 120, 499, 378);
		contentPane.add(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 500, 379);
		panel.add(scrollPane);

		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

		JButton logoutBtn = new JButton("로그아웃");
		logoutBtn.setBounds(439, 46, 109, 43);
		logoutBtn.setFocusable(false);
		logoutBtn.setFont(new Font("휴먼매직체", Font.BOLD, 20));
		contentPane.add(logoutBtn);

		createRoomBtn = new JButton("방만들기");
		createRoomBtn.setBounds(296, 46, 129, 43);
		createRoomBtn.setFocusable(false);
		createRoomBtn.setFont(new Font("휴먼매직체", Font.BOLD, 20));
		contentPane.add(createRoomBtn);
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public void setList(HashMap<Integer, String> o) {
		roomList = o;
		roomNames = new String[roomList.size()];

		Iterator<Integer> it = roomList.keySet().iterator();
		int i = 0;
		while(it.hasNext()) {
			int roomNum = it.next();
			roomNames[i++] =  String.valueOf(roomNum) + " 번 방 " + "  방 이름 : " + roomList.get(roomNum);
		}

		list.setListData(roomNames);
		list.setFont(new Font("휴먼매직체", Font.BOLD, 25));
		scrollPane.setViewportView(list);
	}
}
