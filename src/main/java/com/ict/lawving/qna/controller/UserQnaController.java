package com.ict.lawving.qna.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.lawving.common.Paging;
import com.ict.lawving.qna.model.service.QnaService;
import com.ict.lawving.qna.model.vo.QnaSearch;
import com.ict.lawving.qna.model.vo.QnaVo;

@Controller
public class UserQnaController {
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private Paging paging;
	
//	QNA 목록 조회하기
	@RequestMapping("list_qna.do")
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
		return "qna/qnaListView";
	}
	
//	QNA 검색
	@RequestMapping(value = "search_qna_get.do", method = RequestMethod.GET)
	public String searchQuestionMethod_get(
			@ModelAttribute("status")String status,
			@ModelAttribute("order")String order,
			@ModelAttribute("keyword")String keyword,
			@ModelAttribute("cPage")String cPage
			) {
		return "redirect:search_qna.do";
	}
	
	@RequestMapping(value = "search_qna.do", method = RequestMethod.POST)
	public String searchQuestionMethod(
			@RequestParam("status") String status, 
			@RequestParam("order") String order,
			@RequestParam("keyword") String keyword,
			Model model, HttpServletRequest request) {
		QnaSearch searchObject = new QnaSearch(status, order, keyword);
		ArrayList<QnaVo> qnaList = new ArrayList<QnaVo>();
		Paging paging = new Paging();

		// 1. 전체 게시물의 수 
		int count= qnaService.getTotalCount(searchObject);
		paging.setTotalRecord(count);
		// 2. 전체 페이지의 수
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord()/paging.getNumPerPage());
			if (paging.getTotalRecord()%paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage()+1);
			}
		}
		// 3. 현재 페이지
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		// 4. 시작번호, 끝번호
		paging.setBegin((paging.getNowPage()-1)*paging.getNumPerPage()+1);
		paging.setEnd( (paging.getBegin()-1)+paging.getNumPerPage() );
		// 5. 시작블록, 끝블록
		paging.setBeginBlock( (int)((paging.getNowPage()-1)/paging.getPagePerBlock())*paging.getPagePerBlock() +1);
		paging.setEndBlock((paging.getBeginBlock()+paging.getPagePerBlock()-1));
		// 6. 주의사항(endBlock이 totalPage보다 클 수가 있다.)
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		switch (status) {
			case "all": 
				switch (order) {
					case "desc": 
						qnaList = qnaService.searchAllQuestionDesc(
								searchObject, paging.getBegin(),paging.getEnd());
						break;
					case "asc": 
						qnaList = qnaService.searchAllQuestionAsc(
								searchObject, paging.getBegin(),paging.getEnd());
						break;
					}
				break;
			case "completed": 
				switch (order) {
					case "desc": 
						System.out.println("답변완료-최신순 검색");
						qnaList = qnaService.searchCompletedQuestionDesc(
								searchObject, paging.getBegin(),paging.getEnd());
						break;
					case "asc": 
						qnaList = qnaService.searchCompletedQuestionAsc(
								searchObject, paging.getBegin(),paging.getEnd());
						break;
					}
				break;
			case "waiting": 
				switch (order) {
					case "desc": 
						System.out.println("대기중-최신순 검색");
						qnaList = qnaService.searchWaitingQuestionDesc(
								searchObject, paging.getBegin(),paging.getEnd());
						break;
					case "asc": 
						qnaList = qnaService.searchWaitingQuestionAsc(
								searchObject, paging.getBegin(),paging.getEnd());
						break;
					}
				break;
		}
		
		if (qnaList.size() > 0) {
			model.addAttribute("searchObject", searchObject);
			model.addAttribute("qnaList", qnaList);
			model.addAttribute("paging", paging);
			return "qna/qnaListView";
		} else {
//			model.addAttribute("msg", "게시판에 "+keyword+"를 포함한 글이 없습니다.");
//			return "common/errorPage";
			model.addAttribute("noData", "게시판에 &quot;"+keyword+"&quot;를 포함한 글이 없습니다.");
			return "qna/qnaListView";
		}
	}
	
//	QNA 목록 상세보기
	@RequestMapping("onelist_qna.do")
	public String selectQuestionOnelistMethod(
			@ModelAttribute("qna_idx")String qna_idx,
			@ModelAttribute("cPage")String cPage,
			Model model, HttpServletRequest request) {
		System.out.println("qna_idx : " + qna_idx);
		System.out.println("cPage : " + cPage);
		int result = qnaService.updateQuestionHit(qna_idx);
		QnaVo qnaOnelist = qnaService.selectQuestionOnelist(qna_idx);
		model.addAttribute("qnaOnelist", qnaOnelist);
		return "qna/qnaOneList";
	}
	
//	QNA 이전 글 보기
	@RequestMapping("before_qna.do")
	public String selectQuestionBeforeMethod(
			@RequestParam("qna_idx")String qna_idx,
			@ModelAttribute("cPage")String cPage,
			Model model) {
		System.out.println("qna_idx : " + qna_idx);
		System.out.println("cPage : " + cPage);
		QnaVo qnaOnelist = qnaService.selectQuestionBefore(qna_idx);
		int result = qnaService.updateQuestionHit(qnaOnelist.getQna_idx());
		model.addAttribute("qnaOnelist", qnaOnelist);
		return "qna/qnaOneList";
	}
	
//	QNA 다음 글 보기
	@RequestMapping("after_qna.do")
	public String selectQuestionAfterMethod(
			@RequestParam("qna_idx")String qna_idx,
			@ModelAttribute("cPage")String cPage,
			Model model) {
		System.out.println("qna_idx : " + qna_idx);
		System.out.println("cPage : " + cPage);
		QnaVo qnaOnelist = qnaService.selectQuestionAfter(qna_idx);
		int result = qnaService.updateQuestionHit(qnaOnelist.getQna_idx());
		model.addAttribute("qnaOnelist", qnaOnelist);
		return "qna/qnaOneList";
	}
	
//	QNA 문의 작성 페이지로 이동
	@RequestMapping("go_insert_qna.do")
	public String goInsertQuestion(
			@ModelAttribute("cPage")String cPage) {
		return "qna/qnaWriteForm";
	}
	
//	QNA 문의 작성하기
	@RequestMapping(value = "insert_qna.do", method = RequestMethod.POST)
	public String insertQuestionMethod(
			QnaVo qna, HttpServletRequest request) {
		String qna_view_private = request.getParameter("private");
		switch (qna.getQna_category()) {
			case "question": qna.setQna_category("질문"); break;
			case "suggestion": qna.setQna_category("건의"); break;
		}
		if (qna_view_private=="private") {
			qna.setQna_view("비공개");
		} else {
			qna.setQna_view("공개");
		}
		int result = qnaService.insertQuestion(qna);
		if (result>0) {
			return "redirect:list_qna.do";
		} else {
			return "redirect:list_qna.do";
		}
	}
	
//	QNA 문의 삭제하기
	@RequestMapping(value = "delete_qna.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteQuestionMethod(
			@RequestParam("qna_idx")String qna_idx,
			@RequestParam("members_lev")String members_lev,
			@RequestParam("members_idx")String members_idx,
			Model model) {
		int result =  qnaService.deleteQuestion(qna_idx);
		System.out.println("members_lev : "+members_lev);
		System.out.println("members_idx : "+members_idx);
		System.out.println("qna_idx : " + qna_idx);
		if (result>0) {
//		로그인 한 유저가 회원이라면 목록으로 돌아간다.
			if (members_lev == "1") {
				return "redirect:list_qna.do";
//		로그인 한 유저가 관리자라면 insert_limitmember.do로 redirect한다.
			} 
			else {
				System.out.println("limit 테이블에 회원 추가");
				return "redirect:list_qna.do";
//				return "redirect:insert_limitmember.do?member_idx=" + members_idx;
			}
		} else {
			model.addAttribute("msg", "문의글을 삭제하지 못했습니다.");
			return "common/errorPage";
		}
	}
}