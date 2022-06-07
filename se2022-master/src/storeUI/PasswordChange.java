package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PasswordChange {

	private JFrame frame;
	private JTextField nowPw;
	private JTextField newPw;
	private JTextField newPwCheck;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordChange window = new PasswordChange();
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
	public PasswordChange() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("비밀번호 변경");
		frame.setSize(380, 141);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane nowPwTxt = new JTextPane();
		nowPwTxt.setEditable(false);
		nowPwTxt.setBackground(UIManager.getColor("CheckBox.background"));
		nowPwTxt.setText("현재 비밀번호");
		nowPwTxt.setBounds(28, 10, 83, 21);
		frame.getContentPane().add(nowPwTxt);
		
		JTextPane newPwTxt = new JTextPane();
		newPwTxt.setEditable(false);
		newPwTxt.setText("새 비밀번호");
		newPwTxt.setBackground(SystemColor.menu);
		newPwTxt.setBounds(40, 41, 71, 21);
		frame.getContentPane().add(newPwTxt);
		
		JTextPane newPwCheckTxt = new JTextPane();
		newPwCheckTxt.setEditable(false);
		newPwCheckTxt.setText("새 비밀번호 확인");
		newPwCheckTxt.setBackground(SystemColor.menu);
		newPwCheckTxt.setBounds(12, 72, 99, 21);
		frame.getContentPane().add(newPwCheckTxt);
		
		nowPw = new JTextField();
		nowPw.setBounds(123, 10, 116, 21);
		frame.getContentPane().add(nowPw);
		nowPw.setColumns(10);
		
		newPw = new JTextField();
		newPw.setColumns(10);
		newPw.setBounds(123, 41, 116, 21);
		frame.getContentPane().add(newPw);
		
		newPwCheck = new JTextField();
		newPwCheck.setColumns(10);
		newPwCheck.setBounds(123, 72, 116, 21);
		frame.getContentPane().add(newPwCheck);
		
		JButton modifybtn = new JButton("수정");
		modifybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "미구현 기능입니다. ","미구현 기능", JOptionPane.PLAIN_MESSAGE);
				Manager_UI mmframe = new Manager_UI();
				mmframe.setVisible(true);
				frame.dispose();
			}
		});
		modifybtn.setBounds(251, 8, 99, 85);
		frame.getContentPane().add(modifybtn);
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
