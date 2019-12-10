package DI_03_Spring;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
public class HelloApp {

	public static void main(String[] args) {
		
		//����
		//MessageBean_en messagebean_en = new MessageBean_en();
		//messagebean_en.sayHello("hong");
		
		//�ѱ�
		//MessageBean_kr messagebean_kr = new MessageBean_kr();
		//messagebean_kr.sayHello("hong");	
		
		//interface �ϳ��� ���� ������ �������� �ּҸ� ���� �� �ִ�.(������)
		//MessageBean messagebean = new MessageBean_kr(); //�̷��� �ϸ� �ѱ۷�
		//MessageBean messagebean = new MessageBean_en(); --> �̷��� �ϸ� ����� ��, �ڿ��� �ٲٸ� ���ϴ� �������� ���� ����
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_03_Spring/DI_03.xml");
		MessageBean messagebean = context.getBean("message", MessageBean.class);
		messagebean.sayHello("hong");

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
