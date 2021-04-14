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
		limitlist=sqlSession.selectList("limitMapper.limitlist",map);
		return limitlist;
	}

	public int getCount() {
		int result =0;
		result=sqlSession.selectOne("limitMapper.limitcount");
		return result;
	}

	public int getBlackCount() {
		int result =0;
		result=sqlSession.selectOne("limitMapper.blackcount");
		System.out.println(result);
		return result;
	}
	public List<LimitVo> getBlackList(int begin, int end) {
		List<LimitVo> blacklist=null;
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		blacklist=sqlSession.selectList("limitMapper.blacklist",map);
		return blacklist;
	}

	public int getsearch(LimitVo lvo) {
		int result = 0;
		result = sqlSession.selectOne("limitMapper.search",lvo);
		return result;
	}

	public int getinsert(LimitVo lvo) {
		int result = 0;
		result = sqlSession.insert("limitMapper.insert",lvo);
		return result;
	}

	public int getupdate(LimitVo lvo) {
		int result = 0;
		result = sqlSession.update("limitMapper.update",lvo);
		return result;
	}

	public int getchkcount(LimitVo lvo) {
		int lcount = 0;
		lcount = sqlSession.selectOne("limitMapper.chkcount",lvo);
		return lcount;
	}

	public void chkblackdelete(String chkdel) {
		sqlSession.update("limitMapper.chkblackdelete",chkdel);
		
	}

	

}
