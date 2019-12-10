package DI_06_Spring;

public class ArticleService { 

	//사용자 요청에 따라서 글쓰기, 목록보기, 수정하기
	//위와 같은 서비스를 하기 위해서는 ★DB 작업을 해야 함 -> DAO단
	//ArticleSerive는 DAO를 필요로 함 -> 주입하는 방법 2가지 : 생성자 / setter
	
	private ArticleDao articledao; //변수가 실 객체 주소를 가지면 돼요
	//생성자로 주입하는 방법 
	public ArticleService(ArticleDao articledao) { //집합 연관
		this.articledao = articledao; //주소값 할당
		System.out.println("ArticleService 생성자 함수 호출");
	}
	
	
	//글쓰기 서비스
	public void write(Article article) {
		this.articledao.insert(article); 
	}
}
