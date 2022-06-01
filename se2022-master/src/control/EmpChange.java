package control;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmpChange {
	SQL.dbConnector db = new SQL.dbConnector();
	private String id;
	private String nowuser;
	private SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
	private Calendar cal = Calendar.getInstance();
	private String today = formatter.format(cal.getTime());
	Timestamp Date = Timestamp.valueOf(today);
	
	ResultSet un = db.executeQurey("SELECT Employee_ID FROM Employee_Table");
	ResultSet nowU = db.executeQurey("SELECT Employee_ID FROM Attendance_Table WHERE End_Timestamp IS NULL");
	
	public boolean idChange(String input) {
		boolean check = true;
		try {
			while(un.next()) {
				id = un.getString("Employee_ID");
				if(id.equals(input)) {
					check = true;
					break;
				}
				else {
					check = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	public void dateRecord() {
		
	}
	public String getUser() {
		try {
			while(nowU.next()) {
				nowuser = nowU.getString("Employee_ID");
				System.out.println(nowuser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nowuser;
	}
	public String getId() {
		return id;
	}
	
}
