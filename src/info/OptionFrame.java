package info;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class OptionFrame extends JFrame {

	private JPanel contentPane;
	public JTextField peopleNumText;
	public String mode;
	public JTextField roomNameText;
	public JButton btnCreateRoom;

	private ImageIcon icon1;
	/**
	 * Create the frame.
	 */
	public OptionFrame() {
		super("방만들기");
		setBounds(100, 100, 400, 535);

		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("방이름");
		label.setBounds(42, 102, 83, 49);
		label.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 30));
		contentPane.add(label);

		roomNameText = new JTextField();
		roomNameText.setBounds(181, 102, 140, 37);
		contentPane.add(roomNameText);
		roomNameText.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("모드");
		lblNewLabel_1.setBounds(42, 167, 89, 100);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 30));
		contentPane.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(154, 193, 191, 43);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		contentPane.add(panel);

		JRadioButton radio1 = new JRadioButton("개인전");
		radio1.setBackground(Color.WHITE);
		radio1.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
		radio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMode("single");
			}
		});
		panel.add(radio1);

		JRadioButton radio2 = new JRadioButton("팀전");
		radio2.setBackground(Color.WHITE);
		radio2.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
		radio2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMode("team");
			}
		});
		panel.add(radio2);

		JLabel lblNewLabel_2 = new JLabel("인원제한");
		lblNewLabel_2.setBounds(27, 266, 122, 92);
		lblNewLabel_2.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_2);

		peopleNumText = new JTextField();
		peopleNumText.setBounds(185, 297, 140, 37);
		contentPane.add(peopleNumText);
		peopleNumText.setColumns(10);
		btnCreateRoom = new JButton("방만들기");
		btnCreateRoom.setBounds(27, 385, 166, 51);
		btnCreateRoom.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 30));
			}

			public void mouseExited(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
			}
		});

		btnCreateRoom.setForeground(new Color(255, 0, 102));
		btnCreateRoom.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
		btnCreateRoom.setBorderPainted(false);// 버튼 테두리설
		btnCreateRoom.setContentAreaFilled(true);// 버튼 영역 배경표시 설
		btnCreateRoom.setFocusPainted(false);
		contentPane.add(btnCreateRoom);
		
				JButton btnCancle = new JButton("나가기");
				btnCancle.setBounds(221, 385, 151, 51);
				contentPane.add(btnCancle);
				
				btnCancle.addMouseListener(new MouseAdapter() {
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
				

				btnCancle.setForeground(new Color(255, 0, 102));
				btnCancle.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
				btnCancle.setBorderPainted(false);// 버튼 테두리설
				btnCancle.setContentAreaFilled(true);// 버튼 영역 배경표시 설
				btnCancle.setFocusPainted(false);
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}
}
