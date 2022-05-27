package SQL;

import java.sql.*;

import javax.swing.JOptionPane;

public class dbConnector {

	Connection conn; // java.sql.Connection
	Statement stmt = null;

	
	public dbConnector() {
		
		// 생성자가 실행되면 DB에 자동 연결되어 Connection 객체 생성
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://harinoyama.com:3306/swa4", "swa4","DEUA4program!");
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement();

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			JOptionPane.showMessageDialog(null, "DB 연결 오류", "DB 연결 오류", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public ResultSet executeQurey(String sql) {
		//SQL문 실행하기 위한 메소드 - Parameter : String객체로 만든 SQL문
		//실행결과는 ResultSet으로 반환
		System.out.println(sql);
		ResultSet src = null;
		try {
			src = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(src);
			System.out.println("SQL 실행 에러");
			JOptionPane.showMessageDialog(null, "SQL 실행 에러", "SQL 실행 에러", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		return src;
	}
	
	public Connection getConnection() {
		//PreparedStatement이용해 SQL 작성할 경우 Connection 객체가 필요해 만든 메소드
		
		if(conn!=null) {
			return conn;
		}else {
			return null;
		}
		
	}
	
}