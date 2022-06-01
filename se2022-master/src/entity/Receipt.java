package entity;

import java.io.ObjectInputStream.GetField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import SQL.dbConnector;

public class Receipt {
	private int receiptID;
	private ArrayList<Item> productList;
	private String purchaseDate;
	private String paymentMethod;
	private int balance;
	private dbConnector db;
	
	public Receipt() {
		this.receiptID = 0;
		this.productList = new ArrayList<Item>();
		this.purchaseDate = "";
		this.paymentMethod = "";
		this.balance = 0;
		db = new dbConnector();
	}
	public Receipt(int receiptID,ArrayList<Item> productList, String purchaseDate, String paymentMethod) {
		this.receiptID = receiptID;
		this.productList = productList;
		this.purchaseDate = purchaseDate;
		this.paymentMethod = paymentMethod;
		db = new dbConnector();
	}
	
	public void loadReceipt(int receiptID) {
		this.receiptID = receiptID;
		String sql = "SELECT * FROM `Receipt_Table` WHERE Receipt_Code = " + receiptID;
		try {
			ResultSet receipt = db.executeQurey(sql);
			while(receipt.next()) {
				this.purchaseDate = receipt.getString(2);
				this.paymentMethod = receipt.getString(3);
				this.balance = receipt.getInt(4);
			}
		} catch (SQLException e) {
			System.out.println("DB연결이 실패하거나, SQL문에 오류가 있습니다.");
		}
	}
	
	public ArrayList<Item> getproductList(int receipt) {
		this.receiptID = receipt;
		String sqlString = "SELECT Item_Table.Item_Code, Item_Table.Item_Name, Purchased_Items_List_Table.Sales_Quantity, Item_Table.Item_Price "
				+"FROM Item_Table LEFT JOIN Purchased_Items_List_Table ON Item_Table.Item_Code = Purchased_Items_List_Table.Item_Code AND Purchased_Items_List_Table.Receipt_Code = " + receiptID  + " WHERE Sales_Quantity IS NOT NULL";
		try {
			ResultSet receiptlistResultSet = db.executeQurey(sqlString);
			while(receiptlistResultSet.next()) {
				productList.add(new Item(receiptlistResultSet.getString(1),receiptlistResultSet.getString(2),receiptlistResultSet.getInt(3),receiptlistResultSet.getInt(4)));
			}
		} catch (SQLException e) {
			System.out.println("DB연결이 실패하거나, SQL문에 오류가 있습니다.");
		}
		return productList;
	}
	
	public ResultSet getReceiptList() {
		String sql = "SELECT * FROM `Receipt_Table`";
		return db.executeQurey(sql);
	}
	
	public int max_receiptnum() {
		int num =0;
		String number = "SELECT MAX(`Receipt_Code`) FROM `Receipt_Table`";
		try {
			ResultSet maxnum = db.executeQurey(number);
			if(maxnum.next()) {
				num = maxnum.getInt(1) + 1;
			}
		} catch (SQLException e) {
			System.out.println("DB연결이 실패하거나, SQL문에 오류가 있습니다.");		}
		return num;
	}
	
	
	
	public void create_receipt(String method) {
		receiptID = max_receiptnum();
		String sql = "INSERT INTO `Receipt_Table` (`Receipt_Code`, `Payment_Method` , Receipt_Table.Balance ) VALUES ('6', '" + method + "' , '" + balance + "' )";
		db.executeQurey(sql);
		for(int i =0;i<productList.size();i++) {
			String purchase = "INSERT INTO `Purchased_Items_List_Table` (`Receipt_Code`, `Item_Code`, `Sales_Quantity`) VALUES ('" + receiptID + "', '" + productList.get(i).getBarcode() + " ', '" + productList.get(i).getStock() + "')";
			String subStock = "UPDATE Item_Table set Stock = Stock - '" + productList.get(i).getStock() + "' "
					+ "WHERE Item_Code = '" + productList.get(i).getBarcode() + "'";	
			db.executeQurey(purchase);
			db.executeQurey(subStock);
		}
	}
	
	public void updatepayment(int id) {
		String str = "UPDATE Receipt_Table SET Payment_Method = 'Refund' WHERE Receipt_Table.Receipt_Code = " + id;
		db.executeQurey(str);
	}
	public void updaterefundDB(int stock,String itemID) {
		String str = "UPDATE Item_Table set Stock = Stock + "+ stock +" WHERE Item_Code = " + itemID;
		db.executeQurey(str);
	}
	public void updatebalance(int id) {
		String str = "UPDATE Receipt_Table SET Balance = 0 WHERE Receipt_Table.Receipt_Code = " + id;
		db.executeQurey(str);
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
