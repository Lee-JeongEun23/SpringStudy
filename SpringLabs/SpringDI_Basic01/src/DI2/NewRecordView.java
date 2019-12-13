package DI2;

public class NewRecordView {
	//점수 출력하는 클래스
	private NewRecord record;
	
	//1. [생성자]를 통해서 필요한 객체 생성 or 주입 >> DI1
	//2. 함수([setter])를 통해서 필요한 객체 주입 >> DI2	
	public NewRecordView() {}	
	
	public void setRecord(NewRecord record) {
		//함수의 parameter를 통해서 NewRecord 객체의 주소
		this.record = record;
	}
	
	
	public NewRecordView(int kor, int eng, int math) {
		record = new NewRecord(kor, eng, math);
	}
	


	public void print() {
		System.out.println(record.total() + " / " + record.avg());
	}
}
