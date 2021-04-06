package com.ict.lawving.members.model.dao;

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

}
