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
		result=sqlSession.selectOne("memberMapper.count");
		return result;
	}
	public List<MembersVo> getList(int begin, int end) {
		List<MembersVo> memberslist=null;
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		memberslist=sqlSession.selectList("memberMapper.memberslist",map);
		return memberslist;
	}
	public int updateMember(MembersVo m) {

		return sqlSession.update("updateMember",m);
	}
	public int deleteMember(String id) {
		return sqlSession.delete("delete_member",id);
	}
	public MembersVo selectmemberonelist(int members_idx) {
		return sqlSession.selectOne("selectonemember", members_idx);
	}
	//임시비번변경
	public int updatePw(MembersVo member) {
		
		return sqlSession.update("updatepw",member);
	}
	
	//ID찾기
	public String findId(MembersVo members) {
		return sqlSession.selectOne("memberMapper.findId", members);
	}

	public int seleckemailCheck(String members_email) {
		return sqlSession.selectOne("selectemail",members_email);
	}
	public String searchid(String members_idx) {
		return sqlSession.selectOne("memberMapper.searchid",members_idx);
	}
	public String searchlev(String members_idx) {
		return sqlSession.selectOne("memberMapper.searchlev",members_idx);
	}
	public String searchreg(String members_idx) {
		return sqlSession.selectOne("memberMapper.searchreg",members_idx);

	}
	public int updatelev(String members_idx) {
		return sqlSession.selectOne("memberMapper.updatelev",members_idx);
	}
	public void changelev(String chkdel) {
		sqlSession.update("memberMapper.changelev",chkdel);
	}

}
