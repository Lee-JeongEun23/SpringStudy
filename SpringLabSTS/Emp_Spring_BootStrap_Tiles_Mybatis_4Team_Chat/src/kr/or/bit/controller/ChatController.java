package kr.or.bit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

	@RequestMapping("/chat-ws.do")
	public String show() {
		return "/chat-ws.jsp";
	}
	
	
}
