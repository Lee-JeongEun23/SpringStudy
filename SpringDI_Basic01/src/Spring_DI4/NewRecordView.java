package Spring_DI4;

import java.util.Scanner;

public class NewRecordView implements RecordView{
	//���� ����ϴ� Ŭ����
	private NewRecord record;
	
	//1. [������]�� ���ؼ� �ʿ��� ��ü ���� or ���� >> DI1
	//2. �Լ�([setter])�� ���ؼ� �ʿ��� ��ü ���� >> DI2	
	//3. �������̽� Ȱ�� (������)
	public NewRecordView() {}	
	
	public void setRecord(Record record) { //�θ� �ڵ�μ� record�� ��� �ڽ� ��ü �� �� ����(������ ���� / �Լ��� parameter interface)
		//�Լ��� parameter�� ���ؼ� NewRecord ��ü�� �ּ�
		this.record = (NewRecord)record;
	}
	
	
	public NewRecordView(int kor, int eng, int math) {
		record = new NewRecord(kor, eng, math);
	}
	

	@Override
	public void print() {
		System.out.println(record.total() + " / " + record.avg());
	}

	@Override
	public void input() {
	Scanner scan = new Scanner(System.in);
	System.out.println("kor: ");
	record.setKor(scan.nextInt());
	
	System.out.println("eng: ");
	record.setEng(scan.nextInt());
	
	System.out.println("math: ");
	record.setMath(scan.nextInt());
		
	}
}
