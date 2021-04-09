package com.ict.lawving.members.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.lawving.members.model.service.MembersServiceImpliment;
import com.ict.lawving.members.model.vo.MembersVo;

@Repository
public class MembersDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private Logger logger = LoggerFactory.getLogger(MembersDao.class);
	
	public int insertmembers(MembersVo members) {
		int result =0 ;
		result=sqlSession.insert("memberMapper.insertmembers", members);
		return result;
	}
	
	public int selectid(String id) {
		int result =0;
		result= sqlSession.selectOne("memberMapper.selectCheckId",id);
		return result;
	}
	
	public MembersVo selectloginCheck(String id) {
		return sqlSession.selectOne("memberMapper.selectlogin",id);
	}
	
	
	public MembersVo idCheck(String id) {
		System.out.println("==> Mybatis로 idCheck() 기능 처리");

		return sqlSession.selectOne("memberMapper.idCheck", id);
	}
	
	public String findId(MembersVo members) {
		return sqlSession.selectOne("memberMapper.findId", members);
	}
	
	public String findPw(MembersVo members) {
		return sqlSession.selectOne("memberMapper.findPw", members);
	}

}
