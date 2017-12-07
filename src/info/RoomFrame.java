package info;

import java.awt.BorderLayout;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class RoomFrame extends JFrame {

	private JPanel contentPane;
	public JButton exitRoomBtn;
	
	/**
	 * Create the frame.
	 */
	public RoomFrame(String roomNum) {
		super(roomNum);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		exitRoomBtn = new JButton("방 나가기");
		contentPane.add(exitRoomBtn, BorderLayout.SOUTH);
	}

	public void setDefaultCloseOperation(WindowListener windowListener) {}

}
