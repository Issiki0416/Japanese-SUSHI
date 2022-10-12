package shop.bean;

import java.util.ArrayList;
import java.util.List;

public class CartBean {
	private List<ItemBean> items = new ArrayList<ItemBean>();
	private int total;
	
	public CartBean() {
	}
	
	public List<ItemBean> getItems(){
		return items;
	}
	
	public void addCart(ItemBean bean) {
		ItemBean item = null;
		items.add(bean);
	}
	
	public void deleteCart(int itemId) {
		for(ItemBean item : items) {
			if(item.getId() == itemId) {
				items.remove(item);
				break;
			}
		}
	}
	
	
}
