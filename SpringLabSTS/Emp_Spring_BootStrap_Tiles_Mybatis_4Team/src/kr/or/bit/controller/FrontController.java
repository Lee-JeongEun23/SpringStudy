package kr.or.bit.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

@Controller
public class FrontController {
	
	private SqlSession sqlsession;
	
	@Autowired	
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	
	//로그인
	@RequestMapping(value="Login.do",method=RequestMethod.GET)
	public String Login() {
		return  "./WEB-INF/views/login/Login.jsp";
	}
	
	@RequestMapping(value="Login.do",method=RequestMethod.POST)
	public String Login(String userid, String pwd, HttpServletRequest request) {
		try {
			EmpDao empdao= sqlsession.getMapper(EmpDao.class);
			empdao.checkAdminLogin(userid, pwd);
			
			if (userid != null) {
				request.getSession().setAttribute("userid", userid);
				return "index.jsp";
			} else
				return "Login.do";

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
			return "index.jsp"; 
		}
	
	//회원가입
	@RequestMapping(value="Register.do",method=RequestMethod.GET)
	public String Register() {
		return  "/WEB-INF/views/register/Register.jsp";
	}
	
	@RequestMapping(value="Register.do" , method=RequestMethod.POST)
	public String join(Emp emp, HttpServletRequest request) throws FileNotFoundException{
		CommonsMultipartFile file = emp.getFile();
		if(file != null ) { //최소 1개의 업로드가 있다면 
				String filename = file.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/upload");
				
				String fpath = path + "\\"+ filename; 
			
		try {		
				if(!filename.equals("")) { //실 파일 업로드
					FileOutputStream fs = new FileOutputStream(fpath);
					fs.write(file.getBytes());
					fs.close();
				}
				emp.setImagefilename(filename);					
				
				EmpDao empdao= sqlsession.getMapper(EmpDao.class);
				empdao.insertEmp(emp);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			}
		}
		return "redirect:/Login.do";
	}
	
	@RequestMapping("MemberList.do")
	public String MemberList(Model model) {
				List<Emp> emplist=null;
				try {
				     EmpDao empdao= sqlsession.getMapper(EmpDao.class);
				     emplist = empdao.getEmps();
				     System.out.println(emplist);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				model.addAttribute("emplist", emplist);			
		return  "/WEB-INF/views/admin/MemberList.jsp"; 
	}
	
	@RequestMapping("MemberDetail.do")
	public String MemberDetail(int empno , Model model) {
		
		Emp emp = null;
		try {
			EmpDao empdao= sqlsession.getMapper(EmpDao.class);
			emp = empdao.getEmpByEmpno(empno);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("empdetail", emp);
		
		return  "/WEB-INF/views/admin/MemberDetail.jsp";
	}
	
	@RequestMapping("MemberDelete.do")
	public String MemberDelete(int empno) throws ClassNotFoundException, SQLException {
		EmpDao empdao= sqlsession.getMapper(EmpDao.class);
		empdao.deleteEmpByEmpno(empno);
		return "redirect:/MemberList.do";
	}
	
	@RequestMapping(value="MemberEdit.do", method=RequestMethod.GET)
	public String MemberEdit(int empno, Model model) throws ClassNotFoundException, SQLException {
		EmpDao empdao= sqlsession.getMapper(EmpDao.class);
		Emp emp = empdao.getEmpByEmpno(empno);
		model.addAttribute("emp", emp);
		return  "/WEB-INF/views/admin/MemberEdit.jsp";
	}
	
	@RequestMapping(value="MemberEdit.do",method=RequestMethod.POST)
	public String MemberEdit(Emp emp, HttpServletRequest request) throws IOException, ClassNotFoundException, SQLException {
		CommonsMultipartFile file = emp.getFile();
		
		if(file != null ) { //최소 1개의 업로드가 있다면 
				String filename = file.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/upload");
				
				String fpath = path + "\\"+ filename; 
				System.out.println(fpath);
			
		try {		
				if(!filename.equals("")) { //실 파일 업로드
					FileOutputStream fs = new FileOutputStream(fpath);
					fs.write(file.getBytes());
					fs.close();
				}
				emp.setImagefilename(filename);
				
				EmpDao empdao= sqlsession.getMapper(EmpDao.class);
				empdao.updateEmp(emp);
				
				}catch (Exception e) {
					System.out.println(e.getMessage());
					}
				}
				return "redirect:/MemberList.do";
			}

	@RequestMapping("Logout.do")
	public String Logout(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		request.getSession().invalidate();
		return "redirect:/index.jsp";
	}
	
}
	

