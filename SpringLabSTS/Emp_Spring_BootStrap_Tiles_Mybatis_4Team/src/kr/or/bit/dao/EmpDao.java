package kr.or.bit.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.bit.dto.Emp;

public interface EmpDao {

	//회원 추가
	public int insertEmp(Emp emp) throws ClassNotFoundException, SQLException;
	
	//사원으로 회원 가져오기
	public Emp getEmpByEmpno(int empno) throws ClassNotFoundException, SQLException;
	
	//비동기로 직업 가져오기
	public List<String> getJobRegister() throws ClassNotFoundException, SQLException;
	
	//관리자 로그인
	public String checkAdminLogin(String userid, String pwd) throws ClassNotFoundException, SQLException;
	
	//사원 리스트 가져오기
	public List<Emp> getEmps() throws ClassNotFoundException, SQLException;
	
	//사번으로 사원 삭제
	public int deleteEmpByEmpno(int empno) throws ClassNotFoundException, SQLException;
	
	//사원 수정
	public int updateEmp(Emp emp) throws ClassNotFoundException, SQLException;
	
	//부서번호 가져오기
	public List<Integer> getDethNos() throws ClassNotFoundException, SQLException;

	
	
	
	
	
}
