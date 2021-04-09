package com.ict.lawving.bookmark.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.lawving.bookmark.model.vo.BookmarkVo;

@Repository
public class BookmarkDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public int insertBookmark(BookmarkVo b) {
		return sqlSessionTemplate.insert("insertbookmark",b);
	}


	public ArrayList<BookmarkVo> selectBookmarkList(String members_idx) {
		List<BookmarkVo> bblist = new ArrayList<BookmarkVo>();
		bblist=sqlSessionTemplate.selectList("list_bookmark",members_idx);
		return (ArrayList<BookmarkVo>) bblist;
	}



}
