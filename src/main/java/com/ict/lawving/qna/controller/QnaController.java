package com.ict.lawving.qna.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private Paging paging;
	
//	사용자
	
//	QNA 목록 조회하기
	@RequestMapping("list_qna.do")
	public String selectQuestionListMethod(
			Model model, HttpServletRequest request) {
//		사용할 객체 생성
		ArrayList<QnaVo> qnaList = new ArrayList<QnaVo>();
		Paging paging = new Paging();
//		페이징
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
			if (cPage==null || cPage.isEmpty()) {
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
//			리스트 가져오기
			qnaList = qnaService.selectQuestionList(paging.getBegin(),paging.getEnd());
			model.addAttribute("qnaList", qnaList);
			model.addAttribute("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qna/qnaListView";
	}
	
//	QNA 검색
//	검색했을 때 다음 페이지로 이동
	@RequestMapping("search_qna.do")
	public String searchQuestionMethod(
			@RequestParam("status") String status, 
			@RequestParam("order") String order,
			@RequestParam("keyword") String keyword,
			Model model, HttpServletRequest request) {
//		사용할 객체 생성
		QnaSearch searchObject = new QnaSearch(status, order, keyword);
		ArrayList<QnaVo> qnaList = new ArrayList<QnaVo>();
		Paging paging = new Paging();
//		페이징
		try {
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
			if (cPage==null || cPage.isEmpty()) {
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
//			검색 종류 판별
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
//			검색 결과가 있을 시
			if (qnaList.size() > 0) {
				model.addAttribute("searchObject", searchObject);
				model.addAttribute("qnaList", qnaList);
				model.addAttribute("paging", paging);
				return "qna/qnaListView";
//			검색 결과가 없을시
			} else {
				model.addAttribute("noData", "게시판에 &quot;"+keyword+"&quot;를 포함한 글이 없습니다.");
				return "qna/qnaListView";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	QNA 목록 상세보기
	@RequestMapping("onelist_qna.do")
	public String selectQuestionOnelistMethod(
			@ModelAttribute("qna_idx")String qna_idx,
			@ModelAttribute("cPage")String cPage,
			Model model, HttpServletRequest request) {
//		조회수 + 1
		int result = qnaService.updateQuestionHit(qna_idx);
		if (result>0) {
//			글 가져오기
			QnaVo qnaOnelist = qnaService.selectQuestionOnelist(qna_idx);
			model.addAttribute("qnaOnelist", qnaOnelist);
			return "qna/qnaOneList";
		} else {
			model.addAttribute("message", "선택한 글로 이동하지 못했습니다.");
			return "common/errorPage";
		}
	}
	
//	QNA 이전 글 보기
	@RequestMapping("before_qna.do")
	public String selectQuestionBeforeMethod(
			@ModelAttribute("qna_idx")String qna_idx,
			@ModelAttribute("cPage")String cPage,
			@RequestParam("members_idx")String members_idx,
			Model model, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
//		이전 글 가져오기
		try {
			QnaVo qnaOnelist = qnaService.selectQuestionBefore(qna_idx);
			String view = qnaOnelist.getQna_view();
			String qna_members_idx = qnaOnelist.getMembers_idx();
//			남이 쓴 비공개글 조회 시도
			if (view.equals("비공개") && members_idx != qna_members_idx) {
				out.println("<script>alert('비밀글입니다.'); history.go(-1);</script>");
				out.flush();
//			공개글 조회
			} else {
//				조회수 + 1 
				int result = qnaService.updateQuestionHit(qnaOnelist.getQna_idx());
//				DB에는 조회수 Update 했지만 이미 가져온 데이터는 아니기 때문에 1 더하여 전송
				qnaOnelist.setQna_hit(qnaOnelist.getQna_hit() + 1);
				model.addAttribute("qnaOnelist", qnaOnelist);
				return "qna/qnaOneList";
			}
//		DB에 이전 글이 없을 때 catch
		} catch (Exception e) {
			out.println("<script>alert('이전 글이 없습니다.'); history.go(-1);</script>");
			out.flush();
		}
		return null;
	}
	
//	QNA 다음 글 보기
	@RequestMapping("after_qna.do")
	public String selectQuestionAfterMethod(
			@ModelAttribute("qna_idx")String qna_idx,
			@ModelAttribute("cPage")String cPage,
			@RequestParam("members_idx")String members_idx,
			Model model, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
//		다음 글 가져오기
		try {
			QnaVo qnaOnelist = qnaService.selectQuestionAfter(qna_idx);
			String view = qnaOnelist.getQna_view();
			String qna_members_idx = qnaOnelist.getMembers_idx();
//			회원이 남이 쓴 비공개글 조회 시도
			if (view.equals("비공개") && members_idx != qna_members_idx) {
				out.println("<script>alert('비밀글입니다.'); history.go(-1);</script>");
				out.flush();
//			공개글 조회
			} else {
//				조회수 + 1 
				int result = qnaService.updateQuestionHit(qnaOnelist.getQna_idx());
//				DB에는 조회수 Update 했지만 이미 가져온 데이터는 아니기 때문에 1 더하여 전송
				qnaOnelist.setQna_hit(qnaOnelist.getQna_hit() + 1);
				model.addAttribute("qnaOnelist", qnaOnelist);
				return "qna/qnaOneList";
			}
//		DB에 다음 글이 없을 때 catch
		} catch (Exception e) {
			out.println("<script>alert('다음 글이 없습니다.'); history.go(-1);</script>");
			out.flush();
		}
		return null;
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
			QnaVo qna, HttpServletRequest request, Model model) {
//		private 선택 여부 확인
		String qna_view_private = request.getParameter("private");
		if (qna_view_private.equals(qna_view_private)) {
			qna.setQna_view("비공개");
		} else {
			qna.setQna_view("공개");
		}
//		카테고리 확인
		switch (qna.getQna_category()) {
			case "question": qna.setQna_category("질문"); break;
			case "suggestion": qna.setQna_category("건의"); break;
		}
//		글 입력
		int result = qnaService.insertQuestion(qna);
		if (result>0) {
			return "redirect:list_qna.do";
		} else {
			model.addAttribute("message", "문의글을 작성하지 못했습니다.");
			return "common/errorPage";
		}
	}
	
//	QNA 문의 삭제하기
	@RequestMapping(value = "delete_qna.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteQuestionMethod(
			@RequestParam("qna_idx")String qna_idx,
			@RequestParam("members_lev")String members_lev,
			@RequestParam("members_idx")String members_idx,
			Model model) {
//		qna 글 삭제
		int result =  qnaService.deleteQuestion(qna_idx);
		if (result>0) {
//			로그인 한 유저가 회원이라면 목록으로 돌아간다.
				if (members_lev.equals("1")) {
					return "redirect:list_qna.do";
//			로그인 한 유저가 관리자라면 insert_limitmember.do로 redirect한다.
				} 
				else {
					System.out.println("limit 테이블에 회원 추가");
					return "redirect:list_qna.do";
//					return "redirect:insert_limitmember.do?member_idx=" + members_idx;
				}
		} else {
			model.addAttribute("message", "문의글을 삭제하지 못했습니다.");
			return "common/errorPage";
		}
	}
	 
//	관리자
	
//	체크된 QNA 문의 삭제하기
	@RequestMapping(value = "chk_delete_qna.do", method = RequestMethod.POST)
	public String chkdeleteQuestionMethod(HttpServletRequest request) {
		String [] chkMsg=request.getParameterValues("chkArr");
		int size = chkMsg.length;
		for (int i = 0; i < size; i++) {
			qnaService.chkdeleteQuestion(chkMsg[i]); 
		}
		return "redirect: list_qna.do";
	}
	
//	관리자 QNA 문의글 답변 삭제하기
	@RequestMapping(value = "delete_answer.do", method = RequestMethod.POST)
	public String deleteAnswerMethod(
			@ModelAttribute("qna_idx")String qna_idx, 
			@ModelAttribute("cPage")String cPage, 
			Model model) {
		int result = qnaService.deleteAnswer(qna_idx);
		if (result>0) {
			return "redirect:onelist_qna.do";
		} else {
			model.addAttribute("message", "답변을 삭제하지 못했습니다.");
			return "common/errorPage";
		}
	}
}