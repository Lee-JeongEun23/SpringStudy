package DI_01;

public class HelloApp {

	public static void main(String[] args) {
		MessageBean messagebean = new MessageBean();
		messagebean.SayHello("hong");

	}

}
/*
 �䱸����
 ��������(hong) : Hello hong!
 �ѱ۹���(hong) : �ȳ� hong! 

MessageBean_kr > �Լ� > sayHello
MessageBean_en > �Լ� > sayHello

>>interface ������� ���� : MessageBean �������̽��� �����ؼ� kr, en�� �����ϵ���
 */
