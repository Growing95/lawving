package com.ict.lawving.bookmark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.lawving.bookmark.model.dao.BookmarkDao;

@RestController
public class BookmarkAjaxController {
	@Autowired
	private BookmarkDao bookmarkDao;
	
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
}
