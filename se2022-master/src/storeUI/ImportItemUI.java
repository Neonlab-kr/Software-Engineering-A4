package storeUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImportItemUI {

	private JFrame frame;
	public JTextField recNum;
	public JTextField goodsNum;
	public JTextField price;
	public JTextField goodsName;
	private JTextField barcode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportItemUI window = new ImportItemUI();
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
	public ImportItemUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 356, 264);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton backbtn = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_UI mp = new Main_UI();
				mp.setVisible(true);
				frame.dispose();
			}
		});
		backbtn.setBounds(218, 10, 97, 23);
		frame.getContentPane().add(backbtn);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 43, 316, 168);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JTextPane barcodeTxt = new JTextPane();
		barcodeTxt.setEditable(false);
		barcodeTxt.setText("\uBC14\uCF54\uB4DC");
		barcodeTxt.setBounds(24, 10, 43, 21);
		panel.add(barcodeTxt);

		JTextPane goodsNameTxt = new JTextPane();
		goodsNameTxt.setEditable(false);
		goodsNameTxt.setText("\uC0C1\uD488\uBA85");
		goodsNameTxt.setBounds(24, 41, 43, 21);
		panel.add(goodsNameTxt);

		JTextPane priceTxt = new JTextPane();
		priceTxt.setEditable(false);
		priceTxt.setText("\uAC00\uACA9");
		priceTxt.setBounds(36, 72, 31, 21);
		panel.add(priceTxt);

		JTextPane goodsNumTxt = new JTextPane();
		goodsNumTxt.setEditable(false);
		goodsNumTxt.setText("\uC7AC\uACE0\uC218\uB7C9");
		goodsNumTxt.setBounds(12, 103, 55, 21);
		panel.add(goodsNumTxt);

		JTextPane recNumTxt = new JTextPane();
		recNumTxt.setEditable(false);
		recNumTxt.setText("\uC785\uACE0\uC218\uB7C9");
		recNumTxt.setBounds(12, 134, 55, 21);
		panel.add(recNumTxt);

		recNum = new JTextField();
		recNum.setBounds(79, 134, 116, 21);
		panel.add(recNum);
		recNum.setColumns(10);

		goodsNum = new JTextField();
		goodsNum.setEnabled(false);
		goodsNum.setColumns(10);
		goodsNum.setBounds(79, 103, 116, 21);
		panel.add(goodsNum);

		price = new JTextField();
		price.setEnabled(false);
		price.setColumns(10);
		price.setBounds(79, 72, 116, 21);
		panel.add(price);

		goodsName = new JTextField();
		goodsName.setEnabled(false);
		goodsName.setColumns(10);
		goodsName.setBounds(79, 41, 116, 21);
		panel.add(goodsName);

		barcode = new JTextField();
		barcode.setColumns(10);
		barcode.setBounds(79, 10, 116, 21);
		panel.add(barcode);

		JButton searchbtn = new JButton("\uAC80\uC0C9");
		searchbtn.setBounds(207, 10, 97, 23);
		panel.add(searchbtn);

		JButton recbtn = new JButton("\uC785\uACE0");
		recbtn.setBounds(207, 39, 97, 23);
		panel.add(recbtn);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	public void dispose() {
		frame.dispose();
	}
}
