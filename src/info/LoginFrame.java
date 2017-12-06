package info;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame{
	private JTextField idField = new JTextField(20);
	private JPasswordField pwField = new JPasswordField(20);
	public JButton btn = new JButton("·Î±×ÀÎ");

	public LoginFrame() {
		super("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(250, 300);
		this.getContentPane().setLayout(new BorderLayout());

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int xPos = screenSize.width/2 - this.getSize().width/2 ;
		int yPos = screenSize.height/2 - this.getSize().height/2 ;

		this.setLocation(xPos,yPos);

		Label message1 = new Label("ID");
		Label message2 = new Label("Password");
		Panel panel = new Panel();
		panel.setLayout(new FlowLayout());

		panel.add(message1);
		panel.add(idField);
		panel.add(message2);
		panel.add(pwField);
		panel.add(btn);
		this.getContentPane().add(panel, BorderLayout.CENTER);
		this.pack();
	}

	public String getId() {
		return idField.getText();
	}

	public char[] getPassword() {
		return pwField.getPassword();
	}
}
