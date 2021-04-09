package com.ict.lawving.bookmark.model.dao;

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

}
