package com.ict.lawving.members.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.lawving.members.model.vo.MembersVo;

@Repository
public class MembersDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public int insertmembers(MembersVo members) {
		int result =0 ;
		result=sqlSession.insert("insertmembers", members);
		return result;
	}
	public int selectid(String id) {
		int result =0;
		result= sqlSession.selectOne("selectCheckId",id);
		return result;
	}
	public MembersVo selectloginCheck(String id) {
		return sqlSession.selectOne("selectlogin",id);
	}
	public int getCount() {
		int result =0;
		result=sqlSession.selectOne("count");
		return result;
	}
	public List<MembersVo> getList(int begin, int end) {
		List<MembersVo> memberslist=null;
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		memberslist=sqlSession.selectList("memberslist",map);
		return memberslist;
	}

}
