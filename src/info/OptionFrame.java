package info;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
	/**
	 * Create the frame.
	 */
	public OptionFrame() {
		super("방만들기");
		setBounds(100, 100, 293, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {112, 112, 112};
		gbl_contentPane.rowHeights = new int[]{84, 84, 84, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel label = new JLabel("방이름");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		contentPane.add(label, gbc_label);
		
		roomNameText = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPane.add(roomNameText, gbc_textField);
		roomNameText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("모드");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		
		JRadioButton radio1 = new JRadioButton("개인전");
		radio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMode("single");
			}
		});
		panel.add(radio1);
		
		JRadioButton radio2 = new JRadioButton("팀전");
		radio2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMode("team");
			}
		});
		panel.add(radio2);
		
		JLabel lblNewLabel_2 = new JLabel("인원제한");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		peopleNumText = new JTextField();
		GridBagConstraints gbc_peopleNumText = new GridBagConstraints();
		gbc_peopleNumText.fill = GridBagConstraints.HORIZONTAL;
		gbc_peopleNumText.insets = new Insets(0, 0, 5, 0);
		gbc_peopleNumText.gridx = 1;
		gbc_peopleNumText.gridy = 2;
		contentPane.add(peopleNumText, gbc_peopleNumText);
		peopleNumText.setColumns(10);
		
		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridwidth = 2;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 3;
		contentPane.add(splitPane, gbc_splitPane);
		
		btnCreateRoom = new JButton("방만들기");
		splitPane.setLeftComponent(btnCreateRoom);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		splitPane.setRightComponent(btnCancle);
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}
}
