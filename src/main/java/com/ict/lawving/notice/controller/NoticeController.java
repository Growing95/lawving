package com.ict.lawving.notice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.lawving.common.Paging;
import com.ict.lawving.notice.model.service.NoticeService;
import com.ict.lawving.notice.model.vo.NoticeSearch;
import com.ict.lawving.notice.model.vo.NoticeVo;


@Controller
public class NoticeController {
	private Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private Paging paging;

	
	// 리스트 목록
	@RequestMapping("nlist.do")
	public ModelAndView selectNoticeListMethod(HttpServletRequest request) {
		logger.info("nlist.do");
		ModelAndView mv = new ModelAndView("notice/noticeListView");
		try {
			// 1. 전체 게시물의 수
			int count = noticeService.getTotalCount();
			paging.setTotalRecord(count);

			// 2. 전체 페이지의 수
			if (paging.getTotalRecord() <= paging.getNumPerPage()) {
				paging.setTotalPage(1);
			} else {
				paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
				if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
					paging.setTotalPage(paging.getTotalPage() + 1);
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
			paging.setBegin((paging.getNowPage() - 1) * paging.getNumPerPage() + 1);
			paging.setEnd((paging.getBegin() - 1) + paging.getNumPerPage());
			// 5. 시작블록, 끝블록
			paging.setBeginBlock(
					(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
			paging.setEndBlock((paging.getBeginBlock() + paging.getPagePerBlock() - 1));
			// 6. 주의사항(endBlock이 totalPage보다 클 수가 있다.)
			if (paging.getEndBlock() > paging.getTotalPage()) {
				paging.setEndBlock(paging.getTotalPage());
			}
			List<NoticeVo> noticelist = noticeService.getList(paging.getBegin(), paging.getEnd());
			logger.info("list:" + noticelist);
			mv.addObject("noticelist", noticelist);
			mv.addObject("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	// 검색하기
	@RequestMapping(value = "nsearch.do", method = RequestMethod.POST)
	public String searchNoticeMethod(@RequestParam("category") String category, @RequestParam("order") String order,
			@RequestParam("keyword") String keyword, Model model, HttpServletRequest request) {
		NoticeSearch searchObject = new NoticeSearch(category, order, keyword);
		// 1. 전체 게시물의 수
		int count = noticeService.getTotalCount(searchObject);
		paging.setTotalRecord(count);

		// 2. 전체 페이지의 수
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
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
		paging.setBegin((paging.getNowPage() - 1) * paging.getNumPerPage() + 1);
		paging.setEnd((paging.getBegin() - 1) + paging.getNumPerPage());
		// 5. 시작블록, 끝블록
		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		paging.setEndBlock((paging.getBeginBlock() + paging.getPagePerBlock() - 1));
		// 6. 주의사항(endBlock이 totalPage보다 클 수가 있다.)
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		ArrayList<NoticeVo> list = null;
		switch (searchObject.getCategory()) {
		case "notice_title":
			switch (searchObject.getOrder()) {
			case "desc":
				list = noticeService.selectSearchTitleDesc(searchObject, paging.getBegin(), paging.getEnd());
				break;

			case "asc":
				list = noticeService.selectSearchTitleAsc(searchObject, paging.getBegin(), paging.getEnd());
				break;
			}
			break;

		case "notice_content":
			switch (searchObject.getOrder()) {
			case "desc":
				list = noticeService.selectSearchContentDesc(searchObject, paging.getBegin(), paging.getEnd());
				break;

			case "asc":
				list = noticeService.selectSearchContentAsc(searchObject, paging.getBegin(), paging.getEnd());
				break;
			}
			break;
		}

		if (list.size() > 0) {
			model.addAttribute("noticelist", list);
			model.addAttribute("paging", paging);
			return "notice/noticeListView";
		} else {
			model.addAttribute("msg", keyword + "로 검색된 공지사항 정보가 없습니다.");
			return "common/errorPage";
		}
	}
	// 상세보기

	@RequestMapping(value = "onelist_notice.do", method = RequestMethod.GET)
	public String selectNoticeOnelistMethod(@RequestParam("notice_idx") int notice_idx, Model model,
			HttpSession session) {
		NoticeVo nvo = noticeService.selectOneList(notice_idx);
		session.setAttribute("nvo", nvo);
		return "notice/noticeOneList";

	}

	@RequestMapping(value = "insert_notice.do", method = RequestMethod.GET)
	public String insertNoticeMethod(NoticeVo notice, Model model) {
		return "notice/noticeWriteForm";

		
	}

	
	
	// 글쓰기
	@RequestMapping(value = "insert_noticeData.do", method = RequestMethod.POST)
	public String insertNoticeDataMethod(NoticeVo notice, Model model) {
		logger.info("insert_notice.do :" + notice);// 넘어오는 파라미터값 확인
		logger.info("아이디값확인" + notice.getNotice_idx());

		// 서비스로 전송하고 결과를 받기
		int result = noticeService.insertNotice(notice);

		// 받은 결과로 성공/실패 페이지 내보내기
		if (result > 0) {
			return "notice/noticeOneList";
		} else {
			model.addAttribute("message", "글쓰기 실패");
			return "common/error";
		}
	}

}
