package service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import vo.Member;


@Service
public class MemberService {
	
	private SqlSession sqlSession;

	@Autowired
	public void setSqlsession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insertMember(Member member) throws Exception{
		int result = 0;
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		result = dao.insertMember(member);
		return result;
	}
	
	public int insertRole(String userid) throws Exception{
		int result = 0;
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		result = dao.insertRole(userid);
		return result;
	}
	
	
	public int loginCheck(String username, String password) throws Exception{
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		int result = dao.loginCheck(username, password);
		return result;
	}
	
	public Member getMember(String userid) throws Exception{
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		Member member = dao.getMember(userid);
		return member;
	}
	
	public void updateMember(Member member) throws Exception{
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		int result = dao.updateMember(member);
		if(result > 0){
			System.out.println("업데이트 성공");
		}else{
			System.out.println("업데이트 실패");
		}
	}
	
}
