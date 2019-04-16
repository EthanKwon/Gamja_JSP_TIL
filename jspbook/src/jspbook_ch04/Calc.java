package jspbook_ch04;

public class Calc {
	public int calc(int num1, int num2, String op) {
		int result = 0;
		
		if(op.equals("+")) {
			result = num1 + num2;
		} else if(op.equals("-")) {
			result = num1 - num2;
		} else if(op.equals("*")) {
			result = num1 * num2;
		} else if(op.equals("/")) {
			result = num1 /num2;
		}
		return result;
	}

}
