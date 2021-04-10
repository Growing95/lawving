package com.ict.lawving.bookmark.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.lawving.bookmark.model.service.BookmarkService;
import com.ict.lawving.bookmark.model.vo.BookmarkVo;
import com.ict.lawving.common.Paging;
import com.ict.lawving.library.model.vo.LibraryVo;

@Controller
public class BookmarkController {

	@Autowired
	private BookmarkService bookmarkService;

	@Autowired
	private Paging paging;

	@RequestMapping("insert_bookmark.do")
	public String insertBookmarkMethod(BookmarkVo b, Model model) {
		String bookmark_category = b.getBookmark_category();
		String bookmark_question = b.getBookmark_question();
		String bookmark_answer = b.getBookmark_answer();
		String members_idx = b.getMembers_idx();
		System.out.println("카테고리:" + bookmark_category);
		System.out.println("질문:" + bookmark_question);
		System.out.println("답변:" + bookmark_answer);
		System.out.println("유저번호:" + members_idx);
		String law = bookmark_category;
		model.addAttribute("law", law);
		int result = bookmarkService.insertBookmark(b);

		if (result > 0) {
			return "redirect:list_lawdata.do";
		} else {
			model.addAttribute("msg", "북마크저장에실패하였습니다.다시시도해주세요");
			model.addAttribute("url", "home.do");
			return "common/alert";
		}

		

	}

	@RequestMapping("list_bookmark.do")
	public String selectBookmarkListMethod(@RequestParam("members_idx") String members_idx, Model model,
			HttpServletRequest request) {
		System.out.println("북마크리스트메소드실행");
		System.out.println("회원넘버:" + members_idx);
		List<BookmarkVo> blist = bookmarkService.selectBookmarkList(members_idx);
		if (blist == null) {
			model.addAttribute("msg", "리스트를불러오는중 오류가발생했습니다.");
			model.addAttribute("url", "home.do");
			return "common/alert";
		} else {
			model.addAttribute("blist", blist);
			return "mypage/mypage";
		}

	}

}
