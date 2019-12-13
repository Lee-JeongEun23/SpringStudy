package DI_06_Spring;

//표준화(Orcale, Mysql 둘 중에 어떤 것을 사용해도 동일한 함수를 사용하도록)
public interface ArticleDao {
	void insert(Article article);
}
