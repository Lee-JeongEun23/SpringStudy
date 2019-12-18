package kr.or.bit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

@Controller
public class AjaxController {

		private SqlSession sqlSession;
	
		@Autowired
		private View jsonview;
		
		@Autowired
		public void setSqlsession(SqlSession sqlSession) {
			this.sqlSession = sqlSession;
		}
	
		@RequestMapping(value="GetDeptNo.do")
		public View GetDeptNo(HttpServletRequest request, ModelMap map){			
			EmpDao dao = sqlSession.getMapper(EmpDao.class);		
			List<Integer> deptnolist= dao.getDethNos();	
			map.addAttribute("deptnolist", deptnolist);
						
			return jsonview;  
		}
		
		@RequestMapping(value="GetEmpno.do")
		public View GetEmpno(HttpServletRequest request, ModelMap map){
			
			EmpDao dao = sqlSession.getMapper(EmpDao.class);				
			List<Emp> empnolist= dao.getEmps();
			map.addAttribute("empnolist", empnolist);
		
			return jsonview;  
		}
	
	
		@RequestMapping(value="GetJobRegister.do")
		public View GetJobRegister(HttpServletRequest request, ModelMap map){
			
			EmpDao dao = sqlSession.getMapper(EmpDao.class);				
			List<String> joblist= dao.getJobRegister();
			map.addAttribute("joblist", joblist);
		
			return jsonview;  
		}
	
		@RequestMapping(value="CheckEmpno.do")
		public View CheckEmpno(int empno, HttpServletRequest request, ModelMap map){
			
			EmpDao dao = sqlSession.getMapper(EmpDao.class);				
			Emp emp = dao.getEmpByEmpno(empno);
			
			boolean isUse = false;
			if(emp !=null) {
				isUse = true;
			}
			map.addAttribute("isUse", isUse);
			
		
			return jsonview;  
		}
		
	
}
