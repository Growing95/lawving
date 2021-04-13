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
	//임시비밀번호 이메일발송
	public void sendEmail(MembersVo member, String div) throws Exception;
	int updatepw(MembersVo member);
	//ID찾기
	public void findId(HttpServletResponse response, MembersVo members) throws Exception;

	//회원가입 이메일인증 메소드
	public void emailpost(HttpServletResponse response, MembersVo members_email) throws Exception;
	//인증번호 이메일발송
	public void postEmail(MembersVo members_email, String div,String pw) throws Exception;

	String searchid(String members_idx);
	String searchlev(String members_idx);
	String searchreg(String members_idx);

}
