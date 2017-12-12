package info;

import java.awt.BorderLayout;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

public class RoomFrame extends JFrame {

	private JPanel contentPane;
	public Map1 map;
	public JLabel idLabel;
	public JLabel idLabel2;
	private JTextField textField;
	
	
	/**
	 * Create the frame.
	 */
	public RoomFrame(String roomNum, String id) {
		super(roomNum);
		
		setBounds(100, 100, 785, 718);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("New button");
		centerPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		centerPanel.add(btnNewButton_2);
		
		JPanel westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel player1Panel = new JPanel();
		player1Panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		westPanel.add(player1Panel);
		player1Panel.setLayout(new BorderLayout(0, 0));
		
		JPanel player1IDPanel = new JPanel();
		player1Panel.add(player1IDPanel, BorderLayout.NORTH);
		
		JLabel player1ID = new JLabel("ID");
		player1IDPanel.add(player1ID);
		
		idLabel = new JLabel(id);
		player1IDPanel.add(idLabel);
		
		
		JPanel player2Paenl = new JPanel();
		player2Paenl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		westPanel.add(player2Paenl);
		
		JPanel player2IDPanel = new JPanel();
		player2Paenl.add(player2IDPanel);
		
		JLabel player2ID = new JLabel("ID");
		player2IDPanel.add(player2ID);
		
		idLabel2 = new JLabel();
		player2IDPanel.add(idLabel2);
		
		JPanel northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("준비");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		northPanel.add(btnNewButton);
		
		JButton button = new JButton("방 나가기");
		northPanel.add(button);
		
		JPanel southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		southPanel.add(scrollPane, BorderLayout.SOUTH);
		
		JTextArea textArea = new JTextArea();
		textArea.setRows(5);
		textArea.setTabSize(40);
		textArea.setEnabled(false);
		textArea.setEditable(false);
		southPanel.add(textArea, BorderLayout.NORTH);
		
		textField = new JTextField();
		southPanel.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
	}

	public void setDefaultCloseOperation(WindowListener windowListener) {}
}
