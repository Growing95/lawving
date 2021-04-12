package com.ict.lawving.members.model.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ict.lawving.members.model.vo.MembersVo;

public interface MembersService {

	int insertMember(MembersVo members);
	MembersVo selectloginCheck(String id);
	int selectCheckid(String id);
	int getTotalCount();
	List<MembersVo> getList(int begin, int end);
	int updateMember(MembersVo m);
	int deleteMembers(String id);
	MembersVo selectOneList(int members_idx);
	//비밀번호찾기메소드
	public void findPw(HttpServletResponse response, MembersVo member) throws Exception;
	//이메일발송
	public void sendEmail(MembersVo member, String div) throws Exception;
	int updatepw(MembersVo member);

}
