package storeUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import control.Pay_Cash;
import entity.Item;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Cash {

	private JFrame frame;
	private JTextField receive_money;
	private JTextField received_money;
	private JTextField div_money;
	private Pay_Cash cash;
	private JButton backbtn;
	private Product_Sell_UI sell_UI;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cash window = new Cash();
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
	public Cash() {
		initialize();
	}
	
	public Cash(ArrayList<Item> items ,String totalmoney , Product_Sell_UI sell_UI) {
		int total = Integer.parseInt(totalmoney);
		cash = new Pay_Cash(items,total);
		this.sell_UI = sell_UI;//해당 변수로 상품판매 화면을 제어
		initialize();
		receive_money.setText(totalmoney);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setEnabled(true);
		frame.setTitle("현금결제");
		frame.setSize(335, 140);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane receive_money_txt = new JTextPane();
		receive_money_txt.setEditable(false);
		receive_money_txt.setText("받을 금액");
		receive_money_txt.setBounds(12, 10, 59, 20);
		panel.add(receive_money_txt);
		
		JTextPane received_money_txt = new JTextPane();
		received_money_txt.setEditable(false);
		received_money_txt.setText("받은 금액");
		received_money_txt.setBounds(12, 40, 59, 20);
		panel.add(received_money_txt);
		
		JTextPane div_money_txt = new JTextPane();
		div_money_txt.setEditable(false);
		div_money_txt.setText("거스름돈");
		div_money_txt.setBounds(16, 70, 55, 20);
		panel.add(div_money_txt);
		
		receive_money = new JTextField();
		receive_money.setBounds(83, 10, 116, 21);
		receive_money.setEditable(false);
		panel.add(receive_money);
		receive_money.setColumns(10);
		
		received_money = new JTextField();
		received_money.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){//받은금액을 입력후 엔터를 치면 잔돈을 계산해준다.
					div_money.setText(cash.moneyChanges(received_money.getText()));
	            }
			}
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});
		received_money.setColumns(10);
		received_money.setBounds(83, 40, 116, 21);
		panel.add(received_money);
		
		div_money = new JTextField();
		div_money.setColumns(10);
		div_money.setEditable(false);
		div_money.setBounds(83, 70, 116, 21);
		panel.add(div_money);
		
		JButton calculator_button = new JButton("금액계산");
		calculator_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(div_money.getText().equals(""))//금액을 받지않은 경우 동작하지않음
					return;
				if(Integer.parseInt(div_money.getText()) >= 0) {//잔돈이 0원이상일 경우 결제진행
					cash.product_pay();//결제처리
					sell_UI.table_clear();//장바구니 초기화
					frame.dispose();
				}
			}
		});
		calculator_button.setBounds(211, 40, 98, 50);
		panel.add(calculator_button);
		
		JButton backbtn = new JButton("뒤로가기");//다시 메인화면으로 돌아간다.
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		backbtn.setBounds(211, 10, 98, 20);
		panel.add(backbtn);
		
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
