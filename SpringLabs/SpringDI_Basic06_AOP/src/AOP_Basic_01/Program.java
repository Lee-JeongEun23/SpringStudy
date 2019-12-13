package AOP_Basic_01;

public class Program {

	public static void main(String[] args) {
		Calc calc = new Calc();
		int result= calc.Add(100, 100);
		System.out.println(result);
		
		result = calc.Mul(100, 100);
		System.out.println(result);
	}

}
