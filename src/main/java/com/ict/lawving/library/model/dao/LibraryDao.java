package com.ict.lawving.library.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.lawving.library.model.vo.LibrarySearch;
import com.ict.lawving.library.model.vo.LibraryVo;

@Repository("libraryDao")
public class LibraryDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int getCount() {
		int result =0;
		result=sqlSession.selectOne("libraryMapper.librarycount");
		return result;
	}
	public ArrayList<LibraryVo> selectLibraryList() {
		List<LibraryVo> List = sqlSession.selectList("libraryMapper.list");
		return (ArrayList<LibraryVo>) List;
	}
	
	
	public List<LibraryVo> getLibraryList(int begin, int end) {
		List<LibraryVo> librarylist=null;
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		librarylist=sqlSession.selectList("libraryMapper.librarylist",map);
		return librarylist;
	}

	public int getCount(LibrarySearch searchObject) {
		int result = 0;
		switch (searchObject.getCategory()) {
		case "library_title":
			result = sqlSession.selectOne("libraryMapper.searchCountTitle",searchObject);
			break;
		case "library_content":
			result = sqlSession.selectOne("libraryMapper.searchCountContent",searchObject);
			break;
		}
		return result;
	}

	public ArrayList<LibraryVo> selectSearchTitleDesc(LibrarySearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<LibraryVo> LibraryList = sqlSession.selectList("libraryMapper.searchTitleDesc", map);
		return (ArrayList<LibraryVo>) LibraryList;
	}

	public ArrayList<LibraryVo> selectSearchTitleAsc(LibrarySearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<LibraryVo> LibraryList = sqlSession.selectList("libraryMapper.searchTitleAsc", map);
		return (ArrayList<LibraryVo>) LibraryList;
	}

	public ArrayList<LibraryVo> selectSearchContentDesc(LibrarySearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<LibraryVo> LibraryList = sqlSession.selectList("libraryMapper.searchContentDesc", map);
		return (ArrayList<LibraryVo>) LibraryList;
	}

	public ArrayList<LibraryVo> selectSearchContentAsc(LibrarySearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<LibraryVo> LibraryList = sqlSession.selectList("libraryMapper.searchContentAsc", map);
		return (ArrayList<LibraryVo>) LibraryList;
	}

	public LibraryVo selectOneList(int library_idx) {
		return sqlSession.selectOne("libraryMapper.selectLibrary", library_idx);
	}


	public int insertlibrary(LibraryVo library) {

		int result = 0;
		result = sqlSession.insert("libraryMapper.insertlibrary", library);
		return result;
	}
	public int updatelibrary(LibraryVo library) {
		int result =0 ;
		result = sqlSession.update("libraryMapper.updatelibrary",library);
		return result;
	}
	public void chklistdelete(String chkdel) {
		sqlSession.delete("libraryMapper.chkdelete",chkdel);
		
	}
	public int deletelibrary(int library_idx) {
		int result =0;
		result=sqlSession.delete("libraryMapper.deletelibrary",library_idx);
		return result;
	}
	public LibraryVo selectlibraryBefore(int library_idx) {
		LibraryVo onelist=sqlSession.selectOne("libraryMapper.libraryBefore",library_idx);
		return onelist;
	}
	public LibraryVo selectlibraryAfter(int library_idx) {
		LibraryVo onelist=sqlSession.selectOne("libraryMapper.libraryAfter",library_idx);
		return onelist;
	}
	
}
