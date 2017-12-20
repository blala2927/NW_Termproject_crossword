package info;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public class LoginFrame extends JFrame {
	/*
	 * LoginFrame
	 * Client can login with this frame
	 */
	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField pwField;
	MouseListener listener;
	public JButton loginBtn;
	ImageIcon icon1;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("crossword");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 361, 505);
		icon1 = new ImageIcon("C:\\Users\\kjy79\\workspace\\CrossPuzzle\\src\\info\\back1.png");
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon1.getImage(), 0, 0, 380, 500, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnCancel = new JButton("Exit");
		btnCancel.setLocation(194, 391);
		btnCancel.setSize(141, 50);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton cancelBtn = (JButton) e.getSource();
				cancelBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 30));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JButton cancelBtn = (JButton) e.getSource();
				cancelBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
			}
		});
		btnCancel.setForeground(new Color(255, 0, 102));
		btnCancel.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
		btnCancel.setOpaque(false);
		btnCancel.setBorderPainted(false);// 버튼 테두리설
		btnCancel.setContentAreaFilled(true);// 버튼 영역 배경표시 설
		btnCancel.setFocusPainted(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		loginBtn = new JButton("Login");
		loginBtn.setLocation(6, 391);
		loginBtn.setSize(176, 50);
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 30));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
			}
		});

		JLabel lblNewLabel = new JLabel("PW");
		lblNewLabel.setBounds(56, 349, 35, 30);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));

		idField = new JTextField();
		idField.setBounds(98, 292, 200, 30);
		contentPane.add(idField);

		idField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(56, 294, 100, 30);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));

		pwField = new JPasswordField();

		pwField.setBounds(98, 347, 200, 30);
		pwField.setColumns(10);
		contentPane.add(pwField);

		loginBtn.setForeground(new Color(255, 0, 102));
		loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
		loginBtn.setBorderPainted(false);// 버튼 테두리설
		loginBtn.setContentAreaFilled(true);
		contentPane.add(loginBtn);
		loginBtn.addMouseListener(listener);
		contentPane.add(btnCancel);

		setResizable(false);
	}

	public String getId() {
		return idField.getText();
	}

	public char[] getPassword() {
		return pwField.getPassword();
	}

}