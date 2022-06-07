package entity;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SQL.dbConnecter;

public class Receipt {
	private int receiptID;
	private ArrayList<Item> productList;
	private String purchaseDate;
	private String paymentMethod;
	private int balance;
	private dbConnecter db;
	
	public Receipt() {
		this.receiptID = 0;
		this.productList = new ArrayList<Item>();
		this.purchaseDate = "";
		this.paymentMethod = "";
		this.balance = 0;
		db = new dbConnecter();
	}
	
	public void loadReceipt(int receiptID) {//영수증 정보를 불러와서 저장
		this.receiptID = receiptID;
		String sql = "SELECT * FROM `Receipt_Table` WHERE Receipt_Code = " + receiptID;
		try {
			ResultSet receipt = db.executeQuery(sql);
			while(receipt.next()) {
				this.purchaseDate = receipt.getString(2);
				this.paymentMethod = receipt.getString(3);
				this.balance = receipt.getInt(4);
			}
		} catch (SQLException e) {
			System.out.println("DB연결이 실패하거나, SQL문에 오류가 있습니다.");
		}
	}
	
	public ArrayList<Item> getproductList(int receipt) {//영수증에 저장된 상품의 리스트을 받아온다
		this.receiptID = receipt;
		String sqlString = "SELECT Item_Table.Item_Code, Item_Table.Item_Name, Purchased_Items_List_Table.Sales_Quantity, Item_Table.Item_Price "
				+"FROM Item_Table LEFT JOIN Purchased_Items_List_Table ON Item_Table.Item_Code = Purchased_Items_List_Table.Item_Code AND Purchased_Items_List_Table.Receipt_Code = " + receiptID  + " WHERE Sales_Quantity IS NOT NULL";
		try {
			ResultSet receiptlistResultSet = db.executeQuery(sqlString);
			while(receiptlistResultSet.next()) {
				productList.add(new Item(receiptlistResultSet.getString(1),receiptlistResultSet.getString(2),receiptlistResultSet.getInt(3),receiptlistResultSet.getInt(4)));
			}
		} catch (SQLException e) {
			System.out.println("DB연결이 실패하거나, SQL문에 오류가 있습니다.");
		}
		return productList;
	}
	
	public ResultSet getReceiptList() {//영수증리스트를 받아온다.
		String sql = "SELECT * FROM `Receipt_Table`";
		return db.executeQuery(sql);
	}
	
	public int max_receiptnum() {//영수증 번호의 최고값 받아와서 출력
		int num =0;
		String number = "SELECT MAX(`Receipt_Code`) FROM Receipt_Table";
		try {
			ResultSet maxnum = db.executeQuery(number);
			if(maxnum.next()) {
				num = maxnum.getInt(1);//최고값 저장
			}
		} catch (SQLException e) {
			System.out.println("DB연결이 실패하거나, SQL문에 오류가 있습니다.");		}
		return num;
	}
	
	
	
	public Boolean create_receipt(String method) {//영수증 생성 메소드
		receiptID = max_receiptnum() + 1;
		String sql = "INSERT INTO `Receipt_Table` (`Receipt_Code`, `Payment_Method` , Receipt_Table.Balance ) VALUES ('" + receiptID + "', '" + method + "' , '" + balance + "' )";
		ResultSet resultSet = db.executeQuery(sql);
		for(int i =0;i<productList.size();i++) {//영수증에 구매된 아이템들을 추가,판매된 재고를 삭감
			String purchase = "INSERT INTO `Purchased_Items_List_Table` (`Receipt_Code`, `Item_Code`, `Sales_Quantity`) VALUES ('" + receiptID + "', '" + productList.get(i).getBarcode() + " ', '" + productList.get(i).getStock() + "')";
			String subStock = "UPDATE Item_Table set Stock = Stock - '" + productList.get(i).getStock() + "' "
					+ "WHERE Item_Code = '" + productList.get(i).getBarcode() + "'";	
			db.executeQuery(purchase);
			db.executeQuery(subStock);
		}
		if(resultSet != null)
			return true;
		else
			return false;
	}
	
	public Boolean updatepayment(int id) {//결제수단을 환불처리로 바꾸어 반품된 영수증을 표시한다.
		String str = "UPDATE Receipt_Table SET Payment_Method = 'Refund' WHERE Receipt_Table.Receipt_Code = " + id;
		ResultSet check = db.executeQuery(str);
		if(check == null)
			return false;
		else 
			return true;
	}
	public void updaterefundDB(int stock,String itemID) {//해당 상품의 수량을 추가시켜준다.(반품시에 사용)
		String str = "UPDATE Item_Table set Stock = Stock + "+ stock +" WHERE Item_Code = " + itemID;
		db.executeQuery(str);
	}
	public void updatebalance(int id) {//총금액을 0으로 만든다.
		String str = "UPDATE Receipt_Table SET Balance = 0 WHERE Receipt_Table.Receipt_Code = " + id;
		db.executeQuery(str);
	}
	
	public void setItems(ArrayList<Item> items) {
		this.productList = items;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public ArrayList<Item> getItems(){
		return this.productList;
	}
	public int getID() {
		return receiptID;
	}
	public String getpurchaseDate(){
		return purchaseDate;
	}
	public String getpaymentMethod() {
		return paymentMethod;
	}
	public int getbalance() {
		return balance;
	}
	
	
}
