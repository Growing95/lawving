package com.ict.lawving.library.model.vo;

import java.sql.Date;

public class LibraryVo {
	private int library_idx;
	private int members_idx;
	private String library_category;
	private String library_title;
	private String library_writer;
	private String library_content;
	private String library_file_name;
	private String library_refile_name;
	private Date library_reg;
	public LibraryVo() {}
	public LibraryVo(int library_idx, int members_idx, String library_category, String library_title,
			String library_writer, String library_content, String library_file_name, String library_refile_name,
			Date library_reg) {
		super();
		this.library_idx = library_idx;
		this.members_idx = members_idx;
		this.library_category = library_category;
		this.library_title = library_title;
		this.library_writer = library_writer;
		this.library_content = library_content;
		this.library_file_name = library_file_name;
		this.library_refile_name = library_refile_name;
		this.library_reg = library_reg;
	}
	public int getLibrary_idx() {
		return library_idx;
	}
	public void setLibrary_idx(int library_idx) {
		this.library_idx = library_idx;
	}
	public int getMembers_idx() {
		return members_idx;
	}
	public void setMembers_idx(int members_idx) {
		this.members_idx = members_idx;
	}
	public String getLibrary_category() {
		return library_category;
	}
	public void setLibrary_category(String library_category) {
		this.library_category = library_category;
	}
	public String getLibrary_title() {
		return library_title;
	}
	public void setLibrary_title(String library_title) {
		this.library_title = library_title;
	}
	public String getLibrary_writer() {
		return library_writer;
	}
	public void setLibrary_writer(String library_writer) {
		this.library_writer = library_writer;
	}
	public String getLibrary_content() {
		return library_content;
	}
	public void setLibrary_content(String library_content) {
		this.library_content = library_content;
	}
	public String getLibrary_file_name() {
		return library_file_name;
	}
	public void setLibrary_file_name(String library_file_name) {
		this.library_file_name = library_file_name;
	}
	public String getLibrary_refile_name() {
		return library_refile_name;
	}
	public void setLibrary_refile_name(String library_refile_name) {
		this.library_refile_name = library_refile_name;
	}
	public Date getLibrary_reg() {
		return library_reg;
	}
	public void setLibrary_reg(Date library_reg) {
		this.library_reg = library_reg;
	}
	
	
}
