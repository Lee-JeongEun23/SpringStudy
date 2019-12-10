package kr.or.bit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
public class HelloController implements Controller{

	public HelloController() {
		System.out.println("Hello Controller 객체 생성");
	}
	
	@Override //클라이언트의 요청 및 응답 가능 -> doget, dopost 역할 //자동 호출
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("HelloController 요청 실행 : handlerRequest 함수 실행"); //logfactory로 만들면 빨간색 system으로 찍을 수 있음
		
		//업무 수행(게시판 목록보기, 글쓰기....)
		//데이터 담고 view 지정
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", "hong"); //request.setAttribute()와 같음
		mav.setViewName("Hello"); //view 지정
		
		return mav;
	}

}
