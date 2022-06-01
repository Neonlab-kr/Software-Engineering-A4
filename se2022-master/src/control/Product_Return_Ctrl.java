package control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import entity.Item;
import entity.Receipt;

public class Product_Return_Ctrl {
	private Receipt receipt;
	
	public Object[][] product_receiptSearch(String receiptID){
		receipt = new Receipt();
		if(receiptID.chars().allMatch(Character::isDigit)) {
			int id = Integer.parseInt(receiptID);
			ArrayList<Item> items = receipt.getproductList(id);
			if(items.isEmpty()) {
				System.out.println("존재하지 않는 영수증번호입니다.");
				return null;
			}
			receipt.loadReceipt(id);
			if(receipt.getpaymentMethod().equals("Refund")) {
				System.out.println("이미 처리된 영수증입니다.");
				JOptionPane.showMessageDialog(null, "영수증 에러", "이미 처리된 영수증입니다.", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			Object[][] temp = new Object[items.size()][3];
			for(int i =0;i<items.size();i++) {
				temp[i][0] = items.get(i).getItemName();
				temp[i][1] = items.get(i).getStock();
				temp[i][2] = items.get(i).getcalprice();
			}
			return temp;
		}
		else {
			System.out.println("잘못된 입력방식입니다.");
			return null;
		}
	}
	
	public String Calculator_totalMoney() {
		return Integer.toString(receipt.getbalance());
	}
	
	public void product_return() {
		ArrayList<Item> items = receipt.getproductList(receipt.getID());
		for(int i =0;i<items.size();i++) {
			receipt.updaterefundDB(items.get(i).getStock(), items.get(i).getBarcode());;
		}
		receipt.updatebalance(receipt.getID());
		receipt.updatepayment(receipt.getID());
		System.out.println("DB 클라이언트가 업데이트 되었습니다.");
	}
	
}
