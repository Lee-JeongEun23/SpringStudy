package DI_06_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {

		/*
		insert �۾�
		MySqlArticleDao articledao = new MySqlArticleDao(); mysql ����ϴٰ� oracle�� ����Ѵ� �ϸ� 		
		OracleArticleDao articledao = new OracleArticleDao(); //�̰͸� �ٲ㵵 �ؿ��� �ϳ��� �ȹٲ�
		ArticleService articleservice = new ArticleService(articledao);
		
		Article article = new Article();
		articleservice.write(article); //�̷� �Լ��� ������ main����
		*/
				
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_06_Spring/DI_06.xml"); //������ �����̳� ����� -> xml �о �۾�
		Article article = context.getBean("article", Article.class);
		ArticleService articleservice = context.getBean("articleservice", ArticleService.class);
		
		articleservice.write(article);
	}

}
