package kr.or.bit.handler;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

	private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();
	private Map<String, String> memberName = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log(session.getId() + " 연결 됨");
		users.put(session.getId(), session);
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log(session.getId() + " 연결 종료됨");
		users.remove(session.getId());
		
		String memberout = memberName.get(session.getId());
		memberName.remove(session.getId());
		systemMessage(memberout+"님이 나가셨습니다.", memberName.values());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(message);
		log(session.getId() + "로부터 메시지 수신: " + message.getPayload());
		JSONObject data = new JSONObject(message.getPayload());
		System.out.println(data);
		String cmd = data.getString("cmd");
		if (cmd.equals("message")) {
			JSONObject json = new JSONObject()
					.put("message", data.getString("message"))
					.put("sender", data.getString("sender"));
			
			for (WebSocketSession s : users.values()) {
				
				String auth = session.getId().equals(s.getId()) ? "my" : "other";
				json.put("auth", auth);
				
				s.sendMessage(new TextMessage(json.toString()));
				log(s.getId() + "에 메시지 발송: " + message.getPayload());		
			}
			
		} else if (cmd.equals("join")) {
			memberName.put(session.getId(), data.getString("sender"));
			systemMessage(data.getString("sender")+"님이 들어오셨습니다.", memberName.values());	
			
		}

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log(session.getId() + " 익셉션 발생: " + exception.getMessage());
	}

	private void log(String logmsg) {
		System.out.println(new Date() + " : " + logmsg);
	}

	private void systemMessage(String message, Collection<String> collection) throws Exception {
		String jsonString = new JSONObject().put("message", message).put("auth", "memberInfo").put("member", collection).toString();
		for (WebSocketSession s : users.values()) {
			s.sendMessage(new TextMessage(jsonString));
		}
		
	}
}
