package shop.bean;

import java.io.Serializable;

public class ItemBean implements Serializable{
	private int id;
	private String name;
	private int price;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public ItemBean() {
	}
	public ItemBean(int id ,String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

}
