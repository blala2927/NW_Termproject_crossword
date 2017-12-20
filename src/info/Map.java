package info;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Map implements Serializable {

	/*
	 * Map class
	 * game map 
	 */
	
	private JPanel panel;
	private JTextField[] textField = new JTextField[400];
	public JLabel question1;
	public JLabel question2;
	public JLabel question3;
	public JLabel question4;
	public JLabel question5;
	public JLabel question6;
	public JLabel question7;
	public JLabel question8;
	public JLabel question9;
	public JLabel question10;
	public JLabel question11;
	public JLabel question12;
	public JLabel question13;
	public JLabel question14;
	public JLabel question15;
	public JLabel question16;
	public JLabel question17;
	public JLabel question18;
	public JLabel question19;
	public JLabel question20;
	public JLabel question21;
	public JLabel question22;
	public int[][] answer = {{0, 0, 11}, {1, 1, 8}, {1, 7 ,9}, {0, 45, 12},
			{1, 34, 9}, {0, 141, 5}, {1, 145, 3}, {0, 165, 8},
			{1, 171, 4}, {1, 220, 5}, {0, 220, 4}, {1, 222, 4},
			{0, 242, 6}, {1, 247, 3}, {0, 287, 6}, {0, 229, 3},
			{1, 230, 8}, {0, 349, 11}, {1, 216, 9}, {1, 118, 14},
			{0, 341, 6}, {1, 346, 3}};//타입(0 가로, 1 세로), 번호, 시작 인덱스, 길이 
	/**
	 * Create the frame.
	 */
	public Map() {
		panel = new JPanel();
		panel.setBounds(100, 100, 974, 951);
		panel.setLayout(null);
		
		question1 = new JLabel("1");
		question1.setFont(new Font("Calibri", Font.PLAIN, 10));
		question1.setHorizontalAlignment(SwingConstants.LEFT);
		question1.setBounds(9, 7, 22, 18);
		panel.add(question1);
		
		question2 = new JLabel("2");
		question2.setFont(new Font("Calibri", Font.PLAIN, 10));
		question2.setHorizontalAlignment(SwingConstants.LEFT);
		question2.setBounds(49, 7, 22, 18);
		panel.add(question2);
		
		question3 = new JLabel("3");
		question3.setFont(new Font("Calibri", Font.PLAIN, 10));
		question3.setHorizontalAlignment(SwingConstants.LEFT);
		question3.setBounds(289, 7, 22, 18);
		panel.add(question3);
		
		question4 = new JLabel("4");
		question4.setFont(new Font("Calibri", Font.PLAIN, 10));
		question4.setHorizontalAlignment(SwingConstants.LEFT);
		question4.setBounds(209, 87, 22, 18);
		panel.add(question4);
		
		question5 = new JLabel("5");
		question5.setFont(new Font("Calibri", Font.PLAIN, 10));
		question5.setHorizontalAlignment(SwingConstants.LEFT);
		question5.setBounds(569, 47, 22, 18);
		panel.add(question5);
		
		question6 = new JLabel("6");
		question6.setFont(new Font("Calibri", Font.PLAIN, 10));
		question6.setHorizontalAlignment(SwingConstants.LEFT);
		question6.setBounds(49, 287, 22, 18);
		panel.add(question6);
		
		question7 = new JLabel("7");
		question7.setFont(new Font("Calibri", Font.PLAIN, 10));
		question7.setHorizontalAlignment(SwingConstants.LEFT);
		question7.setBounds(209, 287, 22, 18);
		panel.add(question7);
		
		question8 = new JLabel("8");
		question8.setHorizontalAlignment(SwingConstants.LEFT);
		question8.setFont(new Font("Calibri", Font.PLAIN, 10));
		question8.setBounds(209, 327, 22, 18);
		panel.add(question8);

		question9 = new JLabel("9");
		question9.setFont(new Font("Calibri", Font.PLAIN, 10));
		question9.setHorizontalAlignment(SwingConstants.LEFT);
		question9.setBounds(449, 327, 22, 18);
		panel.add(question9);
		
		question10 = new JLabel("10");
		question10.setFont(new Font("Calibri", Font.PLAIN, 10));
		question10.setHorizontalAlignment(SwingConstants.LEFT);
		question10.setBounds(9, 447, 22, 18);
		panel.add(question10);
		
		question11 = new JLabel("11");
		question11.setHorizontalAlignment(SwingConstants.RIGHT);
		question11.setFont(new Font("Calibri", Font.PLAIN, 10));
		question11.setBounds(20, 447, 22, 18);
		panel.add(question11);

		question12 = new JLabel("12");
		question12.setFont(new Font("Calibri", Font.PLAIN, 10));
		question12.setHorizontalAlignment(SwingConstants.LEFT);
		question12.setBounds(89, 447, 22, 18);
		panel.add(question12);
		
		question13 = new JLabel("13");
		question13.setFont(new Font("Calibri", Font.PLAIN, 10));
		question13.setHorizontalAlignment(SwingConstants.LEFT);
		question13.setBounds(89, 487, 22, 18);
		panel.add(question13);
		
		question14 = new JLabel("14");
		question14.setFont(new Font("Calibri", Font.PLAIN, 10));
		question14.setHorizontalAlignment(SwingConstants.LEFT);
		question14.setBounds(289, 487, 22, 18);
		panel.add(question14);
		
		question15 = new JLabel("15");
		question15.setHorizontalAlignment(SwingConstants.LEFT);
		question15.setFont(new Font("Calibri", Font.PLAIN, 10));
		question15.setBounds(289, 567, 22, 18);
		panel.add(question15);
		
		question16 = new JLabel("16");
		question16.setFont(new Font("Calibri", Font.PLAIN, 10));
		question16.setHorizontalAlignment(SwingConstants.LEFT);
		question16.setBounds(368, 447, 22, 18);
		panel.add(question16);
		
		question17 = new JLabel("17");
		question17.setFont(new Font("Calibri", Font.PLAIN, 10));
		question17.setHorizontalAlignment(SwingConstants.LEFT);
		question17.setBounds(408, 447, 22, 18);
		panel.add(question17);
		
		question18 = new JLabel("18");
		question18.setFont(new Font("Calibri", Font.PLAIN, 10));
		question18.setHorizontalAlignment(SwingConstants.LEFT);
		question18.setBounds(369, 687, 22, 18);
		panel.add(question18);
		
		question19 = new JLabel("19");
		question19.setHorizontalAlignment(SwingConstants.LEFT);
		question19.setFont(new Font("Calibri", Font.PLAIN, 10));
		question19.setBounds(649, 407, 22, 18);
		panel.add(question19);
		
		question20 = new JLabel("20");
		question20.setFont(new Font("Calibri", Font.PLAIN, 10));
		question20.setHorizontalAlignment(SwingConstants.LEFT);
		question20.setBounds(729, 207, 22, 18);
		panel.add(question20);
		
		question21 = new JLabel("21");
		question21.setHorizontalAlignment(SwingConstants.LEFT);
		question21.setFont(new Font("Calibri", Font.PLAIN, 10));
		question21.setBounds(49, 687, 22, 18);
		panel.add(question21);
		
		question22 = new JLabel("22");
		question22.setHorizontalAlignment(SwingConstants.LEFT);
		question22.setFont(new Font("Calibri", Font.PLAIN, 10));
		question22.setBounds(249, 687, 22, 18);
		panel.add(question22);
		
		
		int x = 0, y = 0;

		for(int i = 0; i < 400; i++) {
			textField[i] = new JTextField();
			textField[i].setFont(new Font("Arial", Font.BOLD, 17));
			textField[i].setBackground(new Color(255, 255, 255));
			textField[i].setEditable(false);
			textField[i].setHorizontalAlignment(SwingConstants.CENTER);
			if(i % 20 == 0 && i != 0) {
				x = 0;
				y = y + 40;
			}
			textField[i].setBounds(5 + x , 5 + y, 40, 40);
			panel.add(textField[i]);
			x = x + 40;
		}

		for(int i = 0; i < 22; i++) {
			int type = answer[i][0];
			int index = answer[i][1];
			int length = answer[i][2];

			if(type == 0) {
				for(int a = index; a < index + length; a++)
					textField[a].setBackground(new Color(135, 206, 250));
			}
			else
				for(int a = index; a <= index + (length - 1) * 20; a = a + 20)
					textField[a].setBackground(new Color(135, 206, 250));
		}
						//45, 5, 40, 40
	}

	public void setAnswerText(String str, int questionNum) {
		int type = answer[questionNum - 1][0];
		int index = answer[questionNum - 1][1];
		int length = answer[questionNum - 1][2];
		
		if(type == 0) {
			int j = 0;
			for(int i = index; i < index + length; i++) {
				textField[i].setText(String.valueOf(str.charAt(j++)));
			}
		} 
		else {
			int j = 0;
			for(int i = index; i <= index + (length - 1) * 20; i = i + 20)
				textField[i].setText(String.valueOf(str.charAt(j++)));
		}
	}
	
	public JPanel getContentPanel() {
		return panel;
	}
}

