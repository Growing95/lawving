package com.ict.lawving.library.controller;

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
import com.ict.lawving.library.model.service.LibraryService;
import com.ict.lawving.library.model.vo.LibrarySearch;
import com.ict.lawving.library.model.vo.LibraryVo;
import com.ict.lawving.notice.model.vo.NoticeVo;

@Controller
public class LibraryController {
	@Autowired
	private LibraryService libraryService;
	@Autowired
	private Paging paging;
	
	private Logger logger= LoggerFactory.getLogger(LibraryController.class);
	
	@RequestMapping("llist.do")
	public ModelAndView memberListMethod(HttpServletRequest request) {
		logger.info("llist.do");
		ModelAndView mv= new ModelAndView("library/libraryListView");
		try {
			// 1. 전체 게시물의 수 
			int count= libraryService.getTotalCount();
			paging.setTotalRecord(count);
			// 2. 전체 페이지의 수
			if (paging.getTotalRecord() <= paging.getNumPerPage()) {
				paging.setTotalPage(1);
			}else {
				paging.setTotalPage(paging.getTotalRecord()/paging.getNumPerPage());
				if (paging.getTotalRecord()%paging.getNumPerPage() != 0) {
					paging.setTotalPage(paging.getTotalPage()+1);
				}
			}
			
			// 3. 현재 페이지
			String cPage= request.getParameter("cPage");
			if (cPage == null) {
				paging.setNowPage(1);
			}else {
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
			List<LibraryVo> librarylist=libraryService.getList(paging.getBegin(),paging.getEnd());
			mv.addObject("librarylist", librarylist);
			mv.addObject("paging", paging);
		} catch (Exception e) {
			System.out.println(e);
		}
		return mv;
	}
	
	@RequestMapping(value="lsearch.do",method=RequestMethod.POST)
	public String searchLibraryListMethod(@RequestParam("category") String category, @RequestParam("order") String order,
			@RequestParam("keyword") String keyword, Model model, HttpServletRequest request) {
		LibrarySearch searchObject = new LibrarySearch(category, order, keyword);
		// 1. 전체 게시물의 수
		int count = libraryService.getTotalCount(searchObject);
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
		ArrayList<LibraryVo> list = null;
		switch (searchObject.getCategory()) {
		case "notice_title":
			switch (searchObject.getOrder()) {
			case "desc":
				list = libraryService.selectSearchTitleDesc(searchObject, paging.getBegin(), paging.getEnd());
				break;

			case "asc":
				list = libraryService.selectSearchTitleAsc(searchObject, paging.getBegin(), paging.getEnd());
				break;
			}
			break;

		case "notice_content":
			switch (searchObject.getOrder()) {
			case "desc":
				list = libraryService.selectSearchContentDesc(searchObject, paging.getBegin(), paging.getEnd());
				break;

			case "asc":
				list = libraryService.selectSearchContentAsc(searchObject, paging.getBegin(), paging.getEnd());
				break;
			}
			break;
		}

		if (list.size() > 0) {
			model.addAttribute("librarylist", list);
			model.addAttribute("paging", paging);
			return "library/libraryListView";
		} else {
			model.addAttribute("msg", keyword + "로 검색된 자료 정보가 없습니다.");
			return "common/errorPage";
		}
	}
	// 상세보기

		@RequestMapping(value = "onelist_library.do", method = RequestMethod.GET)
		public String selectlibraryOnelistMethod(@RequestParam("library_idx") int library_idx, Model model,
				HttpSession session) {
			LibraryVo lvo = libraryService.selectOneList(library_idx);
			session.setAttribute("lvo", lvo);
			return "library/libraryOneList";

		}

		// 공지사항 글쓰기(관리자)
		@RequestMapping("library_write.do")
		public String insertNoticeMethod() {
			return "library/libraryWriteForm";

		}
	
	
	
}
