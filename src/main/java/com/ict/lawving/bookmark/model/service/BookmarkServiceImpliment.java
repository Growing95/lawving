package com.ict.lawving.bookmark.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.lawving.bookmark.model.dao.BookmarkDao;
import com.ict.lawving.bookmark.model.vo.BookmarkVo;

@Service("bookmarkService")
public class BookmarkServiceImpliment implements BookmarkService{
	@Autowired
	private BookmarkDao bookmarkDao;



	@Override
	public int insertBookmark(BookmarkVo b) {
	
		return bookmarkDao.insertBookmark(b);
	}

	@Override
	public int getTotalCount(String members_idx) {
		
		return bookmarkDao.getTotalCount(members_idx);
	}

	@Override
	public BookmarkVo onelistbookmark(String bookmark_idx) {
		return bookmarkDao.onelistbookmark(bookmark_idx);
	}


	@Override
	public int deleteBookmark(String bookmark_idx) {
		return bookmarkDao.deleteonebookmark(bookmark_idx);
	}

	@Override
	public ArrayList<BookmarkVo> selectBookmarklist(String members_idx, int begin, int end) {
		// TODO Auto-generated method stub
		return bookmarkDao.selectBookmarkList(members_idx,begin,end);
	}



	



}
