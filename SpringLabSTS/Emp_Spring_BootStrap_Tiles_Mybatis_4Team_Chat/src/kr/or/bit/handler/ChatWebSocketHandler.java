package kr.or.bit.handler;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler{

	private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {		
		log(session.getId() + "채팅 입장");
		users.put(session.getId(), session);
	}


	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		log(session.getId() + "▶" + message.getPayload());
		for(WebSocketSession s : users.values()) {
			s.sendMessage(message);
			log(s.getId() + "에 메세지 발송" + message.getPayload());
		}		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		log(session.getId() + "채팅 종료");
		users.remove(session.getId());
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) {
		log(session.getId() + "예외 발생 : " + exception.getMessage());
	}
	
	private void log(String logmsg) {
		System.out.println(new Date() + ":" + logmsg);
		
	}
}
