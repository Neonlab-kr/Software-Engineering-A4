package control;

import java.sql.*;

public class PasswordControl {
	private String password;

	SQL.dbConnector db = new SQL.dbConnector();

	public boolean pwCheck(String pw) {
		boolean check = false;
		String sql = "SELECT Password FROM Password_Table";
		ResultSet rs = db.executeQuery(sql);
		try {
			if (password.equals(rs.getString("Password")))
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

	public void setPw(String pw) {
		password = pw;
	}
}
