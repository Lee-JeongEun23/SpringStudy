package Spring_DI4;

import java.util.Scanner;

public class NewRecordView implements RecordView{
	//점수 출력하는 클래스
	private NewRecord record;
	
	//1. [생성자]를 통해서 필요한 객체 생성 or 주입 >> DI1
	//2. 함수([setter])를 통해서 필요한 객체 주입 >> DI2	
	//3. 인터페이스 활용 (다형성)
	public NewRecordView() {}	
	
	public void setRecord(Record record) { //부모 코드로서 record의 모든 자식 객체 올 수 있음(다형성 적용 / 함수의 parameter interface)
		//함수의 parameter를 통해서 NewRecord 객체의 주소
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
