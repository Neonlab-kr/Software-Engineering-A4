package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import control.Product_Return_Ctrl;
import control.Product_Sell_Ctrl;

import java.awt.BorderLayout;

import javax.management.loading.PrivateClassLoader;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Product_Sell_UI {

	private JFrame frame;
	private JTable product_list;
	private JTextField all_money;
	private JTextField product_text;
	private JTextField receipt_text;
	private DefaultTableCellRenderer celAlign = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer celRight = new DefaultTableCellRenderer();
	private Product_Sell_Ctrl sell_Ctrl = new Product_Sell_Ctrl();
	private Product_Return_Ctrl return_Ctrl = new Product_Return_Ctrl();
	private Boolean checkReturn = false;//영수증 불러왔을경우 다른 추가,제거기능 못쓰게 막는 변수
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
		
		celAlign.setHorizontalAlignment(JLabel.CENTER);
		celRight.setHorizontalAlignment(JLabel.RIGHT);
		
		JPanel product_list_pane = new JPanel();
		product_list_pane.setBackground(Color.WHITE);
		product_list_pane.setBounds(12, 43, 463, 508);
		frame.getContentPane().add(product_list_pane);
		product_list_pane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane product_list_scroll = new JScrollPane();
		product_list_pane.add(product_list_scroll, BorderLayout.CENTER);
		
		product_list = new JTable();
		product_list_scroll.setViewportView(product_list);
		setTable(null);
		
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
		select_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkReturn)
					return;
				if(product_list.getRowCount() == 0 || product_list.getSelectedRow() == -1) //장바구니에 내역이 없거나 선택된 메뉴가 없을경우 오류방지
					 return;
				setTable(sell_Ctrl.product_deleteOne(product_list.getSelectedRow()));//선택된 메뉴를 제거한다.
				all_money.setText(sell_Ctrl.calculator_totalMoney());
			}
		});
		select_delete.setBounds(12, 52, 161, 32);
		panel.add(select_delete);
		
		JButton select_all_clear = new JButton("전체 제거");
		select_all_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkReturn)
					return;
				setTable(sell_Ctrl.product_deleteAll());
				all_money.setText("");
			}
		});
		select_all_clear.setBounds(12, 94, 161, 32);
		panel.add(select_all_clear);
		
		JButton scan_product = new JButton("상품정보 스캔");//중복된 번호 입력시 수량값만 up해주기
		scan_product.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkReturn)
					return;
				String str = product_text.getText();
				if(str.equals("")) {
					return;
				}
				setTable(sell_Ctrl.product_search(str));
				all_money.setText(sell_Ctrl.calculator_totalMoney());
				product_text.setText("");
			}
		});
		scan_product.setBounds(12, 197, 161, 37);
		panel.add(scan_product);
		
		JButton scan_receipt = new JButton("영수증 번호 스캔");
		scan_receipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = receipt_text.getText();
				if(str.equals("")) {
					return;
				}
				setTable(return_Ctrl.product_receiptSearch(str));
				all_money.setText(return_Ctrl.Calculator_totalMoney());
				receipt_text.setText("");
				checkReturn = true;
			}
		});
		scan_receipt.setBounds(12, 275, 161, 37);
		panel.add(scan_receipt);
		
		JButton pay_cash = new JButton("현금결제하기");
		pay_cash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				Cash cframe = new Cash(sell_Ctrl.getItems());
				cframe.setVisible(true);
				frame.dispose();
			}
		});
		pay_cash.setBounds(12, 338, 161, 46);
		panel.add(pay_cash);
		
		JButton pay_card = new JButton("카드결제하기");
		pay_card.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("미지원 기능입니다.");
				JOptionPane.showMessageDialog(null, "미지원기능", "미기능구현사항입니다.", JOptionPane.ERROR_MESSAGE);
			}
		});
		pay_card.setBounds(12, 394, 161, 46);
		panel.add(pay_card);
		
		JButton return_list = new JButton("반품요청");
		return_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkReturn) {
				checkReturn = false;
				setTable(null);
				all_money.setText("");
				return_Ctrl.product_return();
				}
			}
		});
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
	
	public void setTable(Object[][] temp){
		product_list.setModel(new DefaultTableModel(
				temp,
				new String[] {
					"상품명", "수량", "금액"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, true, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		product_list.getColumn("상품명").setPreferredWidth(70);
		product_list.getColumn("상품명").setCellRenderer(celAlign);
		
		product_list.getColumn("수량").setPreferredWidth(70);
		product_list.getColumn("수량").setCellRenderer(celRight);
		
		product_list.getColumn("금액").setPreferredWidth(70);
		product_list.getColumn("금액").setCellRenderer(celRight);
		
		product_list.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				System.out.println("2");
				int row = e.getFirstRow();
				int col = e.getColumn();
				TableModel model = (TableModel)e.getSource();
				Object dataObject = model.getValueAt(row, col);
				if(!sell_Ctrl.updateStock(row,dataObject)) {
					setTable(sell_Ctrl.getProduct());
				}
				all_money.setText(sell_Ctrl.calculator_totalMoney());
			}
		});
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
