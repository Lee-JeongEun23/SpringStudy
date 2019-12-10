package DI_Annotation_03;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;

public class MonitorViewer {
	private Recorder recorder; //멤버필드 만들기 왜냐하면 recorder 의존 관계이기 떄문에

	public Recorder getRecorder() {
		return recorder;
	}

	//@Autowired(required = true) //기본 설정(무조건 injection)
	@Autowired(required = false) //있으면 넣고 아니면 말고 --> 그런데 이러는 경우가 거의 없음
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
		System.out.println("setter 주입 성공 : " + recorder);
	}

	
}
