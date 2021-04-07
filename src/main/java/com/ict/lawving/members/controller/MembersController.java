package com.ict.lawving.members.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ict.lawving.members.model.service.MembersService;
import com.ict.lawving.members.model.vo.MembersVo;

@SessionAttributes({"loginUser","loginAdmin"})
@Controller
public class MembersController {
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	private MembersService membersService;
	
	private Logger logger = LoggerFactory.getLogger(MembersController.class);
	@RequestMapping("go_signup.do")
	public String enrollView() {
		
		if(logger.isDebugEnabled()) // 프로젝트 배포시에 성능저하를 막기위해 logger의 레벨이 DEBUG인지 여부를 확인
		    logger.debug("회원등록페이지");
		
		return "member/memberInsertForm";
	}
	
/*	@RequestMapping(value="insert_member.do",method=RequestMethod.POST)
	public String insertMember(@ModelAttribute MembersVo members, Model model) {
		
		// 회원가입전에 회원정보를 출력
		System.out.println("Member 정보 : " + members.getMembers_id());
		System.out.println("Member 패스워드 : " + members.getMembers_pw());
		
		
		
		 * 비밀번호 -> 평문으로 되어있다. 1234 
		 * DB에 저장을 할때 평문으로 저장하면 안되기 때문에 "암호화" 처리를 한다.
		 * 
		 * 스프링 시큐리티라는 모듈에서 제공하는 bcrypt라는 암호화 방식으로 암호화 처리를 할꺼다.
		 * 
		 * * bcrypt란?
		 *   DB에 비밀번호를 저장할 목적으로 설계되었다.
		 *   
		 *   jsp/servlet 에서 했던 SHA-512암호화(단방향해쉬알고리즘)
		 *   
		 *   단점 : 111 평문 동일한 암호화 코드를 반화한다. 
		 *   
		 *   해결점 : 솔팅(salting) -> 원문에 아주작은랜덤문자열 추가해서 암호화 코드를 발생시킨다.
		 
		
		// 기존의 평문을 암호문으로 바꾸서 m객체에 다시 담자.
		String encPwd = bcryptPasswordEncoder.encode(members.getMembers_pw());
		System.out.println("암호화 처리 후 값 : " +encPwd);
		// setter를 통해서 Member객체의 pwd를 변경
		members.setMembers_pw(encPwd);
		
		System.out.println("수정된 Member객체 : " + members);
		
		// 회원가입 서비스를 호출
		int result = membersService.insertMember(members);
		
		if(result > 0) {
			return "redirect:home.do";
		}else {
			
			return "common/errorPage";
		}*/
		//회원가입시 작성 아이디 중복 체크 확인용
		@RequestMapping(value = "idCheck.do",method = RequestMethod.POST)
		public void idDuplicationCheck(@RequestParam("members_id")String id,HttpServletResponse response) throws IOException {
			logger.info("idCheck.do :"+ id);
			int idCount = membersService.selectCheckid(id);
			String returnValue = null;
			System.out.println("idCount:"+idCount);
			if (idCount==0) {
				returnValue = "ok";
			}else {
				returnValue="dup";
			}
			//ajax 통신은 입출력 스트림을 이용함
			//response 를이용해서 출력 스트림을 만들고 값 전송하기
			response.setContentType("text/html; charset=utf-8");//데이터타입 정해주기
			PrintWriter out = response.getWriter();
			out.append(returnValue);
			out.flush();
			out.close();
		}
		
		//회원가입 처리(패스워드 암호화 처리)
		@RequestMapping(value = "anroll.do",method = RequestMethod.POST)
		public String enrollMemberMethod(MembersVo members, Model model) {
			logger.info("anroll.do :"+members);//넘어오는 파라미터값 확인
			logger.info("아이디값확인"+members.getMembers_id());
			//패스워드 암호화 처리
			members.setMembers_pw(bcryptPasswordEncoder.encode(members.getMembers_pw()));
			logger.info("pw encode:"+members.getMembers_pw()+","+members.getMembers_pw().length());//암호화된 패스워드 값과 그 길이 확인 
			
			//서비스로 전송하고 결과를 받기
			int result = membersService.insertMember(members);
			
			//받은 결과로 성공/실패 페이지 내보내기
			if (result>0) {
				return "home";
			}else {
				model.addAttribute("message","회원가입 실패");
				return "common/error";
			}
		}
		
		
		// 관리자페이지 이동
		@RequestMapping("go_adminpage.do")
		public String goadminpageMethod() {
			if(logger.isDebugEnabled()) // 프로젝트 배포시에 성능저하를 막기위해 logger의 레벨이 DEBUG인지 여부를 확인
			    logger.debug("관리자페이지");
			
			return "admin/adminPage";

		}
		
		
		
		
		
		
		
		
		
	}
