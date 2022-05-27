package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Manager_UI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_UI window = new Manager_UI();
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
	public Manager_UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("관리자");
		frame.setBounds(100, 100, 512, 371);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel info = new JPanel();
		info.setBackground(Color.WHITE);
		info.setBounds(12, 10, 472, 55);
		frame.getContentPane().add(info);
		info.setLayout(new BorderLayout(0, 0));
		
		JTextPane infotxt = new JTextPane();
		infotxt.setEditable(false);
		infotxt.setFont(new Font("굴림", Font.PLAIN, 40));
		infotxt.setText("관리자모드");
		info.add(infotxt);
		
		JTextPane date = new JTextPane();
		date.setEditable(false);
		date.setText("2022-05-04 13:04:23 (수)");//현재 시간 설정
		info.add(date, BorderLayout.EAST);
		
		JButton move_sales = new JButton("매장매출조회");
		move_sales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesCheck scframe = new SalesCheck();
				scframe.setVisible(true);
				frame.dispose();
			}
		});
		move_sales.setBounds(12, 75, 116, 246);
		frame.getContentPane().add(move_sales);
		
		JButton move_work_result = new JButton("근무현황조회");
		move_work_result.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkTimePage ewframe = new WorkTimePage();
				ewframe.setVisible(true);
				frame.dispose();
			}
		});
		move_work_result.setBounds(268, 75, 116, 246);
		frame.getContentPane().add(move_work_result);
		
		JButton move_employee_search = new JButton("직원 관리");
		move_employee_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffSearchPage ecframe = new StaffSearchPage();
				ecframe.setVisible(true);
				frame.dispose();
			}
		});
		move_employee_search.setBounds(140, 75, 116, 246);
		frame.getContentPane().add(move_employee_search);
		
		JButton move_change_password = new JButton("암호 변경");
		move_change_password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasswordChange pcframe = new PasswordChange();
				pcframe.setVisible(true);
				frame.dispose();
			}
		});
		move_change_password.setBounds(396, 75, 90, 114);
		frame.getContentPane().add(move_change_password);
		
		JButton move_main = new JButton("메인화면");
		move_main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_UI mp = new Main_UI();
				mp.setVisible(true);
				frame.dispose();
			}
		});
		move_main.setBounds(396, 207, 90, 114);
		frame.getContentPane().add(move_main);
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
