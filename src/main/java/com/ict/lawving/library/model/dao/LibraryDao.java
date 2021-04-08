package com.ict.lawving.library.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.lawving.library.model.vo.LibraryVo;
import com.ict.lawving.members.model.vo.MembersVo;

@Repository("libraryDao")
public class LibraryDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int getCount() {
		int result =0;
		result=sqlSession.selectOne("libraryMapper.librarycount");
		return result;
	}

	public List<LibraryVo> getLibraryList(int begin, int end) {
		List<LibraryVo> librarylist=null;
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		librarylist=sqlSession.selectList("libraryMapper.librarylist",map);
		return librarylist;
	}

}
