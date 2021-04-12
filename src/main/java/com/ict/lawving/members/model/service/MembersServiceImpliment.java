package com.ict.lawving.members.model.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ict.lawving.common.Paging;
import com.ict.lawving.members.model.dao.MembersDao;
import com.ict.lawving.members.model.vo.MembersVo;

@Service("membersService")
public class MembersServiceImpliment implements MembersService{

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
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

	@Override
	public int updateMember(MembersVo m) {
		
		return membersdao.updateMember(m);
	}

	@Override
	public int deleteMembers(String id) {

		return membersdao.deleteMember(id);
	}

	@Override
	public MembersVo selectOneList(int members_idx) {
		return membersdao.selectmemberonelist(members_idx);
	}
	
	@Override
	public void sendEmail(MembersVo member, String div) throws Exception {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com"; //네이버 이용시 smtp.naver.com
		String hostSMTPid = "okok6827@naver.com";
		String hostSMTPpwd = "imsinger1357+";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "okok6827@naver.com";
		String fromName = "로빙";
		String subject = "";
		String msg = "";

		if(div.equals("findpw")) {
			subject = "로빙사이트 임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += member.getMembers_id()+"님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += member.getMembers_pw() +"</p></div>";
		}

		// 받는 사람 E-Mail 주소
		String mail = member.getMembers_email();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587); //네이버 이용시 587

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
		
	}
	//비밀번호찾기
		@Override
		public void findPw(HttpServletResponse response, MembersVo member) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			MembersVo ck = membersdao.selectloginCheck(member.getMembers_id());
			PrintWriter out = response.getWriter();
			// 가입된 아이디가 없으면
			if(membersdao.selectloginCheck(member.getMembers_id()) == null) {
				out.print("등록되지 않은 아이디입니다.");
				out.close();
			}
			// 가입된 이메일이 아니면
			else if(!member.getMembers_email().equals(ck.getMembers_email())) {
				out.print("등록되지 않은 이메일입니다.");
				out.close();
			}else {
				// 임시 비밀번호 생성
				String pw = "";
				for (int i = 0; i < 12; i++) {
					pw += (char) ((Math.random() * 26) + 97);
				}
				member.setMembers_pw(bcryptPasswordEncoder.encode(pw));
				// 비밀번호 변경
				membersdao.updatePw(member);
				
				//암호화된 비번을 다시 암호화처리전으로바꿔 이메일로보내준다.
				member.setMembers_pw(pw);
				// 비밀번호 변경 메일 발송
				sendEmail(member, "findpw");

				out.print("이메일로 임시 비밀번호를 발송하였습니다.");
				out.close();
			}
		}

		@Override
		public int updatepw(MembersVo member) {
			return membersdao.updatePw(member);
		}


	

}
