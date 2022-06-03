package control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import entity.Item;
import entity.Receipt;

public class Pay_Cash extends Product_Pay_Ctrl{
	
	public Pay_Cash(ArrayList<Item> items,int totalmoney) {
		super.receipt = new Receipt();
		receipt.setItems(items);//영수증에 장바구니 목록 등록
		receipt.setBalance(totalmoney);//총금액 등록
	}
	
	@Override
	public void product_pay() {//결제처리시 영수증 생성부분 작성
		check_state(receipt.create_receipt("Cash"));
	}
	
	public String moneyChanges(String money) {//잔돈 계산하여 전달
		if(money.chars().allMatch(Character::isDigit)) {//숫자인지 확인
			int changes = Integer.parseInt(money) - receipt.getbalance();//받은금액-총금액
			return Integer.toString(changes);
		}else {
			JOptionPane.showMessageDialog(null, "잘못된 입력양식입니다.", "입력 에러.", JOptionPane.ERROR_MESSAGE);
			System.out.println("잘못된 입력양식입니다.");
			return Integer.toString(receipt.getbalance());
		}
	}
	
}
