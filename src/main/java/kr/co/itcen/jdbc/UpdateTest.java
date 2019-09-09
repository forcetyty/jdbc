package kr.co.itcen.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {

	public static void main(String[] args) {
		update(1L, "총무1팀");
		update(2L, "영업1팀");
		update(3L, "인사1팀");
	}

	public static Boolean update2(String name, Long no) {
		Boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;	//SQL에게 미리 준비하라는 뜻!!!
		//SQL문을 미리 준비시키는 이유!!!
		//String sql = "update department set name = ? where no = ?";
		// '?' -> Binding!!!
		// PreparedStatement
		// 1. + 연산을 하지 않기 때문에 실수를 줄 일수 있다.
		// 2. 보안에 유리하다!!! // or '1'='1"; // 바인딩하지 않고 전송하게 되면 SQL injection 공격에 취약하다.
		// 배치작업!!!

		try {

			// 아래와 같은 코드는 거의 모든 DBMS와 유사하다!!!
			// 그렇기에 스프링과 같은 프레임워크에서는 아래와 같은 코드는 숨기는 기능을한다.!!!

			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.81:3306/webdb?characterEncoding=utf8";
			// 아래는 네트워크 코드
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("연결성공!!!");
			
			//3. SQL문 준비 시키기
			// or '1'='1"; // 바인딩하지 않고 전송하게 되면 SQL injection 공격에 취약하다.
			String sql = "update department set name = ? where no = ?";
			pstmt = connection.prepareStatement(sql);
			
			
			// 4. 바인딩
			pstmt.setString(1, name);
			pstmt.setLong(2, no);
			
			//
			int count = pstmt.executeUpdate();
			result = (count == 1);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail to Loading Driver :" + e);

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("error : " + e);
		} finally {
			try {
				// 연결해제

				if (pstmt != null) {
					pstmt.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return result;

	}

	public static Boolean update(Long no, String name) {

		Boolean result = false;

		Connection connection = null;
		Statement stmt = null;

		try {

			// 아래와 같은 코드는 거의 모든 DBMS와 유사하다!!!
			// 그렇기에 스프링과 같은 프레임워크에서는 아래와 같은 코드는 숨기는 기능을한다.!!!

			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.81:3306/webdb?characterEncoding=utf8";
			// 아래는 네트워크 코드
			connection = DriverManager.getConnection(url, "webdb", "webdb");

			System.out.println("연결성공!!!");

			// 3. Statment 객체 생성(받아오기)
			stmt = connection.createStatement();

			// 4. SQL문 실행
			String sql = "update department set name = '" + name + "' where no =" + no;
			int count = stmt.executeUpdate(sql);

			result = (count == 1);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail to Loading Driver :" + e);

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("error : " + e);
		} finally {
			try {
				// 연결해제

				if (stmt != null) {
					stmt.close();
				}

				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return result;

	}

}
