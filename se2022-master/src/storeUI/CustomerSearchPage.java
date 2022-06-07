package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;

public class CustomerSearchPage {

	private JFrame frame;
	private JTextField SearchTextField;
	private JButton btnNewButton_1;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerSearchPage window = new CustomerSearchPage();
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
	public CustomerSearchPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("고객관리");
		frame.setSize(395, 377);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		SearchTextField = new JTextField();
		SearchTextField.setColumns(10);
		SearchTextField.setBounds(99, 10, 190, 21);
		frame.getContentPane().add(SearchTextField);
		
		JButton btnNewButton = new JButton("조회");
		btnNewButton.setBounds(301, 10, 66, 21);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "미구현 기능입니다. ","미구현 기능", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		btnNewButton_1 = new JButton("뒤로가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_UI mp = new Main_UI();
				mp.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(267, 230, 100, 36);
		frame.getContentPane().add(btnNewButton_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"이름", "전화번호"}));
		comboBox.setBounds(12, 9, 75, 23);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(79, 276, 181, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(UIManager.getColor("CheckBox.background"));
		textPane.setText("이름");
		textPane.setBounds(12, 276, 31, 21);
		frame.getContentPane().add(textPane);
		
		JPanel TablePanel = new JPanel();
		TablePanel.setBounds(12, 41, 355, 179);
		frame.getContentPane().add(TablePanel);
		TablePanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		TablePanel.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", ""},
				{"", null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"이름", "전화번호"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(123);
		scrollPane.setViewportView(table);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(UIManager.getColor("CheckBox.background"));
		textPane_1.setText("전화번호");
		textPane_1.setBounds(12, 307, 55, 21);
		frame.getContentPane().add(textPane_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(79, 307, 181, 21);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton_2 = new JButton("등록");
		btnNewButton_2.setBounds(267, 277, 100, 51);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "미구현 기능입니다. ","미구현 기능", JOptionPane.PLAIN_MESSAGE);
			}
		});
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("삭제");
		btnNewButton_2_1.setBounds(79, 230, 85, 36);
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "미구현 기능입니다. ","미구현 기능", JOptionPane.PLAIN_MESSAGE);
			}
		});
		frame.getContentPane().add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("수정");
		btnNewButton_2_1_1.setBounds(175, 230, 85, 36);
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "미구현 기능입니다. ","미구현 기능", JOptionPane.PLAIN_MESSAGE);
			}
		});
		frame.getContentPane().add(btnNewButton_2_1_1);
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
