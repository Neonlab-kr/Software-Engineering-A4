package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Window.Type;
import javax.swing.BoxLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class SalesCheck {

	private JFrame frame;
	private JTextField yearInput;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesCheck window = new SalesCheck();
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
	public SalesCheck() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("매장 매출");
		frame.setType(Type.POPUP);
		frame.setSize(537, 494);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		yearInput = new JTextField();
		yearInput.setForeground(Color.BLACK);
		yearInput.setBounds(75, 421, 75, 24);
		frame.getContentPane().add(yearInput);
		yearInput.setColumns(10);
		
		JButton yearInputbtn = new JButton("입력");
		yearInputbtn.setBounds(162, 420, 68, 24);
		yearInputbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "미구현 기능입니다. ","미구현 기능", JOptionPane.PLAIN_MESSAGE);
			}
		});
		frame.getContentPane().add(yearInputbtn);
		
		JPanel JanToJunPane = new JPanel();
		JanToJunPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JanToJunPane.setBackground(Color.WHITE);
		JanToJunPane.setBounds(0, 0, 521, 220);
		frame.getContentPane().add(JanToJunPane);
		JanToJunPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(45, 35, 464, 144);
		table.setShowVerticalLines(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		JanToJunPane.add(table);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(71, 189, 25, 21);
		textPane.setText("1월");
		JanToJunPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(150, 189, 25, 21);
		textPane_1.setText("2월");
		JanToJunPane.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(226, 189, 25, 21);
		textPane_2.setText("3월");
		JanToJunPane.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setBounds(299, 189, 25, 21);
		textPane_3.setText("4월");
		JanToJunPane.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setBounds(371, 189, 25, 21);
		textPane_4.setText("5월");
		JanToJunPane.add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setBounds(445, 189, 25, 21);
		textPane_5.setText("6월");
		JanToJunPane.add(textPane_5);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText("50");
		textPane_7.setBounds(15, 150, 25, 21);
		JanToJunPane.add(textPane_7);
		
		JTextPane textPane_7_1 = new JTextPane();
		textPane_7_1.setText("100");
		textPane_7_1.setBounds(12, 119, 31, 21);
		JanToJunPane.add(textPane_7_1);
		
		JTextPane textPane_7_2 = new JTextPane();
		textPane_7_2.setText("150");
		textPane_7_2.setBounds(12, 87, 31, 21);
		JanToJunPane.add(textPane_7_2);
		
		JTextPane textPane_7_3 = new JTextPane();
		textPane_7_3.setText("200");
		textPane_7_3.setBounds(12, 56, 31, 21);
		JanToJunPane.add(textPane_7_3);
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setText("(단위: 만원)");
		textPane_8.setBounds(12, 10, 81, 21);
		JanToJunPane.add(textPane_8);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setFont(new Font("Dialog", Font.PLAIN, 16));
		textPane_9.setText("____년도 매장 매출");
		textPane_9.setBounds(177, 10, 147, 32);
		JanToJunPane.add(textPane_9);
		
		JPanel JulToDec = new JPanel();
		JulToDec.setLayout(null);
		JulToDec.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JulToDec.setBackground(Color.WHITE);
		JulToDec.setBounds(0, 219, 521, 192);
		frame.getContentPane().add(JulToDec);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table_1.setShowVerticalLines(false);
		table_1.setBounds(45, 7, 464, 144);
		JulToDec.add(table_1);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText("7월");
		textPane_6.setBounds(71, 161, 25, 21);
		JulToDec.add(textPane_6);
		
		JTextPane textPane_1_1 = new JTextPane();
		textPane_1_1.setText("8월");
		textPane_1_1.setBounds(150, 161, 25, 21);
		JulToDec.add(textPane_1_1);
		
		JTextPane textPane_2_1 = new JTextPane();
		textPane_2_1.setText("9월");
		textPane_2_1.setBounds(226, 161, 25, 21);
		JulToDec.add(textPane_2_1);
		
		JTextPane textPane_3_1 = new JTextPane();
		textPane_3_1.setText("10월");
		textPane_3_1.setBounds(299, 161, 31, 21);
		JulToDec.add(textPane_3_1);
		
		JTextPane textPane_4_1 = new JTextPane();
		textPane_4_1.setText("11월");
		textPane_4_1.setBounds(371, 161, 31, 21);
		JulToDec.add(textPane_4_1);
		
		JTextPane textPane_5_1 = new JTextPane();
		textPane_5_1.setText("12월");
		textPane_5_1.setBounds(445, 161, 31, 21);
		JulToDec.add(textPane_5_1);
		
		JTextPane textPane_7_4 = new JTextPane();
		textPane_7_4.setText("50");
		textPane_7_4.setBounds(15, 123, 25, 21);
		JulToDec.add(textPane_7_4);
		
		JTextPane textPane_7_1_1 = new JTextPane();
		textPane_7_1_1.setText("100");
		textPane_7_1_1.setBounds(12, 92, 31, 21);
		JulToDec.add(textPane_7_1_1);
		
		JTextPane textPane_7_2_1 = new JTextPane();
		textPane_7_2_1.setText("150");
		textPane_7_2_1.setBounds(12, 60, 31, 21);
		JulToDec.add(textPane_7_2_1);
		
		JTextPane textPane_7_3_1 = new JTextPane();
		textPane_7_3_1.setText("200");
		textPane_7_3_1.setBounds(12, 29, 31, 21);
		JulToDec.add(textPane_7_3_1);
		
		JButton backbtn = new JButton("뒤로가기");
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager_UI mmframe = new Manager_UI();
				mmframe.setVisible(true);
				frame.dispose();
			}
		});
		backbtn.setBounds(412, 421, 97, 23);
		frame.getContentPane().add(backbtn);
		
		JTextPane yearInputTxt = new JTextPane();
		yearInputTxt.setBackground(SystemColor.control);
		yearInputTxt.setEditable(false);
		yearInputTxt.setText("년도 입력: ");
		yearInputTxt.setBounds(10, 421, 69, 24);
		frame.getContentPane().add(yearInputTxt);
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
