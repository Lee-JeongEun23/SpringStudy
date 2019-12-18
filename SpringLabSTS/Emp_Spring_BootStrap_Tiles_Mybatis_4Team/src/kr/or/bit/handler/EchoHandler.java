package kr.or.bit.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//웹소켓 서버가 웹소켓 클라이언트가 보낸 메세지를 그대로 다시 웹 클라이언트에 전송
public class EchoHandler extends TextWebSocketHandler{

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		//웹소켓 클라이언트가 엔드포인트로 연결하면 웹소켓 모듈은 엔드포인트와 연결된 해당 method 호출
		System.out.printf("%s 연결 O  \n", session.getId());
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		//웹소켓 클라이언트가 데이터를 전송하면 해당 method 호출해서 클라이언트가 전송한 데이터 전달
		System.out.printf("%s :  [%s] \n", session.getId(), message.getPayload());
		//getPayload() - 메세지에 담긴 텍스트값 얻을 수 있음
		session.sendMessage(new TextMessage("echo : " + message.getPayload()));
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		//웹소켓 클라이언트가 연결을 종료하면 해당 method 호출
		System.out.printf("%s 연결 X \n", session.getId());
	}

}
