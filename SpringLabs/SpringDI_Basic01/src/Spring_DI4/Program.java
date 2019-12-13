package Spring_DI4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

	public static void main(String[] args) {
		/*
		NewRecordView view = new NewRecordView();
		NewRecord record = new NewRecord();
		
		view.setRecord(record); //필요한 객체의 주소 주입 --> 스프링은 setter로 주입하는 것을 좋아함
		*/
		//view.input();
		//view.print();
		
		//*** SpringFrameWork이 제공하는 메모리 생성 (IOC 컨테이너) ***
		//*** 컨테이너 만들고 그 공간에 필요한 객체 올려 놓기 작업
		ApplicationContext context = new ClassPathXmlApplicationContext("DIConfig.xml");
		//context는 컨테이너의 주소값
		RecordView view = (RecordView)context.getBean("view");
		
		view.input();
		view.print();
	}

}
