package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import info.*;

public class Client {
	public final static int PORT = 9000;
	private BufferedReader in;
	private PrintWriter out;
	private ObjectOutputStream oout;
	private ObjectInputStream oin;
	private String userID, pw;
	private LoginFrame loginFrame;
	private LobbyFrame lobbyFrame;
	private RoomFrame roomFrame;
	private int roomNum = -1;

	public Client() {
		userID = "";
		pw = "";
		loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		loginFrame.loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userID = loginFrame.getId();
				pw = new String(loginFrame.getPassword());

				JSONObject loginJSON = new JSONObject();
				loginJSON.put("type", "LOGIN");
				loginJSON.put("userID", userID);
				loginJSON.put("pw", pw);
				out.println(loginJSON.toString());
			}
		});
	}

	public void run() {
		String serverAddress = "127.0.0.1";
		JSONParser parser = new JSONParser();
		JSONObject inJSON;
		int stringIndex = 0;

		try {
			Socket socket = new Socket(serverAddress, PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			oin = new ObjectInputStream(socket.getInputStream());

			while(true) {
				Object o = null;
				
				String input = in.readLine();
				System.out.println(input);
				
				stringIndex = input.indexOf("{");
				inJSON = (JSONObject) parser.parse(input.substring(stringIndex));
				String type = inJSON.get("type").toString();
				
				JSONObject outJSON = new JSONObject();
				String state;
				
				switch(type) {
				case "LOGIN":
					state = inJSON.get("state").toString();

					if(state.equals("SUCCESS")) {
						System.out.println(userID);
						loginFrame.setVisible(false);
						loginFrame.dispose();
						lobbyFrame = new LobbyFrame();
						lobbyActionHandler();
						
						lobbyFrame.setVisible(true);
						
						o = oin.readObject();
						if(o != null)
							lobbyFrame.setList((HashMap<Integer, String>)o);
					}
					else if(state.equals("FAIL"))
						System.out.println("login fail");
					break;

				case "CREATEROOM":
					state = inJSON.get("state").toString();

					if(state.equals("SUCCESS")) {
						roomNum = Integer.parseInt(inJSON.get("roomNum").toString());
						lobbyFrame.setVisible(false);
						o = oin.readObject();
						roomFrame = (RoomFrame)o;
						roomFrame.setVisible(true);
						roomActionHandler();
					}
					else if(state.equals("FAIL"))
						System.out.println("Fail create room");
					break;

				case "REFRESHROOM":
					o = oin.readObject();
					if(o != null)
						lobbyFrame.setList((HashMap<Integer, String>)o);
					break;

				case "ENTERROOM":
					state = inJSON.get("state").toString();
					if(state.equals("SUCCESS")) {
						roomNum = Integer.parseInt(inJSON.get("roomNum").toString());
						o = oin.readObject();
						roomFrame = (RoomFrame)o;
						lobbyFrame.setVisible(false);
						roomFrame.setVisible(true);
						roomActionHandler();
					} 
					else
						System.out.println("Fail enter the room");
					break;

				case "NEWUSER":
					roomFrame.idLabel2.setText(inJSON.get("newUserID").toString());
					roomFrame.textArea.append(roomFrame.textArea.getText() + inJSON.get("newUserID").toString() + "님이 새로 참여하셨습니다.\n");
					break;
					
				case "CHAT" :
					roomFrame.textArea.append(inJSON.get("userID").toString() + " : " + inJSON.get("content").toString() + "\n");
					break;
					
				case "GAMESTART":
					state = inJSON.get("state").toString();
					if(state.equals("SUCCESS")) {
						roomFrame.gameStart((Map)oin.readObject());
						roomFrame.centerPanel.revalidate();
						roomFrame.centerPanel.repaint();
						mapActionHandler();
					}
					else if(state.equals("FAIL"))
						System.out.println("Fail start game");
					break;
					
				case "ANSWER" :
					state = inJSON.get("state").toString();
					
					if(state.equals("SUCCESS")) {
						if(inJSON.get("userID").equals(userID)) {
							roomFrame.map.setAnswerText(inJSON.get("answer").toString(), Integer.parseInt(inJSON.get("questionNum").toString()));
						}
						
					}
					else {
						roomFrame.alret(inJSON.get("content").toString());
					}
				case "EXITROOM" :
					roomNum = -1;
					break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void mapActionHandler() {
		
		roomFrame.map.question1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question1.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question2.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question3.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question4.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question5.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question6.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question7.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question8.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question9.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question10.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
			
		});
		
		roomFrame.map.question11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question11.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question12.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
			
		});
		
		roomFrame.map.question13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question13.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question14.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question15.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question16.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question17.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question18.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question19.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question20.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question21.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question21.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
		
		roomFrame.map.question22.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = Integer.parseInt(roomFrame.map.question22.getText());
				int length = roomFrame.map.answer[num - 1][2];
				AnswerFrame answer = new AnswerFrame(num, length, roomNum, out);
				answer.setVisible(true);
			}
		});
	}
	
	
	private void roomActionHandler() {
		roomFrame.btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomFrame.dispose();
				lobbyFrame.setVisible(true);
				JSONObject json = new JSONObject();
				json.put("type", "EXITROOM");
				json.put("userID", userID);
				json.put("roomNum", roomNum);
				out.println(json.toString());
			}
		});
		
		roomFrame.btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JSONObject json = new JSONObject();
				json.put("type", "GAMEREADY");
				json.put("userID", userID);
				json.put("roomNum", roomNum);
				out.println(json.toString());
			}
		});
		
		roomFrame.btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JSONObject json = new JSONObject();
				json.put("type", "GAMESTART");
				json.put("userID", userID);
				json.put("roomNum", roomNum);
				
				out.println(json.toString());
				
				System.out.print("map");
			}
		});
		
		roomFrame.textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JSONObject json = new JSONObject();
				json.put("type", "CHAT");
				json.put("userID", userID);
				json.put("roomNum", roomNum);
				json.put("content", roomFrame.textField.getText());
				out.println(json);
				roomFrame.textField.setText("");
			}
		});
		
		
	}
	
	private void lobbyActionHandler() {
		lobbyFrame.list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) { } //클릭
				if (e.getClickCount() == 2) {
					String[] str = lobbyFrame.list.getSelectedValue().toString().split(" ");
					// 더블클릭
					System.out.println(str[0]);
					JSONObject json = new JSONObject();
					json.put("type", "ENTERROOM");
					json.put("roomNum", str[0]);
					json.put("userID", userID);
					out.println(json.toString());
				}
				if (e.getButton() == 3) { } // 오른쪽 클릭
			}
		});

		lobbyFrame.createRoomBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final OptionFrame optionFrame = new OptionFrame();
				optionFrame.setVisible(true);

				optionFrame.btnCreateRoom.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String roomName = optionFrame.roomNameText.getText();
						String mode = optionFrame.getMode();
						int peopleNum = Integer.parseInt(optionFrame.peopleNumText.getText());
						if(roomName.isEmpty() || mode.isEmpty() || peopleNum == 0)
							System.out.println("Wrong Input");
						else {
							JSONObject json = new JSONObject();
							json.put("type", "CREATEROOM");
							json.put("mode", mode);
							json.put("roomName", roomName);
							json.put("peopleNum", peopleNum);
							json.put("userID", userID);
							System.out.println(json.toString());
							out.println(json.toString());
							optionFrame.dispose();
						}
					}
				});
			}
		});
	}

	public static void main(String[] args) {
		Client client = new Client();

		try {
			System.out.println("Login");
			client.run();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
