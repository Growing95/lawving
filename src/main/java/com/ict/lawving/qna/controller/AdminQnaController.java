package com.ict.lawving.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.lawving.qna.model.service.QnaService;
import com.ict.lawving.qna.model.vo.QnaVo;

@RestController
public class AdminQnaController {
	@Autowired
	private QnaService qnaService;
	
//	QNA 문의글 답변 작성/수정하기
	@RequestMapping(value = "update_answer.do", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String updateAnswerMethod(QnaVo qna) {
		QnaVo qnaOnelist = new QnaVo();
		qnaOnelist = qnaService.updateAnswer(qna);
		String answer = qnaOnelist.getQna_comment();
		return answer;
	}
	
//	QNA 문의글 답변 삭제하기
	@RequestMapping(value = "delete_answer.do", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String deleteAnswerMethod(String qna_idx) {
		int result = qnaService.deleteAnswer(qna_idx);
		if (result>0) {
			return "답변이 삭제되었습니다.";
		} else {
			return "답변을 삭제하지 못했습니다.";
		}
	}
}
