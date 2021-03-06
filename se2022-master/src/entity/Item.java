package entity;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JOptionPane;

import SQL.dbConnecter;

public class Item {
	private String barcode;
	private String itemName;
	private int price;
	private int stock;
	private dbConnecter db;
	
	public Item(){
		this.barcode = "";
		this.itemName = "";
		this.price =0;
		this.stock =0;
		db = new dbConnecter();
	}
	public Item(String barcode,String itemName,int stock,int price){
		this.barcode = barcode;
		this.itemName = itemName;
		this.price = price;
		this.stock = stock;
		db = new dbConnecter();
	}
	
	public void getItemDB(String itemID) {
		String sql = "SELECT Item_Code, Item_Name , Item_Price, Stock FROM Item_Table WHERE Item_Code = '" + itemID + "';";
		try {
			ResultSet rs = db.executeQuery(sql);
			if(!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.", "결과 없음", JOptionPane.WARNING_MESSAGE);
			}
			while(rs.next()) {
				this.barcode = rs.getString(1);
				this.itemName = rs.getString(2);
				this.price = rs.getInt(3);
				this.stock =rs.getInt(4);
			}
		} catch (SQLException e) {
			System.out.println("DB연결이 실패하거나, SQL문에 오류가 있습니다.");
		}
	}
	
	public List<Item> getItemListDB(String itemName) {
		List<Item> itemList = new ArrayList<Item>();
		String sql = "SELECT Item_Code, Item_Name , Item_Price, Stock FROM Item_Table WHERE Item_Name like \"%" + itemName + "%\";";
		ResultSet src = db.executeQuery(sql);
		try {
			if (!src.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.", "결과 없음", JOptionPane.WARNING_MESSAGE);
			} else {
				while(src.next()) {
					itemList.add(new Item(src.getString(1),src.getString(2),Integer.parseInt(src.getString(4)),Integer.parseInt(src.getString(3))));
				}
				JOptionPane.showMessageDialog(null, "검색이 완료되었습니다", "검색 완료", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (HeadlessException | NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return itemList;
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
