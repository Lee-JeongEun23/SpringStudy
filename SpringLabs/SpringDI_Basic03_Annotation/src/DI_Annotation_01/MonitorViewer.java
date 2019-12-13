package DI_Annotation_01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;

public class MonitorViewer {
	private Recorder recorder; //멤버필드 만들기 왜냐하면 recorder 의존 관계이기 떄문에

	public Recorder getRecorder() {
		return recorder;
	}

	@Autowired //By Type(IOC 컨테이너 안에 Recorder 타입을 갖는 객체가 있으면 자동 주소 주입) //이것을 걸면 setter함수에 recorder을 주입하려 함 
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
	
}
