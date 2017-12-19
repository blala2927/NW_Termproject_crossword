package info;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnswerFrame extends JFrame{
	
	private JPanel contentPane;
	private JTextField textField;
	public JButton btnSubmit;
	
	public AnswerFrame(int num) {
		super(String.valueOf(num) + "�� �� ���߱�");
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(17, 10, 400, 130);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(160, 155, 116, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSubmit = new JButton("Ȯ��");
		btnSubmit.setBounds(160, 197, 57, 23);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("���");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(222, 197, 57, 23);
		contentPane.add(btnCancel);
	}
}
