package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import SQL.dbConnector;
import entity.Item;
import storeUI.ImportItemUI;

public class ItemControl {
	dbConnector dbConn = new dbConnector();

	public boolean ImportItem(ImportItemUI ui, String barcode) {
		Item item = new Item();
		item.getItemDB(barcode);//바코드를 통해 상품 정보를 받아옴
		ui.goodsName.setText(item.getItemName());
		ui.price.setText(Integer.toString(item.getPrice()));
		ui.goodsNum.setText(Integer.toString(item.getStock()));//ui의 정보 갱신
		return true;
	}

	public boolean AddExcel() {
		return true;
	}

	public boolean AddItem(String barcode, String name, int price, int stock) {
		Connection tmpConn = dbConn.getConnection();
		PreparedStatement pre;
		try {
			pre = tmpConn
					.prepareStatement("insert into Item_Table(Item_Code,Item_Name,Item_Price,Stock) VALUES(?,?,?,?);");
			pre.setString(1, barcode);
			pre.setString(2, name);
			pre.setInt(3, price);
			pre.setInt(4, stock);
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
