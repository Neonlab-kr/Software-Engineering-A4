package control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import entity.Item;
import entity.Receipt;

public abstract class Product_Pay_Ctrl {
	protected Receipt receipt;
	
	abstract public void product_pay();
	
	public void check_state(Boolean check) {
		if(check) {
			System.out.println("성공적으로 결제되었습니다.");
		}else {
			JOptionPane.showMessageDialog(null, "결제 에러", "금액이 부족합니다.", JOptionPane.ERROR_MESSAGE);
			System.out.println("잘못된 결제입니다.");
		}
	}
	public void calculator_Money() {
		int balance =0;
		ArrayList<Item> temp = receipt.getItems();
		for(int i =0;i<temp.size();i++) {
			balance += temp.get(i).getcalprice();
		}
		receipt.setBalance(balance);
	}
	public String viewbalance() {
		return Integer.toString(receipt.getbalance());
	}
}
