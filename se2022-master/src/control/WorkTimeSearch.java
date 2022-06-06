package control;

import java.sql.*;

import SQL.dbConnector;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkTimeSearch {
	SQL.dbConnector db = new SQL.dbConnector();
	
	private Object[][] userData;
	
	public Object[][] getInfo(String name) {
		ResultSet rs = db.executeQuery("SELECT * FROM Attendance_Table WHERE Employee_ID=" + name + " and End_Timestamp IS NOT NULL");
		int rowcnt = 0;
		int j = 0;
		try {
			rs.last();
			rowcnt = rs.getRow();
			rs.first();
			userData = new Object[rowcnt][3];
			while (rs.next()) {
				userData[j][0] = rs.getString("Employee_ID");
				userData[j][1] = rs.getDate("Start_Timestamp");
				userData[j][2] = rs.getDate("End_Timestamp");
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
		return userData;
	}

}
