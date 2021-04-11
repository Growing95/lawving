package com.ict.lawving.bookmark.model.service;

import java.util.ArrayList;

import com.ict.lawving.bookmark.model.vo.BookmarkVo;

public interface BookmarkService {
 ArrayList<BookmarkVo> selectBookmarkList(String members_idx);
int insertBookmark(BookmarkVo b);
int getTotalCount(String members_idx);
BookmarkVo onelistbookmark(String bookmark_idx);
int deleteBookmark(String bookmark_idx);
 
}
