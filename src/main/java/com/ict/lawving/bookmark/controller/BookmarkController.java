package com.ict.lawving.bookmark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.lawving.bookmark.model.service.BookmarkService;
import com.ict.lawving.bookmark.model.vo.BookmarkVo;

@Controller
public class BookmarkController {

	@Autowired
	private BookmarkService bookmarkService;
	
	@RequestMapping("insert_bookmark.do")
	public String insertBookmarkMethod(BookmarkVo b,Model model) {
		String bookmark_category=b.getBookmark_category();
		String bookmark_question=b.getBookmark_question();
		String bookmark_answer = b.getBookmark_answer();
		String members_idx =b.getMembers_idx();
		
		System.out.println("카테고리:"+bookmark_category);
		System.out.println("질문:"+bookmark_question);
		System.out.println("답변:"+bookmark_answer);
		System.out.println("유저번호:"+members_idx);
		int result = bookmarkService.insertBookmark(b);
		String law = bookmark_category;
		return "redirect:list_lawdata.do?law="+b.getBookmark_category();
	}
	
	
}
