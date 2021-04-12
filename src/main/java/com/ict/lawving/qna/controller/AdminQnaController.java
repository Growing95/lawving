package com.ict.lawving.qna.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.lawving.common.Paging;
import com.ict.lawving.qna.model.service.QnaService;
import com.ict.lawving.qna.model.vo.QnaVo;

@RestController
public class AdminQnaController {
	@Autowired
	private QnaService qnaService;
	
//	QNA 목록 조회하기
	@RequestMapping("list_qna_admin.do")
	public String selectQuestionListMethod(
			Model model, HttpServletRequest request) {
		ArrayList<QnaVo> qnaList = new ArrayList<QnaVo>();
		Paging paging = new Paging();
		
		try {
			// 1. 전체 게시물의 수 
			int count= qnaService.getTotalCount();
			paging.setTotalRecord(count);
			// 2. 전체 페이지의 수
			if (paging.getTotalRecord() <= paging.getNumPerPage()) {
				paging.setTotalPage(1);
			} else {
				paging.setTotalPage(
						paging.getTotalRecord()/paging.getNumPerPage());
				if (paging.getTotalRecord()%paging.getNumPerPage() != 0) {
					paging.setTotalPage(paging.getTotalPage()+1);
				}
			}
			// 3. 현재 페이지
			String cPage= request.getParameter("cPage");
			
			System.out.println("cPage : "+cPage);
			
			if (cPage == null) {
				paging.setNowPage(1);
			} else if (cPage == "") {
				paging.setNowPage(1);
			} else {
				paging.setNowPage(Integer.parseInt(cPage));
			}
			// 4. 시작번호, 끝번호
			paging.setBegin((paging.getNowPage()-1)*paging.getNumPerPage()+1);
			paging.setEnd((paging.getBegin()-1)+paging.getNumPerPage() );
			// 5. 시작블록, 끝블록
			paging.setBeginBlock(
					(int)((paging.getNowPage()-1)/paging.getPagePerBlock())*paging.getPagePerBlock()+1);
			paging.setEndBlock((paging.getBeginBlock()+paging.getPagePerBlock()-1));
			// 6. 주의사항(endBlock이 totalPage보다 클 수가 있다.)
			if (paging.getEndBlock()>paging.getTotalPage()) {
				paging.setEndBlock(paging.getTotalPage());
			}
			
			qnaList = qnaService.selectQuestionList(paging.getBegin(),paging.getEnd());
			model.addAttribute("qnaList", qnaList);
			model.addAttribute("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qna/qnaListViewAdmin";
	}
	
//	QNA 문의글 답변 작성/수정하기
	@RequestMapping(value = "update_answer.do", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String updateAnswerMethod(QnaVo qna
			) {
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
