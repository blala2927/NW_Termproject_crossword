package info;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class LobbyFrame extends JFrame {
	public JButton createRoomBtn;
	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public LobbyFrame() {
		super("Lobby");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton, BorderLayout.NORTH);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2, BorderLayout.EAST);
		
		createRoomBtn = new JButton("방만들기");
		panel.add(createRoomBtn, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
	}
	
}
