package control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import entity.Item;
import entity.Receipt;

public abstract class Product_Pay_Ctrl {
	protected Receipt receipt;
	
	abstract public void product_pay();//상품을 결제를 처리하는 추상메소드
	
	public void check_state(Boolean check) {//상태 확인후 오류메시지 출력
		if(check) {
			JOptionPane.showMessageDialog(null, "성공적으로 결제되었습니다.", "결재 성공", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("성공적으로 결제되었습니다.");
		}else {
			JOptionPane.showMessageDialog(null, "잘못된 결제입니다.", "결재 에러.", JOptionPane.ERROR_MESSAGE);
			System.out.println("잘못된 결제입니다.");
		}
	}
}
