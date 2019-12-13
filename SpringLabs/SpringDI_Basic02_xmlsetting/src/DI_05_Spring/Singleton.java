package DI_05_Spring;

public class Singleton {
	//�̱����� ��ü�� ����� ���� �ƴ϶� �ϳ��� ����ʵ带 �����ؼ� �� �ּҸ� ��� ����ϵ���(�����ϵ���)
	private Singleton() {}
	private static Singleton intance = new Singleton();
	
	public static Singleton getInstance() {
		return intance;
	}
}
