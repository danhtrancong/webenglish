package BEAN;

public class Result 
{
	private int resultid;
	private int correctanswernum;
	private int incorrectanswernum;
	private String time;
	private int examinationid;
	private int memberid;
	private int correctanswerread;
	private int correctanswerlisten;
	
	
	public int getResultid() {
		return resultid;
	}
	public void setResultid(int resultid) {
		this.resultid = resultid;
	}
	public int getCorrectanswernum() {
		return correctanswernum;
	}
	public void setCorrectanswernum(int correctanswernum) {
		this.correctanswernum = correctanswernum;
	}
	public int getIncorrectanswernum() {
		return incorrectanswernum;
	}
	public void setIncorrectanswernum(int incorrectanswernum) {
		this.incorrectanswernum = incorrectanswernum;
	}
	
	public int getExaminationid() {
		return examinationid;
	}
	public void setExaminationid(int examinationid) {
		this.examinationid = examinationid;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getCorrectanswerread() {
		return correctanswerread;
	}
	public void setCorrectanswerread(int correctanswerread) {
		this.correctanswerread = correctanswerread;
	}
	public int getCorrectanswerlisten() {
		return correctanswerlisten;
	}
	public void setCorrectanswerlisten(int correctanswerlisten) {
		this.correctanswerlisten = correctanswerlisten;
	}
}
