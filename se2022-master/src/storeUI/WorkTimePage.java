package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.WorkTimeSearch;

import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.text.ParseException;
import java.sql.*;

public class WorkTimePage {

	private JFrame frame;
	private JTable workTable;
	private JTextField nameTextField;
	private JTextField DateTextField;
	private JButton backbtn;
	private JPanel infoPanel;
	private JScrollPane scrollPane;
	private String date;
	private String name;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkTimePage window = new WorkTimePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("여기서 오류");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WorkTimePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(470, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane empNameTxt = new JTextPane();
		empNameTxt.setEditable(false);
		empNameTxt.setBackground(UIManager.getColor("CheckBox.background"));
		empNameTxt.setText("직원ID");
		empNameTxt.setBounds(12, 10, 55, 21);
		frame.getContentPane().add(empNameTxt);
		
		JTextPane dateTxt = new JTextPane();
		dateTxt.setEditable(false);
		dateTxt.setBackground(UIManager.getColor("CheckBox.background"));
		dateTxt.setText("일자");
		dateTxt.setBounds(12, 41, 31, 21);
		frame.getContentPane().add(dateTxt);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(79, 10, 161, 21);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		WorkTimeSearch search = new WorkTimeSearch();
		JButton SearchButton = new JButton("조회");
		SearchButton.setBounds(252, 10, 77, 52);
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[][] table;
				date = DateTextField.getText();
				name = nameTextField.getText();
				if (!name.equals("") && !date.equals("")) {
					JOptionPane.showMessageDialog(null, "한 가지 값만 입력해주세요.","입력 에러", JOptionPane.PLAIN_MESSAGE);
				}
				else if (!date.equals("")) {
					table = search.getInfo(java.sql.Date.valueOf(date));
					setTable(table);
				}
				else if (!name.equals("")) {
					table = search.getInfo(name);
					setTable(table);
				}
				else
					JOptionPane.showMessageDialog(null, "값을 입력하지 않았습니다. ","입력 에러", JOptionPane.PLAIN_MESSAGE);
			}
		});
		frame.getContentPane().add(SearchButton);
		
		DateTextField = new JTextField();
		DateTextField.setForeground(Color.BLACK);
		DateTextField.setColumns(10);
		DateTextField.setBounds(79, 41, 161, 21);
		DateTextField.setToolTipText("ex)2022-06-04");
		frame.getContentPane().add(DateTextField);
		
		backbtn = new JButton("뒤로가기");
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager_UI mmframe = new Manager_UI();
				mmframe.setVisible(true);
				frame.dispose();
			}
		});
		backbtn.setBounds(341, 10, 101, 52);
		frame.getContentPane().add(backbtn);
		
		infoPanel = new JPanel();
		infoPanel.setBounds(12, 72, 430, 179);
		frame.getContentPane().add(infoPanel);
		infoPanel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		infoPanel.add(scrollPane, BorderLayout.CENTER);
		
		workTable = new JTable();
		scrollPane.setViewportView(workTable);
		workTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"직원이름", "출근일시", "퇴근일시"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
	
	public void setTable(Object[][] table) {
		workTable.setModel(new DefaultTableModel(
				table,
				new String[] {
					"직원이름", "출근일시", "퇴근일시"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
	}
}
