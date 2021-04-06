package com.ict.lawving.bookmark.model.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ict.lawving.bookmark.model.vo.BookmarkVo;

@Service("bookmarkService")
public class BookmarkServiceImpliment implements BookmarkService{

	@Override
	public int insertBookmark(String String) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<BookmarkVo> selectBookmarkList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookmarkVo selectBookmarkOnelist(int bookmark_idx) {
		// TODO Auto-generated method stub
		return null;
	}

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

}
