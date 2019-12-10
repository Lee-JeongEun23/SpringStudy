package DI2;

public class NewRecordView {
	//���� ����ϴ� Ŭ����
	private NewRecord record;
	
	//1. [������]�� ���ؼ� �ʿ��� ��ü ���� or ���� >> DI1
	//2. �Լ�([setter])�� ���ؼ� �ʿ��� ��ü ���� >> DI2	
	public NewRecordView() {}	
	
	public void setRecord(NewRecord record) {
		//�Լ��� parameter�� ���ؼ� NewRecord ��ü�� �ּ�
		this.record = record;
	}
	
	
	public NewRecordView(int kor, int eng, int math) {
		record = new NewRecord(kor, eng, math);
	}
	


	public void print() {
		System.out.println(record.total() + " / " + record.avg());
	}
}
