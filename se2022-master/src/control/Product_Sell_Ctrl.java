package control;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import entity.Item;

public class Product_Sell_Ctrl {
	private ArrayList<Item> itemlist = new ArrayList<Item>();
	private Item item;
	
	public Object[][] product_search(String itemID) {//상품 정보가 입력시 찾아주는 장바구니에 추가하는 반환하는 메소드
		item = new Item();
	    int num = itemlist.size();
		Object[][] temp;
		int row = checkoverlap(itemID);//중복처리 검사
		if(row>=0) {//이미 해당 아이템이 장바구니에 있는경우 값만 증가시켜 출력
			itemlist.get(row).setStock(itemlist.get(row).getStock()+1);
			temp = getProduct();
			return temp;
		}
		item.getItemDB(itemID);//상품의 정보를 db에서 받아온다
		item.setStock(1);//초기 수량은 1로 설정
		if(num>0) {//장바구니에 다른내역이 있는경우
			temp = new Object[num+1][3];
			for(int i=0;i<num;i++) {//기존에 등록된 리스트를 불러온다.
				temp[i][0] = itemlist.get(i).getItemName();
				temp[i][1] = itemlist.get(i).getStock();
				temp[i][2] = itemlist.get(i).getcalprice();
			}
			if(item.getItemName().equals("")) {//해당하는 아이템이 없는경우
				JOptionPane.showMessageDialog(null, "해당되는 상품이 없거나 잘못된 입력방식입니다.", "상품 번호 오류", JOptionPane.ERROR_MESSAGE);
			}else {	//추가된 상품을 장바구니에 추가
				temp[num][0] = item.getItemName();
				temp[num][1] = item.getStock();
				temp[num][2] = item.getcalprice();
				itemlist.add(item);
			}
		}else {//장바구니에 내역없는 신규상태의 경우
			if(item.getItemName().equals("")) {//해당하는 아이템이 없는경우
				temp = null;
				JOptionPane.showMessageDialog(null, "해당되는 상품이 없거나 잘못된 입력방식입니다.", "상품 번호 오류", JOptionPane.ERROR_MESSAGE);
			}else {//추가된 상품을 장바구니에 추가
				temp = new Object[1][3];
				temp[0][0] = item.getItemName();
				temp[0][1] = item.getStock();
				temp[0][2] = item.getcalprice();
				itemlist.add(item);
			}
		}
		return temp;
	}
	
	public Boolean updateStock(int row, Object data) {//장바구니에서 상품의 수량을 업데이트할 경우 실행되는 메소드
		String str = data.toString();
		if(str.chars().allMatch(Character::isDigit)) {//수량이 숫자인지 판별
			int stock = Integer.parseInt(str);
			if(stock <=0) {//0이하의 음수는 값으로 들어갈수없다
				JOptionPane.showMessageDialog(null, "0이하의 숫자는 올수없습니다.", "수량 에러", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			itemlist.get(row).setStock(stock);//해당 수량으로 업데이트
			return true;
		}else {//숫자가 아닐경우
			JOptionPane.showMessageDialog(null, "잘못된 수량값입니다.", "수량 에러", JOptionPane.ERROR_MESSAGE);
			itemlist.get(row).setStock(1);
			return false;
		}
	}
	
	public Object[][] getProduct(){//장바구니에 저장된 내역을 변환 출력한다.
		int num = itemlist.size();
		Object[][] temp = new Object[num][3];
		for(int i=0;i<num;i++) {
			temp[i][0] = itemlist.get(i).getItemName();
			temp[i][1] = itemlist.get(i).getStock();
			temp[i][2] = itemlist.get(i).getcalprice();
		}
		return temp;
	}
	
	public int checkoverlap(String itemID) {//중복되는 아이템이 있는지 검사
		for(int i =0;i<itemlist.size();i++) {
			if(itemlist.get(i).getBarcode().equals(itemID))
				return i;//중복되는 해당 위치를 전달
		}
		return -1;
	}
	
	public String calculator_totalMoney() {//총금액을 계산해주는 메소드
		int totalMoney = 0;
		for(int i=0;i<itemlist.size();i++) {
			totalMoney += itemlist.get(i).getcalprice();
		}
		return Integer.toString(totalMoney);
	}
	public ArrayList<Item> getItems(){//최종 장바구니 저장된 리스트를 전달
		return this.itemlist;
	}
	
	public void clear() {//컨트롤에 저장된 리스트 초기화
		itemlist.clear();
	}
	public int getsize() {//현제 장바구니에 등록된 수량을 반환
		return itemlist.size();
	}
	
	public Object[][] product_deleteOne(int row) {//장바구니에 클릭된 상품을 제거
		itemlist.remove(row);
		return getProduct();
	}
	
	public Object[][] product_deleteAll() {//장바구니 초기화
		itemlist.clear();
		return null;
	}
	
}
