package info;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.List;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class LobbyFrame extends JFrame {
	public JButton createRoomBtn;
	public JList list;
	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public LobbyFrame() {
		super("Lobby");
		setBounds(600, 600, 600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
	}
	
}
