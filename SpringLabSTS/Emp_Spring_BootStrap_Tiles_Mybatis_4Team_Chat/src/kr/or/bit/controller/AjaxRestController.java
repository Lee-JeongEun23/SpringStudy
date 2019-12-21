package kr.or.bit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

@RestController
public class AjaxRestController {

		private SqlSession sqlSession;
		
		@Autowired
		public void setSqlsession(SqlSession sqlSession) {
			this.sqlSession = sqlSession;
		}

		
		@RequestMapping(value="GetDeptNo.do")
		public List<Integer> GetDeptNo(){		
			
			EmpDao dao = sqlSession.getMapper(EmpDao.class);		
			List<Integer> deptnolist= dao.getDethNos();	
			return deptnolist;  
		}
		

		@RequestMapping(value="GetEmpno.do")
		public List<Emp> GetEmpno(){
			
			EmpDao dao = sqlSession.getMapper(EmpDao.class);				
			List<Emp> empnolist= dao.getEmps();
		
			return empnolist;  
		}
	
	
		@RequestMapping(value="GetJobRegister.do")
		public List<String> GetJobRegister(){
			
			EmpDao dao = sqlSession.getMapper(EmpDao.class);				
			List<String> joblist= dao.getJobRegister();
		
			return joblist;  
		}
	
	
		@RequestMapping(value="CheckEmpno.do")
		public boolean CheckEmpno(int empno){
			
			EmpDao dao = sqlSession.getMapper(EmpDao.class);				
			Emp emp = dao.getEmpByEmpno(empno);
			
			boolean isUse = false;
			if(emp !=null) {
				isUse = true;
			}
		
			return isUse;  
		}

	
}
