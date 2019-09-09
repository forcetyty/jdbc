package kr.co.itcen.hr;

import java.util.List;
import java.util.Scanner;

public class HRMain {

	public static void main(String[] args) {// 웹이랑 똑같다, 입력과 출력만 다른것이다(HTTP),
		Scanner sc = new Scanner(System.in);
		System.out.print(">>");
		String keyword = sc.nextLine();
		sc.close();

		// 직원 정보 생성(C)
		// dao.insert(f_name,l_name,b_date,gender,h_date);//이러한 값을 담아서 객체로 보내주는것 vo
		// dao.insert(employeeVo);
		// 직원 검색(이름)(R)
		EmployeeDao dao = new EmployeeDao();
		List<EmployeeVo> list = dao.getList(keyword);// 나머지는 과제

		// 직원검색 (사번)(R)
		// EmployeeVo vo =dao.get(no);
		// 직원 삭제(D)
		// Boolean result =dao.delete(no);
		// 직원 정보 수정(U)
		// Boolean result=dao.update(vo);

		printEmployee(list);
	}

	private static void printEmployee(List<EmployeeVo> list) {
		for (EmployeeVo vo : list) {
			System.out.println(vo);
		}
	}

}