package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import control.PasswordControl;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PasswordCheck {

	private JFrame frame;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordCheck window = new PasswordCheck();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PasswordCheck() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("로그인");
		frame.setBounds(100, 100, 448, 81);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane passwordTxt = new JTextPane();
		passwordTxt.setEditable(false);
		passwordTxt.setBackground(UIManager.getColor("CheckBox.background"));
		passwordTxt.setText("비밀번호 입력");
		passwordTxt.setBounds(12, 10, 83, 21);
		frame.getContentPane().add(passwordTxt);
		
		password = new JTextField();
		password.setBounds(107, 10, 116, 21);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		JButton loginbtn = new JButton("로그인");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasswordControl pc = new PasswordControl();
				if (pc.pwCheck(password.getText())) {
					Manager_UI mmframe = new Manager_UI();
					mmframe.setVisible(true);
					frame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "잘못된 비밀번호입니다.","오류", JOptionPane.ERROR_MESSAGE);
					password.setText(null);
				}
			}
		});
		loginbtn.setBounds(235, 10, 87, 23);
		frame.getContentPane().add(loginbtn);
		
		JButton backbtn = new JButton("뒤로가기");
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_UI mainframe = new Main_UI();
				mainframe.setVisible(true);
				frame.dispose();
			}
		});
		backbtn.setBounds(332, 10, 87, 23);
		frame.getContentPane().add(backbtn);
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
