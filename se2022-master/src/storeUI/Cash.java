package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;

import control.Pay_Cash;
import entity.Item;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Cash {

	private JFrame frame;
	private JTextField receive_money;
	private JTextField received_money;
	private JTextField div_money;
	private JButton calculator_button;
	private Pay_Cash cash;
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
	
	public Cash(ArrayList<Item> items) {
		cash = new Pay_Cash(items);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setEnabled(true);
		frame.setTitle("현금결제");
		frame.setBounds(100, 100, 442, 140);
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
		receive_money.setText(cash.viewbalance());
		panel.add(receive_money);
		receive_money.setColumns(10);
		
		received_money = new JTextField();
		received_money.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
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
				if(div_money.getText().equals(""))
					return;
				if(Integer.parseInt(div_money.getText()) >= 0) {
					cash.product_pay();
					Product_Sell_UI gsframe = new Product_Sell_UI();
					gsframe.setVisible(true);
					frame.dispose();
				}
				
			}
		});
		calculator_button.setBounds(211, 10, 97, 80);
		panel.add(calculator_button);
		
		JButton backbtn = new JButton("뒤로가기");
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product_Sell_UI gsframe = new Product_Sell_UI();
				gsframe.setVisible(true);
				frame.dispose();
			}
		});
		backbtn.setBounds(323, 10, 97, 80);
		panel.add(backbtn);
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
