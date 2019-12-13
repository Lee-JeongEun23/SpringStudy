package DI_05_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		/*
		MyBean mybean = new MyBean();
		MyBean mybean2 = new MyBean("lee");
		
		Singleton single = Singleton.getInstance();
		Singleton single2 = Singleton.getInstance();

		
		//�ּҰ�
		System.out.println("mybean : " + mybean);
		System.out.println("mybean2 : " + mybean2);
		System.out.println("single : " + single);
		System.out.println("single2 : " + single2);

		 mybean : DI_05_Spring.MyBean@15db9742
		 mybean2 : DI_05_Spring.MyBean@6d06d69c
		 single : DI_05_Spring.Singleton@7852e922
		 single2 : DI_05_Spring.Singleton@7852e922 
		 */

		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_05_Spring/DI_05.xml"); //������ �����̳� ����� -> xml �о �۾�
		//������ �����̳ʰ� �����ǰ� xml ������ read �ؼ� �Ľ� - ��ü ���� - ������ ��ü ���� >> �����̳� ��ü ���� - �ʿ��� ��ü ���
		
		MyBean mybean = context.getBean("mybean", MyBean.class);
		MyBean mybean2 = context.getBean("mybean", MyBean.class);
		MyBean mybean3 = context.getBean("mybean", MyBean.class);
		
		System.out.println(mybean +" / "+ mybean2 +" / " + mybean3); //������ �����̳ʴ� ��� ��ü�� �̱������� ����, ���� getBean�� ���� ���� �̱����� �ǹ� ����


		MyBean mybean4 = context.getBean("mybean2", MyBean.class); //�ٸ� ��ü�� ����ſ� ���� ���� �ּҰ��� �ٸ�
		//getBean �Լ� : �ڰ�ü�� ����°� �ƴ϶� ���� ���� ������ �Լ� -> �׷��� ������Ÿ���� �ɸ� new �ؼ� ���ο� ��ü�� ������� ���� �׷��� ���� �Ⱦ�
		
		Singleton single = context.getBean("single",Singleton.class);
		Singleton single2 = context.getBean("single",Singleton.class);
		System.out.println(single +" / "+ single2);
		
		
		/*
		 getBean() return Object Type ��ü >> �ʿ信 ���� downcasting�ؼ� ���ϴ°ɷ� ���
		  �ڡڡ� ���� : getBean() ȣ�� �ÿ� ���ο� ��ü�� ������ �ʾƿ�
		  �ڡڡ� ������ �����̳ʰ� ������ ��ü�� Ÿ�� : default�� �̱��� ��� ���� ���� �̱��� ������ �ʿ� ����
		  �׷���, ���������� getBEan() �Լ��� ��ü �����ϵ��� ���� �� ���� 
		 */
		
	}

}
