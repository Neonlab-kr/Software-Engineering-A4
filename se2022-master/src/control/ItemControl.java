package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import SQL.dbConnector;
import entity.Item;
import storeUI.AddItemUI;
import storeUI.ImportItemUI;
import storeUI.ItemInfoUI;

public class ItemControl {
	dbConnector dbConn = new dbConnector();

	public void SearchItem(ImportItemUI ui) {
		Item item = new Item();
		item.getItemDB(ui.barcode.getText());// 바코드를 통해 상품 정보를 받아옴
		ui.goodsName.setText(item.getItemName());
		ui.price.setText(Integer.toString(item.getPrice()));
		ui.goodsNum.setText(Integer.toString(item.getStock()));// ui의 정보 갱신
	}

	public void SearchItem(ItemInfoUI ui) {
		if (!ui.goodsCode.getText().equals("")) {
			Item item = new Item();
			item.getItemDB(ui.goodsCode.getText());

			DefaultTableModel model = (DefaultTableModel) ui.goodsTable.getModel();
			model.addRow(new Object[] { item.getBarcode(), item.getItemName(), Integer.toString(item.getPrice()),
					Integer.toString(item.getStock()) });
		} else if (!ui.goodsName.getText().equals("")) {
			List<Item> itemList = new Item().getItemListDB(ui.goodsName.getText());
			Iterator<Item> it = itemList.iterator();
			DefaultTableModel model = (DefaultTableModel) ui.goodsTable.getModel();
			while (it.hasNext()) {
				Item item = (Item) it.next();
				model.addRow(new Object[] { item.getBarcode(), item.getItemName(), Integer.toString(item.getPrice()),
						Integer.toString(item.getStock()) });
			}
		}
	}

	public void DeleteItem(ItemInfoUI ui) {
		dbConn.getConnection();
		int row = ui.goodsTable.getSelectedRow();
		String sql = "DELETE FROM Item_Table WHERE Item_Code = " + ui.goodsTable.getValueAt(row,1) + ";";
		dbConn.executeQuery(sql);
	}

	public void ModifyItem(ItemInfoUI ui) {
		Connection tmpConn = dbConn.getConnection();
		int row = ui.goodsTable.getSelectedRow();
		PreparedStatement pre;
		try {
			pre = tmpConn
					.prepareStatement("update Item_Table set Item_Name=?, Item_Price=?, Stock=?  WHERE Item_Code = ?;");
			pre.setString(1, (String) ui.goodsTable.getValueAt(row, 1));
			pre.setString(2, (String) ui.goodsTable.getValueAt(row, 2));
			pre.setString(3, (String) ui.goodsTable.getValueAt(row, 3));
			pre.setString(4, (String) ui.goodsTable.getValueAt(row, 0));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean ImportItem(ImportItemUI ui) {
		Connection tmpConn = dbConn.getConnection();
		PreparedStatement pre;
		try {
			pre = tmpConn.prepareStatement("update Item_Table set Stock = Stock + " + ui.recNum.getText()
					+ " where Item_Code=\"" + ui.barcode.getText() + "\";");
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean AddExcel() {
		return true;
	}

	public boolean AddItem(AddItemUI ui) {
		Connection tmpConn = dbConn.getConnection();
		PreparedStatement pre;
		try {
			pre = tmpConn
					.prepareStatement("insert into Item_Table(Item_Code,Item_Name,Item_Price,Stock) VALUES(?,?,?,?);");
			pre.setString(1, ui.barcode.getText());
			pre.setString(2, ui.itemName.getText());
			pre.setString(3, ui.price.getText());
			pre.setString(4, ui.stock.getText());
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
