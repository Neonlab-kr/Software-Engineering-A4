package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import entity.Item;
import entity.Receipt;

public class ReceiptCheck {
	private Receipt receipt;
	
	public String receiptSearch(int receiptID) {//선택된 영수증을 양식대로 출력
		receipt = new Receipt();
		StringBuffer receiptString = new StringBuffer();
		ArrayList<Item> itemlist;
		receipt.loadReceipt(receiptID);//영수증 정보 불러오기
		itemlist = receipt.getproductList(receiptID);//조회된 영수증의 상품들 정보 불러오기
		receiptString.append("품명\t\t단가\t수량\t금액\r\n");
		receiptString.append("---------------------------------------------------------");
		for(int i =0;i<itemlist.size();i++) {//영수증에 저장된 상품정보를 양식에 맞게 추가
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
	
	public Object[][] viewList(){//영수증 리스트 전체를 출력
		receipt = new Receipt();
		if(receipt.max_receiptnum() == 0)//영수증이 존재하지 않을 경우 null
			return null;	
		int maxnum = receipt.max_receiptnum();//영수증 최대번호 받아오기
		Object[][] arr = new Object[maxnum-1][4];
		ResultSet receiptlist = receipt.getReceiptList();//영수증 리스트 받아오기
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
			System.out.println("DB연결이 실패하거나, SQL문에 오류가 있습니다.");
		}
		return arr;
	}
	
	public Object[][] view(int receiptID){//조회한 영수증을 출력
		receipt = new Receipt();
		Object[][] arr;
		receipt.loadReceipt(receiptID);//영수증 조회
		if(receipt.getpaymentMethod().equals("")) {//조회되지 않을 경우 없으므로 null전달
			arr = null;
		}else {//조회된 영수증을 저장
			arr = new Object[1][4];
			arr[0][0] = receiptID;
			arr[0][1] = receipt.getpurchaseDate();
			arr[0][2] = receipt.getpaymentMethod();
			arr[0][3] = receipt.getbalance();
		}
		return arr;
	}
}
