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

		
		//주소값
		System.out.println("mybean : " + mybean);
		System.out.println("mybean2 : " + mybean2);
		System.out.println("single : " + single);
		System.out.println("single2 : " + single2);

		 mybean : DI_05_Spring.MyBean@15db9742
		 mybean2 : DI_05_Spring.MyBean@6d06d69c
		 single : DI_05_Spring.Singleton@7852e922
		 single2 : DI_05_Spring.Singleton@7852e922 
		 */

		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_05_Spring/DI_05.xml"); //스프링 컨테이너 만들기 -> xml 읽어서 작업
		//스프링 컨테이너가 구성되고 xml 파일을 read 해서 파싱 - 객체 생성 - 생성한 객체 조립 >> 컨테이너 객체 생성 - 필요한 객체 사용
		
		MyBean mybean = context.getBean("mybean", MyBean.class);
		MyBean mybean2 = context.getBean("mybean", MyBean.class);
		MyBean mybean3 = context.getBean("mybean", MyBean.class);
		
		System.out.println(mybean +" / "+ mybean2 +" / " + mybean3); //스프링 컨테이너는 모든 객체를 싱글톤으로 만듦, 따라서 getBean할 때는 굳이 싱글톤이 의미 없음


		MyBean mybean4 = context.getBean("mybean2", MyBean.class); //다른 객체를 만든거와 같음 따라서 주소값이 다름
		//getBean 함수 : ★객체를 만드는게 아니라 만든 것을 들고오는 함수 -> 그러나 프로토타입을 걸면 new 해서 새로운 객체를 만들수는 있음 그러나 거의 안씀
		
		Singleton single = context.getBean("single",Singleton.class);
		Singleton single2 = context.getBean("single",Singleton.class);
		System.out.println(single +" / "+ single2);
		
		
		/*
		 getBean() return Object Type 객체 >> 필요에 따라 downcasting해서 원하는걸로 사용
		  ★★★ 주의 : getBean() 호출 시에 새로운 객체를 만들지 않아요
		  ★★★ 스프링 컨테이너가 가지는 객체의 타입 : default로 싱글톤 사용 따라서 굳이 싱글톤 가져갈 필요 없음
		  그러나, 예외적으로 getBEan() 함수가 객체 생성하도록 만들 수 있음 
		 */
		
	}

}
