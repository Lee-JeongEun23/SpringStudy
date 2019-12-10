package AOP_Basic_01;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

/*
 간단한 계산기 프로그램 만들기
 - 주관심 = 업무(core concern) : 사칙연산(ADD, MUL...) -> 기능 (함수)
 - 보조관심 = 공통관심 (cross cutting concern) : 연산에 걸린 시간
 - 요구사항 : log 출력(console 출력), 마치 시스템이 출력하는 것처럼 빨간색으로 시간을 출력하세요
 
 
 */

public class Calc {
	public int Add(int x, int y) {
		Log log = LogFactory.getLog(this.getClass());
		//System.currentTimeMillis(); 이건 자바에서 제공
		StopWatch sw = new StopWatch(); //Spring에서 제공
		sw.start();
		log.info("[타이머 시작]");
		int result = x + y; //주관심

		sw.stop();
		log.info("[타이머 종료]");
		log.info("[TIME LOG Method : ADD]");
		log.info("[TIME LOG Method Time : " + sw.getTotalTimeMillis() + "]");
		return result;
	}
	
	public int Mul(int x, int y) {
		int result = x * y;
		return result;
	}
}
