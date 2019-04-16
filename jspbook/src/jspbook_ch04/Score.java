package jspbook_ch04;

public class Score {
	private int kor;
	private int eng;
	private int math;
	
	public Score(int kor, int eng, int math) {
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	public int getKor() {
		return kor;
	}

	public int getEng() {
		return eng;
	}

	public int getMath() {
		return math;
	}

	public int scoreSum() {
		return kor+eng+math;
	}
	
	public double scoreAvg() {
		return (kor+eng+math)/3.0;
	}
	
	

}
