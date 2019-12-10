package DI_06_Spring;

public class ArticleService { 

	//����� ��û�� ���� �۾���, ��Ϻ���, �����ϱ�
	//���� ���� ���񽺸� �ϱ� ���ؼ��� ��DB �۾��� �ؾ� �� -> DAO��
	//ArticleSerive�� DAO�� �ʿ�� �� -> �����ϴ� ��� 2���� : ������ / setter
	
	private ArticleDao articledao; //������ �� ��ü �ּҸ� ������ �ſ�
	//�����ڷ� �����ϴ� ��� 
	public ArticleService(ArticleDao articledao) { //���� ����
		this.articledao = articledao; //�ּҰ� �Ҵ�
		System.out.println("ArticleService ������ �Լ� ȣ��");
	}
	
	
	//�۾��� ����
	public void write(Article article) {
		this.articledao.insert(article); 
	}
}
