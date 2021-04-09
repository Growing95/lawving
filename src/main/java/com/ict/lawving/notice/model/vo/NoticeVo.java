package com.ict.lawving.notice.model.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class NoticeVo {

	private int notice_idx;
	private int members_idx;
	private String notice_title;
	private String notice_writer;
	private String notice_content;
	private String notice_file_name;
	private String notice_refile_name;
	private Date notice_reg;

	public NoticeVo() {
	}

	public NoticeVo(int notice_idx, int members_idx, String notice_title, String notice_writer, String notice_content,
			String notice_file_name, String notice_refile_name, Date notice_reg) {
		super();
		this.notice_idx = notice_idx;
		this.members_idx = members_idx;
		this.notice_title = notice_title;
		this.notice_writer = notice_writer;
		this.notice_content = notice_content;
		this.notice_file_name = notice_file_name;
		this.notice_refile_name = notice_refile_name;
		this.notice_reg = notice_reg;
	}

	public int getNotice_idx() {
		return notice_idx;
	}

	public void setNotice_idx(int notice_idx) {
		this.notice_idx = notice_idx;
	}

	public int getMembers_idx() {
		return members_idx;
	}

	public void setMembers_idx(int members_idx) {
		this.members_idx = members_idx;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_writer() {
		return notice_writer;
	}

	public void setNotice_writer(String notice_writer) {
		this.notice_writer = notice_writer;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public String getNotice_file_name() {
		return notice_file_name;
	}

	public void setNotice_file_name(String notice_file_name) {
		this.notice_file_name = notice_file_name;
	}

	public String getNotice_refile_name() {
		return notice_refile_name;
	}

	public void setNotice_refile_name(String notice_refile_name) {
		this.notice_refile_name = notice_refile_name;
	}

	public Date getNotice_reg() {
		return notice_reg;
	}

	public void setNotice_reg(Date notice_reg) {
		this.notice_reg = notice_reg;
	}

	@Override
	public String toString() {
		return "NoticeVo [notice_idx=" + notice_idx + ", members_idx=" + members_idx + ", notice_title=" + notice_title
				+ ", notice_writer=" + notice_writer + ", notice_content=" + notice_content + ", notice_file_name="
				+ notice_file_name + ", notice_refile_name=" + notice_refile_name + ", notice_reg=" + notice_reg + "]";
	};

}
