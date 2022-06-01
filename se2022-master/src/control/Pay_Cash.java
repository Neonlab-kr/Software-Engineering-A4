package control;

import java.util.ArrayList;

import entity.Item;
import entity.Receipt;

public class Pay_Cash extends Product_Pay_Ctrl{
	
	public Pay_Cash(ArrayList<Item> items) {
		super.receipt = new Receipt();
		receipt.setItems(items);
		calculator_Money();
	}
	
	@Override
	public void product_pay() {//결제처리시 영수증 생성부분 작성
		receipt.create_receipt("Cash");
	}
	
	public String moneyChanges(String money) {
		if(money.chars().allMatch(Character::isDigit)) {
			int changes = Integer.parseInt(money) - receipt.getbalance();
			return Integer.toString(changes);
		}else {
			System.out.println("잘못된 입력양식입니다.");
			return Integer.toString(receipt.getbalance());
		}
	}
	
}
