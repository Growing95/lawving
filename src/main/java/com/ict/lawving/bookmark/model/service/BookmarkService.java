package com.ict.lawving.bookmark.model.service;

import java.util.ArrayList;

import com.ict.lawving.bookmark.model.vo.BookmarkVo;

public interface BookmarkService {
 int insertBookmark(String String);
 ArrayList<BookmarkVo> selectBookmarkList();
 BookmarkVo selectBookmarkOnelist(int bookmark_idx);
 int Deletebookmark(int[] bookmakr_idx);
 int deleteBookmark(int bookmark_idx);
 
}
