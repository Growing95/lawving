package com.ict.lawving.bookmark.model.vo;

public class BookmarkVo {
private String bookmark_idx,members_idx,bookmark_category,bookmark_question,bookmark_answer,bookmark_reg;

public BookmarkVo() {}

public BookmarkVo(String bookmark_idx, String members_idx, String bookmark_category, String bookmark_question,
		String bookmark_answer, String bookmark_reg) {
	super();
	this.bookmark_idx = bookmark_idx;
	this.members_idx = members_idx;
	this.bookmark_category = bookmark_category;
	this.bookmark_question = bookmark_question;
	this.bookmark_answer = bookmark_answer;
	this.bookmark_reg = bookmark_reg;
}

public String getBookmark_idx() {
	return bookmark_idx;
}

public void setBookmark_idx(String bookmark_idx) {
	this.bookmark_idx = bookmark_idx;
}

public String getMembers_idx() {
	return members_idx;
}

public void setMembers_idx(String members_idx) {
	this.members_idx = members_idx;
}

public String getBookmark_category() {
	return bookmark_category;
}

public void setBookmark_category(String bookmark_category) {
	this.bookmark_category = bookmark_category;
}

public String getBookmark_question() {
	return bookmark_question;
}

public void setBookmark_question(String bookmark_question) {
	this.bookmark_question = bookmark_question;
}

public String getBookmark_answer() {
	return bookmark_answer;
}

public void setBookmark_answer(String bookmark_answer) {
	this.bookmark_answer = bookmark_answer;
}

public String getBookmark_reg() {
	return bookmark_reg;
}

public void setBookmark_reg(String bookmark_reg) {
	this.bookmark_reg = bookmark_reg;
}

}
