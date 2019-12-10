package DI_Annotation_04;
import javax.annotation.Resource;

public class MonitorViewer {
	private Recorder recorder; //멤버필드 만들기 왜냐하면 recorder 의존 관계이기 떄문에

	public Recorder getRecorder() {
		return recorder;
	}

	@Resource(name="yy") //같은 타입이 여러개 있다 하더라도 name 하나 선택
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
		System.out.println("setter 주입 성공 : " + recorder);
	}

	
}
