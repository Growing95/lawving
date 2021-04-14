package com.ict.lawving.qna.controller;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.lawving.qna.model.service.QnaService;
import com.ict.lawving.qna.model.vo.QnaVo;

@RestController
public class AdminQnaController {
	@Autowired
	private QnaService qnaService;
	
//	QNA 문의글 답변 작성/수정하기
	@RequestMapping(value = "update_answer.do", 
					method = RequestMethod.POST, 
					produces = "text/html; charset=utf-8")
	@ResponseBody
	public String updateAnswerMethod(QnaVo qna, Model model) {
//		넘어온 qna_idx를 이용하여 comment, writer 업데이트
		int result = qnaService.updateAnswer(qna);
//		업데이트 성공시 
		if (result>0) {
//			업데이트 된 글 가져오기
			QnaVo qnaOnelist = qnaService.selectQuestionOnelist(qna.getQna_idx());
//			전송용 json 객체 준비
			JSONObject sendJson = new JSONObject();
//			qnaOnelist의 필드값을 sendJson으로 옮기기
			sendJson.put("qna_comment", qnaOnelist.getQna_comment());
			sendJson.put("qna_comment_writer", qnaOnelist.getQna_comment_writer());
			sendJson.put("qna_reg_a", qnaOnelist.getQna_reg_a());
//			jsonView 가 리턴됨
			return sendJson.toJSONString();
		} else {
			model.addAttribute("msg", "답변을 입력하지 못했습니다.");
			return "common/errorPage";
		}
	}
}