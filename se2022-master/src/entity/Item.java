package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import SQL.dbConnector;

public class Item {
	private String barcode;
	private String itemName;
	private int price;
	private int stock;
	private dbConnector db;
	
	public Item(){
		this.barcode = "";
		this.itemName = "";
		this.price =0;
		this.stock =0;
		db = new dbConnector();
	}
	public Item(String barcode,String itemName,int stock,int price){
		this.barcode = barcode;
		this.itemName = itemName;
		this.price = price;
		this.stock = stock;
		db = new dbConnector();
	}
	
	public void getItemDB(String itemID) {
		this.barcode = itemID;
		String sql = "SELECT Item_Name , Item_Price FROM Item_Table WHERE Item_Code = '" + barcode + "'";
		try {
			ResultSet receipt = db.executeQuery(sql);
			if(receipt == null) {
				return;
			}
			while(receipt.next()) {
				this.itemName = receipt.getString(1);
				this.price = receipt.getInt(2);
				this.stock =1;
			}
		} catch (SQLException e) {
			System.out.println("DB연결이 실패하거나, SQL문에 오류가 있습니다.");
		}
	}
	
	public String getBarcode() {
		return barcode;
	}
	public String getItemName() {
		return itemName;
	}
	public int getPrice() {
		return price;
	}
	public int getStock() {
		return stock;
	}
	public int getcalprice() {
		return price*stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}
