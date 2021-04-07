package com.ict.lawving.notice.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.lawving.common.Paging;
import com.ict.lawving.notice.model.dao.NoticeDao;
import com.ict.lawving.notice.model.vo.NoticeSearch;
import com.ict.lawving.notice.model.vo.NoticeVo;

@Service("noticeService")
public class NoticeServiceImpliment implements NoticeService {
	// 의존성 주입(DI : Dependency Injection)
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private Paging paging;

	@Override
	public ArrayList<NoticeVo> selectSearch(NoticeSearch searchObject) {
		return noticeDao.selectSearch(searchObject);
	}

	@Override
	public NoticeVo selectOneList(int notice_idx) {
		return noticeDao.selectOneList(notice_idx);
	}

	@Override
	public int getTotalCount() {
		return noticeDao.getCount();

	}

	@Override
	public List<NoticeVo> getList(int begin, int end) {
		return noticeDao.getList(paging.getBegin(), paging.getEnd());
	}

	@Override
	public int insertNotice(NoticeVo notice) {
		int result = noticeDao.insertnotice(notice);
		return result;
	}

}
