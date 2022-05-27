package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Product_Sell_UI {

	private JFrame frame;
	private JTable product_list;
	private JTextField all_money;
	private JTextField product_text;
	private JTextField receipt_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product_Sell_UI window = new Product_Sell_UI();
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
	public Product_Sell_UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("상품 판매");
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel product_list_pane = new JPanel();
		product_list_pane.setBackground(Color.WHITE);
		product_list_pane.setBounds(12, 43, 463, 508);
		frame.getContentPane().add(product_list_pane);
		product_list_pane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane product_list_scroll = new JScrollPane();
		product_list_pane.add(product_list_scroll, BorderLayout.CENTER);
		
		product_list = new JTable();
		product_list_scroll.setViewportView(product_list);
		product_list.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"상품명", "수량", "금액"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton back_main = new JButton("뒤로가기");
		back_main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_UI mp = new Main_UI();
				mp.setVisible(true);
				frame.dispose();
			}
		});
		back_main.setBounds(12, 10, 97, 23);
		frame.getContentPane().add(back_main);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(487, 43, 185, 508);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		all_money = new JTextField();
		all_money.setEditable(false);
		all_money.setBounds(67, 10, 106, 21);
		panel.add(all_money);
		all_money.setColumns(10);
		
		JTextPane all_money_txt = new JTextPane();
		all_money_txt.setText("총금액");
		all_money_txt.setBounds(12, 10, 43, 21);
		panel.add(all_money_txt);
		
		JButton select_delete = new JButton("선택 제거");
		select_delete.setBounds(12, 52, 161, 32);
		panel.add(select_delete);
		
		JButton select_all_clear = new JButton("전체 제거");
		select_all_clear.setBounds(12, 94, 161, 32);
		panel.add(select_all_clear);
		
		JButton scan_product = new JButton("상품정보 스캔");
		scan_product.setBounds(12, 197, 161, 37);
		panel.add(scan_product);
		
		JButton scan_receipt = new JButton("영수증 번호 스캔");
		scan_receipt.setBounds(12, 275, 161, 37);
		panel.add(scan_receipt);
		
		JButton pay_cash = new JButton("현금결제하기");
		pay_cash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cash cframe = new Cash();
				cframe.setVisible(true);
				frame.dispose();
			}
		});
		pay_cash.setBounds(12, 338, 161, 46);
		panel.add(pay_cash);
		
		JButton pay_card = new JButton("카드결제하기");
		pay_card.setBounds(12, 394, 161, 46);
		panel.add(pay_card);
		
		JButton return_list = new JButton("반품요청");
		return_list.setBounds(12, 450, 161, 48);
		panel.add(return_list);
		
		product_text = new JTextField();
		product_text.setBounds(12, 166, 161, 21);
		panel.add(product_text);
		product_text.setColumns(10);
		
		receipt_text = new JTextField();
		receipt_text.setColumns(10);
		receipt_text.setBounds(12, 244, 161, 21);
		panel.add(receipt_text);
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
