package control;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import entity.Item;
import entity.Receipt;

public class Product_Return_Ctrl {
	private Receipt receipt;
	ArrayList<Item> items = new ArrayList<Item>();
	
	public Object[][] product_receiptSearch(String receiptID){//입력된 영수증 번호로 해당 내역 조회
		receipt = new Receipt();
		if(receiptID.chars().allMatch(Character::isDigit)) {//입력된 영수증 번호가 숫자가 맞는지 확인
			int id = Integer.parseInt(receiptID);
			items.clear();//초기화후 저장
			items = receipt.getproductList(id);//영수증에 등록된 아이템 리스트를 받아온다.
			if(items.isEmpty()) {//받아온 아이템내역이 없으면 오류메시지를 띄어준다.
				JOptionPane.showMessageDialog(null, "존재하지 않는 영수증번호입니다.", "영수증 정보 에러", JOptionPane.ERROR_MESSAGE);
				System.out.println("존재하지 않는 영수증번호입니다.");
				return null;
			}
			receipt.loadReceipt(id);//영수증 정보를 불러온다.
			if(receipt.getpaymentMethod().equals("Refund")) {//해당 영수증이 처리되었을 경우 오류메시지
				JOptionPane.showMessageDialog(null, "이미 처리된 영수증입니다.", "수량 에러", JOptionPane.ERROR_MESSAGE);
				System.out.println("이미 처리된 영수증입니다.");
				return null;
			}
			Object[][] temp = new Object[items.size()][3];
			for(int i =0;i<items.size();i++) {//영수증에 있는 상품리스트를 받아온다
				temp[i][0] = items.get(i).getItemName();
				temp[i][1] = items.get(i).getStock();
				temp[i][2] = items.get(i).getcalprice();
			}
			return temp;
		}
		else {//숫자가 아닐경우 오류메시지
			JOptionPane.showMessageDialog(null, "잘못된 입력방식입니다.", "입력 에러", JOptionPane.ERROR_MESSAGE);
			System.out.println("잘못된 입력방식입니다.");
			return null;
		}
	}
	
	public String Calculator_totalMoney() {//영수증에 저장된 총금액을 받아온다.
		return Integer.toString(receipt.getbalance());
	}
	
	public void product_return() {//상품의 환불을 처리한다.
		for(int i =0;i<items.size();i++) {
			receipt.updaterefundDB(items.get(i).getStock(), items.get(i).getBarcode());//해당 상품들의 내역을 재고에 추가한다.
		}
		receipt.updatebalance(receipt.getID());//총금액을 0으로 초기화
		Boolean check = receipt.updatepayment(receipt.getID());//결제수단을 'Refund'하여 환불처리됨을 표시
		items.clear();//사용된 리스트를 비워준다.
		
		if(check) {
			JOptionPane.showMessageDialog(null, "성공적으로 환불되었습니다.", "반품 완료", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("DB 클라이언트가 업데이트 되었습니다.");
		}
	}
	
}
