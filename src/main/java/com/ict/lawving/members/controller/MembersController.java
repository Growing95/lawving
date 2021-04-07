package com.ict.lawving.members.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ict.lawving.members.controller.MembersController;
import com.ict.lawving.members.model.service.MembersService;
import com.ict.lawving.members.model.vo.MembersVo;

@SessionAttributes({ "loginUser", "loginAdmin" })
@Controller
public class MembersController {
	@Autowired
	MembersService membersService;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	private Logger logger = LoggerFactory.getLogger(MembersController.class);

	@RequestMapping("go_signup.do")
	public String enrollView() {

		if (logger.isDebugEnabled()) // 프로젝트 배포시에 성능저하를 막기위해 logger의 레벨이 DEBUG인지 여부를 확인
			logger.debug("회원등록페이지");

		return "member/signUp";
	}

	@RequestMapping(value = "idcheck.do")
	public String checkId(@RequestParam("id") String id) {
		int count = membersService.selectcheckId(id);
		return "member/signUp";

	}

	@RequestMapping(value="insert_members.do")
	public String insertMembers(@ModelAttribute MembersVo members) {
		String encPwd = bcryptPasswordEncoder.encode(members.getMembers_pw());
		
		members.setMembers_pw(encPwd);
		
		int result = membersService.insertMembers(members);
		
		if(result > 0) {
			return "redirect:home.do";
		}else {
			return "common/errorPage";
		}
		
	}
	
	@RequestMapping(value = "go_login.do")
	public String moveLoginPage() {
		return "member/logIn";
	}

	@RequestMapping(value = "login.do")
	public String loginCheck(@RequestParam(value="id") String id, 
							 @RequestParam(value="pw") String pw,
							 HttpServletRequest request, Model model) {

	    logger.info("id: "+id);
        logger.info("password: "+pw);
        
        request.getSession().setAttribute("id", id); 
        request.getSession().setAttribute("password", pw); 
        
        model.addAttribute("id", id);
        model.addAttribute("password", pw);
        
        return "";
	}

	@RequestMapping(value = "go_mypage.do")
	public String movemyPage() {
		return "member/myPage";
	}

}
