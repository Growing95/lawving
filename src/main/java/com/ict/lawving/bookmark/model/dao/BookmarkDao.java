package com.ict.lawving.bookmark.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.lawving.bookmark.model.vo.BookmarkVo;

@Repository
public class BookmarkDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public int insertBookmark(BookmarkVo b) {
		return sqlSessionTemplate.insert("bookmarkMapper.insertbookmark",b);
	}


	public int getTotalCount(String members_idx) {
		
		return sqlSessionTemplate.selectOne("bookmarkMapper.totalcount",members_idx);
	}


	public int getDelete(String id) {
		int result = 0;
		result= sqlSessionTemplate.delete("bookmarkMapper.chkbookmarkdelete",id );
		return result;
	}


	public BookmarkVo onelistbookmark(String bookmark_idx) {

		return sqlSessionTemplate.selectOne("bookmarkMapper.onelist_bookmark",bookmark_idx);
	}


	public int deleteonebookmark(String bookmark_idx) {
		return sqlSessionTemplate.delete("bookmarkMapper.onedelete_bookmark", bookmark_idx);
	}


	public int selecttotal() {
		return sqlSessionTemplate.selectOne("bookmarkMapper.total");
	}

	public ArrayList<BookmarkVo> selectBookmarkList(String members_idx, int begin, int end) {
	Map<String,Integer> map = new HashMap<String, Integer>();
	int idx = Integer.parseInt(members_idx);
	map.put("members_idx", idx);
	map.put("begin", begin);
	map.put("end", end);
	List<BookmarkVo> blist = new ArrayList<BookmarkVo>();
	blist= sqlSessionTemplate.selectList("bookmarkMapper.bookmarklist",map);
		return (ArrayList<BookmarkVo>) blist;
	}


	public int deleteAllbookmark(String members_idx) {
		return sqlSessionTemplate.delete("bookmarkMapper.deleteAll",members_idx);
	}





}
