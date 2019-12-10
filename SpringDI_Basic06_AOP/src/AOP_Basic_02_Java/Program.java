package AOP_Basic_02_Java;

import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {
		Calc calc = new NewCalc();
		//calc.ADD(100, 100); //이것은 보조업무 안탐, 직접 접근하는 방법
		
		//Proxy 객체 통해서 처리 		//실객체의 meta 정보, 실객체의 행위 정보(인터페이스), 보조객체 >> parameter 실객체 주소
		Calc cal =(Calc)Proxy.newProxyInstance(calc.getClass().getClassLoader(), calc.getClass().getInterfaces(), new LogPrintHandler(calc)); 
		
		//Proxy를 통해서 처리하지만 사용자는 실제 Calc 객체를 사용하는 것처럼 하면 됨
		int result = cal.ADD(5, 10); //이 ADD는 진짜 ADD가 아님, 보조업무가 돌아가는 것
		System.out.println("Main Result : " + result);
		
		result = cal.MUL(7, 3);
		System.out.println("Main Result : " + result);
	}

}
