package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;

import control.StaffControl;

import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class StaffSearchPage {

	private JFrame frame;
	public JTable StaffTable;
	public JTextField SearchTextField;
	private JButton backbtn;
	private JPanel StaffPanel;
	private JScrollPane scrollPane;
	private JButton RegisterButton;
	public JComboBox SearchOptionSel;
	private JTextPane txtpnId;
	private JTextPane txtpnId_1;
	private JTextPane txtpnId_2;
	public JTextField phoneTextField;
	public JTextField nameTextField;
	public JTextField idTextField;
	private JButton AmendButton;
	private JButton DeleteButton;

	private StaffSearchPage This = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffSearchPage window = new StaffSearchPage();
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
	public StaffSearchPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 376);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		SearchTextField = new JTextField();
		SearchTextField.setForeground(Color.BLACK);
		SearchTextField.setBounds(99, 11, 167, 21);
		frame.getContentPane().add(SearchTextField);
		SearchTextField.setColumns(10);

		JButton SearchButton = new JButton("조회");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffControl staffControl = new StaffControl();
				staffControl.StaffSearch(This);
			}
		});
		SearchButton.setBounds(278, 10, 75, 23);
		frame.getContentPane().add(SearchButton);

		backbtn = new JButton("뒤로가기");
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager_UI mmframe = new Manager_UI();
				mmframe.setVisible(true);
				frame.dispose();
			}
		});
		backbtn.setBounds(359, 10, 93, 23);
		frame.getContentPane().add(backbtn);

		StaffPanel = new JPanel();
		StaffPanel.setBounds(12, 36, 440, 191);
		frame.getContentPane().add(StaffPanel);
		StaffPanel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		StaffPanel.add(scrollPane, BorderLayout.CENTER);

		StaffTable = new JTable();
		scrollPane.setViewportView(StaffTable);
		StaffTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "\uC774\uB984", "\uC804\uD654\uBC88\uD638" }){public boolean isCellEditable(int row, int column) {return false;}});
		StaffTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		StaffTable.getColumnModel().getColumn(1).setPreferredWidth(15);
		StaffTable.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int row = StaffTable.getSelectedRow();
				idTextField.setText((String) StaffTable.getValueAt(row, 0));
				nameTextField.setText((String) StaffTable.getValueAt(row, 1));
				phoneTextField.setText((String) StaffTable.getValueAt(row, 2));
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});

		RegisterButton = new JButton("등록");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffControl staffControl = new StaffControl();
				staffControl.StaffRegister(This);
			}
		});
		RegisterButton.setBounds(355, 237, 97, 23);
		frame.getContentPane().add(RegisterButton);

		SearchOptionSel = new JComboBox();
		SearchOptionSel.setModel(new DefaultComboBoxModel(new String[] { "ID", "이름", "전화번호" }));
		SearchOptionSel.setBounds(12, 10, 75, 23);
		frame.getContentPane().add(SearchOptionSel);

		txtpnId = new JTextPane();
		txtpnId.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnId.setText("ID");
		txtpnId.setBounds(12, 239, 18, 21);
		frame.getContentPane().add(txtpnId);

		txtpnId_1 = new JTextPane();
		txtpnId_1.setText("이름");
		txtpnId_1.setBackground(SystemColor.menu);
		txtpnId_1.setBounds(12, 270, 31, 21);
		frame.getContentPane().add(txtpnId_1);

		txtpnId_2 = new JTextPane();
		txtpnId_2.setText("전화번호");
		txtpnId_2.setBackground(SystemColor.menu);
		txtpnId_2.setBounds(12, 301, 55, 21);
		frame.getContentPane().add(txtpnId_2);

		phoneTextField = new JTextField();
		phoneTextField.setBounds(79, 301, 268, 21);
		frame.getContentPane().add(phoneTextField);
		phoneTextField.setColumns(10);

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(79, 270, 268, 21);
		frame.getContentPane().add(nameTextField);

		idTextField = new JTextField();
		idTextField.setColumns(10);
		idTextField.setBounds(79, 237, 268, 21);
		frame.getContentPane().add(idTextField);

		AmendButton = new JButton("수정");
		AmendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffControl staffControl = new StaffControl();
				staffControl.StaffAmend(This);
			}
		});
		AmendButton.setBounds(355, 270, 97, 23);
		frame.getContentPane().add(AmendButton);

		DeleteButton = new JButton("삭제");
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffControl staffControl = new StaffControl();
				staffControl.StaffDelete(This);
			}
		});
		DeleteButton.setBounds(355, 301, 97, 23);
		frame.getContentPane().add(DeleteButton);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
