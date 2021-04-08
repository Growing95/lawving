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

	NoticeVo selectOneList(int notice_idx);

	List<NoticeVo> getList(int begin, int end);
	
	int insertNotice(NoticeVo notice);

	int getTotalCount();
	int getTotalCount(NoticeSearch searchObject);
}
