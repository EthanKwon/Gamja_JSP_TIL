package jspbook_ch04;

public class Member {
	private int id;
	private String name;
	private String email;
	private String phone;
	
	
	public Member(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void seteMail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public int getId() {
		return id; 
	}
	
	public void getNewId() {
		if(phone != null) {
			id = 10000;
			System.out.println(toString());	
		}
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
	
	

}
