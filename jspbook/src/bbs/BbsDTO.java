package bbs;

import java.text.SimpleDateFormat;

public class BbsDTO {
	
	private int id;
	private int memberId;
	private String title;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm");
	private String date;
	private String content;
	private String name;
	private int page;
	
	public BbsDTO(int id, String title, String content) {
		this.id=id;
		this.title = title;
		this.content = content;
	}
	
	public BbsDTO(String title, String content, int memberId) {
		this.memberId=memberId;
		this.title = title;
		this.content = content;
	}
	public BbsDTO() {}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "[ ID :" + id + " | MemberID :" + memberId
				+ " | Title :" + title + " | Date :" + date
				+ " | Content :" + content + "]";
	}
	
	public String toStringName() {
		return "[ ID :" + id + " | Title :" + title 
				+ " | Writer :" + name + " | Date :" + date + "]";
	}
	
	public String toStringContent() {
		return "[ title :" + title + " | Writer :" + name 
				+ " | Date :" + date + " | Content :" + content + "]";
	}
	
	public String toStringUpdate() {
		return "[ title :" + title
				+ " | Content :" + content + "]";
	}
	
	
	
	
	

}
