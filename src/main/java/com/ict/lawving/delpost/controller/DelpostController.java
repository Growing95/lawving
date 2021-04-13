package com.ict.lawving.delpost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.lawving.delpost.model.service.DelpostService;
import com.ict.lawving.delpost.model.vo.DelpostVo;

@Controller
public class DelpostController {
	@Autowired
	private DelpostService delpostService;
	
	
	
	@RequestMapping(value="delete_question.do",method= {RequestMethod.GET,RequestMethod.POST})
	public String deleteQuestion(DelpostVo dvo,@RequestParam("qna_idx")String qna_idx,@RequestParam("delpost_writer")String qna_writer
			) {
		System.out.println("******************************델포스트 아이디받음 : "+qna_writer);
		int result = delpostService.getinsert(dvo);
		if (result>0) {
			System.out.println("*****************성공");
			return "redirect:register_limit.do?members_idx="+dvo.getMembers_idx()+"&qna_idx="+qna_idx+"&qna_writer="+qna_writer;
		}else {
			return "common/errorPage";
		}
	}
	
	
	
	
	
}
