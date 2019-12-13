package AOP_Basic_02_Java;

public class NewCalc implements Calc {

	@Override
	public int ADD(int x, int y) {
		//보조업무(공통 업무) cross-cutting-concern
		int sum = x + y; //주업무(core-concern)
		//보조업무(공통 업무) cross-cutting-concern
		return sum;
	}

	@Override
	public int MUL(int x, int y) {
		//보조업무(공통 업무) cross-cutting-concern
		int mul = x * y; //주업무(core-concern)
		//보조업무(공통 업무) cross-cutting-concern
		return mul;
	}

	@Override
	public int SUB(int x, int y) {
		//보조업무(공통 업무) cross-cutting-concern
		int sub = x - y; //주업무(core-concern)
		//보조업무(공통 업무) cross-cutting-concern
		return sub;
	}

}
