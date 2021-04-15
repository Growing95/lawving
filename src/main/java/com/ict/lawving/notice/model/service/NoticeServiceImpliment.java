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

	// 전체 목록공지사항 개수
	@Override
	public int getTotalCount() {
		return noticeDao.getCount();

	}

	// 검색조건을 만족하는 공지사항 개수
	@Override
	public int getTotalCount(NoticeSearch searchObject) {
		return noticeDao.getCount(searchObject);
	}

	@Override
	public List<NoticeVo> getList(int begin, int end) {
		return noticeDao.getList(paging.getBegin(), paging.getEnd());
	}

	@Override
	public ArrayList<NoticeVo> selectSearchTitleDesc(NoticeSearch searchObject, int begin, int end) {
		return noticeDao.selectSearchTitleDesc(searchObject, begin, end);

	}

	@Override
	public ArrayList<NoticeVo> selectSearchTitleAsc(NoticeSearch searchObject, int begin, int end) {
		return noticeDao.selectSearchTitleAsc(searchObject, begin, end);
	}

	@Override
	public ArrayList<NoticeVo> selectSearchContentDesc(NoticeSearch searchObject, int begin, int end) {
		return noticeDao.selectSearchContentDesc(searchObject, begin, end);
	}

	@Override
	public ArrayList<NoticeVo> selectSearchContentAsc(NoticeSearch searchObject, int begin, int end) {
		return noticeDao.selectSearchContentAsc(searchObject, begin, end);
	}

	@Override
	public NoticeVo selectOneList(int notice_idx) {
		return noticeDao.selectOneList(notice_idx);
	}

	@Override
	public int insertNotice(NoticeVo notice) {
		return noticeDao.insertNotice(notice);
	}

	@Override
	public int updateNotice(NoticeVo notice) {
		return noticeDao.updateNotice(notice);
	}

	@Override
	public int deleteNotice(int notice_idx) {
		return noticeDao.deleteNotice(notice_idx);
	}

	@Override
	public NoticeVo selectNoticeBefore(int notice_idx) {
		return noticeDao.selectNoticeBefore(notice_idx);
	}

	@Override
	public NoticeVo selectNoticeAfter(int notice_idx) {
		return noticeDao.selectNoticeAfter(notice_idx);
	}

	@Override
	public void chkdeleteNotice(String chkdel) {
		noticeDao.chkdeleteNotice(chkdel);
		
	}

	

}
