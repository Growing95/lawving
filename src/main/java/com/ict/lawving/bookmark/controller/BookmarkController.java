package com.ict.lawving.bookmark.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.lawving.bookmark.model.service.BookmarkService;
import com.ict.lawving.bookmark.model.vo.BookmarkVo;
import com.ict.lawving.common.Paging;
import com.ict.lawving.library.model.vo.LibraryVo;
import com.ict.lawving.qna.model.vo.QnaVo;

@Controller
public class BookmarkController {

	@Autowired
	private BookmarkService bookmarkService;

	@Autowired
	private Paging paging;

	@RequestMapping("list_bookmark.do")
	public String selectBookmarkListMethod(
			Model model, HttpServletRequest request,@RequestParam("members_idx") String members_idx) {
//		사용할 객체 생성
		ArrayList<BookmarkVo> bookmarklist= new ArrayList<BookmarkVo>();
		Paging paging = new Paging();
//		페이징
		try {
			// 1. 전체 게시물의 수 
			int count= bookmarkService.getTotalCount(members_idx);
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
//			리스트 가져오기
			bookmarklist = bookmarkService.selectBookmarklist(members_idx,paging.getBegin(),paging.getEnd());
			model.addAttribute("blist", bookmarklist);
			model.addAttribute("paging", paging);
			model.addAttribute("button","m3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mypage/mypage";
	}

	/*
	 * @RequestMapping("list_bookmark.do") public String
	 * selectBookmarkListMethod(@RequestParam("members_idx") String members_idx,
	 * Model model, HttpServletRequest request) { System.out.println("북마크리스트메소드실행");
	 * System.out.println("회원넘버:" + members_idx); List<BookmarkVo> blist =
	 * bookmarkService.selectBookmarkList(members_idx); if (blist == null) {
	 * model.addAttribute("msg", "리스트를불러오는중 오류가발생했습니다."); model.addAttribute("url",
	 * "home.do"); return "common/alert"; } else {
	 * model.addAttribute("button","m3"); model.addAttribute("blist", blist); return
	 * "mypage/mypage"; }
	 * 
	 * }
	 */
	@RequestMapping("list_mypage.do")
	public String selectBookmarkListMethod2(
			Model model, HttpServletRequest request,@RequestParam("members_idx") String members_idx) {
//		사용할 객체 생성
		ArrayList<BookmarkVo> bookmarklist= new ArrayList<BookmarkVo>();
		Paging paging = new Paging();
//		페이징
		try {
			// 1. 전체 게시물의 수 
			int count= bookmarkService.getTotalCount(members_idx);
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
//			리스트 가져오기
			bookmarklist = bookmarkService.selectBookmarklist(members_idx,paging.getBegin(),paging.getEnd());
			model.addAttribute("blist", bookmarklist);
			model.addAttribute("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mypage/mypage";
	}

	
	@RequestMapping("onelist_bookmark.do")
	public String onelist_bookmarkMethod(@RequestParam("bookmark_idx")String bookmark_idx,Model model) {
		BookmarkVo bookmark = bookmarkService.onelistbookmark(bookmark_idx);
		if (bookmark!=null) {
			model.addAttribute("bookmark",bookmark);
			return "mypage/bookmark/bookmark_onelist";
		}else {
			model.addAttribute("msg","정보를불러오는도중 오류가발생했습니다.");
			model.addAttribute("url","home.do");
			return "common/alert";
		}
	}
	
	@RequestMapping("delete_bookmark.do")
	public String deleteBookmarkMethod(@RequestParam("bookmark_idx")String bookmark_idx,Model model,
			@ModelAttribute("members_idx")String members_idx) {
		int result = bookmarkService.deleteBookmark(bookmark_idx);
		if (result>0) {
			return "redirect:list_bookmark.do";
		}else {
			model.addAttribute("msg","삭제 실패");
			model.addAttribute("url", "home.do");
			return "common/alert";
		}
		
	}
}
