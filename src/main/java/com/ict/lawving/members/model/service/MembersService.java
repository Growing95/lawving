package com.ict.lawving.members.model.service;

import java.util.List;

import com.ict.lawving.members.model.vo.MembersVo;

public interface MembersService {

	int insertMember(MembersVo members);
	MembersVo selectloginCheck(String id);
	int selectCheckid(String id);
	int getTotalCount();
	List<MembersVo> getList(int begin, int end);
	int updateMember(MembersVo m);
	int deleteMembers(String id);

}
