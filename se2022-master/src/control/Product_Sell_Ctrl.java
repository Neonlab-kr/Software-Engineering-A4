package control;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import javax.swing.JOptionPane;

import com.jgoodies.forms.layout.Size;

import entity.Item;

public class Product_Sell_Ctrl {
	private ArrayList<Item> itemlist = new ArrayList<Item>();
	private Item item;
	
	public Object[][] product_search(String itemID) {
		item = new Item();
	    int num = itemlist.size();
		Object[][] temp;
		int row = checkoverlap(itemID);//중복처리 검사
		if(row>=0) {
			itemlist.get(row).setStock(itemlist.get(row).getStock()+1);
			temp = getProduct();
			return temp;
		}
		item.getItemDB(itemID);
		if(num>0) {
			temp = new Object[num+1][3];
			if(item.getItemName().equals("")) {//해당하는 아이템이 없는경우
				System.out.println("해당되는 상품이 없거나 잘못된 입력방식입니다.");
			}else {
				for(int i=0;i<num;i++) {
					temp[i][0] = itemlist.get(i).getItemName();
					temp[i][1] = itemlist.get(i).getStock();
					temp[i][2] = itemlist.get(i).getcalprice();
				}
				temp[num][0] = item.getItemName();
				temp[num][1] = item.getStock();
				temp[num][2] = item.getcalprice();
				itemlist.add(item);
			}
		}else {
			if(item.getItemName().equals("")) {//해당하는 아이템이 없는경우
				temp = null;
				System.out.println("해당되는 상품이 없거나 잘못된 입력방식입니다.");
			}else {
				temp = new Object[1][3];
				temp[0][0] = item.getItemName();
				temp[0][1] = item.getStock();
				temp[0][2] = item.getcalprice();
				itemlist.add(item);
			}
		}
		return temp;
	}
	
	public Boolean updateStock(int row, Object data) {
		String str = data.toString();
		if(str.chars().allMatch(Character::isDigit)) {
			int stock = Integer.parseInt(str);
			itemlist.get(row).setStock(stock);
			return true;
		}else {
			JOptionPane.showMessageDialog(null, "수량 에러", "잘못된 수량값입니다.", JOptionPane.ERROR_MESSAGE);
			itemlist.get(row).setStock(1);
			return false;
		}
	}
	
	public Object[][] getProduct(){
		int num = itemlist.size();
		Object[][] temp = new Object[num][3];
		for(int i=0;i<num;i++) {
			temp[i][0] = itemlist.get(i).getItemName();
			temp[i][1] = itemlist.get(i).getStock();
			temp[i][2] = itemlist.get(i).getcalprice();
		}
		return temp;
	}
	
	public int checkoverlap(String itemID) {
		for(int i =0;i<itemlist.size();i++) {
			if(itemlist.get(i).getBarcode().equals(itemID))
				return i;
		}
		return -1;
	}
	
	public String calculator_totalMoney() {
		int totalMoney = 0;
		for(int i=0;i<itemlist.size();i++) {
			totalMoney += itemlist.get(i).getcalprice();
		}
		return Integer.toString(totalMoney);
	}
	public ArrayList<Item> getItems(){
		return this.itemlist;
	}
	
	public Object[][] product_deleteOne(int row) {
		itemlist.remove(row);
		return getProduct();
	}
	
	public Object[][] product_deleteAll() {
		itemlist.clear();
		return null;
	}
	
}
