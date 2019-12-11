package ncontroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer/")
public class CustomerController {
	
	private NoticeDao noticeDao; //new는 xml에서만 하고 여기서는 하지 않을 것 > 따라서 setter가 noticedao의 주소값 전달해주기
	
	@Autowired
	public void setNoticedao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	
	@RequestMapping("notice.htm")
	public String notices(String pg, String f, String q, Model model) { //spring에서 model 인터페이스를 잡으면 model 객체 만들어줌
		
		//default
		int page = 1;
		String field = "TITLE";
		String query = "%%";
		
		
		//조건 처리
		if(pg != null && pg.equals("")) {
			page = Integer.parseInt(pg);
		}
		
		if(f != null && f.equals("")) {
			field = f;
		}
		
		if(q != null && q.equals("")) {
			query = q;
		}
		
		//DAO 데이터 받아오기
		List<Notice> list = null;
		try {
			list = noticeDao.getNotices(page, field, query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Spring >  Model 객체 제공
		model.addAttribute("list", list);  //view까지 전달(forward)	
		return "notice.jsp";
	}

	
	@RequestMapping("noticeDetail.htm")
	public ModelAndView submit(String seq) throws Exception {
		Notice notice = noticeDao.getNotice(seq);		
		ModelAndView mav = new ModelAndView();
		mav.addObject("notice", notice);
		mav.setViewName("noticeDetail.jsp");
		
		return mav;	
	}
	
	
	//글쓰기 화면 noticeReg.htm
	@RequestMapping(value="noticeReg.htm", method=RequestMethod.GET)
	public String form() {
		return "noticeReg.jsp";
	}
	
	//return "redirect:notice.htm"
	//글쓰기 처리 noticeReg.htm
	@RequestMapping(value="noticeReg.htm", method=RequestMethod.POST)
	public String submit(Notice notice, HttpServletRequest request) throws IOException, ClassNotFoundException, SQLException {
		//Notice DTO
		//private List<CommonsMultipartFile> files;
		//files[0] = new CommonsMultipartFile() >> 1.jpg
		//files[1] = new CommonsMultipartFile() >> 2.jpg
		
		List<CommonsMultipartFile> files = notice.getFiles(); //파일 정보 넘어 옴
		List<String> filenames = new ArrayList<String>(); // 파일명 관리
		
		//for문을 돌면서 정보를 가져옴
		if(files != null && files.size() > 0) { //최소 한개의 업로드가 있다면
			for(CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload");
				
				String fpath = path + "\\" + filename;
				if(!filename.equals("")) { //실파일 업로드
					FileOutputStream fs = new FileOutputStream(fpath);
					fs.write(multifile.getBytes());
					fs.close();
				}
				filenames.add(filename); //파일명을 별도 관리 (DB insert)
			}
		}

		//DB 파일명 저장
		notice.setFileSrc(filenames.get(0));
		notice.setFileSrc2(filenames.get(1));
		
		noticeDao.insert(notice);		
		return "redirect:notice.htm";
		
	}
	
	//글삭제하기 (noticeDel.htm) : seq(parameter)
	//return "redirect:notice.htm"	
	@RequestMapping(value="noticeDel.htm", method=RequestMethod.GET)
	public String delete(String seq) {
		int notice;
		try {
			notice = noticeDao.delete(seq);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:notice.htm";
	}
	
	
	//글 수정하기(화면 : select ... where seq=?) : GET :seq (parameter)
	//noticedao.getNotice(seq)
	//Model model >> 화면 >> 데이터 >> noticeEdit.jsp
	@RequestMapping(value="noticeEdit.htm", method=RequestMethod.GET)
	public String editform(String seq, Model model) throws ClassNotFoundException, SQLException {
		Notice notice = noticeDao.getNotice(seq);		
		model.addAttribute("notice", notice);
		return "noticeEdit.jsp";
	}
	
	
	//글 수정하기 (처리 : update ...where seq=?) : POST
	//parameter Notice notice >> Insert와 동일 > 무조건 파일 첨부 하는형태로
	//return "redirect:noticeDetail.htm?seq="notice.getSeq();
	@RequestMapping(value="noticeEdit.htm", method=RequestMethod.POST)
	public String editsubmit(String seq, Notice notice, HttpServletRequest request) throws IOException, ClassNotFoundException, SQLException {

		List<CommonsMultipartFile> files = notice.getFiles(); //파일 정보 넘어 옴
		List<String> filenames = new ArrayList<String>(); // 파일명 관리
		
		//for문을 돌면서 정보를 가져옴
		if(files != null && files.size() > 0) { //최소 한개의 업로드가 있다면
			for(CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload");
				
				String fpath = path + "\\" + filename;
				if(!filename.equals("")) { //실파일 업로드
					FileOutputStream fs = new FileOutputStream(fpath);
					fs.write(multifile.getBytes());
					fs.close();
				}
				filenames.add(filename); //파일명을 별도 관리 (DB insert)
			}
		}

		//DB 파일명 저장
		notice.setFileSrc(filenames.get(0));
		notice.setFileSrc2(filenames.get(1));

		noticeDao.update(notice);
		
		return "redirect:noticeDetail.htm?seq="+notice.getSeq();
		
	}	
	
	
	
	
	
	
	
	
	
	
}