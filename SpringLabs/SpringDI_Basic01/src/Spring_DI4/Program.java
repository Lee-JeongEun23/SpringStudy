package Spring_DI4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

	public static void main(String[] args) {
		/*
		NewRecordView view = new NewRecordView();
		NewRecord record = new NewRecord();
		
		view.setRecord(record); //�ʿ��� ��ü�� �ּ� ���� --> �������� setter�� �����ϴ� ���� ������
		*/
		//view.input();
		//view.print();
		
		//*** SpringFrameWork�� �����ϴ� �޸� ���� (IOC �����̳�) ***
		//*** �����̳� ����� �� ������ �ʿ��� ��ü �÷� ���� �۾�
		ApplicationContext context = new ClassPathXmlApplicationContext("DIConfig.xml");
		//context�� �����̳��� �ּҰ�
		RecordView view = (RecordView)context.getBean("view");
		
		view.input();
		view.print();
	}

}
