package jspbook_ch06;

public class CartDTO {
	private String name;
	private int count;
	
	public CartDTO(String name, int count) {
		this.name = name;
		this.count = count;
	}
	public CartDTO() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CartDTO [name=" + name + ", count=" + count + "]";
	}
	

}
