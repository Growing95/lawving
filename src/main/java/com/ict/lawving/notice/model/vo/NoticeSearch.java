package com.ict.lawving.notice.model.vo;

public class NoticeSearch {
	private String category;
	private String order;
	private String keyword;
	
	public NoticeSearch() {}

	public NoticeSearch(String category, String order, String keyword) {
		super();
		this.category = category;
		this.order = order;
		this.keyword = keyword;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "NoticeSearch [category=" + category + ", order=" + order + ", keyword=" + keyword + "]";
	}
	
}
