package com.ict.lawving.notice.model.vo;

public class NoticeVo {

	private String notice_idx, notice_title, notice_writer, notice_content, notice_file_name, notice_refile_name,
			notice_reg;

	public NoticeVo() {
	}

	public NoticeVo(String notice_idx, String notice_title, String notice_writer, String notice_content,
			String notice_file_name, String notice_refile_name, String notice_reg) {
		super();
		this.notice_idx = notice_idx;
		this.notice_title = notice_title;
		this.notice_writer = notice_writer;
		this.notice_content = notice_content;
		this.notice_file_name = notice_file_name;
		this.notice_refile_name = notice_refile_name;
		this.notice_reg = notice_reg;
	}

	public String getNotice_idx() {
		return notice_idx;
	}

	public void setNotice_idx(String notice_idx) {
		this.notice_idx = notice_idx;
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

	public String getNotice_reg() {
		return notice_reg;
	}

	public void setNotice_reg(String notice_reg) {
		this.notice_reg = notice_reg;
	}

	@Override
	public String toString() {
		return "NoticeVo [notice_idx=" + notice_idx + ", notice_title=" + notice_title + ", notice_writer="
				+ notice_writer + ", notice_content=" + notice_content + ", notice_file_name=" + notice_file_name
				+ ", notice_refile_name=" + notice_refile_name + ", notice_reg=" + notice_reg + "]";
	}

}
