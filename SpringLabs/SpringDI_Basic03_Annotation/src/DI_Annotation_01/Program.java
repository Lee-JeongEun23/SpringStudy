package DI_Annotation_01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class Program {
	public static void main(String[] args) {
		/*
		1. 자바 코드로 구현
		MonitorViewer viewer = new MonitorViewer();
		Recorder recorder = new Recorder();
		
		viewer.setRecorder(recorder);
		System.out.println(viewer.getRecorder());
		*/
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_Annotation_01/DI_Annotation_01.xml");
		MonitorViewer viewer = context.getBean("monitorViewer", MonitorViewer.class);
		System.out.println(viewer.getRecorder());
		
		
		//2. 
	}
}
