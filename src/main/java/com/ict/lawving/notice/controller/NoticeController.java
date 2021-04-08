package com.ict.lawving.notice.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.lawving.notice.model.service.NoticeService;
import com.ict.lawving.notice.model.vo.NoticeSearch;
import com.ict.lawving.notice.model.vo.NoticeVo;

@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger("NoticeController.class");

	@Autowired
	private NoticeService noticeService;

	@RequestMapping(value = "nsearch.do", method = RequestMethod.POST)
	public String noticeSearchTitleMethod(@RequestParam("category") String category,
			@RequestParam("order") String order, @RequestParam("keyword") String keyword, Model model) {
		NoticeSearch searchObject = new NoticeSearch(category, order, keyword);
		ArrayList<NoticeVo> list = noticeService.selectSearch(searchObject);

		if (list.size() > 0) {
			model.addAttribute("list", list);
			return "notice/noticeListView";
		} else {
			model.addAttribute("msg", keyword + "로 검색된 공지사항 정보가 없습니다.");
			return "common/errorPage";
		}
	}
}
