package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.ItemControl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ItemInfoUI {

	private JFrame frame;
	public JTextField goodsCode;
	public JTextField goodsName;
	public JTable goodsTable;

	private ItemInfoUI This = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemInfoUI window = new ItemInfoUI();
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
	public ItemInfoUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("상품정보");
		frame.setBounds(100, 100, 454, 439);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextPane goodsCodeTxt = new JTextPane();
		goodsCodeTxt.setEditable(false);
		goodsCodeTxt.setBackground(UIManager.getColor("CheckBox.background"));
		goodsCodeTxt.setText("상품 코드 : ");
		goodsCodeTxt.setBounds(40, 25, 71, 21);
		frame.getContentPane().add(goodsCodeTxt);

		JTextPane goodsNameTxt = new JTextPane();
		goodsNameTxt.setEditable(false);
		goodsNameTxt.setText("상품명 : ");
		goodsNameTxt.setBackground(SystemColor.menu);
		goodsNameTxt.setBounds(56, 56, 55, 21);
		frame.getContentPane().add(goodsNameTxt);

		goodsCode = new JTextField();
		goodsCode.setBounds(110, 25, 219, 21);
		frame.getContentPane().add(goodsCode);
		goodsCode.setColumns(10);

		goodsName = new JTextField();
		goodsName.setColumns(10);
		goodsName.setBounds(110, 56, 219, 21);
		frame.getContentPane().add(goodsName);

		JButton searchbtn = new JButton("검색");
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemControl itemControl = new ItemControl();
				itemControl.SearchItem(This);
			}
		});
		searchbtn.setBounds(341, 25, 81, 52);
		frame.getContentPane().add(searchbtn);
		String header[] = { "바코드번호", "상품명", "가격", "재고수량" };

		String contents[][] = { };
		
		JScrollPane TableScrollPane = new JScrollPane();
		TableScrollPane.setBounds(12, 87, 410, 236);
		frame.getContentPane().add(TableScrollPane);
		
				goodsTable = new JTable();
				TableScrollPane.setViewportView(goodsTable);
				goodsTable.setModel(new DefaultTableModel(contents,header));
				goodsTable.getColumnModel().getColumn(0).setPreferredWidth(130);
				goodsTable.getColumnModel().getColumn(0).setMinWidth(25);
				goodsTable.getColumnModel().getColumn(1).setPreferredWidth(140);
				goodsTable.getColumnModel().getColumn(1).setMinWidth(25);
				goodsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
				goodsTable.getColumnModel().getColumn(2).setMinWidth(25);
				goodsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
				goodsTable.getColumnModel().getColumn(3).setMinWidth(25);

		JButton modifybtn = new JButton("수정");
		modifybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemControl itemControl = new ItemControl();
				itemControl.ModifyItem(This);
			}
		});
		modifybtn.setBounds(12, 333, 130, 52);
		frame.getContentPane().add(modifybtn);

		JButton deletebtn = new JButton("\uC0AD\uC81C");
		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemControl itemControl = new ItemControl();
				itemControl.DeleteItem(This);
			}
		});
		deletebtn.setBounds(154, 333, 130, 52);
		frame.getContentPane().add(deletebtn);

		JButton backbtn = new JButton("\uD655\uC778");
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_UI mp = new Main_UI();
				mp.setVisible(true);
				frame.dispose();
			}
		});
		backbtn.setBounds(296, 333, 126, 52);
		frame.getContentPane().add(backbtn);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
