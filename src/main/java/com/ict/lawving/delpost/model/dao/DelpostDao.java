package com.ict.lawving.delpost.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.lawving.delpost.model.vo.DelpostVo;

@Repository("delpostDao")
public class DelpostDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	public int insertQuestion(DelpostVo dvo) {
		int result = 0;
		result = sqlSession.insert("delpostMapper.insertQuestion",dvo);
		return result;
	}
	
	
	
	
	
}
