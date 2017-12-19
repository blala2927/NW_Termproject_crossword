package info;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RoomFrame extends JFrame {

	private JPanel contentPane;
	public Map map;
	public JLabel idLabel;
	public JLabel idLabel2;
	public JButton btnStart;
	public JButton btnReady;
	public JTextArea textArea;
	public JTextField textField;
	public JButton btnExit;
	private JPanel player1Panel;
	public JPanel centerPanel;
	public JPanel westPanel;
	public JPanel player1IDPanel;
	public JPanel player2Panel;
	public JPanel player2IDPanel;
	public JPanel northPanel;
	
	/**
	 * Create the frame.
	 */
	public RoomFrame(int roomNum, String id) {
		super(String.valueOf(roomNum));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 718);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		btnStart = new JButton("게임시작");
		centerPanel.add(btnStart, BorderLayout.NORTH);
		
		westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		player1Panel = new JPanel();
		player1Panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		westPanel.add(player1Panel);
		player1Panel.setLayout(new BorderLayout(0, 0));
		
		JPanel player1IDPanel = new JPanel();
		player1Panel.add(player1IDPanel, BorderLayout.NORTH);
		
		JLabel player1ID = new JLabel("ID");
		player1IDPanel.add(player1ID);
		
		idLabel = new JLabel(id);
		player1IDPanel.add(idLabel);
		
		
		player2Panel = new JPanel();
		player2Panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		westPanel.add(player2Panel);
		
		player2IDPanel = new JPanel();
		player2Panel.add(player2IDPanel);
		
		JLabel player2ID = new JLabel("ID");
		player2IDPanel.add(player2ID);
		
		idLabel2 = new JLabel("     ");
		player2IDPanel.add(idLabel2);
		
		northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		btnReady = new JButton("준비");
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ddddd");
			}
		});
		northPanel.add(btnReady);
		
		btnExit = new JButton("방 나가기");
		northPanel.add(btnExit);
		
		JPanel southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		southPanel.add(scrollPane, BorderLayout.SOUTH);
		
		textArea = new JTextArea();
		textArea.setText(" ");
		textArea.setRows(5);
		textArea.setTabSize(40);
		textArea.setEnabled(false);
		textArea.setEditable(false);
		southPanel.add(textArea, BorderLayout.NORTH);
		
		textField = new JTextField();
		southPanel.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
	}
	
	public void enterNewUser(String newUserID) {
		idLabel2.setText(newUserID);
	}
	
	public void gameStart(Map map) {
		this.map = map;
		centerPanel.add(map.getContentPanel());
		centerPanel.revalidate();
		centerPanel.repaint();
	}

}
