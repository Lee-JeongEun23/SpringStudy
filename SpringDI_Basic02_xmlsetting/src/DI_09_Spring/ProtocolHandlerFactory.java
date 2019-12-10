package DI_09_Spring;

import java.util.Map;

public class ProtocolHandlerFactory {
	
	public ProtocolHandlerFactory() {
		System.out.println("객체 생성");
	}

	private Map<String, ProtocolHandler> handlers; //map 타입을 가지고 있고 map의 value는 protocolhandler

	public void setHandlers(Map<String, ProtocolHandler> handlers) {
		this.handlers = handlers;
		System.out.println("setter 주입 성공 : " + this.handlers); //주소값 찍기
	}
	
	
	
}
