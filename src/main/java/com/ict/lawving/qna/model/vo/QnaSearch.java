package com.ict.lawving.qna.model.vo;

public class QnaSearch {
	private String status;
	private String order;
	private String keyword;
	
	public QnaSearch() {}

	public QnaSearch(String status, String order, String keyword) {
		super();
		this.status = status;
		this.order = order;
		this.keyword = keyword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "NoticeSearch [status=" + status + ", order=" + order + ", keyword=" + keyword + "]";
	}
}
