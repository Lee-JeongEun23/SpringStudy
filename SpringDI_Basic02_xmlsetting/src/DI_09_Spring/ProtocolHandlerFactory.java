package DI_09_Spring;

import java.util.Map;

public class ProtocolHandlerFactory {
	
	public ProtocolHandlerFactory() {
		System.out.println("��ü ����");
	}

	private Map<String, ProtocolHandler> handlers; //map Ÿ���� ������ �ְ� map�� value�� protocolhandler

	public void setHandlers(Map<String, ProtocolHandler> handlers) {
		this.handlers = handlers;
		System.out.println("setter ���� ���� : " + this.handlers); //�ּҰ� ���
	}
	
	
	
}
