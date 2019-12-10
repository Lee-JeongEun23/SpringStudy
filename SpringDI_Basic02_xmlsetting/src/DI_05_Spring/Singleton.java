package DI_05_Spring;

public class Singleton {
	//싱글톤은 객체를 만드는 것이 아니라 하나의 멤버필드를 생성해서 그 주소를 계속 사용하도록(리턴하도록)
	private Singleton() {}
	private static Singleton intance = new Singleton();
	
	public static Singleton getInstance() {
		return intance;
	}
}
