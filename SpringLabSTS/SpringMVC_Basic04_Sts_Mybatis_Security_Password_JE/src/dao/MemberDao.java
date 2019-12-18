package dao;

import java.sql.SQLException;
import vo.Member;

public interface MemberDao {
	
	//회원정보 얻기
	public Member getMember(String userid) throws ClassNotFoundException, SQLException;
	
	//회원가입
	public int insertMember(Member member) throws ClassNotFoundException, SQLException;

	//회원 ROLE
	public int insertRole(String userid) throws ClassNotFoundException, SQLException;
	
	//회원 아이디 검증하기 (비동기)
	
	//로그인 체크하기 
	public int loginCheck(String username, String password) throws ClassNotFoundException, SQLException;
	
	//회원정보 수정하기
	public int updateMember(Member member) throws ClassNotFoundException, SQLException;
}
