package com.ict.lawving.notice.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ict.lawving.notice.model.vo.NoticeSearch;
import com.ict.lawving.notice.model.vo.NoticeVo;

public interface NoticeService {
	ArrayList<NoticeVo> selectSearchTitleDesc(NoticeSearch searchObject, int begin, int end);
	ArrayList<NoticeVo> selectSearchTitleAsc(NoticeSearch searchObject, int begin, int end);
	ArrayList<NoticeVo> selectSearchContentDesc(NoticeSearch searchObject, int begin, int end);
	ArrayList<NoticeVo> selectSearchContentAsc(NoticeSearch searchObject, int begin, int end);


	List<NoticeVo> getList(int begin, int end);
	
	
	int getTotalCount();
	int getTotalCount(NoticeSearch searchObject);
	
	NoticeVo selectOneList(int notice_idx);
	int insertNotice(NoticeVo notice);
	int updateNotice(NoticeVo notice);
	int deleteNotice(int notice_idx);
	NoticeVo selectNoticeBefore(int notice_idx);
	NoticeVo selectNoticeAfter(int notice_idx);
	void chkdeleteNotice(String chkdel);
	
	
}
