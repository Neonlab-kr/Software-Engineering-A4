package control;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmpChange {
	SQL.dbConnector db = new SQL.dbConnector();
	Connection conn = db.getConnection();
	private String id;
	private String nowuser;
	private String inputid;
	private String nowname;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private Calendar cal = Calendar.getInstance();
	private String today = formatter.format(cal.getTime());
	Timestamp Date = Timestamp.valueOf(today);

	String sql_insertuser = "INSERT INTO Attendance_Table(Employee_ID, Start_Timestamp, End_Timestamp) VALUES(?, ?, ?)";
	String sql_nowuser = "UPDATE Attendance_Table SET Start_Timestamp=?, End_Timestamp=? WHERE Employee_ID=?";
	String sql_changeduser = "UPDATE Attendance_Table SET End_Timestamp=? WHERE End_Timestamp IS NULL";
	String sql_selectuser = "SELECT Employee_ID FROM Attendance_Table WHERE End_Timestamp IS NULL";

	ResultSet un = db.executeQuery("SELECT Employee_ID FROM Employee_Table");
	ResultSet nowU = db.executeQuery(sql_selectuser);
	ResultSet attTable = db.executeQuery("SELECT Employee_ID FROM Attendance_Table");

	public boolean idChange(String input) {//입력한 직원이 직원 테이블에 존재하는지 체크함
		boolean check = true;
		inputid = input;
		try {
			while (un.next()) {
				id = un.getString("Employee_ID");
				if (id.equals(inputid)) {
					check = true;
					dateRecord();
					break;
				} else {
					check = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (un != null)
				try {
					un.close();
				} catch (Exception e) {
				}
		}
		return check;
	}

	public void dateRecord() {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			System.out.println("기존 사용자: " + getUser() + "새 사용자: " + inputid);
			pstmt2 = conn.prepareStatement(sql_changeduser);
			pstmt2.setTimestamp(1, Date);
			pstmt2.execute();
			
			pstmt = conn.prepareStatement(sql_insertuser);
			pstmt.setString(1, inputid);
			pstmt.setTimestamp(2, Date);
			pstmt.setTimestamp(3, null);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (pstmt2 != null)
				try {
					pstmt2.close();
				} catch (Exception e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
		}
	}

	public String getUser() {
		try {
			while (nowU.next()) {
				nowuser = nowU.getString("Employee_ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (nowU != null)
				try {
					nowU.close();
				} catch (Exception e) {
				}
		}
		return nowuser;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		ResultSet username = db.executeQuery("SELECT Employee_Name FROM Employee_Table WHERE Employee_Table.Employee_ID IN (" + sql_selectuser + ")");
		try {
			while (username.next()) {
				nowname = username.getString("Employee_Name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (username != null)
				try {
					username.close();
				} catch (Exception e) {
				}
		}
		return nowname;
	}

}
