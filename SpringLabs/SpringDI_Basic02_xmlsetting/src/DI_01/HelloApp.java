package DI_01;

public class HelloApp {

	public static void main(String[] args) {
		MessageBean messagebean = new MessageBean();
		messagebean.SayHello("hong");

	}

}
/*
 요구사항
 영문버전(hong) : Hello hong!
 한글버전(hong) : 안녕 hong! 

MessageBean_kr > 함수 > sayHello
MessageBean_en > 함수 > sayHello

>>interface 기반으로 설계 : MessageBean 인터페이스를 설계해서 kr, en도 구현하도록
 */
