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
	public int Deletebookmark(int[] bookmakr_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBookmark(int bookmark_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertBookmark(BookmarkVo b) {
	
		return bookmarkDao.insertBookmark(b);
	}


	@Override
	public ArrayList<BookmarkVo> selectBookmarkList(String members_idx) {
		
		return bookmarkDao.selectBookmarkList(members_idx);
	}

	@Override
	public int getTotalCount(String members_idx) {
		
		return bookmarkDao.getTotalCount(members_idx);
	}


	



}
