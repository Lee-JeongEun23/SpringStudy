package ncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vo.Member;

import dao.MemberDao;

@Controller
@RequestMapping("/joinus/")
public class JoinController {

	private MemberDao memberdao;

	@Autowired
	public void setMemberdao(MemberDao memberdao) {
		this.memberdao = memberdao;
	}
	
	//회원가입페이지
	@RequestMapping(value="join.htm", method=RequestMethod.GET)
	public String join() {
		return "join.jsp";
		
	}
	//회원가입 처리
	@RequestMapping(value="join.htm", method=RequestMethod.POST)	
	public String join(Member member) {
		System.out.println(member.toString());
		
		try {
			memberdao.insert(member);
		}catch(Exception e) {
			
		}
		return "redirect:/index.htm"; //이렇게 /를 붙이면 밖으로 빠짐
		
	}
	
}
