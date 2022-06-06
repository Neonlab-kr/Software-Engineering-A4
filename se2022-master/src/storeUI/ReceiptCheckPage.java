package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import control.ReceiptCheck;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;

public class ReceiptCheckPage {

	private JFrame frame;
	private JTextField receiptNum;
	private JPanel panel;
	private JTextPane receipt;
	private JScrollPane receipt_scroll;
	private JScrollPane receipt_table_scroll;
	private JTable receipt_table;
	private boolean backCheck = false; //현제 리스트화면인지 조회화면인지 판단
	private DefaultTableCellRenderer celAlign = new DefaultTableCellRenderer();
	private DefaultTableCellRenderer celRight = new DefaultTableCellRenderer();
	private ReceiptCheck receiptCheck = new ReceiptCheck();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceiptCheckPage window = new ReceiptCheckPage();
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
	public ReceiptCheckPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("영수증 조회");
		frame.setSize(392, 380);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 10, 351, 41);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		receiptNum = new JTextField();
		receiptNum.setBounds(12, 10, 138, 23);
		panel.add(receiptNum);
		receiptNum.setForeground(SystemColor.desktop);
		receiptNum.setColumns(10);
		
		JButton Checkbtn = new JButton("조회");
		Checkbtn.setBounds(162, 10, 66, 23);
		panel.add(Checkbtn);
		
		JButton backbtn = new JButton("돌아가기");
		backbtn.setBounds(240, 10, 99, 23);
		panel.add(backbtn);
		
		receipt_scroll = new JScrollPane();
		receipt_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		receipt_scroll.setBounds(12, 61, 351, 270);
		frame.getContentPane().add(receipt_scroll);
		receipt_scroll.setVisible(false);
		
		receipt = new JTextPane();
		receipt_scroll.setViewportView(receipt);
		receipt.setText("품명\t\t단가\t수량\t금액\r\n"//영수증 출력
				+ "---------------------------------------------------------"
				+ "\r\n과자\t\t1,450\t1\t2,900"
				+ "\r\n\r\n\r\n\r\n\r\n\r\n\r\n"
				+ "---------------------------------------------------------"
				+ "\r\n합      계:\t\t\t\t2,900"
				+ "\r\n받을금액:\t\t\t\t2,900"
				+ "\r\n받은금액:\t\t\t\t2,900"
				+ "\r\n"
				+ "---------------------------------------------------------"
				+ "\r\n결제수단\t\t\t\t현금결제");
		
		receipt.setFont(new Font("굴림", Font.PLAIN, 12));
		receipt.setEditable(false);
		
		receipt_table_scroll = new JScrollPane();
		receipt_table_scroll.setBounds(12, 61, 351, 270);
		frame.getContentPane().add(receipt_table_scroll);
		
		celAlign.setHorizontalAlignment(JLabel.CENTER);
		celRight.setHorizontalAlignment(JLabel.RIGHT);
		
		receipt_table = new JTable();
		receipt_table_scroll.setViewportView(receipt_table);
		setTable(receiptCheck.viewList());

		receipt_table.addMouseListener(new MouseAdapter() {//해당 영수증 클릭시 영수증 세부내역 보여줌
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					int row = receipt_table.getSelectedRow();
					receipt_table_scroll.setVisible(false);
					receipt.setText(receiptCheck.receiptSearch(row+1));//영수증 세부내용 가져오기
					receipt_scroll.setVisible(true);
					backCheck = true;//조회상태 전환
				}
			}
		});
		backbtn.addActionListener(new ActionListener() {//뒤로가기 버튼클릭시 이벤트
			public void actionPerformed(ActionEvent e) {
				if(!backCheck) {//조회상태일시 리스트화면으로 이동
					Main_UI mp = new Main_UI();
					mp.setVisible(true);
					frame.dispose();
				}else {//리스트 화면일시 메인화면으로 이동
					receipt_table_scroll.setVisible(true);
					receipt_scroll.setVisible(false);
					backCheck = false;
				}
			}
		});
		Checkbtn.addActionListener(new ActionListener() {//조회버튼 클릭시 이벤트
			public void actionPerformed(ActionEvent e) {
				String str = receiptNum.getText();
				if(str.equals("")) {//아무것도 입력하지 않고 조회시 전체 리스트 출력
					setTable(receiptCheck.viewList());
				}else {
					if(str.chars().allMatch(Character::isDigit)) {//영수증 번호가 숫자일 경우만 조회 출력
						setTable(receiptCheck.view(Integer.parseInt(str)));
					}else{//오류메시지 출력
						JOptionPane.showMessageDialog(null, "숫자가 아닌 잘못된 문자가 입력되었습니다.", "영수증 번호 오류", JOptionPane.ERROR_MESSAGE);
						System.out.println("숫자가 아닌 잘못된 문자가 입력되었습니다.");
					}
				}
				receiptNum.setText("");
			}
		});
	}
	
	public void setTable(Object[][] temp) {//영수증 리스트를 설정
		receipt_table.setModel(new DefaultTableModel(
				temp,
				new String[] {
					"영수증 번호" , "결제 시간" , "결제 방법" , "총 금액"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		receipt_table.getColumn("영수증 번호").setPreferredWidth(70);
		receipt_table.getColumn("영수증 번호").setCellRenderer(celAlign);
		
		receipt_table.getColumn("결제 시간").setPreferredWidth(120);
		receipt_table.getColumn("결제 시간").setCellRenderer(celAlign);
		
		receipt_table.getColumn("결제 방법").setPreferredWidth(60);
		receipt_table.getColumn("결제 방법").setCellRenderer(celAlign);
		
		//receipt_table.getColumn("총 금액").setPreferredWidth(1);
		receipt_table.getColumn("총 금액").setCellRenderer(celRight);
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
