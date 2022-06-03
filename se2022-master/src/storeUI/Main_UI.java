package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import control.EmpChange;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;

public class Main_UI implements Runnable {
	private JFrame frame;
	private JLabel clock;
	private Thread thread;
	private SimpleDateFormat sf;
	private control.EmpChange nowU;
	private JTextPane nowEmp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_UI window = new Main_UI();
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
	public Main_UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("메인화면");
		frame.setResizable(false);
		frame.setBounds(100, 100, 600, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton GoodsRegister = new JButton("상품 등록");
		GoodsRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddItemUI grframe = new AddItemUI();
				grframe.setVisible(true);
				frame.dispose();
			}
		});
		GoodsRegister.setBounds(146, 265, 122, 130);
		frame.getContentPane().add(GoodsRegister);

		JButton GoodsManagement = new JButton("상품 관리");
		GoodsManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemInfoUI giframe = new ItemInfoUI();
				giframe.setVisible(true);
				frame.dispose();
			}
		});
		GoodsManagement.setBounds(280, 265, 122, 130);
		frame.getContentPane().add(GoodsManagement);

		JButton SellButton = new JButton("상품 판매");
		SellButton.setBounds(14, 75, 388, 180);
		SellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product_Sell_UI gsframe = new Product_Sell_UI();
				gsframe.setVisible(true);
				frame.dispose();
			}
		});
		frame.getContentPane().add(SellButton);

		JButton Receiving = new JButton("입고 처리");
		Receiving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImportItemUI recframe = new ImportItemUI();
				recframe.setVisible(true);
				frame.dispose();
			}
		});
		Receiving.setBounds(12, 265, 122, 130);
		frame.getContentPane().add(Receiving);

		JButton Receipt = new JButton("영수증 조회");
		Receipt.setBounds(414, 188, 160, 67);
		Receipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReceiptCheckPage receiptframe = new ReceiptCheckPage();
				receiptframe.setVisible(true);
				frame.dispose();
			}
		});
		frame.getContentPane().add(Receipt);

		JPanel Info = new JPanel();
		Info.setBackground(Color.WHITE);
		Info.setBounds(14, 10, 560, 55);
		frame.getContentPane().add(Info);
		Info.setLayout(null);

		nowU = new control.EmpChange();
		nowEmp = new JTextPane();
		nowEmp.setBounds(432, 34, 128, 21);
		nowEmp.setEditable(false);
		nowEmp.setFont(new Font("굴림", Font.PLAIN, 12));
		nowEmp.setText("근무 직원: " + nowU.getName());
		Info.add(nowEmp);

		JTextPane storeName = new JTextPane();
		storeName.setText("OO마트");
		storeName.setEditable(false);
		storeName.setFont(new Font("굴림", Font.BOLD, 40));
		storeName.setBounds(0, 0, 247, 55);
		Info.add(storeName);

		clock = new JLabel("New label");
		clock.setFont(new Font("Gulim", Font.PLAIN, 12));
		sf = new SimpleDateFormat("yyyy년MM월dd일 a hh:mm:ss");
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		clock.setBounds(393, 0, 167, 15);
		Info.add(clock);

		JButton Manager = new JButton("관리자 모드 진입");
		Manager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasswordCheck pcframe = new PasswordCheck();
				pcframe.setVisible(true);
				frame.dispose();
			}
		});
		Manager.setBounds(414, 265, 160, 130);
		frame.getContentPane().add(Manager);

		JButton empChangebtn = new JButton("직원 교대");
		empChangebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeChange ecframe = new EmployeeChange();
				ecframe.setVisible(true);
			}
		});
		empChangebtn.setBounds(414, 75, 158, 28);
		frame.getContentPane().add(empChangebtn);

		JButton customerbtn = new JButton("고객 관리");
		customerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerSearchPage cmframe = new CustomerSearchPage();
				cmframe.setVisible(true);
				frame.dispose();
			}
		});
		customerbtn.setBounds(412, 111, 160, 67);
		frame.getContentPane().add(customerbtn);

	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			clock.setText(sf.format(new Date()));
			nowEmp.setText("근무 직원: " + nowU.getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}
