package com.ict.lawving.limit.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.lawving.limit.model.vo.LimitVo;
import com.ict.lawving.members.model.vo.MembersVo;

@Repository("limitDao")
public class LimitDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<LimitVo> getList(int begin, int end) {
		List<LimitVo> limitlist=null;
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		limitlist=sqlSession.selectList("limitlist",map);
		return limitlist;
	}

	public int getCount() {
		int result =0;
		result=sqlSession.selectOne("count");
		return result;
	}

}
