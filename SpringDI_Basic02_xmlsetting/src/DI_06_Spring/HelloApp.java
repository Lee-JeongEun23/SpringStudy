package DI_06_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {

		/*
		insert 작업
		MySqlArticleDao articledao = new MySqlArticleDao(); mysql 사용하다가 oracle을 사용한다 하면 		
		OracleArticleDao articledao = new OracleArticleDao(); //이것만 바꿔도 밑에는 하나도 안바뀜
		ArticleService articleservice = new ArticleService(articledao);
		
		Article article = new Article();
		articleservice.write(article); //이런 함수의 실행은 main에서
		*/
				
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_06_Spring/DI_06.xml"); //스프링 컨테이너 만들기 -> xml 읽어서 작업
		Article article = context.getBean("article", Article.class);
		ArticleService articleservice = context.getBean("articleservice", ArticleService.class);
		
		articleservice.write(article);
	}

}
