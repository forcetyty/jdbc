package kr.co.itcen.hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao2 {

	public List<EmployeeVo> getList() {
		// TODO Auto-generated method stub

		List<EmployeeVo> result = new ArrayList<EmployeeVo>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// 아래와 같은 코드는 거의 모든 DBMS와 유사하다!!!
			// 그렇기에 스프링과 같은 프레임워크에서는 아래와 같은 코드는 숨기는 기능을한다.!!!

			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.81:3306/employees?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "hr", "hr");

			System.out.println("연결성공!!!");

			String sql = " select emp_no," + " first_name," + " last_name," + " gender,"
					+ " date_format(hire_date,'%Y년 %m월 %d일')" + " from employees" + " order by hire_date desc";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String gender = rs.getString(4);
				String hireDate = rs.getString(5);

				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setGender(gender);
				vo.setHireDate(hireDate);

				result.add(vo);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail to Loading Driver :" + e);

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("error : " + e);
		} finally {
			try {
				// 연결해제
				if (rs != null) {
					rs.close();
				}

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

		return null;
	}

	public List<EmployeeVo> getList(String keyword) {
		// TODO Auto-generated method stub

		List<EmployeeVo> result = new ArrayList<EmployeeVo>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// 아래와 같은 코드는 거의 모든 DBMS와 유사하다!!!
			// 그렇기에 스프링과 같은 프레임워크에서는 아래와 같은 코드는 숨기는 기능을한다.!!!

			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.1.81:3306/employees?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "hr", "hr");

			System.out.println("연결성공!!!");

			String sql = " select emp_no," + 
						 " first_name," + 
					     " last_name," + 
						 " gender," + 
					     " date_format(hire_date,'%Y년 %m월 %d일')" + 
						 " from employees" + " where first_name like ?"+ 
					     " and last_name like ?" + 
						 " order by hire_date desc";

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");

			rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				Long no = rs.getLong(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				String gender = rs.getString(4);
				String hireDate = rs.getString(5);

				EmployeeVo vo = new EmployeeVo();

				vo.setNo(no);
				vo.setFirstName(firstname);
				vo.setHireDate(hireDate);
				vo.setLastName(lastname);
				vo.setGender(gender);
				result.add(vo);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail to Loading Driver :" + e);

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("error : " + e);
		} finally {
			try {
				// 연결해제
				if (rs != null) {
					rs.close();
				}

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

		return null;
	}

}
