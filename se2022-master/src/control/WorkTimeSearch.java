package control;

import java.sql.*;

import SQL.dbConnecter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class WorkTimeSearch {
	SQL.dbConnecter db = new SQL.dbConnecter();
	
	private Object[][] userData;
	
	public Object[][] getInfo(String name) {
		ResultSet rs = db.executeQuery("SELECT Employee_Name, Start_Timestamp, End_Timestamp "
				+ "FROM Attendance_Table, Employee_Table "
				+ "WHERE Attendance_Table.Employee_ID=\'" + name + "\' and End_Timestamp IS NOT NULL and Employee_Table.Employee_ID = Attendance_Table.Employee_ID");
		int rowcnt = 0;
		int j = 0;
		try {
			rs.last();
			rowcnt = rs.getRow();
			rs.beforeFirst();
			userData = new Object[rowcnt][3];
			while (rs.next()) {
				userData[j][0] = rs.getString("Employee_Name");
				userData[j][1] = rs.getTimestamp("Start_Timestamp");
				userData[j][2] = rs.getTimestamp("End_Timestamp");
				j++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("여기서 오류");
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
		}
		return userData;
	}

	public Object[][] getInfo(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		java.sql.Date tomorrow = new java.sql.Date(c.getTimeInMillis());
		ResultSet rs = db.executeQuery("SELECT Employee_Name, Start_Timestamp, End_Timestamp "
				+ "FROM Attendance_Table, Employee_Table "
				+ "WHERE Start_Timestamp between (\'" + date + "\') and (\'" + tomorrow + "\') and End_Timestamp IS NOT NULL and Employee_Table.Employee_ID = Attendance_Table.Employee_ID");
		int rowcnt = 0;
		int j = 0;
		try {
			rs.last();
			rowcnt = rs.getRow();
			rs.beforeFirst();
			userData = new Object[rowcnt][3];
			while (rs.next()) {
				userData[j][0] = rs.getString("Employee_Name");
				userData[j][1] = rs.getTimestamp("Start_Timestamp");
				userData[j][2] = rs.getTimestamp("End_Timestamp");
				j++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("여기서 오류");
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
		}
		return userData;
	}

}
