package com.ict.lawving.members.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.lawving.common.Paging;
import com.ict.lawving.members.model.dao.MembersDao;
import com.ict.lawving.members.model.vo.MembersVo;

@Service("membersService")
public class MembersServiceImpliment implements MembersService{

	@Autowired
	private MembersDao membersdao;
	@Autowired
	private Paging paging;
	@Override
	public int insertMember(MembersVo members) {
		int result = membersdao.insertmembers(members);
		return result;
	}

	@Override
	public int selectCheckid(String id) {
		int result = membersdao.selectid(id);
		return result;
	}

	@Override
	public MembersVo selectloginCheck(String id) {
		return membersdao.selectloginCheck(id);
	}

	@Override
	public int getTotalCount() {
		return membersdao.getCount();
		
	}

	@Override
	public List<MembersVo> getList(int begin, int end) {
		return membersdao.getList(paging.getBegin(),paging.getEnd());
	}


	

}
