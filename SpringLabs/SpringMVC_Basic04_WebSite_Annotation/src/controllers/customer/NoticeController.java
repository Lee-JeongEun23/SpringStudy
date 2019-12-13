package controllers.customer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import dao.NoticeDao;
import vo.Notice;

//게시판 목록 보기 업무

public class NoticeController implements Controller {

	public NoticeController() {
		System.out.println("[NoticeController]");
	}
	
	private NoticeDao noticedao; //new는 xml에서만 하고 여기서는 하지 않을 것 > 따라서 setter가 noticedao의 주소값 전달해주기
	
	
	//Spring 코드 상에서 new 작업(x) 그리고 컨테이너 안에 NoticeDao가 있어야 setter 만들 수 있음
	//injection : xml, annotation
	public void setNoticedao(NoticeDao noticedao) {
		this.noticedao = noticedao;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//검색 처리
		String _page = request.getParameter("pg");
		String _field = request.getParameter("f");
		String _query = request.getParameter("p");
		
		//default
		int page = 1;
		String field = "TITLE";
		String query = "%%";
		
		
		//조건 처리
		if(_page != null && _page.equals("")) {
			page = Integer.parseInt(_page);
		}
		
		if(_field != null && _field.equals("")) {
			field = _field;
		}
		
		if(_query != null && _query.equals("")) {
			query = _query;
		}
		
		//DAO 데이터 받아오기
		List<Notice> list = noticedao.getNotices(page, field, query);
		
		//Spring > ModelAndView or Model 객체 제공
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("notice.jsp");
		
		
		return mv;
	}

}
