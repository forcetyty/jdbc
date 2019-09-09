package kr.co.itcen.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {


	public static void main(String[] args) {
		Connection connection = null;
		
		try {
			
			//아래와 같은 코드는 거의 모든 DBMS와 유사하다!!!
			//그렇기에 스프링과 같은 프레임워크에서는 아래와 같은 코드는 숨기는 기능을한다.!!!
			
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.81:3306/webdb?characterEncoding=utf8";
			//아래는 네트워크 코드
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
			System.out.println("연결성공!!!");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail to Loading Driver :" + e);
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("error : " + e);
		} finally {
			try {
				//연결해제
				if(connection != null) {
					connection.close();
				} 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}

}
