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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.ActionListener;
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
		back_main.addActionListener(new ActionListener() {//뒤로가기 동작 이벤트
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
		select_delete.addActionListener(new ActionListener() {//클릭된 상품을 내역에서 제거하는 이벤트
			public void actionPerformed(ActionEvent e) {
				if(checkReturn)//반품진행중에는 동작하지않는다.
					return;
				if(product_list.getRowCount() == 0 || product_list.getSelectedRow() == -1) { //장바구니에 내역이 없거나 선택된 메뉴가 없을경우 오류방지
					JOptionPane.showMessageDialog(null, "해당상품을 선택해주세요.", "선택 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				setTable(sell_Ctrl.product_deleteOne(product_list.getSelectedRow()));//선택된 메뉴를 제거한다.
				all_money.setText(sell_Ctrl.calculator_totalMoney());//총금액 업데이트
			}
		});
		select_delete.setBounds(12, 52, 161, 32);
		panel.add(select_delete);
		
		JButton select_all_clear = new JButton("전체 제거");
		select_all_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkReturn)//반품진행중에는 동작하지않는다.
					return;
				setTable(sell_Ctrl.product_deleteAll());//장바구니를 비워준다
				all_money.setText("");//총금액 업데이트
			}
		});
		select_all_clear.setBounds(12, 94, 161, 32);
		panel.add(select_all_clear);
		
		JButton scan_product = new JButton("상품정보 스캔");//중복된 번호 입력시 수량값만 up해주기
		scan_product.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkReturn)//반품진행중에는 동작하지않는다.
					return;
				String str = product_text.getText();
				if(str.equals("")) {//입력된 값이 없으면 동작하지 않는다.
					return;
				}
				setTable(sell_Ctrl.product_search(str));//해당 상품을 받아와 테이블 업데이트
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
				if(str.equals("")) {//입력된 값이 없으면 동작하지 않는다.
					return;
				}
				setTable(return_Ctrl.product_receiptSearch(str));
				all_money.setText(return_Ctrl.Calculator_totalMoney());
				receipt_text.setText("");
				checkReturn = true;//반품 진행중으로 상태전환(부가기능들을 못쓰도록 제어)
			}
		});
		scan_receipt.setBounds(12, 275, 161, 37);
		panel.add(scan_receipt);
		
		JButton pay_cash = new JButton("현금결제하기");
		pay_cash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(checkReturn)//반품진행중에는 동작하지않는다.
					return;
				if(sell_Ctrl.getsize() == 0) {//장바구니에 상품이 없을경우 오류메시지
					JOptionPane.showMessageDialog(null, "결제할 상품이 없습니다.", "상품 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Cash cframe = new Cash(sell_Ctrl.getItems(),sell_Ctrl.calculator_totalMoney() ,getMy());//상품결제창으로 이동
				cframe.setVisible(true);
				//frame.dispose();
			}
		});
		pay_cash.setBounds(12, 338, 161, 46);
		panel.add(pay_cash);
		
		JButton pay_card = new JButton("카드결제하기");
		pay_card.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//미기능 구현사항
				if(checkReturn)//반품진행중에는 동작하지않는다.
					return;
				System.out.println("미지원 기능입니다.");
				JOptionPane.showMessageDialog(null, "미기능구현사항입니다.", "미지원기능", JOptionPane.ERROR_MESSAGE);
			}
		});
		pay_card.setBounds(12, 394, 161, 46);
		panel.add(pay_card);
		
		JButton return_list = new JButton("반품요청");
		return_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkReturn) {
				setTable(null);//테이블을 초기화
				all_money.setText("");//총금액 초기화
				return_Ctrl.product_return();//환불 진행
				checkReturn = false;//반품진행상황을 해제한다.
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
		
		product_list.getModel().addTableModelListener(new TableModelListener() {//테이블에 수량 수정시 이벤트
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int col = e.getColumn();
				TableModel model = (TableModel)e.getSource();
				Object dataObject = model.getValueAt(row, col);//수정된 수량값을 받아온다
				if(!sell_Ctrl.updateStock(row,dataObject)) {//수량이 정상적으로 업데이트 됬을경우
					setTable(sell_Ctrl.getProduct());//테이블을 업데이트 해줌으로서 금액도 업데이트된다.
				}
				all_money.setText(sell_Ctrl.calculator_totalMoney());//총금액도 업데이트 해준다.
			}
		});
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
	
	public Product_Sell_UI getMy() {//현 객체의 레퍼런스를 전달
		return this;
	}
	public void table_clear() {
		sell_Ctrl.clear();
	}
}
