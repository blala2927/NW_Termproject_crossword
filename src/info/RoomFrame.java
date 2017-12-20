package info;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.SystemColor;

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
	private JPanel panel;
	ImageIcon icon1;
	private JLabel labelScore1;
	private JLabel lblNewLabel_1;
	
	/**
	 * Create the frame.
	 */
	public RoomFrame(int roomNum, String id) {
		super(String.valueOf(roomNum));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 10, 930, 1040);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		centerPanel = new JPanel();
		centerPanel.setBackground(new Color(240, 248, 255));
		centerPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		player1Panel = new JPanel();
		player1Panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		westPanel.add(player1Panel);
		player1Panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel player1IDPanel_1 = new JPanel();
		player1IDPanel_1.setBackground(new Color(211, 211, 211));
		player1Panel.add(player1IDPanel_1);
		
		JLabel player1ID = new JLabel("ID");
		 player1ID  .setFont(new Font("휴먼매직체", Font.BOLD, 20));
		player1IDPanel_1.add(player1ID);
		
		idLabel = new JLabel(id);
		player1IDPanel_1.add(idLabel);
		
		panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		player1Panel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		labelScore1 = new JLabel("점수");
		labelScore1 .setFont(new Font("휴먼매직체", Font.BOLD, 20));
		panel.add(labelScore1);
		
		lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1  .setFont(new Font("휴먼매직체", Font.BOLD, 15));
		panel.add(lblNewLabel_1);
		
		
		player2Panel = new JPanel();
		player2Panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		westPanel.add(player2Panel);
		player2Panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		player2IDPanel = new JPanel();
		player2IDPanel.setBackground(new Color(211, 211, 211));
		player2Panel.add(player2IDPanel);
		
		JLabel player2ID = new JLabel("ID");
		player2ID  .setFont(new Font("휴먼매직체", Font.BOLD, 20));
		player2IDPanel.add(player2ID);
		
		idLabel2 = new JLabel("");
		player2IDPanel.add(idLabel2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		player2Panel.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("점수");
		lblNewLabel_2 .setFont(new Font("휴먼매직체", Font.BOLD, 20));
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("0");
		lblNewLabel  .setFont(new Font("휴먼매직체", Font.BOLD, 15));
		panel_1.add(lblNewLabel);
		
		northPanel = new JPanel();
		northPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		northPanel.setBackground(SystemColor.activeCaption);
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		btnReady = new JButton("준비");
		btnReady.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 30));
			}

			public void mouseExited(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
			}
		});

		btnReady.setForeground(new Color(0, 0, 128));
		btnReady.setFont(new Font("휴먼매직체", Font.BOLD, 20));
		btnReady.setBorderPainted(false);// 버튼 테두리설
		btnReady.setContentAreaFilled(true);// 버튼 영역 배경표시 설
		btnReady.setFocusPainted(false);
		
		
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ddddd");
			}
		});
		northPanel.add(btnReady);
		
		btnStart = new JButton("게임시작");
		btnStart.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 30));
			}

			public void mouseExited(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
			}
		});

		btnStart.setForeground(new Color(0, 0, 128));
		btnStart.setFont(new Font("휴먼매직체", Font.BOLD, 20));
		btnStart.setBorderPainted(false);// 버튼 테두리설
		btnStart.setContentAreaFilled(true);// 버튼 영역 배경표시 설
		btnStart.setFocusPainted(false);
		northPanel.add(btnStart);
		
		btnExit = new JButton("방 나가기");
		btnExit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 30));
			}

			public void mouseExited(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
			}
		});

		btnExit.setForeground(new Color(0, 0, 128));
		btnExit.setFont(new Font("휴먼매직체", Font.BOLD, 20));
		btnExit.setBorderPainted(false);// 버튼 테두리설
		btnExit.setContentAreaFilled(true);// 버튼 영역 배경표시 설
		btnExit.setFocusPainted(false);
		northPanel.add(btnExit);
		
		JPanel southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setFont(new Font("굴림체", Font.PLAIN, 15));
		textArea.setForeground(Color.BLACK);
		textArea.setRows(5);
		textArea.setTabSize(40);
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		southPanel.add(scrollPane, BorderLayout.CENTER);
		
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

	public void alret(String str) {
		JOptionPane.showMessageDialog(contentPane, str);
	}
}
