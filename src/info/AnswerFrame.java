package info;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.awt.Font;
import java.awt.Color;

public class AnswerFrame extends JFrame{

	private JPanel contentPane;
	private JTextField textField;
	public JButton btnSubmit;
	private int num;
	private int length;
	private int roomNum;
	private PrintWriter out;
	private ImageIcon icon1;
	private QuestionList questionList;

	public AnswerFrame(int n, int l, int rn, PrintWriter p) {
		super(String.valueOf(n) + "번 답 맞추기");
		setBounds(100, 100, 480, 337);
		questionList = QuestionList.getInstance();

		icon1 = new ImageIcon("C:\\Users\\kjy79\\workspace\\CrossPuzzle\\src\\info\\back3.png");
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon1.getImage(), 0, 0, 470, 290, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		setContentPane(contentPane);
		contentPane.setLayout(null);
		num = n;
		length = l;
		roomNum = rn;
		out = p;

		JLabel lblNewLabel = new JLabel(questionList.getQustion(num - 1));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 20));
		lblNewLabel.setBounds(41, 10, 382, 130);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(115, 155, 208, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		btnSubmit = new JButton("확인");


		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 30));
			}

			public void mouseExited(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
			}
		});

		btnSubmit.setForeground(new Color(255, 0, 102));
		btnSubmit.setFont(new Font("휴먼매직체", Font.BOLD, 25));
		btnSubmit.setBorderPainted(false);// 버튼 테두리설
		btnSubmit.setContentAreaFilled(true);// 버튼 영역 배경표시 설
		btnSubmit.setFocusPainted(false);


		btnSubmit.setBounds(115, 197, 107, 43);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length() == length) {
					JSONObject json = new JSONObject();
					json.put("type", "ANSWER");
					json.put("questionNum", num);
					json.put("answer", textField.getText());
					json.put("roomNum", 1);
					out.println(json.toString());
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "길이가 맞지 않습니다.");
				}
			}
		});
		contentPane.add(btnSubmit);

		JButton btnCancel = new JButton("취소");



		btnCancel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 30));
			}

			public void mouseExited(MouseEvent e) {
				JButton loginBtn = (JButton) e.getSource();
				loginBtn.setFont(new Font("휴먼매직체", Font.BOLD | Font.ITALIC, 20));
			}
		});

		btnCancel.setForeground(new Color(255, 0, 102));
		btnCancel.setFont(new Font("휴먼매직체", Font.BOLD, 25));
		btnCancel.setBorderPainted(false);// 버튼 테두리설
		btnCancel.setContentAreaFilled(true);// 버튼 영역 배경표시 설
		btnCancel.setFocusPainted(false);






















		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(224, 197, 99, 43);
		contentPane.add(btnCancel);
	}
}
