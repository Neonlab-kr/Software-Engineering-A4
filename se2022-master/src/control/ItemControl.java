package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import SQL.dbConnecter;
import entity.Item;
import storeUI.AddItemUI;
import storeUI.ImportItemUI;
import storeUI.ItemInfoUI;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ItemControl {
	dbConnecter dbConn = new dbConnecter();

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

			if (!item.getBarcode().equals("")) {
				DefaultTableModel model = (DefaultTableModel) ui.goodsTable.getModel();
				model.setNumRows(0);
				model.addRow(new Object[] { item.getBarcode(), item.getItemName(), Integer.toString(item.getPrice()),
						Integer.toString(item.getStock()) });
			}
			ui.goodsCode.setText("");
			ui.goodsName.setText("");
		} else {
			List<Item> itemList = new Item().getItemListDB(ui.goodsName.getText());
			Iterator<Item> it = itemList.iterator();
			DefaultTableModel model = (DefaultTableModel) ui.goodsTable.getModel();
			model.setNumRows(0);
			while (it.hasNext()) {
				Item item = (Item) it.next();
				model.addRow(new Object[] { item.getBarcode(), item.getItemName(), Integer.toString(item.getPrice()),
						Integer.toString(item.getStock()) });
			}
			ui.goodsCode.setText("");
			ui.goodsName.setText("");
		}
	}

	public void DeleteItem(ItemInfoUI ui) {
		dbConn.getConnection();
		int row = ui.goodsTable.getSelectedRow();
		String sql = "DELETE FROM Item_Table WHERE Item_Code = " + ui.goodsTable.getValueAt(row, 0) + ";";
		dbConn.executeQuery(sql);
		((DefaultTableModel) ui.goodsTable.getModel()).removeRow(row);
		JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
	}

	public void ModifyItem(ItemInfoUI ui) {
		Connection tmpConn = dbConn.getConnection();
		PreparedStatement pre;
		for (int row = 0; row < ui.goodsTable.getRowCount(); row++) {
			try {
				pre = tmpConn.prepareStatement(
						"update Item_Table set Item_Name=?, Item_Price=?, Stock=?  WHERE Item_Code = ?;");
				pre.setString(1, (String) ui.goodsTable.getValueAt(row, 1));
				pre.setString(2, (String) ui.goodsTable.getValueAt(row, 2));
				pre.setString(3, (String) ui.goodsTable.getValueAt(row, 3));
				pre.setString(4, (String) ui.goodsTable.getValueAt(row, 0));
				pre.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "수정이 실패하였습니다", "수정 실패", JOptionPane.ERROR_MESSAGE);
			}
		}
		JOptionPane.showMessageDialog(null, "수정이 완료되었습니다", "수정 완료", JOptionPane.INFORMATION_MESSAGE);
	}

	public void ImportItem(ImportItemUI ui) {
		Connection tmpConn = dbConn.getConnection();
		PreparedStatement pre;
		try {
			if(Integer.parseInt(ui.recNum.getText())<0){
				throw new SQLException();
			}
			pre = tmpConn.prepareStatement("update Item_Table set Stock = Stock + " + ui.recNum.getText()
					+ " where Item_Code=\"" + ui.barcode.getText() + "\";");
			pre.executeUpdate();
			JOptionPane.showMessageDialog(null, "상품입고가 완료되었습니다", "입고 완료", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "상품입고에 실패하였습니다", "입고 실패", JOptionPane.ERROR_MESSAGE);
		} finally {
			ui.barcode.setText("");
			ui.goodsName.setText("");
			ui.goodsNum.setText("");
			ui.price.setText("");
			ui.recNum.setText("");
		}
	}

	public void AddExcel(String filePath) {
		try {
			Connection tmpConn = dbConn.getConnection();
			PreparedStatement pre = tmpConn
					.prepareStatement("insert into Item_Table(Item_Code,Item_Name,Item_Price,Stock) VALUES(?,?,?,?);");
			;

			Workbook workbook = null;
			File file = new File(filePath);
			FileInputStream fileInputStream = new FileInputStream(file);
			if (file.getAbsolutePath().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(fileInputStream);
			} else if (file.getAbsolutePath().endsWith("xls")) {
				workbook = new HSSFWorkbook(fileInputStream);
			}

			int rowindex = 0;
			int columnindex = 0;
			// 시트 수 (첫번째에만 존재하므로 0을 준다)
			// 만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
			Sheet sheet = workbook.getSheetAt(0);
			// 행의 수
			int rows = sheet.getPhysicalNumberOfRows();
			for (rowindex = 1; rowindex < rows; rowindex++) {
				// 행을읽는다
				Row row = sheet.getRow(rowindex);
				if (row != null) {
					// 셀의 수
					int cells = row.getPhysicalNumberOfCells();
					for (columnindex = 0; columnindex <= cells; columnindex++) {
						// 셀값을 읽는다
						Cell cell = row.getCell(columnindex);
						String value = "";
						// 셀이 빈값일경우를 위한 널체크
						if (cell == null) {
							continue;
						} else {
							switch (cell.getCellType()) {
							case FORMULA:
								value = cell.getCellFormula();
								break;
							case NUMERIC:
								value = cell.getNumericCellValue() + "";
								break;
							case STRING:
								value = cell.getStringCellValue() + "";
								break;
							case BLANK:
								value = cell.getBooleanCellValue() + "";
								break;
							case ERROR:
								value = cell.getErrorCellValue() + "";
								break;
							}
						}
						pre.setString(columnindex + 1, value);
					}
				}
				pre.executeUpdate();
			}
			JOptionPane.showMessageDialog(null, "상품일괄등록이 완료되었습니다", "등록 완료", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "상품일괄등록에 실패하였습니다", "등록 실패", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void AddItem(AddItemUI ui) {
		Connection tmpConn = dbConn.getConnection();
		PreparedStatement pre;
		try {
			if(Integer.parseInt(ui.price.getText())<0) {
				throw new SQLException();
			}
			pre = tmpConn
					.prepareStatement("insert into Item_Table(Item_Code,Item_Name,Item_Price,Stock) VALUES(?,?,?,?);");
			pre.setString(1, ui.barcode.getText());
			pre.setString(2, ui.itemName.getText());
			pre.setString(3, ui.price.getText());
			pre.setString(4, ui.stock.getText());
			pre.executeUpdate();
			JOptionPane.showMessageDialog(null, "상품등록이 완료되었습니다", "등록 완료", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "상품등록에 실패하였습니다", "등록 실패", JOptionPane.ERROR_MESSAGE);
		}
		ui.barcode.setText("");
		ui.itemName.setText("");
		ui.price.setText("");
		ui.stock.setText("");
	}
}
