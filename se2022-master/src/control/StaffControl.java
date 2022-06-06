package control;

import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import SQL.dbConnector;
import entity.Staff;
import storeUI.StaffSearchPage;

public class StaffControl {
	dbConnector dbConn = new dbConnector();
	
	public void StaffSearch(StaffSearchPage ui) {
		if(ui.SearchOptionSel.getSelectedItem().toString().equals("ID")) {
			Staff staff = new Staff();
			staff.getStaffDB(ui.SearchTextField.getText());

			DefaultTableModel model = (DefaultTableModel) ui.StaffTable.getModel();
			model.setNumRows(0);
			model.addRow(new Object[] { staff.getId(), staff.getName(), staff.getPhone()});
			ui.SearchTextField.setText("");
		}
		else if(ui.SearchOptionSel.getSelectedItem().toString().equals("이름")) {
			List<Staff> stafflist = new Staff().getStaffListByNameDB(ui.SearchTextField.getText());
			Iterator<Staff> it = stafflist.iterator();
			DefaultTableModel model = (DefaultTableModel) ui.StaffTable.getModel();
			model.setNumRows(0);
			while (it.hasNext()) {
				Staff staff = (Staff) it.next();
				model.addRow(new Object[] { staff.getId(), staff.getName(),staff.getPhone()});
			}
			ui.SearchTextField.setText("");
		}
		else {
			List<Staff> stafflist = new Staff().getStaffListByPhoneDB(ui.SearchTextField.getText());
			Iterator<Staff> it = stafflist.iterator();
			DefaultTableModel model = (DefaultTableModel) ui.StaffTable.getModel();
			model.setNumRows(0);
			while (it.hasNext()) {
				Staff staff = (Staff) it.next();
				model.addRow(new Object[] { staff.getId(), staff.getName(),staff.getPhone()});
			}
			ui.SearchTextField.setText("");
		}
	}
	
	public void StaffRegister(StaffSearchPage ui) {
		Connection tmpConn = dbConn.getConnection();
		PreparedStatement pre;
		try {
			pre = tmpConn
					.prepareStatement("insert into Employee_Table(Employee_ID , Employee_Name, Employee_Phone) VALUES(?,?,?);");
			pre.setString(1, ui.idTextField.getText());
			pre.setString(2, ui.nameTextField.getText());
			pre.setString(3, ui.phoneTextField.getText());
			pre.executeUpdate();
			JOptionPane.showMessageDialog(null, "직원등록이 완료되었습니다", "등록 완료", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "직원등록에 실패하였습니다", "등록 실패", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void StaffAmend(StaffSearchPage ui) {
		Connection tmpConn = dbConn.getConnection();
		PreparedStatement pre;
		try {
			pre = tmpConn
					.prepareStatement("update Employee_Table set Employee_Name=?, Employee_Phone=? where Employee_ID = ?;");
			pre.setString(1, ui.nameTextField.getText());
			pre.setString(2, ui.phoneTextField.getText());
			pre.setString(3, ui.idTextField.getText());
			pre.executeUpdate();
			JOptionPane.showMessageDialog(null, "직원정보수정이 완료되었습니다", "수정 완료", JOptionPane.INFORMATION_MESSAGE);
			int row = ui.StaffTable.getSelectedRow();
			ui.StaffTable.setValueAt(ui.nameTextField.getText(),row, 1);
			ui.StaffTable.setValueAt(ui.phoneTextField.getText(),row, 2);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "직원정보수정에 실패하였습니다", "수정 실패", JOptionPane.ERROR_MESSAGE);
		}
		ui.idTextField.setText("");
		ui.nameTextField.setText("");
		ui.phoneTextField.setText("");
	}
	
	public void StaffDelete(StaffSearchPage ui) {
		dbConn.getConnection();
		int row = ui.StaffTable.getSelectedRow();
		String sql = "DELETE FROM Employee_Table WHERE Employee_ID = \'" + ui.StaffTable.getValueAt(row, 0) + "\';";
		dbConn.executeQuery(sql);
		((DefaultTableModel)ui.StaffTable.getModel()).removeRow(row);
		JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
	}

}
