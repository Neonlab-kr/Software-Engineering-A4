package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import entity.Item;
import entity.Receipt;

public class ReceiptCheck {
	private int totalMoney;
	private Receipt receipt;
	
	public String receiptSearch(int receiptID) {
		receipt = new Receipt();
		StringBuffer receiptString = new StringBuffer();
		ArrayList<Item> itemlist;
		receiptString.append("품명\t\t단가\t수량\t금액\r\n");
		receiptString.append("---------------------------------------------------------");
		receipt.loadReceipt(receiptID);
		itemlist = receipt.getproductList(receiptID);
		for(int i =0;i<itemlist.size();i++) {
			receiptString.append("\r\n");
			receiptString.append(itemlist.get(i).getItemName());
			receiptString.append("\t");
			receiptString.append(itemlist.get(i).getPrice());
			receiptString.append("\t");
			receiptString.append(itemlist.get(i).getStock());
			receiptString.append("\t");
			receiptString.append(itemlist.get(i).getcalprice());
		}
		
		receiptString.append("\r\n---------------------------------------------------------");
		receiptString.append("\r\n합      계:\t\t\t\t");
		receiptString.append(receipt.getbalance());
		receiptString.append("\r\n받을금액:\t\t\t\t");
		receiptString.append(receipt.getbalance());
		receiptString.append("\r\n받은금액:\t\t\t\t");
		receiptString.append(receipt.getbalance());
		receiptString.append("\r\n---------------------------------------------------------");
		receiptString.append("\r\n결제수단\t\t\t\t");
		receiptString.append(receipt.getpaymentMethod());	
		return receiptString.toString();		
	}
	
	public Object[][] viewList(){
		receipt = new Receipt();
		int maxnum = receipt.max_receiptnum() -1;
		Object[][] arr = new Object[maxnum][4];
		ResultSet receiptlist = receipt.getReceiptList();
		try {
			int i =0;
			while (receiptlist.next()) {
				arr[i][0] = receiptlist.getInt(1);
				arr[i][1] = receiptlist.getString(2);
				arr[i][2] = receiptlist.getString(3);
				arr[i][3] = receiptlist.getInt(4);
				i++;
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return arr;
	}
	
	public Object[][] view(int receiptID){
		receipt = new Receipt();
		Object[][] arr;
		receipt.loadReceipt(receiptID);
		if(receipt.getbalance() == 0) {
			arr = null;
		}else {
			arr = new Object[1][4];
			arr[0][0] = receiptID;
			arr[0][1] = receipt.getpurchaseDate();
			arr[0][2] = receipt.getpaymentMethod();
			arr[0][3] = receipt.getbalance();
		}
		return arr;
	}
}
