package ncontroller;

import java.security.Principal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import service.MemberService;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {

	private Validator validator;
	
	@Autowired
	private MemberService service;

	///////////////////////////////////////////////////////
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	//회원가입페이지
	@RequestMapping(value="join.htm" , method=RequestMethod.GET)
	public String join() {
		
		return  "joinus.join"; //"join.jsp"; 
	}
	
	//회원가입처리
	@RequestMapping(value="join.htm" , method=RequestMethod.POST)
	public String join(@Valid Member member, BindingResult error, Model model) throws Exception{
		System.out.println("야 나다");
		
		/*
		 * Set<ConstraintViolation<Member>> violations = validator.validate(member);
		 * 
		 * for(ConstraintViolation<Member> violation : violations) { String propertyPath
		 * = violation.getPropertyPath().toString(); String message =
		 * violation.getMessage();
		 * 
		 * error.addError(new FieldError("member", propertyPath, message)); }
		 */
		

		if(error.hasErrors()) {
			System.out.println(error.toString());
			model.addAttribute("member", member);
			
			return "joinus.join";
		}
		else {
			System.out.println("야 나다3");
		int result = 0;
		String viewpage = "";
		member.setPwd(this.bCryptPasswordEncoder.encode(member.getPwd()));
		result = service.insertMember(member);
		result = service.insertRole(member.getUserid());
		
		if (result > 0) {
			System.out.println("삽입 성공");
			viewpage = "redirect:/index.htm";
		} else {
			System.out.println("삽입 실패");
			viewpage = "join.htm";
		}
		return viewpage; 
		}
	}
		
    @RequestMapping(value="login.htm",method=RequestMethod.GET)
	 public String login(){
	  return "joinus.login"; //Tiles 적용
	 }
    
	
	//회원정보 조회 및 수정 /////////////////////////////////////////////////////////////
	@RequestMapping(value="memberconfirm.htm",method=RequestMethod.GET)
	public String memberConfirm(){
		return "joinus.memberConfirm";
	}
	
	@RequestMapping(value="memberconfirm.htm",method=RequestMethod.POST)
	public String memberConfirm(@RequestParam("password") String rawPassword, Principal principal) throws Exception{
		String viewpage="";
		
		//회원정보
		Member member = service.getMember(principal.getName());
		
		
		//DB에서 가져온 암호화된 문자열
		String encodedPassword = member.getPwd();
		boolean result = bCryptPasswordEncoder.matches(rawPassword, encodedPassword);		
		if(result){
			viewpage="redirect:memberupdate.htm";
		}else{
			viewpage="redirect:memberconfirm.htm";
		}	
		return viewpage;
	}
	
	
	@RequestMapping(value="memberupdate.htm", method=RequestMethod.GET)
	public String updateMember(Model model, Principal principal) throws Exception{
		Member member = service.getMember(principal.getName());
		model.addAttribute("member", member);
		return "joinus.memberUpdate";
	}
	
	@RequestMapping(value="memberupdate.htm", method=RequestMethod.POST)
	public String updateMember(Model model, Member member, Principal principal) throws Exception{
		
		Member update = service.getMember(principal.getName());
		
		update.setName(member.getName());
		update.setCphone(member.getCphone());
		update.setEmail(member.getEmail());
		update.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
		service.updateMember(update);
		return "redirect:/index.htm";
	}
    
    
	
}











