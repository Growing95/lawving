package com.ict.lawving.notice.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ict.lawving.notice.model.vo.NoticeSearch;
import com.ict.lawving.notice.model.vo.NoticeVo;

public interface NoticeService {
	ArrayList<NoticeVo> selectSearch(NoticeSearch searchObject);

	NoticeVo selectOneList(int notice_idx);

	int getTotalCount();

	List<NoticeVo> getList(int begin, int end);
	
	int insertNotice(NoticeVo notice);
}
