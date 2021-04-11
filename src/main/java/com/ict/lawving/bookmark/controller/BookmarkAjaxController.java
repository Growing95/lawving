package com.ict.lawving.bookmark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.lawving.bookmark.model.dao.BookmarkDao;
import com.ict.lawving.bookmark.model.service.BookmarkService;
import com.ict.lawving.bookmark.model.vo.BookmarkVo;

@RestController
public class BookmarkAjaxController {
	@Autowired
	private BookmarkDao bookmarkDao;
	@Autowired
	private BookmarkService bookmarkService;

	
	@RequestMapping(value = "chk_del.do",produces = "text/html; charset=utf-8")
	@ResponseBody
	public String chkdelCommand(String[] chk_id) {
		System.out.println("선택삭제메소드실행");
		int result = 0;
		for (String k : chk_id) {
			System.out.println("넘어온북마크아이디"+k);
			result += bookmarkDao.getDelete(k);
		}
		return String.valueOf(result);
	}
	@RequestMapping(value = "insert_bookmark.do",produces = "text/html; charset=utf-8")
	@ResponseBody
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
			return String.valueOf(result);
		} else {
			 model.addAttribute("msg", "북마크저장에실패하였습니다.다시시도해주세요");
			 model.addAttribute("url", "home.do"); 
			 return "common/alert"; }
		}
}
