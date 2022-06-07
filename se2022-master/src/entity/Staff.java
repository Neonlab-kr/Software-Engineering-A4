package entity;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JOptionPane;

import SQL.dbConnecter;

public class Staff {
	private String id;
	private String name;
	private String phone;
	private dbConnecter db;
	
	
	public Staff() {
		db = new dbConnecter();
	}
	
	public Staff(String id, String name, String phone) {
		this.id=id;
		this.name=name;
		this.phone=phone;
		db = new dbConnecter();
	}
	
	public void getStaffDB(String id) {
		String sql = "SELECT Employee_ID , Employee_Name, Employee_Phone FROM Employee_Table WHERE Employee_ID = '" + id + "';";
		try {
			ResultSet rs = db.executeQuery(sql);
			if(!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.", "결과 없음", JOptionPane.WARNING_MESSAGE);
			}
			while(rs.next()) {
				this.id = rs.getString(1);
				this.name = rs.getString(2);
				this.phone =rs.getString(3);
			}
		} catch (SQLException e) {
			System.out.println("DB연결이 실패하거나, SQL문에 오류가 있습니다.");
		}
	}
	
	public List<Staff> getStaffListByNameDB(String name){
		List<Staff> staffList = new ArrayList<Staff>();
		String sql = "SELECT Employee_ID, Employee_Name, Employee_Phone FROM Employee_Table WHERE Employee_Name like \"%" + name + "%\";";
		ResultSet src = db.executeQuery(sql);
		try {
			if (!src.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.", "결과 없음", JOptionPane.WARNING_MESSAGE);
			} else {
				while(src.next()) {
					staffList.add(new Staff(src.getString(1),src.getString(2),src.getString(3)));
				}	
			}
		} catch (HeadlessException | NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "검색이 완료되었습니다", "검색 완료", JOptionPane.INFORMATION_MESSAGE);
		return staffList;
	}
	
	public List<Staff> getStaffListByPhoneDB(String phone){
		List<Staff> staffList = new ArrayList<Staff>();
		String sql = "SELECT Employee_ID, Employee_Name, Employee_Phone FROM Employee_Table WHERE Employee_Phone like \"%" + phone + "%\";";
		ResultSet src = db.executeQuery(sql);
		try {
			if (!src.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.", "결과 없음", JOptionPane.WARNING_MESSAGE);
			} else {
				while(src.next()) {
					staffList.add(new Staff(src.getString(1),src.getString(2),src.getString(3)));
				}	
			}
		} catch (HeadlessException | NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "검색이 완료되었습니다", "검색 완료", JOptionPane.INFORMATION_MESSAGE);
		return staffList;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
