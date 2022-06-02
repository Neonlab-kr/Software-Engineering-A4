package control;

import java.sql.*;

public class PasswordControl {
	private String password;

	SQL.dbConnector db = new SQL.dbConnector();

	public boolean pwCheck(String pw) {
		boolean check = false;
		String DBpassword = null;
		password = pw;
		String sql = "SELECT Password FROM Password_Table";
		ResultSet rs = db.executeQuery(sql);
		try {
			while (rs.next()) {
				DBpassword = rs.getString("Password");
			}
			if (password.equals(DBpassword))
				check = true;
			else
				check = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
		}
		return check;
	}
}
