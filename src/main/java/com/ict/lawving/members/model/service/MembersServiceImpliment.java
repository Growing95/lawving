package com.ict.lawving.members.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.lawving.members.model.dao.MembersDao;
import com.ict.lawving.members.model.vo.MembersVo;

@Service("membersService")
public class MembersServiceImpliment implements MembersService{

	@Autowired
	private MembersDao membersdao;
	
	@Override
	public int insertMember(MembersVo members) {
		int result = membersdao.insertmembers(members);
		return result;
	}

	@Override
	public int selectCheckid(String id) {
		return membersdao.selectid(id);
	}

	@Override
	public MembersVo selectloginCheck(MembersVo members) {
		
		return membersdao.selectloginCheck(members);
	}

	

}
